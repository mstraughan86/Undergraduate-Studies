package io.github.mstraughan86.dungeon2.Characters.Skills;

import io.github.mstraughan86.dungeon2.BattleController;
import io.github.mstraughan86.dungeon2.Characters.BattleCharacter;

import java.util.ArrayList;

public class Attack extends Skill {

    public Attack(BattleCharacter parent){
        super(parent, "Attack", 0, true);
    }

    public void doSkill
            (BattleCharacter currentPlayer, BattleCharacter targetPlayer, ArrayList playerArray, ArrayList enemyArray) {

        int damage = currentPlayer.getStr() - targetPlayer.getDef();
        if (damage < 0) {
            damage = 0;
        }
        targetPlayer.setCurrentHP(targetPlayer.getCurrentHP() - damage);
        if (targetPlayer.getCurrentHP() < 0) {
            targetPlayer.setCurrentHP(0);
        }

        if (currentPlayer.getStage() instanceof BattleController) {
            ((BattleController) currentPlayer.getStage()).textOutput(
                    currentPlayer.getName() + " is attacking " + targetPlayer.getName() + " for " + damage);
        }
    }
}
