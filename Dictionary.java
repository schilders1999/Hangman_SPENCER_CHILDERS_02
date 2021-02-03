
import java.security.SecureRandom;
import java.io.*;

public class Dictionary {
	private String [] wordList = new String[200];
	private int currentCard;
	private SecureRandom randomNumbers= new SecureRandom();
	
	
//This is a dictionary constructor.
public Dictionary(String fileName){
	fileName = "HangmanList.txt";
	readFile(fileName);
}


//This method takes the text file and scans all of the words within it pulling them into the dictionary object.
private void readFile(String fileName){
	try {
		BufferedReader wordReader = new BufferedReader(new FileReader(fileName));
	    for (int i = 0 ; i < 200 ; i++){
	        wordList [i] = wordReader.readLine();
	    }
	    wordReader.close();
	}
	    catch (FileNotFoundException e) {
	    	System.out.print(e);
	    }
		catch (IOException e) {
			System.out.print(e);
		}
}


//This method randomly chooses one of the 200 strings pulled from the text file.
public String chooseWord() {
	int randomWord = randomNumbers.nextInt(200);
	return wordList[randomWord];
}


}


