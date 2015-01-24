package com.jhdev.hr1;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Date;
import java.util.UUID;

/**
 *  The ProfileEmployee Object. to be linked to Users that are looking for jobs
 */
@ParseClassName("ProfileEmployee")
public class ProfileEmployee extends ParseObject {
    public ProfileEmployee() {
        //empty constructor
    }

    public ParseUser getUser() {
        return getParseUser("user");
    }

    public void setUser(ParseUser currentUser) {
        put("user", currentUser);
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

    public static ParseQuery<ProfileEmployee> getQuery() {
        return ParseQuery.getQuery(ProfileEmployee.class);
    }

    public Date getBirthday() {
        return getDate("birthday");
    }

    public void setBirthday(Date birthday) {
        put("birthday", birthday);
    }

    public String getExperience() {
        return getString("experience");
    }

    public void setExperience(String experience) {
        put("experience", experience);
    }
}
