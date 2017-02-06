package io.github.mstraughan86.dungeon2.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.audio.*;
import io.github.mstraughan86.dungeon2.Characters.PC.DevPC;
import io.github.mstraughan86.dungeon2.StageController;
import io.github.mstraughan86.dungeon2.UIFactory;

public class StageOverworld extends Stage {

    TextButton cancelButton;
    TextButton desertButton;
    TextButton caveButton;
    TextButton sewerButton;
    TextButton innButton;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;

    DevPC player;
    TiledMap map;
    Music OverworldMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/JRPG_mainTheme.ogg"));

    public StageOverworld(Viewport viewport) {
        super(new ScreenViewport());
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 640, 480);
        getViewport().setCamera(camera);

        map = new TmxMapLoader().load("Levels/Overworld.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        renderer.setView(camera);

        createEntities();
        createActions();
        createListeners();
        createInputs();
    }

    private void createEntities() {
        cancelButton = UIFactory.textButton("CANCEL");
        desertButton = UIFactory.textButton("Desert");
        caveButton = UIFactory.textButton("Caves");
        sewerButton = UIFactory.textButton("Sewers");
        innButton = UIFactory.textButton("Inn");


        cancelButton.setSize(160, 50);
        cancelButton.setPosition(400, 700);
        desertButton.setSize(100, 50);
        desertButton.setPosition(600, 320);
        caveButton.setSize(100, 50);
        caveButton.setPosition(280, 650);
        sewerButton.setSize(100, 50);
        sewerButton.setPosition(240, 550);
        innButton.setSize(100, 50);
        innButton.setPosition(30, 610);

        this.addActor(cancelButton);
        this.addActor(desertButton);
        this.addActor(caveButton);
        this.addActor(sewerButton);
        this.addActor(innButton);

        player = new DevPC();
        player.setPosition(120,700);


        this.addActor(player);
        this.setKeyboardFocus(player);
    }

    private void createActions() {

    }

    private void createListeners() {
        cancelButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                event.cancel();
                StageController.INSTANCE.setStage(StageController.INSTANCE.mainMenu);
                OverworldMusic.dispose();
            }
        });

        caveButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor){
                event.cancel();
                StageController.INSTANCE.setStage(StageController.INSTANCE.caveLevel);
                OverworldMusic.dispose();
            }
        });

        desertButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor){
                event.cancel();
                StageController.INSTANCE.setStage(StageController.INSTANCE.desertLevel);
                OverworldMusic.dispose();
            }
        });

        sewerButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor){
                event.cancel();
                StageController.INSTANCE.setStage(StageController.INSTANCE.sewerLevel);
                OverworldMusic.dispose();
            }
        });

        innButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor){
                event.cancel();
                StageController.INSTANCE.setStage(StageController.INSTANCE.inn);
                OverworldMusic.dispose();
            }
        });

        }




    private void createInputs() {

    }

    @Override
    public void draw() {
        renderer.setView(camera);
        camera.position.x = player.getX();
        camera.position.y = player.getY();
        camera.position.x = MathUtils.clamp(camera.position.x, (camera.viewportWidth / 2), 800 - (camera.viewportWidth / 2));
        camera.position.y = MathUtils.clamp(camera.position.y, (camera.viewportHeight / 2), 800 - (camera.viewportHeight / 2));

        renderer.render();
        super.draw();


        OverworldMusic.play();
    }

}