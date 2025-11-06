package com.juggle.chat.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juggle.chat.apimodels.Result;
import com.juggle.chat.apimodels.TransReq;

@RestController
@RequestMapping("/jim")
public class TranslateController {
    @PostMapping("/translate")
    public Result translate(@RequestBody TransReq req) {
        return new Result(0, "");
    }
}
