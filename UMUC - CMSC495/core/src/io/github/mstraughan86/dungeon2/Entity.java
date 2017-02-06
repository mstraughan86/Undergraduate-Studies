package io.github.mstraughan86.dungeon2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Entity extends Actor {
	
	Texture texture;
	Sprite sprite;
	
	//Animation animation;
	//TextureRegion[] animationFrames;
	//float elapsedTime;
	
	public Entity(String path, int x, int y) {
		this.sprite = new Sprite(new Texture(Gdx.files.internal(path)));
		sprite.setX(x);
		sprite.setY(y);
		//sprite.setZIndex(z);
		
		setBounds(getX(), getY(), sprite.getWidth(), sprite.getHeight());
		
		//setTouchable(Touchable.disabled);
		//addListener(new InputListener())
		//Actor a = new Actor();
		
		//texture = new Texture(Gdx.files.internal(path));
		//TextureRegion[][] tempFrames = TextureRegion.split(texture, 128, 80);
		
		/*
		// alternative to setting the animationFrames array manually.
		//
		
		int index = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				animationFrames[index++] = tempFrames[j][i];
			}
		}
		 */
		
		//animationFrames = new TextureRegion[2];
		//animationFrames[0] = tempFrames[0][0];
		//animationFrames[1] = tempFrames[0][1];
		
		//animation = new Animation(1f/4f, animationFrames);
	}
	
	//public Entity() {
	//}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		sprite.draw(batch);
	}
	
	public void setTexture(String path) {
		this.sprite = new Sprite(new Texture(Gdx.files.internal(path)));
	}
	
	public void setXYZ(int x, int y, int z) {
		sprite.setX(x);
		sprite.setY(y);
		//sprite.setZIndex(z);
	}
	
	public void setAnimation() {
		
	}
	
	public void setAction() {
		
	}
}
