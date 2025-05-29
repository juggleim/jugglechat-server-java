package com.juggle.chat.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.juggle.chat.interceptors.RequestContext;
import com.juggle.chat.mappers.FriendRelMapper;
import com.juggle.chat.models.FriendRel;

@Service
public class FriendCheckService {
    @Resource
    private FriendRelMapper friendMapper;

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
}
