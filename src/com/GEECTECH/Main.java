package com.GEECTECH;

import java.util.Random;

public class Main {
    public static String[] heroesNames = {"Liu Kang", "Jax", "Kung Lao", "Raiden"};
    public static int[] heroesHealth = {270, 280, 250, 500};
    public static int[] heroesDamage = {20, 15, 25, 0};

    public static String bossName = "Shao Kahn";
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static int roundNumber = 0;
    public static String superDamageHero = "";


    public static void main(String[] args) {
        printStatistics();
        System.out.println("______Mortal Kombat started____");

        while (!isGameFinished()) {
            round();
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won and Raiden !!!" + " Mortal Kombat finished");
            if (heroesHealth[3] <= 0) {
                System.out.println("Raiden alive");
            }
            return true;
        }
        boolean allHeroesDead = true;
        for (int heroHealth : heroesHealth) {
            if (heroHealth > 0) {
                allHeroesDead = false;
                break;
            }

        }
        if (allHeroesDead) {
            System.out.println(bossName + " Won!!!" + " Mortal Kombat finished");
        }
        return allHeroesDead;
    }

    public static void bossDamage() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                heroesHealth[i] = heroesHealth[i] - bossDamage;
            }
        }
        if (bossHealth < 0) {
            bossHealth = 0;
        }


    }


    public static void round() {
        roundNumber++;
        System.out.println("_____ Round " + roundNumber + "______");
        bossDamage();
        superDamageHero = getHeroForDamageBossDefence();
        medicHelp();
        heroesDamage();
        printStatistics();
    }

    public static void heroesDamage() {
        Random random = new Random();
        int coeff = random.nextInt(9) + 2;
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (superDamageHero == heroesNames[i]) {
                    bossHealth = bossHealth - heroesDamage[i] * coeff;
                    System.out.println("Super damage hero = " +
                            superDamageHero + " " +
                            (heroesDamage[i] * coeff));
                } else {
                    bossHealth = bossHealth - heroesDamage[i];
                }
            }
            if (heroesHealth[i] < 0) {
                heroesHealth[i] = 0;
            }
            if (bossHealth < 0) {
                bossHealth = 0;
            }
        }
    }

    public static String getHeroForDamageBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesNames.length);
        if (3 == randomIndex) {
            randomIndex = random.nextInt(heroesDamage.length);
        }
        return heroesNames[randomIndex];
    }


    public static void medicHelp() {
        Random random = new Random();
        int medicine = random.nextInt(100);
        int randomHero = random.nextInt(heroesHealth.length);
        if (randomHero == 3) randomHero = random.nextInt(heroesDamage.length);
        if (heroesHealth[randomHero] > 0) {
            if (heroesHealth[3] > 0) {
                heroesHealth[randomHero] = heroesHealth[randomHero] + medicine;
                System.out.println("\n" + heroesNames[randomHero] + " Hero recovery " + " +" + medicine);
            }
        }

    }

    public static void printStatistics() {
        System.out.println(bossName + " = health " + bossHealth + " damage [" + bossDamage + "]\n");
        for (int i = 0; i < heroesNames.length; i++) {
            System.out.println(heroesNames[i] + " health " + heroesHealth[i] + " damage [" + heroesDamage[i] + "]");

        }
    }
}
