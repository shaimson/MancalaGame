package Mancala;
import java.util.Scanner;

public class MancalaGame {
	
	private Player[] players;
	private Scanner input;
	
	public MancalaGame(String name1, String name2) {
		
		players = new Player[2];
		players[0] = new Player(name1);
		players[1] = new Player(name2);	
		
		input = new Scanner(System.in);
	}
	
	public int removePebbles(int pitPosition, int playerPosition) {
																	
		Player player = players[playerPosition];
		
		//take all pebbles from chosen pit
		int pebblesToMove = player.getPebbles(pitPosition);
		player.emptyPit(pitPosition); 
		
		//return number of pebbles to play this turn
		return pebblesToMove;
	}
	
	public boolean movePebbles(int pitPosition, int playerPosition, int pebblesToMove) {
		
		Player player = players[playerPosition];
		//player 1 position increments and player 2 positions decrements
		int currPosition = (playerPosition == 0) ? --pitPosition : ++pitPosition;
		
		int captured;
		boolean myPits = true;
		boolean extraTurn = false;
		
		while (pebblesToMove > 0) {	
			//if not up to a mancala			
			if (currPosition > -1 && currPosition < 6) {
				
				//capture pebbles when land on empty pit on your side
				if (pebblesToMove == 1 && player.getPebbles(currPosition) == 0 && myPits) {
		
					captured = capturePebbles(currPosition, playerPosition);	
					player.addToMancala(captured);
					return extraTurn;
				}
			
				player.addPebble(currPosition);
				pebblesToMove--;

				//player 1 position increments and player 2 positions decrements
				currPosition = (playerPosition == 0) ? --currPosition : ++currPosition;
			}
			
			//if currPosition is up to the mancala
			else {	
				if (myPits) {
					//add pebble to mancala when reach end of your pits
					player.addToMancala(1);
					pebblesToMove--;
					
					//extra turn if end in your mancala
					if (pebblesToMove == 0) {
						extraTurn = true;
						return extraTurn;
					}	
				}	
				//swap to next player's pits boolean
				myPits = (myPits == true) ? false : true;		
																		
				//swap to next player's pit since reached the end
				playerPosition = (playerPosition == 0) ? 1 : 0;
				player = players[playerPosition];
				
				//counter clockwise -> player 1 starts at end of array and player 2 starts at beginning
				currPosition = (playerPosition == 0) ? 5 : 0;	
			}			
		}
		return extraTurn;
	}
	
	public int capturePebbles(int currPosition, int playerPosition) {
		
		//capture pebbles in pit across from landing pit
		playerPosition = (playerPosition == 0) ? 1 : 0;
		Player player = players[playerPosition];
		int captured = player.getPebbles(currPosition) + 1;
		player.emptyPit(currPosition);
		
		return captured;
	}
	
	public int choosePit(int playerNum) {
		
		int pitPosition;
		boolean emptyPit;
		
		do {
			System.out.print("Which pit would you like to pick? ");
			pitPosition = (input.nextInt() - 1);
		
			if (pitPosition > 5 || pitPosition < 0) {
				System.out.println("\nInvalid entry!\nChoose number between 1-6");
				emptyPit = false;
			}
			
			else {
				emptyPit = players[playerNum].isPitEmpty(pitPosition);
				if (emptyPit) {
					System.out.println("\nThat pit is empty. Choose a pit containing pebbles.");
				}
			}
			
		} while (pitPosition > 5 || pitPosition < 0 || emptyPit);
		
		return pitPosition;
	}
	
	public boolean endGame() {
		
		if (players[0].emptyPits()) {
			
			players[1].addToMancala(players[1].pebblesSum());
			return true;
		}
		else if (players[1].emptyPits()) {
			players[0].addToMancala(players[0].pebblesSum());
			return true;
		}
		return false;
	}
	
	public void getWinner() {
		
		int score1 = players[0].getMancalaPebbles();
		int score2 = players[1].getMancalaPebbles();
		
		System.out.println("Game over\nPress enter to view the winner");
		input.nextLine();
		input.nextLine();
		int i = (score1 > score2) ? 0 : 1;

		System.out.println("\nCongratulations!\n" + players[i].getName() + " is the winner!");
		System.out.println("\nPoints summary:\n" + players[0].getName() +": " + score1);
		System.out.println(players[1].getName() +": " + score2);
		
	}
	
	public void display() {
		
		System.out.println("\n\t" + players[0].getName());
		System.out.println("   " + players[0].toString());
		System.out.print(players[0].getMancalaPebbles());
		System.out.println("\t\t     " +players[1].getMancalaPebbles());
		System.out.println("   " + players[1].toString());
		System.out.println("\t" + players[1].getName() + "\n");
	}
	
	public void play() {
		
		//instructions
		System.out.println("Welcome to Mancala");
		System.out.println("Board set up:");
		System.out.println(players[0].getName() + "'s pits are on the top row and their Mancala is on the left side.");
		System.out.println(players[1].getName() + "'s pits are on the bottom row and their Mancala is on the right side.");
		System.out.println("*Pits numbers are 1-6 starting from the left.");
		boolean endGame = false, extraTurn; //see what to do with that
		int pitPosition, playerPosition = 0, pebblesToMove;
		
		display();
		
		while (!endGame) {
			
			do {
				System.out.println(players[playerPosition].getName() + "'s turn");			
			
				pitPosition = choosePit(playerPosition);
			
			
				pebblesToMove = removePebbles(pitPosition,playerPosition);
				extraTurn = movePebbles(pitPosition, playerPosition, pebblesToMove);
				display();
				
				//game over breaks out of the loop
				endGame = endGame();
				if (endGame) {
					break;
				}
				
				if (extraTurn) {
					System.out.println("Congrats, you get an extra turn!");
				}
			} while (extraTurn);

			playerPosition = (playerPosition == 0) ? 1 : 0;
		}
		
		getWinner();	
	}
	
}
