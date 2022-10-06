package com.oren.urlshortener.beans;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@PrimaryKeyClass
public class UserClickKey implements Serializable {
    @PrimaryKeyColumn(name = "user_name", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String username;

    @PrimaryKeyColumn(name = "click_time", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private LocalDateTime clickTime;

    public UserClickKey(String username, LocalDateTime clickTime) {
        this.username = username;
        this.clickTime = clickTime;
    }

    public UserClickKey() {

    }

    public static UserClickKeyBuilder builder() {
        return new UserClickKeyBuilder();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getClickTime() {
        return clickTime;
    }

    public void setClickTime(LocalDateTime clickTime) {
        this.clickTime = clickTime;
    }


    public static final class UserClickKeyBuilder {
        private String username;
        private LocalDateTime clickTime;

        private UserClickKeyBuilder() {

        }

        public UserClickKeyBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserClickKeyBuilder clickTime(LocalDateTime clickTime) {
            this.clickTime = clickTime;
            return this;
        }

        public UserClickKey build() {
            UserClickKey userClickKey = new UserClickKey();
            userClickKey.setUsername(username);
            userClickKey.setClickTime(clickTime);
            return userClickKey;
        }
    }

}
