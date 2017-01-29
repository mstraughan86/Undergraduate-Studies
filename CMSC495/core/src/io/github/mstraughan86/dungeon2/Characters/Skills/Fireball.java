package io.github.mstraughan86.dungeon2.Characters.Skills;

import io.github.mstraughan86.dungeon2.Characters.BattleCharacter;

import java.util.ArrayList;

public class Fireball extends Skill{

    public Fireball(BattleCharacter parent){
        super(parent, "Fireball", 5, true);
    }

    public void doSkill
            (BattleCharacter currentPlayer, BattleCharacter targetPlayer, ArrayList playerArray, ArrayList enemyArray) {
        System.out.println("Fireball!");
    }

}
