package io.github.mstraughan86.dungeon2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.mstraughan86.dungeon2.Scenes.*;

public enum StageController {
	INSTANCE;

	public OrthographicCamera camera;
	public ScreenViewport viewport;

	public StageMainMenu mainMenu;
	public StageDevLevel devLevel;
	public StageNewLevel newLevel;
	public StageLoadMenu loadLevel;
	public StageSettingMenu settingMenu;
	public BattleController battleTestLevel;
	public StageSewerLevel sewerLevel;
	public StageOverworld overworld;
	public StageCaveLevel caveLevel;
	public StageDesertLevel desertLevel;
	public StageInn inn;


	//add new stages here
	
	public Stage currentStage;
	public Stage previousStage;
	//TO ADD: maybe add universal stages... such as pause menu, escape menu, overlay menu?
	
	StageController() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 640,480);
		viewport = new ScreenViewport();
		viewport.setCamera(camera);

		mainMenu = new StageMainMenu(viewport);
		devLevel = new StageDevLevel(viewport);
		newLevel = new StageNewLevel(viewport);
		loadLevel = new StageLoadMenu(viewport);
		settingMenu = new StageSettingMenu(viewport);
		battleTestLevel = new BattleController(viewport);
		sewerLevel = new StageSewerLevel(viewport);
		overworld = new StageOverworld(viewport);
		caveLevel = new StageCaveLevel(viewport);
		desertLevel = new StageDesertLevel(viewport);
		inn = new StageInn(viewport);

	}
	
	public Stage getStage() {
		return currentStage;
	}
	
	public void setStage(Stage stage) {
		previousStage = currentStage;
		currentStage = stage;
		Gdx.input.setInputProcessor(currentStage);
	}
}
