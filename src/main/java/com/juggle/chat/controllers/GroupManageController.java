package com.juggle.chat.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juggle.chat.apimodels.Result;

@RestController
@RequestMapping("/jim/groups/management")
public class GroupManageController {
    @PostMapping("/chgowner")
    public Result chgGroupOwner(){
        return new Result(0, "");
    }

    @PostMapping("/setmute")
    public Result setGroupMute(){
        return new Result(0, "");
    }

    @PostMapping("/setgrpverifytype")
    public Result setGrpVerifyType(){
        return new Result(0, "");
    }

    @PostMapping("/sethismsgvisible")
    public Result setGrpHisMsgVisible(){
        return new Result(0, "");
    }

    @PostMapping("/adminstrators/add")
    public Result addGrpAdministrator(){
        return new Result(0, "");
    }

    @PostMapping("/adminstrators/del")
    public Result delGrpAdministrator(){
        return new Result(0, "");
    }

    @GetMapping("/adminstrators/list")
    public Result qryGrpAdministrator(){
        return new Result(0, "");
    }
}
