package com.juggle.chat.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juggle.chat.apimodels.Result;

@RestController
@RequestMapping("/jim/telegrambots")
public class TelegramBotController {
    @PostMapping("/add")
    public Result telegramBotAdd(){
        return new Result(0, "");
    }

    @PostMapping("/del")
    public Result telegramBotDel(){
        return new Result(0, "");
    }

    @PostMapping("/batchdel")
    public Result telegramBotBatchDel(){
        return new Result(0, "");
    }

    @GetMapping("/list")
    public Result telegramBotList(){
        return new Result(0, "");
    }
}
