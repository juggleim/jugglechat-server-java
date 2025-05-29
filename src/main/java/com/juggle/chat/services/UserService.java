package com.juggle.chat.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.juggle.chat.apimodels.UserInfo;
import com.juggle.chat.apimodels.UserInfos;
import com.juggle.chat.apimodels.UserSettings;
import com.juggle.chat.exceptions.JimException;
import com.juggle.chat.interceptors.RequestContext;
import com.juggle.chat.mappers.UserExtMapper;
import com.juggle.chat.mappers.UserMapper;
import com.juggle.chat.models.User;
import com.juggle.chat.models.UserExt;
import com.juggle.chat.models.UserExtKeys;
import com.juggle.chat.utils.CommonUtil;
import com.juggle.im.JuggleIm;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;
    @Resource 
    private UserExtMapper userExtMapper;
    // @Resource
    // private FriendService friendService;
    @Resource
    private FriendCheckService friendCheckService;

    public UserInfo qryUserInfo(String userId){
        UserInfo user = this.getUserInfo(userId);
        if(userId.equals(RequestContext.getCurrentUserIdFromCtx())){
            //add usersettings
            user.setSettings(this.getUserSettings(userId));
        }else{
            //check friend
            user.setFriend(friendCheckService.checkFriend(RequestContext.getCurrentUserIdFromCtx(), userId));
        }
        return user;
    }

    public UserSettings getUserSettings(String userId){
        UserSettings settings = new UserSettings();
        List<UserExt> exts = userExtMapper.qryExtFields(RequestContext.getAppkeyFromCtx(), userId);
        if(exts!=null&&exts.size()>0){
            for (UserExt ext : exts) {
                if(ext.getItemKey().equals(UserExtKeys.UserExtKey_Language)){
                    settings.setLanguage(ext.getItemValue());
                }else if(ext.getItemKey().equals(UserExtKeys.UserExtKey_Undisturb)){
                    settings.setUndisturb(ext.getItemValue());
                }else if(ext.getItemKey().equals(UserExtKeys.UserExtKey_FriendVerifyType)){
                    settings.setFriendVerifyType(CommonUtil.string2Int(ext.getItemValue()));
                }else if(ext.getItemKey().equals(UserExtKeys.UserExtKey_GrpVerifyType)){
                    settings.setGrpVerifyType(CommonUtil.string2Int(ext.getItemValue()));
                }
            }
        }
        return settings;
    }

    public void updateUser(UserInfo user)throws JimException{
        String appkey = RequestContext.getAppkeyFromCtx();
        int ok = userMapper.update(appkey,user.getUserId(),user.getNickname(),user.getAvatar());
        if(ok>0){
            JuggleIm sdk = ImSdkService.getJimSdk(appkey);
            try {
                sdk.user.register(new com.juggle.im.models.user.UserInfo(user.getUserId(), user.getNickname(), user.getAvatar()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public UserInfo getUserInfo(String userId){
        UserInfo user = new UserInfo();
        user.setUserId(userId);
        User u = userMapper.findByUserId(RequestContext.getAppkeyFromCtx(), userId);
        if(u!=null){
            user.setNickname(u.getNickname());
            user.setAvatar(u.getUserPortrait());
            user.setUserType(u.getUserType());
        }
        return user;
    }

    public void updateUserSettings(UserSettings settings){
        String appkey = RequestContext.getAppkeyFromCtx();
        String currentUserId = RequestContext.getCurrentUserIdFromCtx();
        List<UserExt> exts = new ArrayList<>();
        if(settings.getLanguage()!=null&&!settings.getLanguage().isEmpty()){
            exts.add(new UserExt(appkey, currentUserId, UserExtKeys.UserExtKey_Language, settings.getLanguage(), UserExtKeys.AttItemType_Setting));
        }
        if(settings.getUndisturb()!=null&&!settings.getUndisturb().isEmpty()){
            exts.add(new UserExt(appkey, currentUserId, UserExtKeys.UserExtKey_Undisturb, settings.getUndisturb(), UserExtKeys.AttItemType_Setting));
        }
        exts.add(new UserExt(appkey, currentUserId, UserExtKeys.UserExtKey_FriendVerifyType, CommonUtil.int2String(settings.getFriendVerifyType()), UserExtKeys.AttItemType_Setting));
        exts.add(new UserExt(appkey, currentUserId, UserExtKeys.UserExtKey_GrpVerifyType, CommonUtil.int2String(settings.getGrpVerifyType()), UserExtKeys.AttItemType_Setting));
        if(exts.size()>0){
            this.userExtMapper.batchUpsert(exts);
            //TODO sync to im
        }
    }

    public UserInfos searchByPhone(String phone)throws JimException{
        UserInfos users = new UserInfos();
        User u = this.userMapper.findByPhone(RequestContext.getAppkeyFromCtx(), phone);
        if(u!=null){
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(u.getUserId());
            userInfo.setNickname(u.getNickname());
            userInfo.setAvatar(u.getUserPortrait());
            userInfo.setUserType(u.getUserType());
            userInfo.setFriend(this.friendCheckService.checkFriend(RequestContext.getCurrentUserIdFromCtx(), u.getUserId()));
            users.addUserInf(userInfo);
        }
        return users;
    }
}
