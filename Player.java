package Mancala;

public class Player {
	
	private String name;
	private Pit[] pits;
	private Mancala mancala;
	
	public Player(String name) {
		
		this.name= name;
		pits = new Pit[6];
		
		for (int i = 0; i < pits.length; i++) {
			pits[i] = new Pit();
		}
		mancala = new Mancala();		
	}
	
	public String getName() {
		
		return name;
	}
	
	public int getMancalaPebbles() {
		return mancala.getPebbles();
	}
	
	public int getPebbles(int position) {
		
		return pits[position].getPebbles();
	}
	
	public void addPebble(int position) {
		pits[position].addPebble();
	}
	
	public void emptyPit(int position) {
		
		pits[position].emptyPit();
	}
	
	public void addToMancala(int captured) {
		
		mancala.addToMancala(captured);
	}
	
	public boolean isPitEmpty(int position) {
		
		return pits[position].isEmpty();
	}
	
	public boolean emptyPits() {
		
		for (int i = 0; i < pits.length; i++) {
			
			if (pits[i].getPebbles() > 0) {
				return false;
			}
		}
		
		return true;
	}
	
	public int pebblesSum() {
		
		int sum = 0;
		
		for (int i = 0; i < pits.length; i++) {
			
			sum += pits[i].getPebbles();
		}
		return sum;
	}
	
	@Override
	public String toString() {
		
		StringBuilder str = new StringBuilder();
		
		for (int i = 0; i < pits.length; i++) {
			
			str.append(pits[i].getPebbles() + "  ");
		}		
		return str.toString();
	}
	

	
	
}
