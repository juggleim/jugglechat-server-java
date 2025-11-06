package com.juggle.chat.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juggle.chat.apimodels.Result;

@RestController
@RequestMapping("/jim")
public class SyncController {
    @GetMapping("/syncconfs")
    public Result syncConfs() {
        return new Result(0, "");
    }
}
