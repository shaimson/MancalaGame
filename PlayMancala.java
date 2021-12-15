package Mancala;

import java.util.Scanner;

public class PlayMancala {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter name of player #1: ");
		String player1 = input.nextLine();
		System.out.print("Enter name of player #2: ");
		String player2 = input.nextLine();
		
		MancalaGame game = new MancalaGame(player1, player2);
		game.play();
		
	}
}
