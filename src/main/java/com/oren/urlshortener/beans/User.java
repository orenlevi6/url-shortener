package com.oren.urlshortener.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document(collection = "users")
public class User {
    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    private Integer allUrlSearches = 0;

    private Map<String, LinkMonitor> searches = new HashMap<>();

    public User(String id, String username, Integer allUrlSearches, Map<String, LinkMonitor> searches) {
        this.id = id;
        this.username = username;
        this.allUrlSearches = allUrlSearches;
        this.searches = searches;
    }

    public User() {

    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAllUrlSearches() {
        return allUrlSearches;
    }

    public void setAllUrlSearches(Integer allUrlSearches) {
        this.allUrlSearches = allUrlSearches;
    }

    public Map<String, LinkMonitor> getSearches() {
        return searches;
    }

    public void setSearches(Map<String, LinkMonitor> searches) {
        this.searches = searches;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id='").append(id).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", allUrlSearches=").append(allUrlSearches);
        sb.append('}');
        return sb.toString();
    }


    public static final class UserBuilder {
        private String id;
        private String username;
        private Integer allUrlSearches = 0;
        private Map<String, LinkMonitor> searches = new HashMap<>();

        private UserBuilder() {

        }

        public UserBuilder id(String id) {
            this.id = id;
            return this;
        }

        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder allUrlSearches(Integer allUrlSearches) {
            this.allUrlSearches = allUrlSearches;
            return this;
        }

        public UserBuilder searches(Map<String, LinkMonitor> searches) {
            this.searches = searches;
            return this;
        }

        public User build() {
            User user = new User();
            user.setId(id);
            user.setUsername(username);
            user.setAllUrlSearches(allUrlSearches);
            user.setSearches(searches);
            return user;
        }
    }

}
