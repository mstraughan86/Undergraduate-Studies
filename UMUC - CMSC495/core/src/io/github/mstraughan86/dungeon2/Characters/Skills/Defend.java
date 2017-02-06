package io.github.mstraughan86.dungeon2.Characters.Skills;

import io.github.mstraughan86.dungeon2.BattleController;
import io.github.mstraughan86.dungeon2.Characters.BattleCharacter;

import java.util.ArrayList;

public class Defend extends Skill{

    public Defend(BattleCharacter parent){
        super(parent, "Defend", 0, false);
    }

    public void doSkill
            (BattleCharacter currentPlayer, BattleCharacter targetPlayer, ArrayList playerArray, ArrayList enemyArray) {

        if (currentPlayer.getStage() instanceof BattleController) {
            ((BattleController) currentPlayer.getStage()).textOutput(
                    currentPlayer.getName() + " is defending!");
        }
    }

}
