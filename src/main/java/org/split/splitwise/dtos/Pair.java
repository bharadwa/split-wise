package org.split.splitwise.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Pair <U,V>{
    U first;
    U second;
}
