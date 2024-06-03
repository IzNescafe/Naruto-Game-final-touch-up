package ui;

import static utilz.constants.ANI_SPEED;
import static utilz.constants.UI.Buttons.*;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import utilz.LoadSave;

public class ChoiceButton {
	
//	private static Player player;
	private int xPos, yPos, rowIndex;
	public int code;
	private int xOffsetCenter = B_WIDTH/2;
	private Gamestate state;
	private BufferedImage[][] animations;
	private boolean mouseOver, mousePressed;
	private Rectangle bounds;
	private int aniTick, aniIndex;
	
	public ChoiceButton(int xPos, int yPos, int rowIndex, Gamestate state) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.rowIndex = rowIndex;
		this.state = state;
		loadImgs();
		initBounds();
	}
	

	public ChoiceButton() {
		
	}


	private void initBounds() {
		bounds = new Rectangle(xPos - xOffsetCenter, yPos, C_WIDTH, C_HEIGHT);
		
	}


	private void loadImgs() {
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.CHOICE_BUTTONS);

		animations = new BufferedImage[4][6];
		for (int j = 0; j < animations.length; j++)
			for (int i = 0; i < animations[j].length; i++)
				animations[j][i] = img.getSubimage(i * C_WIDTH_DEFAULT, j * C_HEIGHT_DEFAULT,  C_WIDTH_DEFAULT, C_HEIGHT_DEFAULT);
	}

	public void draw(Graphics g) {
		g.drawImage(animations[rowIndex][aniIndex], xPos - xOffsetCenter, yPos, C_WIDTH, C_HEIGHT , null);
	}
	

	public void update() {
		if(mouseOver) {
			updateAnimationTick();
		}
		if(mousePressed) {
//			setCode(rowIndex);
		}
		
	}
	
//	public void render(Graphics g) {
//		g.drawImage(imgs[rowIndex][aniIndex], xPos - xOffsetCenter, yPos, C_WIDTH, C_HEIGHT , null);
//	}
	
	private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= ANI_SPEED) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= 6) {
				aniIndex = 0;
			}

		}
		
	}

	public int getRowIndex() {
		return rowIndex;
	}
	
	public int getCode() {
		System.out.println("code : "+code);
		return code;
	}

	public void setCode(int rowIndex) {
		System.out.println("Setting code to: " + rowIndex);
		this.code = rowIndex;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}
	
	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}
	
	public boolean isMousePressed() {
		return mousePressed;
	}
	
	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void applyGamestate() {
		Gamestate.state = state;
	}

	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}
	public Gamestate getState() {
		return state;
	}

}
