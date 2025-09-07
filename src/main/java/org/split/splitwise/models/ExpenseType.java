package org.split.splitwise.models;

public enum ExpenseType {

    EXPENSE,
    TRANSACTION;

    public static ExpenseType getExpenseType(String type) {
        for(ExpenseType e : ExpenseType.values()){
            if(e.name().equalsIgnoreCase(type)){
                return e;
            }
        }
        return null;
    }
}
