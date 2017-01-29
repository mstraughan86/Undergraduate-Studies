package io.github.mstraughan86.dungeon2.Characters.Skills;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import io.github.mstraughan86.dungeon2.BattleController;
import io.github.mstraughan86.dungeon2.Characters.BattleCharacter;

import java.util.ArrayList;

public abstract class Skill {

    BattleCharacter parent;
    String name;
    int cost;
    boolean needsTarget;

    public Skill(BattleCharacter parent, String name, int cost, boolean needsTarget) {
        this.parent = parent;
        this.name = name;
        this.cost = cost;
        this.needsTarget = needsTarget;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public boolean getNeedsTarget() {
        return needsTarget;
    }

    protected void getMultiTarget() {
        System.out.println("Getting Multiple Targets!");
    }

    public abstract void doSkill
            (BattleCharacter currentPlayer, BattleCharacter targetPlayer, ArrayList playerArray, ArrayList enemyArray);

}
