package com.juggle.chat.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.protobuf.ServiceException;
import com.juggle.chat.apimodels.GroupAdministrators;
import com.juggle.chat.apimodels.GroupInfo;
import com.juggle.chat.apimodels.GroupInviteResp;
import com.juggle.chat.apimodels.GroupManagement;
import com.juggle.chat.apimodels.GroupMemberInfo;
import com.juggle.chat.apimodels.UserInfo;
import com.juggle.chat.apimodels.UserSettings;
import com.juggle.chat.exceptions.JimErrorCode;
import com.juggle.chat.exceptions.JimException;
import com.juggle.chat.interceptors.RequestContext;
import com.juggle.chat.mappers.GroupAdminMapper;
import com.juggle.chat.mappers.GroupExtMapper;
import com.juggle.chat.mappers.GroupMapper;
import com.juggle.chat.mappers.GroupMemberMapper;
import com.juggle.chat.mappers.GrpApplicationMapper;
import com.juggle.chat.mappers.UserMapper;
import com.juggle.chat.models.Group;
import com.juggle.chat.models.GroupAdmin;
import com.juggle.chat.models.GroupExt;
import com.juggle.chat.models.GroupExtKeys;
import com.juggle.chat.models.GroupMember;
import com.juggle.chat.models.GrpApplication;
import com.juggle.chat.models.User;
import com.juggle.chat.models.UserExtKeys;
import com.juggle.chat.utils.CommonUtil;
import com.juggle.chat.utils.N3d;

@Service
public class GroupService {
    @Resource
    private GroupMapper grpMapper;
    @Resource
    private GroupMemberMapper grpMemberMapper;
    @Resource
    private GroupExtMapper grpExtMapper;
    @Resource
    private GroupAdminMapper grpAdminMapper;
    @Resource 
    private UserService userService;
    @Resource 
    private GrpApplicationMapper grpApplicationMapper;
    @Resource
    private UserMapper userMapper;

    public void createGroup(GroupInfo grpInfo)throws JimException{
        String appkey = RequestContext.getAppkeyFromCtx();
        String userId = RequestContext.getCurrentUserIdFromCtx();
        String grpId = grpInfo.getGroupId();
        List<String> memberIds = new ArrayList<>();
        memberIds.add(userId);
        memberIds.addAll(grpInfo.getMemberIds());
        //create group
        Group group =  new Group();
        group.setAppkey(appkey);
        group.setGroupId(grpInfo.getGroupId());
        group.setGroupName(grpInfo.getGroupName());
        group.setGroupPortrait(grpInfo.getGroupPortrait());
        group.setCreatorId(userId);
        this.grpMapper.create(group);

        //add grp members
        List<GroupMember> grpMembers = new ArrayList<>();
        for (String memberId : memberIds) {
            GroupMember member = new GroupMember();
            member.setGroupId(grpId);
            member.setMemberId(memberId);
            member.setAppkey(appkey);
            grpMembers.add(member);
        }
        this.grpMemberMapper.batchCreate(grpMembers);
        //TODO sync to imserver
        //TODO send notify msg
    }

    public void updateGroup(GroupInfo grp)throws JimException{
        String appkey = RequestContext.getAppkeyFromCtx();
        String requestId = RequestContext.getCurrentUserIdFromCtx();
        int ok = this.grpMapper.updateGrpName(appkey, grp.getGroupId(), grp.getGroupName(), grp.getGroupPortrait());
        if(ok>0){
            //TODO sync to imserver
            //TODO send notify msg
        }
    }

    public void dissolveGroup(String groupId)throws JimException{
        String appkey = RequestContext.getAppkeyFromCtx();
        int ok = this.grpMapper.delete(appkey, groupId);
        if(ok>0){
            ok = this.grpMemberMapper.deleteByGroupId(appkey, groupId);
            if(ok>0){
                //TODO send notify msg
                //TODO sync to imserver
            }
        }
    }

    public GroupInfo qryGroupInfo(String groupId)throws JimException{
        GroupInfo grpInfo = new GroupInfo();
        grpInfo.setGroupId(groupId);
        String appkey = RequestContext.getAppkeyFromCtx();
        String requestId = RequestContext.getCurrentUserIdFromCtx();
        Group group = this.grpMapper.findById(appkey, groupId);
        if(group!=null){
            grpInfo.setGroupName(group.getGroupName());
            grpInfo.setGroupPortrait(group.getGroupPortrait());
            grpInfo.setMemberCount(this.grpMemberMapper.countByGroup(appkey, groupId));
        }else{
            throw new JimException(JimErrorCode.ErrorCode_APP_GROUP_DEFAULT);
        }
        boolean isMmeber = false;
        //check is member
        GroupMember member = this.grpMemberMapper.find(appkey, groupId, requestId);
        if(member!=null){
            isMmeber = true;
            grpInfo.setGrpDisplayName(member.getGrpDisplayName());
        }
        if(!isMmeber){
            grpInfo.setMyRole(GroupMember.GrpMemberRole_GrpNotMember);
            return grpInfo;
        }
        //grp setting
        List<GroupExt> exts = this.grpExtMapper.qryExtFields(appkey, groupId);
        if(exts!=null&&exts.size()>0){
            GroupManagement grpManage = new GroupManagement();
            for (GroupExt ext : exts) {
                if(ext.getItemKey().equals(GroupExtKeys.GrpExtKey_GrpVerifyType)){
                    grpManage.setGroupVerifyType(CommonUtil.string2Int(ext.getItemValue()));
                }else if(ext.getItemKey().equals(GroupExtKeys.GrpExtKey_HideGrpMsg)){
                    int hideGrpMsg = CommonUtil.string2Int(ext.getItemValue());
                    grpManage.setGroupHisMsgVisible(hideGrpMsg>0?0:1);
                }
            }
            grpInfo.setGrpManagement(grpManage);
        }
        //grp administrator
        Map<String,Boolean> administrators = new HashMap<>();
        List<GroupAdmin> admins = this.grpAdminMapper.qryAdmins(appkey, groupId);
        if(admins!=null&&admins.size()>0){
            for (GroupAdmin admin : admins) {
                administrators.put(admin.getAdminId(), true);
            }
        }
        // my role
        if(requestId.equals(group.getCreatorId())){
            grpInfo.setMyRole(GroupMember.GrpMemberRole_GrpCreator);
        }else if(administrators.get(requestId)){
            grpInfo.setMyRole(GroupMember.GrpMemberRole_GrpAdmin);
        }
        // owner
        if(group.getCreatorId()!=null&&!group.getCreatorId().isEmpty()){
            UserInfo owner = this.userService.getUserInfo(group.getCreatorId());
            if(owner!=null){
                GroupMemberInfo memberInfo = new GroupMemberInfo();
                memberInfo.setUserId(owner.getUserId());
                memberInfo.setNickname(owner.getNickname());
                memberInfo.setAvatar(owner.getAvatar());
                memberInfo.setMemberType(owner.getUserType());
                grpInfo.setOwner(memberInfo);
            }
        }
        //top members
        List<GroupMember> members = this.grpMemberMapper.queryMembers(appkey, groupId, 0, 20);
        if(members!=null&&members.size()>0){
            List<GroupMemberInfo> memberInfos = new ArrayList<>();
            for (GroupMember grpMember : members) {
                int role = GroupMember.GrpMemberRole_GrpMember;
                if(grpMember.getMemberId().equals(group.getCreatorId())){
                    role = GroupMember.GrpMemberRole_GrpCreator;
                }else if(administrators.get(grpMember.getMemberId())){
                    role = GroupMember.GrpMemberRole_GrpAdmin;
                }
                GroupMemberInfo memberInfo = new GroupMemberInfo();
                memberInfo.setUserId(grpMember.getMemberId());
                memberInfo.setRole(role);
                memberInfo.setNickname(grpMember.getNickname());
                memberInfo.setAvatar(grpMember.getUserPortrait());
                memberInfo.setMemberType(grpMember.getMemberType());
                try {
                    grpInfo.setMemberOffset(N3d.encode(grpMember.getId()));
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
                memberInfos.add(memberInfo);
            }
            grpInfo.setMembers(memberInfos);
        }
        return grpInfo;
    }

    public void grpJoinApply(String groupId)throws JimException{
        String appkey = RequestContext.getAppkeyFromCtx();
        String userId = RequestContext.getCurrentUserIdFromCtx();
        //check grp member exists
        GroupMember member = this.grpMemberMapper.find(appkey, groupId, userId);
        if(member!=null){
            throw new JimException(JimErrorCode.ErrorCode_APP_GROUP_MEMBEREXISTED);
        }
        //join group
        member = new GroupMember();
        member.setGroupId(groupId);
        member.setMemberId(userId);
        member.setAppkey(appkey);
        int succ = this.grpMemberMapper.create(member);
        if(succ>0){
            //TODO sync to imserver
            //TODO send notify msg
        }
    }

    public GroupInviteResp grpInviteMembers(String groupId, List<String> memberIds)throws JimException{
        String appkey = RequestContext.getAppkeyFromCtx();
        String requesterId = RequestContext.getCurrentUserIdFromCtx();
        //TODO check operator
        GroupInviteResp resp = new GroupInviteResp();
        resp.setResults(new HashMap<>());
        //TODO check grp member exist

        //check user's setting
        List<String> directAddMemberIds = new ArrayList<>();
        for (String memberId : memberIds) {
            int reason = GroupInviteResp.GrpInviteReason_InviteSucc;
            UserSettings uSettings = this.userService.getUserSettings(memberId);
            if(uSettings!=null){
                if(uSettings.getGrpVerifyType()==UserExtKeys.GrpVerifyType_DeclineGroup){
                    reason = GroupInviteResp.GrpInviteReason_InviteDecline;
                }else if(uSettings.getGrpVerifyType() == UserExtKeys.GrpVerifyType_NeedGrpVerify){
                    GrpApplication grpApp = new GrpApplication();
                    grpApp.setGroupId(groupId);
                    grpApp.setApplyTime(System.currentTimeMillis());
                    grpApp.setRecipientId(memberId);
                    grpApp.setInviterId(requesterId);
                    grpApp.setApplyType(GrpApplication.GrpApplicationType_Invite);
                    grpApp.setStatus(GrpApplication.GrpApplicationStatus_Invite);
                    grpApp.setAppkey(appkey);
                    this.grpApplicationMapper.inviteUpsert(grpApp);
                    reason = GroupInviteResp.GrpInviteReason_InviteSendOut;
                }else if(uSettings.getGrpVerifyType() == UserExtKeys.GrpVerifyType_NoNeedGrpVerify){
                    directAddMemberIds.add(memberId);
                    reason = GroupInviteResp.GrpInviteReason_InviteSucc;
                }
            }else{
                directAddMemberIds.add(memberId);
                reason = GroupInviteResp.GrpInviteReason_InviteSucc;
            }
            resp.getResults().put(memberId, reason);
        }
        if(directAddMemberIds.size()>0){
            List<GroupMember> grpMembers = new ArrayList<>();
            for (String memberId : memberIds) {
                GroupMember grpMember = new GroupMember();
                grpMember.setGroupId(groupId);
                grpMember.setMemberId(memberId);
                grpMember.setAppkey(appkey);
                grpMembers.add(grpMember);
            }
            if(grpMembers.size()>0){
                int succ = this.grpMemberMapper.batchCreate(grpMembers);
                if(succ>0){
                    //TODO sync to imerver
                    //TODO send notify msg
                }
            }
        }

        return resp;
    }

    public void ChgGroupOwner(String groupId, String newOwnerId)throws JimException{
        String appkey = RequestContext.getAppkeyFromCtx();
        //TODO check right
        this.grpMapper.updateCreatorId(appkey, groupId, newOwnerId);
        //TODO send notify
    }

    public void setGroupMute(String groupId, int isMute)throws JimException{
        String appkey = RequestContext.getAppkeyFromCtx();
        int succ = this.grpMapper.updateGroupMuteStatus(appkey, groupId, isMute);
        if(succ>0){
            //TODO sync to imserver
        }
    }

    public void setGroupVerifyType(String groupId, int verifyType)throws JimException{
        String appkey = RequestContext.getAppkeyFromCtx();
        GroupExt grpExt = new GroupExt();
        grpExt.setGroupId(groupId);
        grpExt.setItemKey(GroupExtKeys.GrpExtKey_GrpVerifyType);
        grpExt.setItemValue(CommonUtil.int2String(verifyType));
        grpExt.setItemType(UserExtKeys.AttItemType_Setting);
        grpExt.setAppkey(appkey);
        this.grpExtMapper.upsert(grpExt);
    }

    public void setGroupHisMsgVisible(String groupId, int visible)throws JimException{
        String appkey = RequestContext.getAppkeyFromCtx();
        //TODO check right
        int hidGrpMsg = 1;
        if(visible>0){
            hidGrpMsg = 0;
        }else{
            hidGrpMsg = 1;
        }
        GroupExt grpExt = new GroupExt();
        grpExt.setGroupId(groupId);
        grpExt.setItemKey(GroupExtKeys.GrpExtKey_HideGrpMsg);
        grpExt.setItemValue(CommonUtil.int2String(hidGrpMsg));
        grpExt.setItemType(UserExtKeys.AttItemType_Setting);
        grpExt.setAppkey(appkey);
        int succ = this.grpExtMapper.upsert(grpExt);
        if(succ>0){
            //TODO sync to imserver
        }
    }

    public void addGroupAdmins(String groupId, List<String> adminIds)throws JimException{
        String appkey = RequestContext.getAppkeyFromCtx();
        for (String adminId : adminIds) {
            GroupAdmin admin = new GroupAdmin();
            admin.setGroupId(groupId);
            admin.setAdminId(adminId);
            admin.setAppkey(appkey);
            this.grpAdminMapper.upsert(admin);
        }
    }

    public void delGroupAdmins(String groupId, List<String> adminIds)throws JimException{
        String appkey = RequestContext.getAppkeyFromCtx();
        this.grpAdminMapper.batchDel(appkey, groupId, adminIds);
    }

    public GroupAdministrators qryGroupAdmins(String groupId)throws JimException{
        GroupAdministrators ret = new GroupAdministrators();
        ret.setGroupId(groupId);
        ret.setItems(new ArrayList<>());
        String appkey = RequestContext.getAppkeyFromCtx();
        List<GroupAdmin> admins = this.grpAdminMapper.qryAdmins(appkey, groupId);
        if(admins!=null&&admins.size()>0){
            List<String> adminIds = new ArrayList<>();
            Map<String,GroupMemberInfo> memberInfos = new HashMap<>();
            for (GroupAdmin admin : admins) {
                GroupMemberInfo memberInfo = new GroupMemberInfo();
                memberInfo.setUserId(admin.getAdminId());
                memberInfo.setRole(GroupMember.GrpMemberRole_GrpAdmin);
                memberInfos.put(admin.getAdminId(), memberInfo);
                adminIds.add(admin.getAdminId());
                ret.getItems().add(memberInfo);
            }
            List<User> users = this.userMapper.findByUserIds(appkey, adminIds);
            for (User user : users) {
                GroupMemberInfo mInfo = memberInfos.get(user.getUserId());
                if(mInfo!=null){
                    mInfo.setNickname(user.getNickname());
                    mInfo.setAvatar(user.getUserPortrait());
                    mInfo.setMemberType(user.getUserType());
                }
            }
        }
        return ret;
    }
}
