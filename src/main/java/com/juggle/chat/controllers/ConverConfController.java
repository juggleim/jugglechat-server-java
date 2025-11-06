package com.juggle.chat.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.juggle.chat.apimodels.Result;
import com.juggle.chat.apimodels.SetConverConfsReq;

@RestController
@RequestMapping("/jim/converconfs")
public class ConverConfController {
    @GetMapping("/get")
    public Result getConverConfs(@RequestParam("target_id") String targetId,
            @RequestParam(value = "sub_channel", required = false) String subChannel,
            @RequestParam("conver_type") int converType) {
        return new Result(0, "");
    }

    @PostMapping("/set")
    public Result setConverConfs(@RequestBody SetConverConfsReq req) {
        return new Result(0, "");
    }
}
