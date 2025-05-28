package com.juggle.chat.controllers;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.juggle.chat.apimodels.AiBotInfos;
import com.juggle.chat.apimodels.Result;
import com.juggle.chat.exceptions.JimException;
import com.juggle.chat.services.BotService;

@RestController
@RequestMapping("/jim/bots")
public class BotController {
    @Resource
    private BotService botService;

    @GetMapping("/list")
    public Result qryBots(@RequestParam String offset, @RequestParam int count)throws JimException{
        if(count<=0){
            count = 20;
        }
        AiBotInfos botInfos = botService.qryAiBots(count, offset);
        return Result.success(botInfos);
    }

    @PostMapping("/messages/listener")
    public Result botMsgListener(){
        return new Result(0, "");
    }
}
