package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import main.Game;

public abstract class Entity {

	protected float x, y;
	protected int width, height;
	protected Rectangle2D.Float hitbox, topHitBox, bottomHitbox;
	protected int aniTick, aniIndex;
	protected int state;
	protected float airSpeed;
	protected boolean inAir = false;
	protected int maxHealth;
	protected int currentHealth;
	protected Rectangle2D.Float attackBox;
	protected float walkSpeed = 1.0f * Game.SCALE;

	public Entity(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

	}

	protected void drawHitBoxes(Graphics g, int lvlOffset) {
        // Top hitbox debugging
        g.setColor(java.awt.Color.RED);
        g.drawRect((int) topHitBox.x - lvlOffset, (int) topHitBox.y, (int) topHitBox.width, (int) topHitBox.height);

        // Bottom hitbox debugging
        g.setColor(java.awt.Color.GREEN);
        g.drawRect((int) bottomHitbox.x - lvlOffset, (int) bottomHitbox.y, (int) bottomHitbox.width, (int) bottomHitbox.height);
        
        // actual working hitbox for jumping
        g.setColor(java.awt.Color.pink);
        g.drawRect((int) hitbox.x - lvlOffset,(int) hitbox.y,(int) hitbox.width,(int) hitbox.height);

        
    }
	
	protected void drawAttackBox(Graphics g, int xlvlOffset) {
		g.setColor(Color.red);
		g.drawRect((int) attackBox.x - xlvlOffset,(int)  attackBox.y, (int) attackBox.width, (int) attackBox.height);
		
	}

    protected void initHitBoxes(float width, float height) {
        topHitBox = new Rectangle2D.Float(x,  y + height*2 , (int)(width * Game.SCALE), (int)(height * Game.SCALE));
        bottomHitbox = new Rectangle2D.Float(x, y + height*2 , (int)(width * Game.SCALE), (int)(height * Game.SCALE));
        hitbox = new Rectangle2D.Float(x, y, (int)(width * Game.SCALE), (int)(height * Game.SCALE));
    }
    
    public int getState() {
    	return state;
    }
    
    public int getAniIndex() {
    	return aniIndex;
    }
    
    public Rectangle2D.Float getHitbox(){
    	return hitbox;
    }

    public Rectangle2D.Float getTopHitBox() {
        return topHitBox;
    }

    public Rectangle2D.Float getBottomHitBox() {
        return bottomHitbox;
    }
    
    public int getCurrentHealth() {
    	return currentHealth;
    }
}