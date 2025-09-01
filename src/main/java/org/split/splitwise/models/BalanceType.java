package org.split.splitwise.models;

public enum BalanceType {
    OWES,
    OWED;

    public BalanceType getBalanceType(String type) {
        for(BalanceType balanceType : BalanceType.values()){
            if(balanceType.name().equalsIgnoreCase(type)){
                return balanceType;
            }
        }
        return null;
    }
}
