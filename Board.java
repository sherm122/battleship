
public class Board {

	public char[][] char2DArray = new char[6][6];
	public void userBoard(){
	
		String textLine = "  0 1 2 3 4 5 \n";
	
		for(int i = 0; i < 6; ++i){
			textLine += i;
			for(int j = 0; j < 6; ++j){
				char2DArray[i][j] = '~';
				textLine += " " + char2DArray[i][j];
				
			}
			textLine += "\n";
		}
		
		System.out.println(textLine);
	}
	
	
	public static void main(String[] args) {
		
		Board obj = new Board();
		
		obj.userBoard();
		
		
		

	}

}
