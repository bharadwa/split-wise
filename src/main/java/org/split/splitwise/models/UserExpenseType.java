package org.split.splitwise.models;

public enum UserExpenseType {
    PAID,
    HAS_TO_PAY;

    public UserExpenseType getBalanceType(String type) {
        for(UserExpenseType balanceType : UserExpenseType.values()){
            if(balanceType.name().equalsIgnoreCase(type)){
                return balanceType;
            }
        }
        return null;
    }
}
