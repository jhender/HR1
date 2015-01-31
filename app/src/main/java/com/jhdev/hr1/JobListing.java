package com.jhdev.hr1;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.UUID;

/**
 *  The JobListing Object
 */
@ParseClassName("JobListing")
public class JobListing extends ParseObject {
    public JobListing() {
        //empty constructor
    }

    //the displayed items:

    public String getCategory() {
        return getString("category");
    }

    public void setCategory(String category) {
        put("category", category);
    }

    public String getTitle() {
        return getString("title");
    }

    public void setTitle(String title) {
        put("title", title);
    }

    public String getDescription() {
        return getString("description");
    }

    public void setDescription(String description) {
        put("description", description);
    }

    public String getLocation() {
        return getString("location");
    }

    public void setLocation(String location) {
        put("location", location);
    }

    public ParseUser getLister() {
        return getParseUser("lister");
    }

    public void setLister(ParseUser currentUser) {
        put("lister", currentUser);
    }

    // the backend items:

    public boolean isDraft() {
        return getBoolean("isDraft");
    }

    public void setDraft(boolean isDraft) {
        put("isDraft", isDraft);
    }

    public void setUuidString() {
        UUID uuid = UUID.randomUUID();
        put("uuid", uuid.toString());
    }

    public String getUuidString() {
        return getString("uuid");
    }

    public static ParseQuery<JobListing> getQuery() {
        return ParseQuery.getQuery(JobListing.class);
    }

    public String getStatus() {
        return getString("status");
    }

    public void setStatus(String status) {
        put("status", status);
    }

    public Number getOpen() {
        return getNumber("open");
    }


}
