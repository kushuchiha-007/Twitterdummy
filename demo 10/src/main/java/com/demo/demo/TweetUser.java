package com.demo.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class TweetUser {
    @Id
    private String id;
    private String userId;
    private String screenName;

    public TweetUser() {
    }

    public TweetUser(String userId, String screenName) {
        this.userId = userId;
        this.screenName = screenName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
// Getters and setters
    // ...
}
