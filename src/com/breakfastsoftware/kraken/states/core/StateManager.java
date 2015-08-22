package com.breakfastsoftware.kraken.states.core;

import java.awt.Graphics2D;

public class StateManager {

	private State state;
	
	public StateManager(State state) {
		this.state = state;
	}
	
	public void update() {
		state.update();
	}
	
	public void render(Graphics2D g) {
		state.render(g);
	}
	
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
}