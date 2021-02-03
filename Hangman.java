


import java.util.Scanner;
import java.io.*;


public class Hangman
{
	private int wins;
	private int losses;
	private String currentWord;
	private Dictionary dict = new Dictionary("HangmanList.txt");
		
		
		
		
	public Hangman()
	{
	}
		
	
	
	
	//This scans the wins and losses from the WinLossFile.txt and pulls them in to the program.
	private void loadWL()
	{
		try {
			Scanner wlReader = new Scanner(new File("WinLossFile.txt"));
			while (wlReader.hasNext()) {
				wins = wlReader.nextInt();
				losses = wlReader.nextInt();
			}
			wlReader.close();
		}
		catch(FileNotFoundException e) {
			System.out.println(e);
		}

	}
	
	
	
	
	//This method rewrites the wins and losses in the WinLossFile.txt.
	private void writeWL()
	{
		try {
			PrintWriter wlWriter = new PrintWriter("WinLossFile.txt");
			wlWriter.print(wins);
			wlWriter.print(" ");
			wlWriter.print(losses);
			wlWriter.close();
		}
		catch(FileNotFoundException e) {
			System.out.println(e);
		}
	}
	
	
	
	
	//This method contains all of the code required to run the hangman game itself. 
	public void playGame()
	{
		
		Scanner scan = new Scanner(System.in);
		char g='+';
		String answer="Y";
		loadWL();
		
		while(true) 
		{
			int guesses=5;
			System.out.println("Would you like to play a game (Y/N)? ");
				answer=scan.nextLine();
				if (answer.equalsIgnoreCase("n"))
				{
					break;
				}
	
			currentWord = dict.chooseWord();

			
			System.out.println("The number of letters in the word is " + currentWord.length() + ".");
			System.out.println();
			
			char [] ArrayChar = new char [currentWord.length()];
			
			for(int i=0 ; i<currentWord.length() ; i++) 
			{
					System.out.print("_ ");
			}
			System.out.println();
			
			
			while(true) 
			{
				
				int sameChar=0;
				
				System.out.println();
				System.out.println("Please guess a letter (lowercase).");
					g=scan.next().charAt(0);
					
				
				//Tests each letter of currentWord and compares it to g (letter guessed).
				for(int l = 0 ; l < currentWord.length() ; l++) 
				{ 
					if (currentWord.charAt(l)==g) 
					{
						System.out.print(g+" ");
						sameChar++;
						ArrayChar [l] = g;
					}
					else 
					{
						System.out.print("_ ");
					}
				
				

				}//for loop ends
				
					//Displays the current letters that are correctly guessed.
					for (int z = 0 ; z<currentWord.length()-1 ; z++) {
					System.out.print(ArrayChar[z]);
					}
				
				//If the character guessed doesn't match the correct character, a guess is lost
				if (currentWord.contains(String.valueOf(g))==false)
				{
					guesses--;
				}
				
				System.out.println();
				System.out.println("You have " + guesses + " guesses remaining.");
				
				
				//Tests to see if the word guessed is completed by testing if all the guessed characters match the correct ones.
				boolean charMatch=true;
				for(int l = 0 ; l < currentWord.length() ; l++) 
				{
					if (currentWord.charAt(l)!=ArrayChar[l])
					{
						charMatch=false;
					}	
				}
				
				if (charMatch==true)
				{
					System.out.println("Congratulations, you have chosen all the correct letters. The correct word is: " + currentWord + ".");
					wins++;
					break;
				}
				
				if (guesses==0) 
				{
					losses++;
					System.out.println();
					System.out.println("You lose. The correct word is: " + currentWord + ".");
					System.out.println();
					break;
				}
				
			}//Inner While loop ends
			
			answer=scan.nextLine();
		}//Outer while loop ends
		writeWL();
		System.out.println("Thank you for playing! Your Win/Loss record is: " + wins + "," + losses + ".");
		scan.close();
	}
} 
