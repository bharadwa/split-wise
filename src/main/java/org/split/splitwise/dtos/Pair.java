package org.split.splitwise.dtos;

import lombok.Getter;

@Getter
public class Pair <U,V>{
    U first;
    V second;

    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }

}
