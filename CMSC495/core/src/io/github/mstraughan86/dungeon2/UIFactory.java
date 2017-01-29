package io.github.mstraughan86.dungeon2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class UIFactory {
	//	NOTES:
	//	Possibly add different button sizes = small, medium, large.
	//	Create make() param and case logic for sizes

	//RENAME THIS CLASS TO UIFactory
	//Create Unique Functions for: TextButton (rename make() ), and other stuff.

	private static Skin skin = new Skin(Gdx.files.internal("UI/uiskin.json"));

	public static TextButton textButton(String name) {
		return new TextButton(name, skin, "custom");
	}

	public static TextButton textButton(String name, String style) {
		return new TextButton(name, skin, style);
	}

	public static SplitPane splitPane(Actor actor1, Actor actor2) {
		return new SplitPane(actor1, actor2, false, skin);
	}

	public static Label	label(String string) {
		return new Label(string, skin);
	}
}