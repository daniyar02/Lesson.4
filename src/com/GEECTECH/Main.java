package com.GEECTECH;

import java.util.Random;

public class Main {
    public static String[] heroesNames = {"Liu Kang", "Jax", "Kung Lao"};
    public static int[] heroesHealth = {270, 280, 250};
    public static int[] heroesDamage = {20, 15, 25};
    public  static  String medicName = "Raiden";
    public static  int medicHealth = 500;
    public  static  String medicHelp = "";

    public static String bossName = "Shao Kahn";
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static int roundNumber = 0;
    public static String superDamageHero = "";

    public static void main(String[] args) {
        printStatistics();
        System.out.println("______Mortal Kombat started____");

        while (!isGameFinished()){
            round();
        }
    }
    public static  boolean isGameFinished(){
      if (bossHealth  <= 0 && medicHealth <= 0){
          System.out.println("Heroes won and Raiden !!!" +" Mortal Kombat finished" );
           if (medicHealth <= 0) {
               System.out.println("Raiden alive");
          }
          return true;
      }
      boolean allHeroesDead = true;
        for (int heroHealth:heroesHealth
             ) {if (heroHealth > 0 ){
                 allHeroesDead = false;
                 break;
        }

        }
        if (allHeroesDead){
            System.out.println(bossName + " Won!!!" +" Mortal Kombat finished");
        }
        return  allHeroesDead;
    }

    public static void bossDamage() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                heroesHealth[i]  = heroesHealth[i] - bossDamage;
            }
            if (medicHealth > 0 && bossHealth > 0){
                medicHealth =medicHealth - bossDamage;
            }
            if (bossHealth < 0){
                bossHealth = 0;
            }


        }
    }
    public  static  void  round (){
        roundNumber++;
        System.out.println("_____ Round " + roundNumber + "______");
        bossDamage();
        superDamageHero =getHeroForDamageBossDefence();
        medicHelp();  getHeroForRecovery();
        heroesDamage();
        printStatistics();
    }

    public static void heroesDamage() {
        Random random = new Random();
        int coeff = random.nextInt(9) + 2;
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth [i] > 0 && bossHealth > 0) {
                if (superDamageHero == heroesNames[i])
                bossHealth = bossHealth - heroesDamage[i] * coeff;
                System.out.println("Super damage hero ==" + superDamageHero + "" + (heroesDamage [i]* coeff));
            }else {    bossHealth = bossHealth - heroesDamage[i] ;
            }
            if (heroesHealth[i] < 0){
                heroesHealth[i] = 0;
            }
        }
    }
    public  static  String getHeroForDamageBossDefence(){
        Random random = new Random();
        int randomIndex = random.nextInt(heroesNames.length);
        return  heroesNames[randomIndex];
    }
    public  static  String getHeroForRecovery(){
        Random random = new Random();
        int randomIndex = random.nextInt(heroesNames.length);
        return  heroesNames[randomIndex];
    }
    public static  void  medicHelp (){
        Random random = new Random();
        int medicine = random.nextInt(100);
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth [i] > 0 && medicHealth > 0){
                if (medicHelp == heroesNames[i])
                    heroesHealth [i]=heroesHealth[i] + medicine;
                System.out.println( heroesNames + " Hero recovery" + heroesHealth + "" + medicine);
            }else  { heroesHealth[i] =heroesHealth[i] - bossDamage;
        }if (heroesHealth [i] <=0 && medicHealth <= 0){

            }

        }
    }

    public static  void  printStatistics (){
        System.out.println(bossName+ " = health " + bossHealth + " damage [" + bossDamage + "]");
        System.out.println(medicName + " = health " + medicHealth + " health [" + medicHelp + "]" );
        for (int i = 0; i < heroesNames.length ; i++) {
            System.out.println(heroesNames[i] +" health " + heroesHealth[i] + " damage [" + heroesDamage[i] + " ]"  );

        }
    }
}
