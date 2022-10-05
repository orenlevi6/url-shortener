package com.oren.urlshortener.controllers;

import com.oren.urlshortener.exceptions.NotExistException;
import com.oren.urlshortener.services.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.exceptions.InvalidURIException;

@RestController
@RequestMapping("/")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @PostMapping("/create-short-url")
    public ResponseEntity<?> createShortUrl(@RequestHeader String longUrl) throws InvalidURIException {
        return new ResponseEntity<>(linkService.createShortUrl(longUrl), HttpStatus.OK);
    }

    @GetMapping("/at/{shortUrl}/")
    public ModelAndView getLongUrl(@PathVariable String shortUrl) throws NotExistException {
        return new ModelAndView("redirect:" + linkService.getLongUrl(shortUrl));
    }

}
