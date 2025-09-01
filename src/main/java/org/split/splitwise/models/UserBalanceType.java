package org.split.splitwise.models;

public enum UserBalanceType {
    OWES,
    OWED;

    public UserBalanceType getBalanceType(String type) {
        for(UserBalanceType balanceType : UserBalanceType.values()){
            if(balanceType.name().equalsIgnoreCase(type)){
                return balanceType;
            }
        }
        return null;
    }
}
