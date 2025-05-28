package com.juggle.chat.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juggle.chat.apimodels.Result;

@RestController
@RequestMapping("/jim/assistants")
public class AssistantsController {
    @PostMapping("/answer")
    public Result assistantAnswer(){
        return new Result(0, "");
    }

    @PostMapping("/prompts/add")
    public Result PromptAdd(){
        return new Result(0, "");
    }

    @PostMapping("/prompts/update")
    public Result promptUpdate(){
        return new Result(0, "");
    }

    @PostMapping("/prompts/del")
    public Result promptDel(){
        return new Result(0, "");
    }

    @PostMapping("/prompts/batchdel")
    public Result promptBatchDel(){
        return new Result(0, "");
    }

    @GetMapping("/prompts/list")
    public Result qryPrompts(){
        return new Result(0, "");
    }
}
