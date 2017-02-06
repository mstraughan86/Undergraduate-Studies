package io.github.mstraughan86.dungeon2.Characters.Skills;

import io.github.mstraughan86.dungeon2.Characters.BattleCharacter;

import java.util.ArrayList;

public class Cure extends Skill {

    public Cure(BattleCharacter parent){
        super(parent, "Cure", 2, true);
    }

    public void doSkill
            (BattleCharacter currentPlayer, BattleCharacter targetPlayer, ArrayList playerArray, ArrayList enemyArray) {
        System.out.println("Cure!");
    }

}
