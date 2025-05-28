package com.juggle.chat.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juggle.chat.apimodels.Result;

@RestController
@RequestMapping("/jim/friends")
public class FriendController {
    @GetMapping("/list")
    public Result qryFriendsWithPage(){
        return new Result(0, "");
    }

    @PostMapping("/add")
    public Result addFriend(){
        return new Result(0, "");
    }

    @PostMapping("/apply")
    public Result applyFriend(){
        return new Result(0, "");
    }

    @PostMapping("/confirm")
    public Result confirmFriend(){
        return new Result(0, "");
    }

    @PostMapping("/del")
    public Result delFriend(){
        return new Result(0, "");
    }

    @GetMapping("/applications")
    public Result qryFriendApplications(){
        return new Result(0, "");
    }

    @GetMapping("/myapplications")
    public Result qryMyFriendApplications(){
        return new Result(0, "");
    }

    @GetMapping("/mypendingapplications")
    public Result qryMyPendingFriendApplications(){
        return new Result(0, "");
    }
}
