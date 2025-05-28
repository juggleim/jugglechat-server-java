package com.juggle.chat.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.juggle.chat.apimodels.ConfirmFriend;
import com.juggle.chat.apimodels.Friend;
import com.juggle.chat.apimodels.FriendIds;
import com.juggle.chat.apimodels.Result;
import com.juggle.chat.apimodels.UserInfos;
import com.juggle.chat.exceptions.JimErrorCode;
import com.juggle.chat.exceptions.JimException;
import com.juggle.chat.services.FriendService;

@RestController
@RequestMapping("/jim/friends")
public class FriendController {
    @Resource
    private FriendService friendService;

    @GetMapping("/list")
    public Result qryFriendsWithPage(@RequestParam("page") int page, @RequestParam("size")int size, @RequestParam("order_tag")String orderTag)throws JimException{
        UserInfos users = this.friendService.qryFriendsWithPage(page, size, orderTag);
        return Result.success(users);
    }

    @PostMapping("/add")
    public Result addFriend(@RequestBody Friend friend)throws JimException{
        if(friend==null||friend.getFriendId()==null||friend.getFriendId().isEmpty()){
            throw new JimException(JimErrorCode.ErrorCode_APP_REQ_BODY_ILLEGAL);
        }
        FriendIds friendIds = new FriendIds();
        friendIds.setFriendIds(List.of(friend.getFriendId()));
        this.friendService.addFriends(friendIds);
        return new Result(0, "");
    }

    @PostMapping("/apply")
    public Result applyFriend(@RequestBody Friend friend)throws JimException{
        if(friend==null||friend.getFriendId()==null||friend.getFriendId().isEmpty()){
            throw new JimException(JimErrorCode.ErrorCode_APP_REQ_BODY_ILLEGAL);
        }
        this.friendService.applyFriend(friend.getFriendId());
        return new Result(0, "");
    }

    @PostMapping("/confirm")
    public Result confirmFriend(@RequestBody ConfirmFriend confirm)throws JimException{
        if(confirm==null||confirm.getSponsorId()==null||confirm.getSponsorId().isEmpty()){
            throw new JimException(JimErrorCode.ErrorCode_APP_REQ_BODY_ILLEGAL);
        }
        this.friendService.confirmFriend(confirm.getSponsorId(), confirm.isAgree());
        return new Result(0, "");
    }

    @PostMapping("/del")
    public Result delFriend(@RequestBody FriendIds friendIds){
        if(friendIds==null||friendIds.getFriendIds()==null||friendIds.getFriendIds().size()<=0){
            throw new JimException(JimErrorCode.ErrorCode_APP_REQ_BODY_ILLEGAL);
        }
        this.friendService.delFriends(friendIds.getFriendIds());
        return new Result(0, "");
    }

    @GetMapping("/applications")
    public Result qryFriendApplications(@RequestParam long start, @RequestParam int count, @RequestParam int order){
        if(count<=0){
            count = 20;
        }
        if(order>1||order<0){
            order = 0;
        }
        if(order==0&&start<=0){
            start = System.currentTimeMillis();
        }
        return Result.success(this.friendService.qryFriendApplications(start,count,order));
    }

    @GetMapping("/myapplications")
    public Result qryMyFriendApplications(@RequestParam long start, @RequestParam int count, @RequestParam int order){
        if(count<=0){
            count = 20;
        }
        if(order>1||order<0){
            order = 0;
        }
        if(order==0&&start<=0){
            start = System.currentTimeMillis();
        }
        return Result.success(this.friendService.qryMyFriendApplications(start, count, order));
    }

    @GetMapping("/mypendingapplications")
    public Result qryMyPendingFriendApplications(@RequestParam long start, @RequestParam int count, @RequestParam int order){
        if(count<=0){
            count = 20;
        }
        if(order>1||order<0){
            order = 0;
        }
        if(order==0&&start<=0){
            start = System.currentTimeMillis();
        }
        return Result.success(this.friendService.qryMyPendingFriendApplications(start, count, order));
    }
}
