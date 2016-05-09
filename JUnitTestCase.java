import static org.junit.Assert.*;

import java.util.List;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import junit.framework.Assert;

import org.junit.Test;

public class JUnitTestCase {
	
	public static String value1;
	public static String value2;
	public static int value3;
	public static char[][] board = new char[6][6];
	
	
	
	static Board game = new Board();
	//@Test
	public void setUp(){
		value1 = "hi";
		value2 = "adrian";
		value3 = 10;
		
		
		
	}
	@Test
	
	//@Test
	
	//@Test
	public void testUserFire(){
		
		
		int x = 3;
		int y = 4;
		
		char[][] b = game.cloneComputerBoard();
		
		
		game.userFire(x,y, false);
		
		
		char[][] c = game.cloneComputerBoard();
		
		
//		Assert.assertEquals(c[x][y], b[x][y]);
		
		
		
		for(int i =0; i < 6; ++i){
			for(int j = 0; j < 6; ++j){
				
				if(i == x && j == y){
					//System.out.print("hi");
					Assert.assertTrue(c[i][j] != b[i][j]);
				}
				else{
					//System.out.print(c[x][y]);
					Assert.assertEquals(c[i][j], b[i][j]);
				}
			}
		}
		
		
		// check some proper
				 
		 
	}
	//@Test
	
	
	
	//@Test	
	

}
