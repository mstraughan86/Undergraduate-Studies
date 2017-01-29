package io.github.mstraughan86.dungeon2.Characters.PC;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.mstraughan86.dungeon2.Characters.Skills.*;

public class AlarmKnight extends PC {

    public AlarmKnight() {
        super(new Sprite(alarmKnight));
        this.name = "Alarm Knight";
        this.currentHP = 120;
        this.maxHP = 120;
        this.currentMP = 60;
        this.maxMP = 60;
        this.str = 1000;
        this.def = 10;
        this.speed = 5;
        turnPoints = 0;

        this.primarySkill = new Attack(this);
        this.secondarySkill = new Defend(this);
        this.supportSkill = new Flee(this);

        this.skillsName = "Alert";
        this.skills.add(0, new Whirlwind(this));
        this.skills.add(1, new Whirlwind(this));
    }

}
