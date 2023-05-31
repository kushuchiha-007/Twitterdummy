package com.demo.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import twitter4j.*;
import twitter4j.User;

import java.util.List;

@Service
public class TwitterService {
    private final Twitter twitter;
    private final TweetRepository tweetRepository;

    @Autowired
    public TwitterService(Twitter twitter, TweetRepository tweetRepository) {
        this.twitter = twitter;
        this.tweetRepository = tweetRepository;
    }

    public String getUserDetails() {
        try {
            twitter4j.User user = twitter.verifyCredentials();
            TweetUser user1 = new TweetUser (user.getDescription(), user.getScreenName());
            tweetRepository.save(user1);
            return "User ID: " + user.getId() + "\nScreen Name: " + user.getScreenName();
        } catch (TwitterException e) {
            System.out.println(e);
            e.printStackTrace();
            return "Error retrieving user details";
        }
    }

    public List<User> getFollowers(String screenName) {
        try {
            long cursor = -1;
            PagableResponseList<User> followers = twitter.getFollowersList(screenName, cursor);
            return followers;
        } catch (TwitterException e) {
            System.out.println(e);
            e.printStackTrace();
            return null;
        }
    }
    public ResponseEntity<String> postTweet(String tweetContent) {
        try {
            StatusUpdate statusUpdate = new StatusUpdate(tweetContent);
            Status status = twitter.updateStatus(statusUpdate);
            System.out.println("Tweet posted successfully. Tweet ID: " + status.getId());
            return ResponseEntity.ok("Tweet posted successfully. Tweet ID: " + status.getId());
        } catch (TwitterException e) {
            System.out.println("Failed to post tweet: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to post tweet: " + e.getMessage());
        }
    }

    public String deleteTweet(String tweetId) {
        try {
            Status status = twitter.destroyStatus(Long.parseLong(tweetId));
            return "Tweet deleted successfully! Tweet ID: " + status.getId();
        } catch (TwitterException e) {
            System.out.println(e);
            e.printStackTrace();
            return "Error deleting tweet";
        }
    }

}
