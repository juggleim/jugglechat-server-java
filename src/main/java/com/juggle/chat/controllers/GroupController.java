package com.juggle.chat.controllers;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.juggle.chat.apimodels.GroupInfo;
import com.juggle.chat.apimodels.GroupInvite;
import com.juggle.chat.apimodels.GroupInviteResp;
import com.juggle.chat.apimodels.QrCode;
import com.juggle.chat.apimodels.Result;
import com.juggle.chat.exceptions.JimErrorCode;
import com.juggle.chat.exceptions.JimException;
import com.juggle.chat.interceptors.RequestContext;
import com.juggle.chat.services.GroupService;
import com.juggle.chat.utils.CommonUtil;

@RestController
@RequestMapping("/jim/groups")
public class GroupController {
    @Resource
    private GroupService grpService;

    @PostMapping(value={"/add","/create"})
    public Result createGroup(@RequestBody GroupInfo grpInfo)throws JimException{
        if(grpInfo==null||grpInfo.getGroupId().isEmpty()||grpInfo.getMemberIds()==null){
            throw new JimException(JimErrorCode.ErrorCode_APP_REQ_BODY_ILLEGAL);
        }
        this.grpService.createGroup(grpInfo);
        return new Result(0, "");
    }

    @PostMapping("/update")
    public Result updateGroup(@RequestBody GroupInfo grpInfo)throws JimException{
        if(grpInfo==null||grpInfo.getGroupId()==null){
            throw new JimException(JimErrorCode.ErrorCode_APP_REQ_BODY_ILLEGAL);
        }
        this.grpService.updateGroup(grpInfo);
        return new Result(0, "");
    }

    @PostMapping("/dissolve")
    public Result dissolveGroup(@RequestBody GroupInfo grpInfo)throws JimException{
        if(grpInfo==null||grpInfo.getGroupId()==null){
            throw new JimException(JimErrorCode.ErrorCode_APP_REQ_BODY_ILLEGAL);
        }
        this.grpService.dissolveGroup(grpInfo.getGroupId());
        return new Result(0, "");
    }

    @GetMapping("/info")
    public Result qryGroupInfo(@RequestParam("group_id") String groupId){
        if(groupId.isEmpty()){
            throw new JimException(JimErrorCode.ErrorCode_APP_REQ_BODY_ILLEGAL);
        }
        return Result.success(this.grpService.qryGroupInfo(groupId));
    }

    @GetMapping("/qrcode")
    public Result qryGrpQrCode(@RequestParam("group_id") String groupId)throws JimException{
        if(groupId==null||groupId.isEmpty()){
            throw new JimException(JimErrorCode.ErrorCode_APP_REQ_BODY_ILLEGAL);
        }
        QrCode ret = new QrCode();
        String userId = RequestContext.getCurrentUserIdFromCtx();
        Map<String,String> qrContent = new HashMap<>();
        qrContent.put("action", "join_group");
        qrContent.put("group_id", groupId);
        qrContent.put("user_id", userId);
        String content = CommonUtil.toJson(qrContent);
        int width = 400;
        int height = 400;
        String format = "png";
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, format, baos);
            byte[] imgBs = baos.toByteArray();
            ret.setQrCode(Base64.getUrlEncoder().encodeToString(imgBs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success(ret);
    }

    @PostMapping("/apply")
    public Result groupApply(@RequestBody GroupInvite grpInvite)throws JimException{
        if(grpInvite==null||grpInvite.getGroupId()==null||grpInvite.getGroupId().isEmpty()){
            throw new JimException(JimErrorCode.ErrorCode_APP_REQ_BODY_ILLEGAL);
        }
        this.grpService.grpJoinApply(grpInvite.getGroupId());
        return new Result(0, "");
    }

    @PostMapping("/invite")
    public Result groupInvite(@RequestBody GroupInvite grpInvite){
        if(grpInvite==null||grpInvite.getGroupId()==null||grpInvite.getGroupId().isEmpty()||grpInvite.getMemberIds()==null||grpInvite.getMemberIds().size()<=0){
            throw new JimException(JimErrorCode.ErrorCode_APP_REQ_BODY_ILLEGAL);
        }
        GroupInviteResp resp = this.grpService.grpInviteMembers(grpInvite.getGroupId(), grpInvite.getMemberIds());
        return Result.success(resp);
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
