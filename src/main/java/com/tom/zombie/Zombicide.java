package com.tom.zombie;

import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

public class Zombicide {

    @Setter
    Zone zone;

    Dice dice = new Dice();

    public void meleeCombat() {

        zone.getSurvivors().stream()
            .filter(Survivor::isAlive)
            .forEach(this::launchAttack);

        // Next the Zombies Attack
    }

    protected void launchAttack(Survivor survivor) {

        List<Integer> attackDamage = computeAttackDamage(survivor.getWeaponName());

        System.out.println("\n" + survivor.getWeaponName() + " launchAttack damage " + attackDamage);

        attackDamage.stream()
                    .forEach(this::attackZombies);

    }

    protected void attackZombies(Integer attackDamage) {
         zone.getZombies()
             .stream()
             .filter(Zombie::isAlive)
             .findFirst()
             .ifPresent(aliveZombie -> attack(aliveZombie, attackDamage));
    }

    protected void attack(Zombie aliveZombie, Integer attackDamage) {
        System.out.println("Attacking " + aliveZombie + " with damage of " + attackDamage);

        switch (aliveZombie.getType()) {
            case "Walker" :
            case "Runner" :
                aliveZombie.killed();
                return;

            case "Fatty":
                if (attackDamage >= 2) {
                    aliveZombie.killed();
                }
                return;

            case "Abomination":
                if (attackDamage >= 3) {
                    aliveZombie.killed();
                }
                return;
        }
        throw new AssertionError("Unknown Zombie type " + aliveZombie.getType());
    }

    protected List<Integer> computeAttackDamage(String weaponName) {

        switch (weaponName) {
            case "The Pan":     return computeWeaponDamage(1, 6, 1);
            case "Pistol":
            case "Crow Bar":    return computeWeaponDamage(1, 4, 1);
            case "Fire Ax":     return computeWeaponDamage(1, 4, 2);
            case "Rifle":       return computeWeaponDamage(1, 3, 1);
            case "Katana":      return computeWeaponDamage(2, 4, 1);
            case "Shot Gun":    return computeWeaponDamage(2, 4, 2);
            case "Claw Hammer": return computeWeaponDamage(3, 5, 1);
        }

        throw new AssertionError("Unknown weapon " + weaponName);
    }

    protected List<Integer> computeWeaponDamage(int numberOfDice, int minScoreForDamage, int damageAmount) {
        return dice.roll(numberOfDice)
                   .stream()
                   .filter(roll -> roll >= minScoreForDamage)
                   .map(goodRoll -> damageAmount)
                   .collect(Collectors.toList());
    }
}
