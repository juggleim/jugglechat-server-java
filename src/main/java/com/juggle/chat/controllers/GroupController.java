package com.juggle.chat.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juggle.chat.apimodels.Result;

@RestController
@RequestMapping("/jim/groups")
public class GroupController {
    @PostMapping(value={"/add","/create"})
    public Result createGroup(){
        return new Result(0, "");
    }

    @PostMapping("/update")
    public Result updateGroup(){
        return new Result(0, "");
    }

    @PostMapping("/dissolve")
    public Result dissolveGroup(){
        return new Result(0, "");
    }

    @GetMapping("/info")
    public Result qryGroupInfo(){
        return new Result(0, "");
    }

    @GetMapping("/qrcode")
    public Result qryGrpQrCode(){
        return new Result(0, "");
    }

    @PostMapping("/apply")
    public Result groupApply(){
        return new Result(0, "");
    }

    @PostMapping("/invite")
    public Result groupInvite(){
        return new Result(0, "");
    }

    @PostMapping("/quit")
    public Result quitGroup(){
        return new Result(0, "");
    }

    @PostMapping("/members/add")
    public Result addGrpMembers(){
        return new Result(0, "");
    }

    @PostMapping("/members/del")
    public Result delGrpMembers(){
        return new Result(0, "");
    }

    @GetMapping("/members/list")
    public Result qryGrpMembers(){
        return new Result(0, "");
    }

    @PostMapping("/members/check")
    public Result checkGroupMembers(){
        return new Result(0, "");
    }

    @PostMapping("/setgrpannouncement")
    public Result setGrpAnnouncement(){
        return new Result(0, "");
    }

    @GetMapping("/getgrpannouncement")
    public Result getGrpAnnouncement(){
        return new Result(0, "");
    }

    @PostMapping("/setdisplayname")
    public Result setGrpDisplayName(){
        return new Result(0, "");
    }

    @GetMapping("/mygroups")
    public Result qryMyGroups(){
        return new Result(0, "");
    }

    @GetMapping("/myapplications")
    public Result qryMyGrpApplications(){
        return new Result(0, "");
    }

    @GetMapping("/mypendinginvitations")
    public Result qryMyPendingGrpInvitations(){
        return new Result(0, "");
    }

    @GetMapping("/grpinvitations")
    public Result qryGrpInvitations(){
        return new Result(0, "");
    }
    
    @GetMapping("/grppendingapplications")
    public Result qryGrpPendingApplications(){
        return new Result(0, "");
    }
}
