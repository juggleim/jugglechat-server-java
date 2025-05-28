package com.juggle.chat.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.juggle.chat.apimodels.FriendApplicationItem;
import com.juggle.chat.apimodels.FriendApplications;
import com.juggle.chat.apimodels.FriendIds;
import com.juggle.chat.apimodels.UserInfo;
import com.juggle.chat.apimodels.UserInfos;
import com.juggle.chat.apimodels.UserSettings;
import com.juggle.chat.exceptions.JimErrorCode;
import com.juggle.chat.exceptions.JimException;
import com.juggle.chat.interceptors.RequestContext;
import com.juggle.chat.mappers.FriendApplicationMapper;
import com.juggle.chat.mappers.FriendRelMapper;
import com.juggle.chat.mappers.UserMapper;
import com.juggle.chat.models.FriendApplication;
import com.juggle.chat.models.FriendRel;
import com.juggle.chat.models.User;
import com.juggle.chat.models.UserExtKeys;

@Service
public class FriendService {
    @Resource
    private FriendRelMapper friendMapper;
    @Resource
    private UserMapper userMapper;
    @Resource 
    private FriendApplicationMapper friendApplicationMapper;
    @Resource 
    private UserService userService;

    public boolean checkFriend(String userId, String friendId){
        Map<String,Boolean> map = this.checkFriends(userId, List.of(friendId));
        return map.get(friendId);
    }

    public Map<String,Boolean> checkFriends(String userId, List<String> friendIds){
        Map<String,Boolean> ret = new HashMap<String,Boolean>();
        if(friendIds!=null&&friendIds.size()>0){
            for (String friendId : friendIds) {
                ret.put(friendId, false);
            }
            List<FriendRel> rels = friendMapper.queryFriendRelsByFriendIds(RequestContext.getAppkeyFromCtx(),userId,friendIds);
            if(rels!=null&&rels.size()>0){
                for (FriendRel rel : rels) {
                    ret.put(rel.getFriendId(), true);
                }
            }
        }
        return ret;
    }

    public UserInfos qryFriendsWithPage(int page, int size, String orderTag)throws JimException{
        String appkey = RequestContext.getAppkeyFromCtx();
        String currentUserId = RequestContext.getCurrentUserIdFromCtx();
        UserInfos ret = new UserInfos();
        List<FriendRel> items = this.friendMapper.queryFriendRelsWithPage(appkey, currentUserId, orderTag, page, size);
        if(items!=null&&items.size()>0){
            HashMap<String,UserInfo> userMap = new HashMap<>();
            List<String> friendIds = new ArrayList<>();
            for (FriendRel item : items) {
                UserInfo userInfo = new UserInfo();
                userInfo.setUserId(item.getFriendId());
                userMap.put(item.getFriendId(), userInfo);
                ret.addUserInf(userInfo);
                friendIds.add(item.getFriendId());
            }
            List<User> users = this.userMapper.findByUserIds(appkey, friendIds);
            if(users!=null&&users.size()>0){
                for (User user : users) {
                    UserInfo u = userMap.get(user.getUserId());
                    if(u!=null){
                        u.setNickname(user.getNickname());
                        u.setAvatar(user.getUserPortrait());
                        u.setUserType(user.getUserType());
                    }
                }
            }
        }
        return ret;
    }

    public void addFriends(FriendIds friendIds){
        String appkey = RequestContext.getAppkeyFromCtx();
        String currentUserId = RequestContext.getCurrentUserIdFromCtx();
        for (String friendId : friendIds.getFriendIds()) {
            List<FriendRel> rels = new ArrayList<>();
            FriendRel rel = new FriendRel();
            rel.setAppkey(appkey);
            rel.setUserId(currentUserId);
            rel.setFriendId(friendId);
            rels.add(rel);

            FriendRel rel2 = new FriendRel();
            rel.setAppkey(appkey);
            rel.setUserId(friendId);
            rel.setFriendId(currentUserId);
            rels.add(rel2);
            int ok = this.friendMapper.batchUpsert(rels);
            if(ok>0){
                //TODO sync to imserver
                //TODO send notify msg
            }
        }
    }

    public void applyFriend(String friendId){
        String appkey = RequestContext.getAppkeyFromCtx();
        String currentUserId = RequestContext.getCurrentUserIdFromCtx();
        if(this.checkFriend(currentUserId, friendId)){
            FriendRel rel = new FriendRel();
            rel.setAppkey(appkey);
            rel.setUserId(currentUserId);
            rel.setFriendId(friendId);
            this.friendMapper.upsert(rel);
            //TODO sync to im
            
            FriendApplication app = new FriendApplication();
            app.setRecipientId(friendId);
            app.setSponsorId(currentUserId);
            app.setApplyTime(System.currentTimeMillis());
            app.setStatus(FriendApplication.FriendApplicationStatus_Agree);
            app.setAppkey(appkey);
            this.friendApplicationMapper.upsert(app);
            return;
        }
        UserSettings settings = this.userService.getUserSettings(friendId);
        if(settings.getFriendVerifyType() == UserExtKeys.FriendVerifyType_Decline){
            throw new JimException(JimErrorCode.ErrorCode_APP_FRIEND_APPLY_DECLINE);
        }else if(settings.getFriendVerifyType() == UserExtKeys.FriendVerifyType_Need){
            FriendApplication app = new FriendApplication();
            app.setRecipientId(friendId);
            app.setSponsorId(currentUserId);
            app.setApplyTime(System.currentTimeMillis());
            app.setStatus(FriendApplication.FriendApplicationStatus_Apply);
            app.setAppkey(appkey);
            int ok = this.friendApplicationMapper.upsert(app);
            if(ok>0){
                //TODO send notify msg
            }
        }else if(settings.getFriendVerifyType() == UserExtKeys.FriendVerifyType_NoNeed){
            List<FriendRel> rels = new ArrayList<>();
            FriendRel rel = new FriendRel();
            rel.setAppkey(appkey);
            rel.setUserId(currentUserId);
            rel.setFriendId(friendId);
            rels.add(rel);

            FriendRel rel2 = new FriendRel();
            rel.setAppkey(appkey);
            rel.setUserId(friendId);
            rel.setFriendId(currentUserId);
            rels.add(rel2);
            int ok = this.friendMapper.batchUpsert(rels);
            if(ok>0){
                //TODO sync to imserver
                //TODO send notify msg
            }
        }
    }

    public void confirmFriend(String sponsorId, boolean isAgree)throws JimException{
        String appkey = RequestContext.getAppkeyFromCtx();
        String currentUserId = RequestContext.getCurrentUserIdFromCtx();
        if(isAgree){
            this.friendApplicationMapper.updateStatus(appkey, sponsorId, currentUserId, FriendApplication.FriendApplicationStatus_Agree);
            List<FriendRel> rels = new ArrayList<>();
            FriendRel rel = new FriendRel();
            rel.setAppkey(appkey);
            rel.setUserId(currentUserId);
            rel.setFriendId(sponsorId);
            rels.add(rel);

            FriendRel rel2 = new FriendRel();
            rel.setAppkey(appkey);
            rel.setUserId(sponsorId);
            rel.setFriendId(currentUserId);
            rels.add(rel2);
            int ok = this.friendMapper.batchUpsert(rels);
            if(ok>0){
                //TODO sync to imserver
                //TODO send notify msg
            }
        }else{
            this.friendApplicationMapper.updateStatus(appkey, sponsorId, currentUserId, FriendApplication.FriendApplicationStatus_Decline);
        }
    }

    public void delFriends(List<String> friendIds)throws JimException{
        String appkey = RequestContext.getAppkeyFromCtx();
        String currentUserId = RequestContext.getCurrentUserIdFromCtx();
        int ok = this.friendMapper.batchDelete(appkey, currentUserId, friendIds);
        if(ok>0){
            //TODO sync to imserver
        }
    }

    public FriendApplications qryFriendApplications(long start, int count, int order)throws JimException{
        String appkey = RequestContext.getAppkeyFromCtx();
        String currentUserId = RequestContext.getCurrentUserIdFromCtx();
        FriendApplications ret = new FriendApplications();
        List<FriendApplication> apps = this.friendApplicationMapper.queryApplications(appkey, currentUserId, start, count, order>0);
        if(apps!=null&&apps.size()>0){
            for (FriendApplication app : apps) {
                FriendApplicationItem item = new FriendApplicationItem();
                item.setStatus(app.getStatus());
                item.setApplyTime(app.getApplyTime());
                if(currentUserId.equals(app.getSponsorId())){
                    item.setSponsor(true);
                    item.setTargetUser(this.userService.getUserInfo(app.getRecipientId()));
                }else{
                    item.setTargetUser(this.userService.getUserInfo(app.getSponsorId()));
                }
                ret.addFriendApplication(item);
            }
        }
        return ret;
    }

    public FriendApplications qryMyFriendApplications(long start,int count,int order)throws JimException{
        String appkey = RequestContext.getAppkeyFromCtx();
        String currentUserId = RequestContext.getCurrentUserIdFromCtx();
        FriendApplications ret = new FriendApplications();
        List<FriendApplication> apps = this.friendApplicationMapper.queryMyApplications(appkey, currentUserId, start, count, order>0);
        if(apps!=null&&apps.size()>0){
            for (FriendApplication app : apps) {
                FriendApplicationItem item = new FriendApplicationItem();
                item.setRecipient(this.userService.getUserInfo(app.getRecipientId()));
                item.setStatus(app.getStatus());
                item.setApplyTime(app.getApplyTime());
                item.setSponsor(true);
                ret.addFriendApplication(item);
            }
        }
        return ret;
    }

    public FriendApplications qryMyPendingFriendApplications(long start,int count,int order)throws JimException{
        String appkey = RequestContext.getAppkeyFromCtx();
        String currentUserId = RequestContext.getCurrentUserIdFromCtx();
        FriendApplications ret = new FriendApplications();
        List<FriendApplication> apps = this.friendApplicationMapper.queryPendingApplications(appkey, currentUserId, start, count, order>0);
        if(apps!=null&&apps.size()>0){
            for (FriendApplication app : apps) {
                FriendApplicationItem item = new FriendApplicationItem();
                item.setSponsorUser(this.userService.getUserInfo(app.getSponsorId()));
                item.setStatus(app.getStatus());
                item.setApplyTime(app.getApplyTime());
                ret.addFriendApplication(item);
            }
        }
        return ret;
    }
}
