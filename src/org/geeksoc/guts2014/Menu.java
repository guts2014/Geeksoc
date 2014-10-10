package org.geeksoc.guts2014;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState {
	
	private int id;
	private static int buttonWidth = 300;
	private static int buttonHeight = 50;
	private Rectangle startButton;
	
	public Menu(int idee) {
		id=idee;
	}

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		int x1 = (container.getWidth()/2)-(buttonWidth/2);
		startButton=new Rectangle(x1, 100, buttonWidth, buttonHeight);
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.fill(startButton);
	}

	public int getID() {
		return id;
	}

}
