package org.split.splitwise.models;

public enum UserStatus {

    ACTIVE,
    INVITED,
    INACTIVE,
    BLOCKED;

    public static UserStatus fromString(String userStatus) {

        for(UserStatus s : UserStatus.values()) {
            if(s.name().equals(userStatus)) {
                return s;
            }
        }
        return null;
    }
}
