package io.github.mstraughan86.dungeon2.Scenes;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.mstraughan86.dungeon2.StageController;
import io.github.mstraughan86.dungeon2.UIFactory;

public class StageSettingMenu extends Stage{

    TextButton graphicsButton;
    TextButton audioButton;
    TextButton controlsButton;
    TextButton gameplayButton;
    TextButton cancelButton;

    public StageSettingMenu(Viewport viewport) {
        super(viewport);

        createEntities();
        createActions();
        createListeners();
        createInputs();
    }

    private void createEntities() {

        graphicsButton = UIFactory.textButton("GRAPHICS");
        audioButton = UIFactory.textButton("AUDIO");
        controlsButton = UIFactory.textButton("CONTROLS");
        gameplayButton = UIFactory.textButton("GAME PLAY");
        cancelButton = UIFactory.textButton("CANCEL");

        graphicsButton.setSize(160,50);
        audioButton.setSize(160,50);
        controlsButton.setSize(160,50);
        gameplayButton.setSize(160,50);
        cancelButton.setSize(160,50);

        graphicsButton.setPosition(20,325);
        audioButton.setPosition(20,275);
        controlsButton.setPosition(20,225);
        gameplayButton.setPosition(20,175);
        cancelButton.setPosition(20,125);

        this.addActor(graphicsButton);
        this.addActor(audioButton);
        this.addActor(controlsButton);
        this.addActor(gameplayButton);
        this.addActor(cancelButton);
    }

    private void createActions() {

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

    }
}