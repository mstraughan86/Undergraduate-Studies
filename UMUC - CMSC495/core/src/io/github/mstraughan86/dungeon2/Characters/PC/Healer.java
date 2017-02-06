package io.github.mstraughan86.dungeon2.Characters.PC;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.mstraughan86.dungeon2.Characters.Skills.*;

public class Healer extends PC{

    public Healer() {
        super(new Sprite(healer));
        this.name = "Healer";
        this.currentHP = 80;
        this.maxHP = 80;
        this.currentMP = 160;
        this.maxMP = 160;
        this.str = 5;
        this.def = 5;
        this.speed = 5;
        turnPoints = 0;

        this.primarySkill = new Attack(this);
        this.secondarySkill = new Defend(this);
        this.supportSkill = new Flee(this);

        this.skillsName = "Recover";
        this.skills.add(0, new Cure(this));
        this.skills.add(1, new Cure(this));
    }

}
