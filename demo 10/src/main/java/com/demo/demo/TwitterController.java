package com.demo.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import twitter4j.User;

import java.util.List;

@RestController
@RequestMapping("/tweets")
public class TwitterController {
    private final TwitterService twitterService;

    @Autowired
    public TwitterController(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    @GetMapping("/user")
    public String getUserDetails() {
        return twitterService.getUserDetails();
    }

    @PostMapping("/post")
    public ResponseEntity<String> postTweet(@RequestBody String tweetContent) {
        return twitterService.postTweet(tweetContent);
    }

    @DeleteMapping("/{id}")
    public String deleteTweet(@PathVariable("id") String tweetId) {
        return twitterService.deleteTweet(tweetId);
    }

    @GetMapping("/followers/{screenName}")
    public List<User> getFollowers(@PathVariable("screenName") String screenName) {
        return twitterService.getFollowers(screenName);
    }
}
