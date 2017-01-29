package io.github.mstraughan86.dungeon2.Characters.Skills;

import io.github.mstraughan86.dungeon2.Characters.BattleCharacter;

import java.util.ArrayList;

public class Whirlwind extends Skill {

    public Whirlwind(BattleCharacter parent){
        super(parent, "Whirlwind", 5, true);
    }

    public void doSkill
            (BattleCharacter currentPlayer, BattleCharacter targetPlayer, ArrayList playerArray, ArrayList enemyArray) {
        System.out.println("Whirlwind!");
    }

}
