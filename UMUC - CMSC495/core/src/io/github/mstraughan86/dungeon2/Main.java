package io.github.mstraughan86.dungeon2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import io.github.mstraughan86.dungeon2.Scenes.*;

public class Main extends ApplicationAdapter {
	
	//called when application begins
	@Override
	public void create () {
		StageController.INSTANCE.setStage(StageController.INSTANCE.mainMenu);
	}
	
	//resizing the window.
	@Override
	public void resize (int width, int height) {
		//redo this with a new global viewport. Don't want a new viewport for every scene
		StageController.INSTANCE.getStage().getViewport().update(width, height, true);
	}

	//application looping method
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0.4f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//I believe that this will only allow one stage to be drawn at a time.
		//This can cause conflict if we are trying to manage multiple stages at once.
		//Cons: Unable to split the game display into multiple stages.
		//All stages must be fully encapsulated. Not so flexible.
		//Pros: Full encapsulation. Each stage MUST work by itself.
		//Simple to follow act() and draw() processes. One-at-a-time design.



		StageController.INSTANCE.getStage().act(Gdx.graphics.getDeltaTime());
		StageController.INSTANCE.getStage().draw();

		if (StageController.INSTANCE.getStage() instanceof BattleController) {
			((BattleController) StageController.INSTANCE.getStage()).battleLoop();
		}
		
		//Possible Solution: Create a dedicated .draw() handler class/function
		//Will also need a dedicated .act() handler class/function. Combine both?
	}

	//called when application ends
	@Override
	public void dispose () {
		StageController.INSTANCE.getStage().dispose();
	}

	//pausing the game.
	@Override
	public void pause() {}
	
	//resuming the game.
	@Override
	public void resume() {}
}
