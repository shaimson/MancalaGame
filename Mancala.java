package Mancala;

public class Mancala {
	
	private int pebbles;
	
	public Mancala() {
		
		pebbles = 0;
	}
	
	public void addPebble() {
		
		pebbles++;
	}
	
	public void addToMancala(int pebbles) {
		 
		this.pebbles += pebbles;
	}
	
	public int getPebbles() {
		
		return pebbles;
	}
	
	
	
	
}
