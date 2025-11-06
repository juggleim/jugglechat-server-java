package com.juggle.chat.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juggle.chat.apimodels.Feedback;
import com.juggle.chat.apimodels.Result;

@RestController
@RequestMapping("/jim/feedbacks")
public class FeedbackController {
    @PostMapping("/add")
    public Result addFeedback(@RequestBody Feedback feedback) {
        return new Result(0, "");
    }
}
