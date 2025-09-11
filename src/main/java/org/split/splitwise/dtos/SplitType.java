package org.split.splitwise.dtos;

public enum SplitType {

    EQUAL, PERCENT, RATIO, EXACT;

    public static SplitType fromString(String str) {
        for (SplitType type : SplitType.values()) {
            if (type.toString().equalsIgnoreCase(str)) {
                return type;
            }
        }
        return null;
    }
}
