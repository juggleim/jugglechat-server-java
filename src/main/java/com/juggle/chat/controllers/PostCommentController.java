package com.juggle.chat.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juggle.chat.apimodels.Result;

@RestController
@RequestMapping("/jim/postcomments")
public class PostCommentController {
    @GetMapping("/list")
    public Result qryPostComments(){
        return new Result(0,"");
    }

    @PostMapping("/add")
    public Result addPostComment(){
        return new Result(0,"");
    }

    @PostMapping("/update")
    public Result updatePostComment(){
        return new Result(0,"");
    }

    @PostMapping("/del")
    public Result delPostComment(){
        return new Result(0,"");
    }
}
