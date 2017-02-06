package io.github.mstraughan86.dungeon2.Characters.NPC;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.mstraughan86.dungeon2.Characters.BattleCharacter;
import io.github.mstraughan86.dungeon2.Characters.Skills.Attack;
import io.github.mstraughan86.dungeon2.Characters.Skills.Defend;
import io.github.mstraughan86.dungeon2.Characters.Skills.Fireball;

import java.util.ArrayList;
import java.util.Random;

public class Dwarf extends NPC {

    public Dwarf() {
        super(new Sprite(dwarf));

        this.name = "Dwarf";
        this.currentHP = 100;
        this.maxHP = 100;
        this.currentMP = 100;
        this.maxMP = 100;
        this.str = 8;
        this.def = 8;
        this.speed = 5;
        this.turnPoints = 0;

        this.skills.add(0, new Attack(this));
        this.skills.add(1, new Defend(this));
    }

    public void action(
            BattleCharacter currentPlayer, ArrayList playerArray, ArrayList enemyArray) {
        Random rand = new Random();
        int n = rand.nextInt(5) + 1;
        if (n > 2) {
            n = rand.nextInt(3);
            skills.get(0).doSkill(currentPlayer, (BattleCharacter) playerArray.get(n), playerArray, enemyArray);
        } else {
            skills.get(1).doSkill(currentPlayer, (BattleCharacter) playerArray.get(n), playerArray, enemyArray);
        }
    }
}
