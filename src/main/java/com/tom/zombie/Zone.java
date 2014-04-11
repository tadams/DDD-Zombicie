package com.tom.zombie;

import lombok.Data;

import java.util.List;

@Data
public class Zone {

    List<Survivor> survivors;
    List<Zombie> zombies;
}
