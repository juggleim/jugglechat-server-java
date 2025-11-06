package com.juggle.chat.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juggle.chat.apimodels.DelHisMsgsReq;
import com.juggle.chat.apimodels.RecallMsgReq;
import com.juggle.chat.apimodels.Result;

@RestController
@RequestMapping("/jim/messages")
public class MessageController {
    @PostMapping("/recall")
    public Result recallMessage(@RequestBody RecallMsgReq req) {
        return new Result(0, "");
    }

    @PostMapping("/del")
    public Result deleteMessages(@RequestBody DelHisMsgsReq req) {
        return new Result(0, "");
    }
}
