package io.github.mstraughan86.dungeon2.Characters.NPC;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.mstraughan86.dungeon2.Characters.BattleCharacter;

import java.util.ArrayList;

public abstract class NPC extends BattleCharacter{

    static TextureAtlas enemySprites = new TextureAtlas(Gdx.files.internal(
            "CharacterSpritesheets/EnemySprites.atlas"));
    static TextureRegion cerberus = enemySprites.findRegion("cerberus");
    static TextureRegion dwarf = enemySprites.findRegion("dwarf");
    static TextureRegion goblin = enemySprites.findRegion("goblin");
    static TextureRegion golem = enemySprites.findRegion("golem");
    static TextureRegion gryphon = enemySprites.findRegion("gryphon");
    static TextureRegion minotaur = enemySprites.findRegion("minotaur");
    static TextureRegion slime = enemySprites.findRegion("slime");
    static TextureRegion troll = enemySprites.findRegion("troll");
    static TextureRegion werewolf = enemySprites.findRegion("werewolf");
    static TextureRegion wyvern = enemySprites.findRegion("wyvern");

    public NPC(Sprite sprite) {
        super(sprite);
    }

    public abstract void action(
            BattleCharacter currentPlayer, ArrayList playerArray, ArrayList enemyArray);

}
