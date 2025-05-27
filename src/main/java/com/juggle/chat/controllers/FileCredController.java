package com.juggle.chat.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juggle.chat.apimodels.Result;

@RestController
@RequestMapping("/jim")
public class FileCredController {
    @PostMapping("/file_cred")
    public Result getFileCred(){
        return new Result(0,"succ");
    }
}
