package io.github.mstraughan86.dungeon2.Scenes;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.mstraughan86.dungeon2.StageController;
import io.github.mstraughan86.dungeon2.UIFactory;

public class StageLoadMenu extends Stage{
    TextButton saveButton1;
    TextButton saveButton2;
    TextButton saveButton3;
    TextButton copyButton;
    TextButton deleteButton;
    TextButton cancelButton;


    public StageLoadMenu(Viewport viewport) {
        super(viewport);

        createEntities();
        createActions();
        createListeners();
        createInputs();
    }

    private void createEntities() {

        saveButton1 = UIFactory.textButton("SAVE 1");
        saveButton2 = UIFactory.textButton("SAVE 2");
        saveButton3 = UIFactory.textButton("SAVE 3");
        cancelButton = UIFactory.textButton("CANCEL");
        copyButton = UIFactory.textButton("COPY");
        deleteButton = UIFactory.textButton("DELETE");

        saveButton1.setSize(160,50);
        saveButton2.setSize(160,50);
        saveButton3.setSize(160,50);
        cancelButton.setSize(160,50);
        copyButton.setSize(160,50);
        deleteButton.setSize(160,50);

        saveButton1.setPosition(240,350);
        saveButton2.setPosition(240,300);
        saveButton3.setPosition(240,250);
        copyButton.setPosition(240,200);
        deleteButton.setPosition(240,150);
        cancelButton.setPosition(240,100);

        this.addActor(saveButton1);
        this.addActor(saveButton2);
        this.addActor(saveButton3);
        this.addActor(copyButton);
        this.addActor(deleteButton);
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