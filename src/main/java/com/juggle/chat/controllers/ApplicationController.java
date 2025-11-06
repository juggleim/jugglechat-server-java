package com.juggle.chat.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.juggle.chat.apimodels.Result;

@RestController
@RequestMapping("/jim/applications")
public class ApplicationController {
    @GetMapping("/list")
    public Result qryApplications(@RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "order", required = false) Integer order) {
        return new Result(0, "");
    }
}
