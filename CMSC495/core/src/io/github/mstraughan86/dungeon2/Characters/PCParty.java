package io.github.mstraughan86.dungeon2.Characters;

import io.github.mstraughan86.dungeon2.Characters.PC.AlarmKnight;
import io.github.mstraughan86.dungeon2.Characters.PC.Healer;
import io.github.mstraughan86.dungeon2.Characters.PC.PC;
import io.github.mstraughan86.dungeon2.Characters.PC.Pyromancer;

public enum PCParty {
    INSTANCE;

    PC player1, player2, player3;
    PC[] party = new PC[3];

    PCParty() {
        this.player1 = new AlarmKnight();
        this.player2 = new Pyromancer();
        this.player3 = new Healer();

        party[0] = this.player1;
        party[1] = this.player2;
        party[2] = this.player3;
    }

    public PC[] getParty() {
        return party;
    }

    //not ready
    public boolean addMember(PC m) {
        if (player2 == null) {
            player2 = m;
        } else if (player3 == null){
            player3 = m;
        } else {
            // party is full
            return false;
        }
        return true;
    }

    //not ready
    public void removeMember(BattleCharacter m) {

    }

    //not ready
    public void changePosition(PC p1, PC p2) {
        // i dont think this will actually change the party position

        PC temp = p1;
        p1 = p2;
        p2 = temp;
    }
}
