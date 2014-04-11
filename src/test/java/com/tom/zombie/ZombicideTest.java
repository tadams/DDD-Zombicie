package com.tom.zombie;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ZombicideTest {

    @Mock
    Dice dice;

    @Before
    public void given() {
        initMocks(this);
    }

    @Test
    public void shouldComputeWeaponDamageForPan() {
        when(dice.roll(1)).thenReturn(asList(6));
        Zombicide zombicide = new Zombicide();
        zombicide.dice = dice;

        List<Integer> attackDamage = zombicide.computeAttackDamage("The Pan");
        assertThat(attackDamage, is(asList(1)));
    }

    @Test
    public void shouldComputeNoWeaponDamageForKatana() {
        when(dice.roll(1)).thenReturn(asList(3));
        Zombicide zombicide = new Zombicide();
        zombicide.dice = dice;

        List<Integer> attackDamage = zombicide.computeAttackDamage("Katana");
        assertThat(attackDamage.isEmpty(), is(true));
    }

    @Test
    public void shouldKillWalkerZombie() {
        Zombie zombie1 = new Zombie("Walker");
        Zombicide zombicide = new Zombicide();

        zombicide.attack(zombie1, 1);
        assertThat(zombie1.isAlive(), is(false));
    }

    @Test
    public void shouldNotKillFattyZombie() {
        Zombie zombie1 = new Zombie("Fatty");
        Zombicide zombicide = new Zombicide();

        zombicide.attack(zombie1, 1);
        assertThat(zombie1.isAlive(), is(true));
    }

    @Test
    public void shouldKillTwoZombies() {
        Zombie zombie1 = new Zombie("Walker");
        Zombie zombie2 = new Zombie("Fatty");
        Zone zone = new Zone();
        zone.setZombies(asList(zombie1, zombie2));
        Zombicide zombicide = new Zombicide();
        zombicide.setZone(zone);

        zombicide.attackZombies(1);
        zombicide.attackZombies(3);
        zone.getZombies()
            .stream()
            .filter(Zombie::isAlive)
            .findFirst()
            .ifPresent(z -> fail("All zombies should be killed "));
    }

}
