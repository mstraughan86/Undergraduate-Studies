package io.github.mstraughan86.dungeon2.Characters;

import io.github.mstraughan86.dungeon2.Characters.NPC.Dwarf;
import io.github.mstraughan86.dungeon2.Characters.NPC.NPC;

public class EnemyParty {

    NPC enemy1, enemy2, enemy3, enemy4;
    NPC[] party = new NPC[4];

    public EnemyParty() {
        this.enemy1 = new Dwarf();
        this.enemy2 = new Dwarf();
        this.enemy3 = new Dwarf();
        this.enemy4 = new Dwarf();

        party[0] = this.enemy1;
        party[1] = this.enemy2;
        party[2] = this.enemy3;
        party[3] = this.enemy4;
    }

    public NPC[] getParty() {
        return party;
    }

    //not ready
    public void addMember(BattleCharacter m) {
    }

    //not ready
    public void removeMember(BattleCharacter m) {

    }

    //not ready
    public void changePosition(BattleCharacter p1, BattleCharacter p2) {
        // i dont think this will actually change the party position

        BattleCharacter temp = p1;
        p1 = p2;
        p2 = temp;
    }
}
