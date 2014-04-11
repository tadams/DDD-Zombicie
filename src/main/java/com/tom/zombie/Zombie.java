package com.tom.zombie;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class Zombie {

    boolean isAlive = true;
    final String type;

    public void killed() {
        isAlive = false;
        System.out.println("Killed " + this);
    }
}
