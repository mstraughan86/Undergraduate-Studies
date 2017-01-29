package io.github.mstraughan86.dungeon2.Characters.PC;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DevPC extends Actor{

    private static final float WALK_FRAME_DURATION = 0.06f;

    private static final Set<Integer> WASD = new HashSet<Integer>(Arrays.asList(
            new Integer[] {29, 47, 32, 51}
    ));

    public enum State {
        IDLE, WALKING ,WAIT, ATTACK, HIT, DEFEND, DEAD
        //    WORLD STATES
        //IDLE is not walking
        //WALKING is walking

        //    BATTLE STATES
        //WAIT can be the same as IDLE
        //ATTACK can be the same as WALKING
        //HIT is being hit
        //DEFEND is defending
        //DEAD is dead state
    }

    public enum Direction {
        DOWN, LEFT, RIGHT, UP
    }

    private static TextureAtlas textureAtlas;
    private static TextureRegion downIdle;
    private static TextureRegion leftIdle;
    private static TextureRegion rightIdle;
    private static TextureRegion upIdle;
    private static Animation downWalk;
    private static Animation leftWalk;
    private static Animation rightWalk;
    private static Animation upWalk;
    private TextureRegion[] tempFrames;
    private TextureRegion currentFrame;

    State state;
    Direction direction;
    int isPressed;
    float stateTime;
    ArrayDeque<Direction> directionStack;

    InputListener input;

    float speed;

    public DevPC() {
        state = State.IDLE;
        direction = Direction.DOWN;
        isPressed = 0;
        stateTime = 0f;
        directionStack = new ArrayDeque();

        setX(100);
        setY(100);
        //z = 0;
        speed = 4f;

        createAnimation();
        createInputs();
        createChangeListener();
    }

    private void createAnimation() {
        //Textures
        textureAtlas = new TextureAtlas(Gdx.files.internal(
                "PC_Spritesheets/DevSprites.atlas"));

        //Idle Animation
        downIdle = textureAtlas.findRegion("01");
        leftIdle = textureAtlas.findRegion("04");
        rightIdle = textureAtlas.findRegion("07");
        upIdle = textureAtlas.findRegion("10");

        //Walk Animation
        tempFrames = new TextureRegion[3];
        for (int i = 0; i < 3; i++) {
            tempFrames[i] = textureAtlas.findRegion("0" + i);
        }
        downWalk = new Animation(WALK_FRAME_DURATION, tempFrames);

        tempFrames = new TextureRegion[3];
        for (int i = 0; i < 3; i++) {
            tempFrames[i] = textureAtlas.findRegion("0" + (i + 3));
        }
        leftWalk = new Animation(WALK_FRAME_DURATION, tempFrames);

        tempFrames = new TextureRegion[3];
        for (int i = 0; i < 3; i++) {
            tempFrames[i] = textureAtlas.findRegion("0" + (i + 6));
        }
        rightWalk = new Animation(WALK_FRAME_DURATION, tempFrames);

        tempFrames = new TextureRegion[3];
        tempFrames[0] = textureAtlas.findRegion("09");
        tempFrames[1] = textureAtlas.findRegion("10");
        tempFrames[2] = textureAtlas.findRegion("11");
        upWalk = new Animation(WALK_FRAME_DURATION, tempFrames);
    }

    private void createInputs() {
        input = new InputListener() {
            public boolean keyDown(InputEvent event, int keycode) {
                if (WASD.contains(keycode) ) {
                    state = State.WALKING;
                    isPressed++;
                }

                if(keycode == Input.Keys.W) {
                    directionStack.add(Direction.UP);

                }
                if(keycode == Input.Keys.A) {
                    directionStack.add(Direction.LEFT);

                }
                if(keycode == Input.Keys.S) {
                    directionStack.add(Direction.DOWN);

                }
                if(keycode == Input.Keys.D) {
                    directionStack.add(Direction.RIGHT);

                }
                return true;
            }

            public boolean keyUp(InputEvent event, int keycode) {
                if (WASD.contains(keycode) ) {
                    isPressed--;
                }

                if(directionStack.size() == 1) {
                    direction = directionStack.peek();
                }

                if(keycode == Input.Keys.W) {
                    directionStack.remove(Direction.UP);
                }
                if(keycode == Input.Keys.A) {
                    directionStack.remove(Direction.LEFT);
                }
                if(keycode == Input.Keys.S) {
                    directionStack.remove(Direction.DOWN);
                }
                if(keycode == Input.Keys.D) {
                    directionStack.remove(Direction.RIGHT);
                }

                if(isPressed == 0) {
                    state = State.IDLE;
                }

                return true;
            }
        };

        this.addListener(input);
    }

    private void createChangeListener() {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        stateTime += Gdx.graphics.getDeltaTime();

        if (state.equals(State.IDLE)){
            if (direction.equals(Direction.DOWN)) {
                currentFrame = downIdle;
            } if (direction.equals(Direction.LEFT)) {
                currentFrame = leftIdle;
            } if (direction.equals(Direction.RIGHT)) {
                currentFrame = rightIdle;
            } if (direction.equals(Direction.UP)) {
                currentFrame = upIdle;
            }
        } if (state.equals(State.WALKING)) {
            if (directionStack.peekLast().equals(Direction.DOWN)) {
                currentFrame = downWalk.getKeyFrame(stateTime, true);
                setY(getY() - speed);
                //y -= speed;
            } if (directionStack.peekLast().equals(Direction.LEFT)) {
                currentFrame = leftWalk.getKeyFrame(stateTime, true);
                setX(getX() - speed);
                //x -= speed;
            } if (directionStack.peekLast().equals(Direction.RIGHT)) {
                currentFrame = rightWalk.getKeyFrame(stateTime, true);
                setX(getX() + speed);
                //x += speed;
            } if (directionStack.peekLast().equals(Direction.UP)) {
                currentFrame = upWalk.getKeyFrame(stateTime, true);
                setY(getY() + speed);
                //y += speed;
            }
        }

        batch.draw(currentFrame, getX(), getY());

    }

}
