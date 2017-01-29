package io.github.mstraughan86.dungeon2.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.mstraughan86.dungeon2.BattleController;
import io.github.mstraughan86.dungeon2.Characters.Items.Item;
import io.github.mstraughan86.dungeon2.Characters.Skills.Skill;

import java.util.ArrayList;

public class BattleCharacter extends Actor {

    private static final float BLINK_FRAME_DURATION = 0.06f;

    protected boolean cursorOverSprite;
    protected float stateTime;
    protected TextureRegion[] tempFrames;
    protected Pixmap pixmap;
    protected Animation blink;
    protected TextureRegion currentFrame;

    protected int currentHP, maxHP, currentMP, maxMP, str, def, speed, turnPoints;
    protected String name;

    protected ArrayList<Skill> skills = new ArrayList<Skill>();

    protected Sprite sprite;

    public BattleCharacter(Sprite sprite) {
        this.sprite = sprite;
        setBounds(getX(), getY(), this.sprite.getWidth(), this.sprite.getHeight());

        tempFrames = new TextureRegion[2];

        pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.rgba8888(0,0,0,0));
        pixmap.fill();
        tempFrames[0] = sprite;
        tempFrames[1] = new TextureRegion(new Texture(pixmap));

        blink = new Animation(BLINK_FRAME_DURATION, tempFrames);
        currentFrame = sprite;

        cursorOverSprite = false;
        this.addListener(new HoverListener(this));
    }

    public int getStr() {
        return str;
    }

    public int getDef() {
        return def;
    }

    public int getSpeed() {
        return speed;
    }

    public void stepTurnPoints() {
        this.turnPoints += speed;
    }

    public int getTurnPoints() {
        return this.turnPoints;
    }

    public void setTurnPoints(int points) {
        this.turnPoints = points;
    }

    public void addTurnPoints(int points) { this.turnPoints += points;}

    public String getName() {
        return name;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setCurrentHP(int i) { currentHP = i; }

    public void resetCurrentHP() {
        currentHP = maxHP;
    }

    public int getCurrentMP() {
        return currentMP;
    }

    public void setCurrentMP(int i) { currentMP = i; }

    public int getMaxMP() {
        return maxMP;
    }

    public void resetCurrentMP() {
        currentMP = maxMP;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public TextureRegion getSprite() {
        return sprite;
    }

    public boolean getCursorOverSprite() {
        return cursorOverSprite;
    }

    public void setCursorOverSprite(boolean bool) {
        cursorOverSprite = bool;
    }

    class HoverListener extends ClickListener {
        BattleCharacter parent;

        public HoverListener(BattleCharacter parent) {
            this.parent = parent;
        }

        @Override
        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
            if (parent instanceof BattleCharacter){
                ((BattleCharacter) parent).setCursorOverSprite(true);
            }
        }

        @Override
        public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
            if (parent instanceof BattleCharacter){
                ((BattleCharacter) parent).setCursorOverSprite(false);
            }
        }


        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button){
            if (((BattleController) parent.getStage()).getWaitForPlayerInput()) {
                if (((BattleController) parent.getStage()).getNeedTarget()) {
                    if (!((BattleController) parent.getStage()).getGotTarget()) {
                        ((BattleController) parent.getStage()).setGotTarget(true);
                        ((BattleController) parent.getStage()).setTarget(parent);
                    }
                }
            }
        }


        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
            return true;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        stateTime += Gdx.graphics.getDeltaTime();

        if (getStage() instanceof BattleController) {
            if (((BattleController) getStage()).getWaitForPlayerInput()) {
                if (((BattleController) getStage()).getNeedTarget()) {
                    if (!((BattleController) getStage()).getGotTarget()) {
                        if (cursorOverSprite) {
                            currentFrame = blink.getKeyFrame(stateTime, true);
                        } else {
                            currentFrame = sprite;
                        }
                    }
                }
            } else {
                currentFrame = sprite;
            }
        }

        batch.draw(currentFrame, getX(), getY());
    }
}