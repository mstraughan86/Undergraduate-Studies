package io.github.mstraughan86.dungeon2.Characters.Skills;

import java.util.ArrayList;

public enum SkillList {
    INSTANCE;

    public ArrayList<Skill> list = new ArrayList();

    SkillList() {
        //list.add(0, new Attack());
        //list.add(1, new Defend());
        //list.add(2, new Flee());
        //list.add(3, new Cure());
        //list.add(4, new Fireball());
        //list.add(5, new Whirlwind());
    }

    public Skill get(int i) {
        return list.get(i);
    }

}
