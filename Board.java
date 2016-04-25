import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Board {
	
	public int compShipCounter = 0;
	public int userShipCounter = 0;
	public int row = 6;
	public int col = 6;
	public int shipSize = 3;
	
	public char[][] userChar2DArray = new char[row][col];
	public char[][] compChar2DArray = new char[row][col];
	public char[][] visCompChar2DArray = new char[row][col];
	
	public void initBoard(char[][] anyBoard){
		
		for(int i = 0; i < row; ++i){
			
			for(int j = 0; j < col; ++j){
				anyBoard[i][j] = '~';
			}
			
		}
		
		
	}
	
	public void printBoard(char[][] anyBoard){
		
		String textLine = " ";
		
		for(int i = 0; i < col; ++i){
			textLine += " " + i;
		
		}
		textLine += "\n";
		for(int i = 0; i < row; ++i){
			textLine += i;
			for(int j = 0; j < col; ++j){
				textLine += " " + anyBoard[i][j];
				
			}
			textLine += "\n";
		}
		
		System.out.println(textLine);
	}
	
	public void setCompShips(char[][] board){
		Random randShips = new Random();

		for (int shipSize = 1; shipSize <= 3; ++shipSize){	
			int startRow = 0;
			int startCol = 0;
			
			boolean horizontal = randShips.nextBoolean();
			
			
			if(horizontal){ //horizontal placement
				boolean allow = false;
				while(!allow){
					startRow = (int)(Math.random() * row);
					startCol = (int)(Math.random() * (col  - shipSize));
					allow = true;
					
					for(int i = 0; i < shipSize; ++i){
						if(board[startRow][startCol + i] != '~'){
							allow = false;
						}
					}
				}
				
				for(int i = 0; i < shipSize; ++i){
					
					board[startRow][startCol + i] = 'O';
				}
			}
			else{ //vertical placement
				boolean allow = false;
				while(!allow){
					startRow = (int)(Math.random() * (row - shipSize));
					startCol = (int)(Math.random() * col);
					allow = true;
					
					for(int i = 0; i < shipSize; ++i){
						if(board[startRow + i][startCol] != '~'){
							allow = false;
						}
					}
				}
				
				for(int i = 0; i < shipSize; ++i){
					board[startRow + i][startCol] = 'O';
				}
			}
		}
	}
		
	
	public void setUserShips(char[][]board, int shipSize){
		Scanner input = new Scanner(System.in);
		int inputRow; 
		int inputCol;
		String inputHV; 
		for(int i = 1; i <= shipSize; ++i){
			boolean allow = false;
			while(!allow){
				while(true)
				{
					try
					{
						System.out.println("Place your ships strategically :)");
						System.out.print("Type in an integer for row(shipSize: " + i +"): " );
						inputRow = input.nextInt();
						
					if (inputRow < row && inputRow >= 0)
						break;
					else
						System.out.println("Please type a number between 0 and " + (row - 1) + ".");
					}
					catch (InputMismatchException e)
					{
						System.out.println("Input should be an integer.");
						input.nextLine();
					}
				}
				while(true)
				{
					try
					{
						System.out.print("Type in an integer for column(shipSize: " + i +"): " );
						inputCol = input.nextInt();
						
					if (inputCol < col && inputCol >= 0)
						break;
					else
						System.out.println("Please type a number between 0 and " + (col - 1) + ".");
					}
					catch (InputMismatchException e)
					{
						System.out.println("Input should be an integer.");
						input.nextLine();
					}
				}
				while(true)
				{
					System.out.print("Do you want to place the ship horizontally/vertically(h/v): ");
					inputHV= input.next();
					if (inputHV.equals("H") || inputHV.equals("h") || inputHV.equals("V") || inputHV.equals("v"))
						break;
					else
						System.out.println("Please enter \"h\" or \"v\".");
				}
				
				if(inputHV.equals("h") || inputHV.equals("H")){
						int placeRow = inputRow;
						int placeCol = inputCol + i - 1;
						if(placeCol >= col){
							allow = false;
							System.out.println("Coordinates are out of bounds");
						}
						else{
							allow = true;
							if((board[inputRow][inputCol] != '~') ){
								System.out.println("Ship already exists in that location");
								allow = false;
							}
						}		
				}
				
				if(inputHV.equals("v") || inputHV.equals("V")){
						int placeRow = inputRow + i - 1; //+ (i-1);
						int placeCol = inputCol;
						if(placeRow >= row){
							allow = false;
							System.out.println("Coordinates are out of bounds");
						}
						else{
							allow = true;
							if((board[inputRow][inputCol] != '~') ){
								System.out.println("Ship already exists in that location");
								allow = false;
							}
						}
				}	
				
				if(allow == true){
					if(inputHV.equals("v") || inputHV.equals("V")){
						for(int n = 0; n < i; ++n){
							board[inputRow+n][inputCol] = 'O';
						}
					}
					else if(inputHV.equals("h") || inputHV.equals("H")){
						for(int n = 0; n < i; ++n){
							board[inputRow][inputCol+n] = 'O';
						}
					}
					System.out.print("\033[H\033[2J");
					System.out.flush();
					System.out.println("*****This is the user board*****\n");
					printBoard(board);
				}
				
			}
		}
	}
	
	public void userFire(char[][]compChar2DArray){
		Scanner input = new Scanner(System.in);
		boolean allow = false;
		int inputRow; 
		int inputCol;
		
		while(!allow){
				while(true)
				{
					try
					{
						System.out.print("Input the row you want to hit: ");
						inputRow = input.nextInt();
							
						if (inputRow < row && inputRow >= 0)
							break;
						else
							System.out.println("Please type a number between 0 and " + (row - 1) + ".");
						}
					catch (InputMismatchException e)
					{
						System.out.println("Input should be an integer.");
						input.nextLine();
					}
				}

			
				while(true)
				{
					try
					{
						System.out.print("Input the column you want to hit: ");
						inputCol = input.nextInt();
						
						if (inputCol < col && inputCol >= 0)
							break;
						else
							System.out.println("Please type a number between 0 and " + (col - 1) + ".");
						}
					catch (InputMismatchException e)
					{
						System.out.println("Input should be an integer.");
						input.nextLine();
					}
				}
				
				System.out.print("\033[H\033[2J");
				System.out.flush();
		
			if((inputRow < 0 || inputRow > row) || (inputCol < 0 || inputCol > col)){ //checks if the coordinates are out of bounds
				System.out.println("You are firing out of bounds, try again: ");
				allow = false;
			}
			else{ //the coordinates are in bounds
				allow = true;
				
				if((compChar2DArray[inputRow][inputCol] == 'X') || (compChar2DArray[inputRow][inputCol] == '*')){
					System.out.println("You have already used this coordinate shoot again");
					allow = false;
				}
				else if(compChar2DArray[inputRow][inputCol] == 'O'){ //if it hits a ship
					System.out.println("You hit a ship!!\n\n");
					compChar2DArray[inputRow][inputCol] = 'X';
					visCompChar2DArray[inputRow][inputCol] = 'X';
					System.out.println("*****This is the computer board*****\n");
					printBoard(visCompChar2DArray);
					userShipCounter++;
					if(userShipCounter == 6){
						System.out.println("You win, computer loses!!");
						System.exit(0);
								
					}
				}
				else{ //it is a miss
					System.out.println("You have missed\n\n");
					visCompChar2DArray[inputRow][inputCol] = '*';
					compChar2DArray[inputRow][inputCol] = '*';
					System.out.println("*****This is the computer board*****\n");
					printBoard(visCompChar2DArray);
							
				}

			
			}
		}
	}
	
	public void compFire(char[][] userChar2DArray){
		
		Random rand = new Random();
		boolean active = false;
		int compRow;
		int compCol;
		
		
		while(!active){
			compRow = rand.nextInt(row);
			compCol = rand.nextInt(col);
			active = true;
			if((userChar2DArray[compRow][compCol] == 'X') || (userChar2DArray[compRow][compCol] == '*'))
				active = false;
			
			else if(userChar2DArray[compRow][compCol] == 'O'){ //if it hits a ship
				System.out.println("Computer hit a ship!!\n\n");
				userChar2DArray[compRow][compCol] = 'X';
				
				System.out.println("*****This is the user board*****\n");
				printBoard(userChar2DArray);
				compShipCounter++;
				if(compShipCounter == 6){
					System.out.println("Computer won, you lose!!");
					System.exit(0);
					
				}
					
			}
			else{ //it is a miss
				System.out.println("Computer has missed\n\n");
				userChar2DArray[compRow][compCol] = '*';
				System.out.println("*****This is the user board*****\n");
				printBoard(userChar2DArray);
						
			}
			
			
		}

	}
	
	
	
	public static void main(String[] args) {
		
		
		Board obj = new Board();
		obj.initBoard(obj.userChar2DArray);
		obj.initBoard(obj.compChar2DArray);
		obj.initBoard(obj.visCompChar2DArray);
		
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println("*****This is the user board*****\n");
		obj.printBoard(obj.userChar2DArray);
		
		
		//obj.printBoard(obj.visCompChar2DArray);
		
		obj.setCompShips(obj.compChar2DArray);
		obj.setUserShips(obj.userChar2DArray, obj.shipSize);
		
		while(true){
			System.out.println("*****This is the computer board*****\n");
			obj.printBoard(obj.visCompChar2DArray);
			
			obj.userFire(obj.compChar2DArray);
			obj.compFire(obj.userChar2DArray);
			try {
			    Thread.sleep(10000);                 //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			System.out.print("\033[H\033[2J");
			System.out.flush();
			
			
			
		}
	}

}
