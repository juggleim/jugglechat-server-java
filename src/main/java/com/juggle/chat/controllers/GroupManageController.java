package com.juggle.chat.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.juggle.chat.apimodels.GroupAdministratorsReq;
import com.juggle.chat.apimodels.GroupManagement;
import com.juggle.chat.apimodels.GroupOwnerChgReq;
import com.juggle.chat.apimodels.Result;
import com.juggle.chat.apimodels.SetGroupHisMsgVisibleReq;
import com.juggle.chat.apimodels.SetGroupMemberMuteReq;
import com.juggle.chat.apimodels.SetGroupMuteReq;
import com.juggle.chat.apimodels.SetGroupVerifyTypeReq;

@RestController
@RequestMapping("/jim/groups/management")
public class GroupManageController {
    @PostMapping("/chgowner")
    public Result chgGroupOwner(@RequestBody GroupOwnerChgReq req){
        return new Result(0, "");
    }

    @PostMapping("/setmute")
    public Result setGroupMute(@RequestBody SetGroupMuteReq req){
        return new Result(0, "");
    }

    @PostMapping("/setgrpmembersmute")
    public Result setGroupMembersMute(@RequestBody SetGroupMemberMuteReq req){
        return new Result(0, "");
    }

    @PostMapping("/setgrpverifytype")
    public Result setGrpVerifyType(@RequestBody SetGroupVerifyTypeReq req){
        return new Result(0, "");
    }

    @PostMapping("/sethismsgvisible")
    public Result setGrpHisMsgVisible(@RequestBody SetGroupHisMsgVisibleReq req){
        return new Result(0, "");
    }

    @PostMapping("/administrators/add")
    public Result addGrpAdministrator(@RequestBody GroupAdministratorsReq req){
        return new Result(0, "");
    }

    @PostMapping("/administrators/del")
    public Result delGrpAdministrator(@RequestBody GroupAdministratorsReq req){
        return new Result(0, "");
    }

    @GetMapping("/administrators/list")
    public Result qryGrpAdministrator(@RequestParam("group_id") String groupId){
        return new Result(0, "");
    }

    @PostMapping("/set")
    public Result setGrpManagementConfs(@RequestBody GroupManagement management){
        return new Result(0, "");
    }
}
