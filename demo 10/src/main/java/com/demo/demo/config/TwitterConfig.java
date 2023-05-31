package com.demo.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
public class TwitterConfig {

    @Value("${twitter.consumerKey}")
    private String consumerKey;

    @Value("${twitter.consumerSecret}")
    private String consumerSecret;

    @Value("${twitter.accessToken}")
    private String accessToken;

    @Value("${twitter.accessTokenSecret}")
    private String accessTokenSecret;

    @Value("${twitter.bearerToken}")
    private String bearerToken;

    @Bean
    public Twitter twitter() {
        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret)
                .setOAuth2TokenType("AAAAAAAAAAAAAAAAAAAAAOpGnwEAAAAAJwqvyeMXA3f284X0liNUSqJ9bws%3D0hPgGL0fgHUmSTGuSo6EXINHb0RRikAT6hAax7xwzW8sIKJ5eo")
                .setOAuth2AccessToken(bearerToken);

        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }
}
