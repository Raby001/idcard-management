package net.orderzone.idcard.model;

import java.time.LocalDate;
import java.util.UUID;

public class ProfileBuilder {

    public static Profile createDefaultStudent(String fullName) {

        Profile profile = new Profile();

        profile.setUuid(UUID.randomUUID().toString());
        profile.setFullName(fullName);
        profile.setType(ProfileType.STUDENT);
        profile.setIssueDate(LocalDate.now());

        return profile;
    }

    public static Profile createDefaultEmployee(String fullName) {

        Profile profile = new Profile();

        profile.setUuid(UUID.randomUUID().toString());
        profile.setFullName(fullName);
        profile.setType(ProfileType.EMPLOYEE);
        profile.setIssueDate(LocalDate.now());

        return profile;
    }
}