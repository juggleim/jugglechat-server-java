package com.juggle.chat.controllers;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.juggle.chat.apimodels.GroupAdminIds;
import com.juggle.chat.apimodels.GroupOwnerChg;
import com.juggle.chat.apimodels.Result;
import com.juggle.chat.apimodels.SetGroupHisMsgVisible;
import com.juggle.chat.apimodels.SetGroupMute;
import com.juggle.chat.apimodels.SetGroupVerifyType;
import com.juggle.chat.exceptions.JimErrorCode;
import com.juggle.chat.exceptions.JimException;
import com.juggle.chat.services.GroupService;

@RestController
@RequestMapping("/jim/groups/management")
public class GroupManageController {
    @Resource
    private GroupService grpService;

    @PostMapping("/chgowner")
    public Result chgGroupOwner(@RequestBody GroupOwnerChg chg)throws JimException{
        if(chg==null||chg.getGroupId()==null||chg.getOwnerId()==null){
            throw new JimException(JimErrorCode.ErrorCode_APP_REQ_BODY_ILLEGAL);
        }
        this.grpService.ChgGroupOwner(chg.getGroupId(), chg.getOwnerId());
        return new Result(0, "");
    }

    @PostMapping("/setmute")
    public Result setGroupMute(@RequestBody SetGroupMute setMute)throws JimException{
        if(setMute==null||setMute.getGroupId()==null||setMute.getGroupId().isEmpty()){
            throw new JimException(JimErrorCode.ErrorCode_APP_REQ_BODY_ILLEGAL);
        }
        this.grpService.setGroupMute(setMute.getGroupId(), setMute.getIsMute());
        return new Result(0, "");
    }

    @PostMapping("/setgrpverifytype")
    public Result setGrpVerifyType(@RequestBody SetGroupVerifyType verify)throws JimException{
        if(verify==null||verify.getGroupId()==null||verify.getGroupId().isEmpty()){
            throw new JimException(JimErrorCode.ErrorCode_APP_REQ_BODY_ILLEGAL);
        }
        this.grpService.setGroupVerifyType(verify.getGroupId(), verify.getVerifyType());
        return new Result(0, "");
    }

    @PostMapping("/sethismsgvisible")
    public Result setGrpHisMsgVisible(@RequestBody SetGroupHisMsgVisible visible)throws JimException{
        if(visible==null||visible.getGroupId()==null||visible.getGroupId().isEmpty()){
            throw new JimException(JimErrorCode.ErrorCode_APP_REQ_BODY_ILLEGAL);
        }
        this.grpService.setGroupHisMsgVisible(visible.getGroupId(), visible.getGroupHisMsgVisible());
        return new Result(0, "");
    }

    @PostMapping("/adminstrators/add")
    public Result addGrpAdministrator(@RequestBody GroupAdminIds adminIds)throws JimException{
        if(adminIds==null||adminIds.getGroupId()==null||adminIds.getGroupId().isEmpty()||adminIds.getAdminids()==null||adminIds.getAdminids().size()<=0){
            throw new JimException(JimErrorCode.ErrorCode_APP_REQ_BODY_ILLEGAL);
        }
        this.grpService.addGroupAdmins(adminIds.getGroupId(), adminIds.getAdminids());
        return new Result(0, "");
    }

    @PostMapping("/adminstrators/del")
    public Result delGrpAdministrator(@RequestBody GroupAdminIds adminIds)throws JimException{
        if(adminIds==null||adminIds.getGroupId()==null||adminIds.getGroupId().isEmpty()||adminIds.getAdminids()==null||adminIds.getAdminids().size()<=0){
            throw new JimException(JimErrorCode.ErrorCode_APP_REQ_BODY_ILLEGAL);
        }
        this.grpService.delGroupAdmins(adminIds.getGroupId(), adminIds.getAdminids());
        return new Result(0, "");
    }

    @GetMapping("/adminstrators/list")
    public Result qryGrpAdministrator(@RequestParam("group_id") String groupId)throws JimException{
        if(groupId==null||groupId.isEmpty()){
            throw new JimException(JimErrorCode.ErrorCode_APP_REQ_BODY_ILLEGAL);
        }
        return Result.success(this.grpService.qryGroupAdmins(groupId));
    }
}
