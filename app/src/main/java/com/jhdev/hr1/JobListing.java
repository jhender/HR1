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

    public String getTitle() {
        return getString("title");
    }

    public void setTitle(String title) {
        put("title", title);
    }

    public ParseUser getLister() {
        return getParseUser("lister");
    }

    public void setLister(ParseUser currentUser) {
        put("lister", currentUser);
    }

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

    public String getDescription() {
        return getString("description");
    }

    public void setDescription(String description) {
        put("description", description);
    }

    public String getWorkType() {
        return getString("worktype");
    }

    public void setWorkType(String WorkType) {
        put("worktype", WorkType);
    }

    public String getStatus() {
        return getString("status");
    }

    public Number getOpen() {
        return getNumber("open");
    }

}
