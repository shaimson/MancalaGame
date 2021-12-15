package Mancala;

public class Pit {
	
	private int pebbles;
	
	public Pit() {
		
		pebbles = 4;
	}	
	
	public void addPebble() {
		pebbles++;
	}
	
	public void emptyPit() {
		pebbles = 0;
	}
	
	public int getPebbles() {
		
		return pebbles;
	}
	
	public boolean isEmpty() {
		
		if (pebbles == 0) 
			return true;
		else
			return false;
		
	}
	
	
}
	
	

