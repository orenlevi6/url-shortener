package com.oren.urlshortener.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class LinkMonitor implements Serializable {
    private Map<String, Integer> clicks = new HashMap<>();

    public LinkMonitor(Map<String, Integer> clicks) {
        this.clicks = clicks;
    }

    public LinkMonitor() {

    }

    public Map<String, Integer> getClicks() {
        return clicks;
    }

    public void setClicks(Map<String, Integer> clicks) {
        this.clicks = clicks;
    }

}
