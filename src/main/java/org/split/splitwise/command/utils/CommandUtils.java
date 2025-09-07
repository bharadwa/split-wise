package org.split.splitwise.command.utils;

import java.util.Arrays;
import java.util.List;

public class CommandUtils {

    public static List<String> splitWords(String input) {
        return Arrays.asList(input.split(" "));
    }
}
