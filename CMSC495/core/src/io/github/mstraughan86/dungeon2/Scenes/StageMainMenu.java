package io.github.mstraughan86.dungeon2.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.mstraughan86.dungeon2.Entity;
import io.github.mstraughan86.dungeon2.StageController;
import io.github.mstraughan86.dungeon2.UIFactory;

public class StageMainMenu extends Stage{

	Entity background;
	TextButton battleTestButton;
	TextButton devGameButton;
	TextButton newGameButton;
	TextButton loadGameButton;
	TextButton settingsButton;
	TextButton exitButton;
	InputListener input;
	Music mainTheme = Gdx.audio.newMusic(Gdx.files.internal("Music/Loop_1.ogg"));
	
	public StageMainMenu(Viewport viewport) {
		super(viewport);
		createEntities();
		createActions();
		createListeners();
		createInputs();
	}
	
	public void nextStage() {
		
	}

	private void createEntities() {

		background = new Entity("Title Screen 1.png", 0,0);

		battleTestButton = UIFactory.textButton("BATTLE");
		devGameButton = UIFactory.textButton("DEVELOPER MENU");
		newGameButton = UIFactory.textButton("NEW GAME");
		loadGameButton = UIFactory.textButton("LOAD GAME");
		settingsButton = UIFactory.textButton("SETTINGS");
		exitButton = UIFactory.textButton("EXIT");

		battleTestButton.setSize(160, 50);
		devGameButton.setSize(160, 50);
		newGameButton.setSize(160, 50);
		loadGameButton.setSize(160, 50);
		settingsButton.setSize(160, 50);
		exitButton.setSize(160, 50);

		battleTestButton.setPosition(420,270);
		devGameButton.setPosition(420,220);
		newGameButton.setPosition(420,170);
		loadGameButton.setPosition(420,120);
		settingsButton.setPosition(420,70);
		exitButton.setPosition(420,20);

		this.addActor(background);

		this.addActor(battleTestButton);
		this.addActor(devGameButton);
		this.addActor(newGameButton);
		this.addActor(loadGameButton);
		this.addActor(settingsButton);
		this.addActor(exitButton);

		mainTheme.play();
	}

	private void createActions() {
		
	}
	
	private void createListeners() {

		battleTestButton.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				event.cancel();
				StageController.INSTANCE.setStage(StageController.INSTANCE.battleTestLevel);
				mainTheme.dispose();
			}
		});

		devGameButton.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				event.cancel();
				StageController.INSTANCE.setStage(StageController.INSTANCE.devLevel);
				mainTheme.dispose();
			}
		});

		newGameButton.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				event.cancel();
				StageController.INSTANCE.setStage(StageController.INSTANCE.overworld);
				mainTheme.dispose();
			}
		});

		loadGameButton.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				event.cancel();
				StageController.INSTANCE.setStage(StageController.INSTANCE.loadLevel);
				mainTheme.dispose();
			}
		});

		settingsButton.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				event.cancel();
				StageController.INSTANCE.setStage(StageController.INSTANCE.settingMenu);
				mainTheme.dispose();
			}
		});

		exitButton.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				event.cancel();
				Gdx.app.exit();
			}
		});
	}

	private void createInputs() {
		input = new InputListener() {
			public boolean keyDown(InputEvent event, int keycode) {
				if(keycode == Input.Keys.UP) {
					System.out.println("Just pressed the UP Key");
				}
				return true;

			}

			public boolean keyUp(InputEvent event, int keycode) {
				if(keycode == Input.Keys.DOWN) {
					System.out.println("Just released the DOWN Key");
				}
				return true;
			}

		};

		addListener(input);
	}

}

