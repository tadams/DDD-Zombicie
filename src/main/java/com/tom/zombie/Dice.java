package com.tom.zombie;

import java.util.*;
import java.util.stream.Collectors;

public class Dice {

    public static final int MIN_ROLL = 1;
    public static final int MAX_ROLL = 7;

    Random random = new Random();

    public List<Integer> roll(int numberOfDice) {
        return random.ints(numberOfDice, MIN_ROLL, MAX_ROLL)
                     .mapToObj(Integer::new)
                     .collect(Collectors.toList());
    }
}
