import java.util.Random;
import java.util.Scanner;

public class Board {
    
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
                System.out.print("Type in an integer for row(shipSize: " + i +"): " );
                inputRow = input.nextInt();
                System.out.print("Type in an integer for column(shipSize: " + i +"): " );
                inputCol = input.nextInt();
                
                System.out.println("Do you want to place the ship horizontally/vertically(h/v): ");
                inputHV= input.next();
                
                if(inputHV.equals("h") || inputHV.equals("H")){
                    int placeRow = inputRow;
                    int placeCol = inputCol + i;
                    if(placeCol >= col){
                        allow = false;
                        System.out.print("Coordinates are out of bounds");
                        
                    }
                    else{
                        allow = true;
                        for(int j = 0; j < shipSize; ++j){
                            if(board[placeRow][placeCol] != '~'){
                                allow = false;
                                System.out.print("Ship already exists in that location");
                                
                            }
                            
                        }
                    }
                    
                    
                }
                
                if(inputHV.equals("v") || inputHV.equals("V")){
                    int placeRow = inputRow + i;
                    int placeCol = inputCol;
                    if(placeRow >= row){
                        allow = false;
                        System.out.print("Coordinates are out of bounds\n");
                        
                    }
                    else{
                        allow = true;
                        
                        for(int j = 0; j < shipSize; ++j){
                            if(board[placeRow][placeCol] != '~'){
                                allow = false;
                                System.out.print("Ship already exists in that location\n");
                            }
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
                    printBoard(board);
                }
                
            }
        }
    }
    
    public static void main(String[] args) {
        
        
        Board obj = new Board();
        obj.initBoard(obj.userChar2DArray);
        obj.initBoard(obj.compChar2DArray);
        obj.initBoard(obj.visCompChar2DArray);
        
        System.out.println("This is the user board\n");
        obj.printBoard(obj.userChar2DArray);
        
        //System.out.println("This is the computer board\n");
        //obj.printBoard(obj.visCompChar2DArray);
        
        obj.setCompShips(obj.compChar2DArray);
        //obj.printBoard(obj.compChar2DArray);
        
        obj.setUserShips(obj.userChar2DArray, obj.shipSize);
        
        
    }
    
}
