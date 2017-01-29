package io.github.mstraughan86.dungeon2.Characters.PC;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.mstraughan86.dungeon2.Characters.Skills.*;

public class Pyromancer extends PC{

    public Pyromancer() {
        super(new Sprite(pyromancer));
        this.name = "Pyromancer";
        this.currentHP = 100;
        this.maxHP = 100;
        this.currentMP = 100;
        this.maxMP = 100;
        this.str = 5;
        this.def = 5;
        this.speed = 5;
        turnPoints = 0;

        this.primarySkill = new Attack(this);
        this.secondarySkill = new Defend(this);
        this.supportSkill = new Flee(this);

        this.skillsName = "Fyre";
        this.skills.add(0, new Fireball(this));
        this.skills.add(1, new Fireball(this));
    }

}