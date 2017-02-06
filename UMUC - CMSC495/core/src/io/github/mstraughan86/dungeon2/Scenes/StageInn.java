package io.github.mstraughan86.dungeon2.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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
import com.badlogic.gdx.audio.Music;
import io.github.mstraughan86.dungeon2.Characters.PC.DevPC;
import io.github.mstraughan86.dungeon2.StageController;
import io.github.mstraughan86.dungeon2.UIFactory;

public class StageInn extends Stage {

    TextButton cancelButton;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;

    DevPC player;
    TiledMap map;
    Music TownMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/JRPG_town_loop.ogg"));


    public StageInn(Viewport viewport) {
        super(new ScreenViewport());
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 640, 480);
        getViewport().setCamera(camera);

        map = new TmxMapLoader().load("Levels/Inn.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        renderer.setView(camera);

        createEntities();
        createActions();
        createListeners();
        createInputs();
    }

    private void createEntities() {
        cancelButton = UIFactory.textButton("CANCEL");

        cancelButton.setSize(160, 50);
        cancelButton.setPosition(100, 10);

        this.addActor(cancelButton);

        player = new DevPC();
        player.setPosition(300, 20);

        this.addActor(player);
        this.setKeyboardFocus(player);
    }

    private void createActions() {

    }

    private void createListeners() {
        cancelButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                event.cancel();
                StageController.INSTANCE.setStage(StageController.INSTANCE.overworld);
                TownMusic.dispose();
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
        camera.position.x = MathUtils.clamp(camera.position.x, (camera.viewportWidth / 2), 672 - (camera.viewportWidth / 2));
        camera.position.y = MathUtils.clamp(camera.position.y, (camera.viewportHeight / 2), 480 - (camera.viewportHeight / 2));

        renderer.render();
        super.draw();

        TownMusic.play();

    }

}