package io.github.mstraughan86.dungeon2.Scenes;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.mstraughan86.dungeon2.Characters.PC.DevPC;
import io.github.mstraughan86.dungeon2.Entity;
import io.github.mstraughan86.dungeon2.StageController;
import io.github.mstraughan86.dungeon2.UIFactory;

public class StageDevLevel extends Stage{

    TextButton cancelButton;
    //Entity testCharacter;
    InputListener input;

    public StageDevLevel(Viewport viewport) {
        super(viewport);

        createEntities();
        createActions();
        createListeners();
        createInputs();
    }

    public void nextStage() {

    }

    private void createEntities() {
        cancelButton = UIFactory.textButton("CANCEL");

        cancelButton.setSize(160,50);
        cancelButton.setPosition(20,20);

        this.addActor(cancelButton);

        //testCharacter = new Entity("dude", 1, 1);

        DevPC test = new DevPC();

        this.addActor(test);
        this.setKeyboardFocus(test);

    }

    private void createActions() {
        RunnableAction test = new RunnableAction();
        test.setRunnable(new Runnable() {
            @Override
            public void run() {
                System.out.println("Running this action!");
                System.out.println("alternating this action!");
            }
        });
        this.addAction(test);
    }

    private void createListeners() {
        cancelButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                event.cancel();
                StageController.INSTANCE.setStage(StageController.INSTANCE.previousStage);
            }
        });
    }

    private void createInputs() {
       /*
        input = new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if(keycode == Input.Keys.ESCAPE) {
					MoveByAction mba = new MoveByAction();
					mba.setAmount(100f, 0f);
					mba.setDuration(5f);
				}
				return true;

				MyActor.this.addAction(mba);
			}

            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if(keycode == Input.Keys.ESCAPE) {

                }
                return true;
            }

        };

        addListener(input);

        */
    }
}
