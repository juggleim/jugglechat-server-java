package com.juggle.chat.controllers;


import com.juggle.chat.apimodels.Result;
import com.juggle.chat.exceptions.JimException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/blockusers")
public class BlockUsersController {
    @PostMapping("/add")
    public Result addBlock(@RequestBody List<String> blockUsers)throws JimException {
        return Result.success();
    }


    @PostMapping("/del")
    public Result delBlock(@RequestBody List<String> blockUsers)throws JimException {
        return Result.success();
    }


    @PostMapping("/list")
    public Result listBlock(@RequestParam("offset") int offset,@RequestParam("count") int count)throws JimException {
        return Result.success();
    }
}
