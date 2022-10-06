package com.oren.urlshortener.beans;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;

@Table("user_click")
public class UserClick implements Serializable {
    @PrimaryKey
    private UserClickKey userClickKey;

    private String shortUrl;
    private String longUrl;

    public UserClick(UserClickKey userClickKey, String shortUrl, String longUrl) {
        this.userClickKey = userClickKey;
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
    }

    public UserClick() {

    }

    public static UserClickBuilder builder() {
        return new UserClickBuilder();
    }

    public UserClickKey getUserClickKey() {
        return userClickKey;
    }

    public void setUserClickKey(UserClickKey userClickKey) {
        this.userClickKey = userClickKey;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }


    public static final class UserClickBuilder {
        private UserClickKey userClickKey;
        private String shortUrl;
        private String longUrl;

        private UserClickBuilder() {

        }

        public UserClickBuilder userClickKey(UserClickKey userClickKey) {
            this.userClickKey = userClickKey;
            return this;
        }

        public UserClickBuilder shortUrl(String shortUrl) {
            this.shortUrl = shortUrl;
            return this;
        }

        public UserClickBuilder longUrl(String longUrl) {
            this.longUrl = longUrl;
            return this;
        }

        public UserClick build() {
            UserClick userClick = new UserClick();
            userClick.setUserClickKey(userClickKey);
            userClick.setShortUrl(shortUrl);
            userClick.setLongUrl(longUrl);
            return userClick;
        }
    }

}
