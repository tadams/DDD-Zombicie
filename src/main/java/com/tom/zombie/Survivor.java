package com.tom.zombie;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class Survivor {

    boolean isAlive = true;
    int hitPoints = 2;
    final String weaponName;
}
