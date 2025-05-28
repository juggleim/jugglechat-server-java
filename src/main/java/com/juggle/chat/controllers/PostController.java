package com.juggle.chat.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juggle.chat.apimodels.Result;

@RestController
@RequestMapping("/jim/posts")
public class PostController {
    @GetMapping("/list")
    public Result qryPosts(){
        return new Result(0,"");
    }

    @GetMapping("/info")
    public Result qryPostInfo(){
        return new Result(0,"");
    }

    @PostMapping("/add")
    public Result postAdd(){
        return new Result(0,"");
    }

    @PostMapping("/update")
    public Result postUpdate(){
        return new Result(0,"");
    }

    @PostMapping("/del")
    public Result postDel(){
        return new Result(0,"");
    }

    @PostMapping("/reactions/add")
    public Result addPostReaction(){
        return new Result(0,"");
    }

    @GetMapping("/reactions/list")
    public Result qryPostReactions(){
        return new Result(0,"");
    }
}
