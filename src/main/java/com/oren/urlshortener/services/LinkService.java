package com.oren.urlshortener.services;

import com.oren.urlshortener.beans.Link;
import com.oren.urlshortener.beans.User;
import com.oren.urlshortener.exceptions.NotExistException;
import com.oren.urlshortener.repositories.LinkRepo;
import com.oren.urlshortener.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import redis.clients.jedis.exceptions.InvalidURIException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class LinkService {
    private static final int SHORT_URL_LENGTH = 6;
    private static final Random RANDOM = new Random();

    @Value("${base.url}")
    private String BASE_URL;

    @Autowired
    private LinkRepo linkRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    public String createShortUrl(String longUrl, String username) throws InvalidURIException {
        Optional<Link> optionalLink = extractOptionalLink(longUrl);
        if (optionalLink.isPresent()) {
            return BASE_URL + optionalLink.get().getShortUrl() + "/";
        }

        try {
            URL url = new URL(longUrl);
            url.toURI().toURL().openConnection().connect();
        } catch (IOException | URISyntaxException err) {
            throw new InvalidURIException("Please make sure your URL is valid");
        }

        String user;
        Optional<User> optionalUser = userRepo.findByUsername(username);
        if (optionalUser.isEmpty()) {
            user = "Anonymous";
        } else {
            user = username;
        }

        String newShortUrl;
        do {
            newShortUrl = generateShortUrl();
        } while (linkRepo.findById(newShortUrl).isPresent());

        Link newLink = new Link(newShortUrl, longUrl, user);
        linkRepo.save(newLink);
        return BASE_URL + newLink.getShortUrl() + "/";
    }

    public String getLongUrl(String shortUrl) throws NotExistException {
        Link optionalLink = linkRepo.findById(shortUrl)
                .orElseThrow(() -> new NotExistException("Short URL was not found"));

        incrementMongoField(optionalLink.getSubmittedUsername(), "allUrlSearches");
        incrementMongoField(optionalLink.getSubmittedUsername(),
                String.format("searches.%s.clicks.%s", shortUrl, formatDate()));

        return optionalLink.getLongUrl();
    }

    private Optional<Link> extractOptionalLink(String longUrl) {
        List<Link> links = (List<Link>) linkRepo.findAll();
        return links.stream()
                .filter(link -> link.getLongUrl().equals(longUrl))
                .findAny();
    }

    private String generateShortUrl() {
        String base62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            result.append(base62.charAt(RANDOM.nextInt(base62.length())));
        }
        return result.toString();
    }

    private void incrementMongoField(String username, String key) {
        Query query = Query.query(Criteria.where("username").is(username));
        Update update = new Update().inc(key, 1);
        mongoTemplate.updateFirst(query, update, "users");
    }

    private String formatDate() {
        LocalDate localDate = LocalDate.now();
        int day = localDate.getDayOfMonth();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();
        return String.format("%d-%d-%d", day, month, year);
    }

}
