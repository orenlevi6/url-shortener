package com.oren.urlshortener.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Link")
public class Link implements Serializable {
    @Id
    private String shortUrl;

    private String longUrl;
    private String submittedUsername;

    public Link(String shortUrl, String longUrl, String submittedUsername) {
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
        this.submittedUsername = submittedUsername;
    }

    public Link() {

    }

    public static LinkBuilder builder() {
        return new LinkBuilder();
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

    public String getSubmittedUsername() {
        return submittedUsername;
    }

    public void setSubmittedUsername(String submittedUsername) {
        this.submittedUsername = submittedUsername;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Link{");
        sb.append("shortUrl='").append(shortUrl).append('\'');
        sb.append(", longUrl='").append(longUrl).append('\'');
        sb.append(", submittedUsername='").append(submittedUsername).append('\'');
        sb.append('}');
        return sb.toString();
    }


    public static final class LinkBuilder {
        private String shortUrl;
        private String longUrl;
        private String submittedUsername;

        private LinkBuilder() {

        }

        public LinkBuilder shortUrl(String shortUrl) {
            this.shortUrl = shortUrl;
            return this;
        }

        public LinkBuilder longUrl(String longUrl) {
            this.longUrl = longUrl;
            return this;
        }

        public LinkBuilder submittedUsername(String submittedUsername) {
            this.submittedUsername = submittedUsername;
            return this;
        }

        public Link build() {
            Link link = new Link();
            link.setShortUrl(shortUrl);
            link.setLongUrl(longUrl);
            link.setSubmittedUsername(submittedUsername);
            return link;
        }
    }

}
