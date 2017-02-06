package io.github.mstraughan86.dungeon2.Characters.PC;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.mstraughan86.dungeon2.Characters.BattleCharacter;
import io.github.mstraughan86.dungeon2.Characters.Items.Item;
import io.github.mstraughan86.dungeon2.Characters.Skills.Skill;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class PC extends BattleCharacter {

    static TextureAtlas pcSprites = new TextureAtlas(Gdx.files.internal(
            "CharacterSpritesheets/PCSprites.atlas"));
    static TextureRegion alarmKnight = pcSprites.findRegion("00");
    static TextureRegion shadeKnight = pcSprites.findRegion("01");
    static TextureRegion flaxKnight = pcSprites.findRegion("02");
    static TextureRegion siltMage = pcSprites.findRegion("03");
    static TextureRegion cryomancer = pcSprites.findRegion("04");
    static TextureRegion pyromancer = pcSprites.findRegion("05");
    static TextureRegion statue = pcSprites.findRegion("06");
    static TextureRegion uniform = pcSprites.findRegion("07");
    static TextureRegion samurai = pcSprites.findRegion("08");
    static TextureRegion redSoldier = pcSprites.findRegion("09");
    static TextureRegion prophet = pcSprites.findRegion("10");
    static TextureRegion geomancer = pcSprites.findRegion("11");
    static TextureRegion childOfDark = pcSprites.findRegion("12");
    static TextureRegion healer = pcSprites.findRegion("13");
    static TextureRegion archeologist = pcSprites.findRegion("14");
    static TextureRegion royalBanner = pcSprites.findRegion("15");

    Skill primarySkill;
    Skill secondarySkill;
    Skill supportSkill;

    String skillsName;

    ArrayList<Item> items = new ArrayList<Item>();

    Boolean alive = true;

    public PC(Sprite sprite) {
        super(sprite);
    }

    public Skill getPrimarySkill() {
        return primarySkill;
    }

    public Skill getSecondarySkill() {
        return secondarySkill;
    }

    public Skill getSupportSkill() {
        return supportSkill;
    }

    public String getSkillsName() {
        return skillsName;
    }

    public boolean getAlive() {
        return alive;
    }

    public void setAlive(Boolean bool) {
        alive = bool;
    }

}
