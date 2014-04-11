package com.tom.zombie;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class DiceTest {

    Dice dice = new Dice();

    @Test
    public void shouldReturnNumberOfDiceAskedFor() {
        List<Integer> rolls = dice.roll(5);
        assertThat(rolls.size(), is(5));
    }

    @Test
    public void shouldRollSixSidedDice() {
        dice.roll(10)
            .stream()
            .filter(i -> i > 6 || i < 1)
            .findFirst()
            .ifPresent(i -> fail("Found dice out of range " + i));
    }
}
