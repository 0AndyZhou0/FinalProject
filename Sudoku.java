import java.util.*;
public class Sudoku{
    
    public static void main(String[] args){
	Scanner input = new Scanner(System.in);
	int[][] stuff = {{2,4,8,3,9,5,7,1,6},
			 {5,7,1,6,2,8,3,4,9},
			 {9,3,6,7,4,1,5,8,2},
			 {6,8,2,5,3,9,1,7,4},
			 {3,5,9,1,7,4,6,2,8},
			 {7,1,4,8,6,2,9,5,3},
			 {8,6,3,4,1,7,2,9,5},
			 {1,9,5,2,8,6,4,3,7},
			 {4,2,7,9,5,3,8,6,1}};
	Sudoku a = new Sudoku(stuff);
	System.out.println(a.isValid(3,3));
	/*
	  int[][] thing  = {{1,2,3,4,5,6,7,8,9},
			  {2,3,4,5,6,7,8,9,1},
			  {3,4,5,6,7,8,9,1,2},
			  {4,5,6,7,8,9,1,2,3},
			  {5,6,7,8,9,1,2,3,4},
			  {6,7,8,9,1,2,3,4,5},
			  {7,8,9,1,2,3,4,5,6},
			  {8,9,1,2,3,4,5,6,7},
			  {9,1,2,3,4,5,6,7,8}};
	Sudoku b = new Sudoku(thing);
*/
	a.display();
	do{
	    String in = input.nextLine();
	    if(in.substring(0,3).equals("set")){
		int row = Integer.parseInt(in.substring(4,5));
		int col = Integer.parseInt(in.substring(6,7));
		int num = Integer.parseInt(in.substring(8,9));
		a.set(row,col,num);
	    }
	    a.display();
	} while(!a.isSolved());
    }
    
    private Box[][] data,solution;
    private int[][] numVariety;
    private boolean moved;

    /*
      Later creates a random Sudoku Board
     */
    public Sudoku(){
	data = new Box[9][9];
	for(int i = 0;i < 9;i++){
	    for(int x = 0;x < 9;x++){
		data[i][x] = new Box(x);
	    }
	}
    }

    /*
      Creates a puzzle from an existing puzzle
    */
    public Sudoku(Sudoku a){
	data = new Box[9][9];
	for(int i = 0;i < 9;i++){
	    for(int x = 0;x < 9;x++){
		data[i][x] = new Box(a.get(i,x));
	    }
	}
    }

    /*
      Creates a puzzle from an existing array of ints
    */
    public Sudoku(int[][] a){
	data = new Box[9][9];
	for(int i = 0;i < 9;i++){
	    for(int x = 0;x < 9;x++){
		data[i][x] = new Box(a[i][x]);
	    }
	}
    }

    private void Solve(){
	if(isSolved()){
	    solution = data;
	}else{
	    for(int x = 0;x < 9;x++){
		for(int y = 0;y < 9;y++){
		    if(data[x][y].isMutable()){
			SolveBox(x,y);
		    }
		}
	    }
	}
    }

    private boolean SolveBox(int x,int y){
	for(int value = 1;value < 10;value++){
	    set(x,y,value);
	    if(isValid(x,y)){
		if(isSolved()){
		    return true;
		}
	    }
	}
	return true;
    }








    
    /*
      prints the puzzle in an elementary fashion
    */
    private void display(){
	for(int i = 0;i < 9;i++){
	    String line = "";
	    for(int x = 0;x < 9;x++){
		line += data[i][x] + " ";
	    }
	    System.out.println(line);
	}
    }

    private static String makeString(Box[][] stuff){
    	String line = "";
    	for(int i = 0;i < stuff.length;i++){
    	    for(int x = 0;x < stuff.length;x++){
    		line += stuff[i][x] + " ";
    	    }
    	    line = line + "\n";
    	}
	return line;
    }

    private void dataToSol(){
        solution = new Box[9][9];
	for(int i = 0;i < 9;i++){
	    for(int x = 0;x < 9;x++){
		solution[i][x] = new Box(get(i,x));
	    }
	}
    }
    
    /*
      sets the box at the row and column to the number you want
    */
    private void set(int row,int col,int num){
	if(data[row][col].isMutable()){
	    data[row][col].setValue(num);
	}
    }

    /*
      set in the solution
    */
    private void setSol(int row,int col,int num){
	if(solution[row][col].isMutable()){
	    solution[row][col].setValue(num);
	}
    }

    /*
      returns the value at the given coordinates
    */
    private int get(int row,int col){
	return data[row][col].getValue();
    }

    /*
      Checks if an individual box is valid in its given row and column
    */
    private boolean isValidRowCol(int x, int y){
	for(int num = 0;num < 9;num++){
	    if(num != y){
		if (data[x][y].getValue() == data[x][num].getValue()){
		    return false;
		}
	    }
	    if(num != x){
		if (data[x][y].getValue() == data[num][y].getValue()){
		    return false;
		}
	    }
	}
	return true;
    }

    /*
      Checks if an individual box is valid in its given 3x3 box
    */
    public boolean isValidBox(int x, int y){
	int boxX = x / 3 * 3;
	int boxY = y / 3 * 3;
	for(int row = boxX;row < boxX+3;row++){
	    for(int col = boxY;col < boxY+3;col++){
		if(row != x && col != y){
		    if(data[row][col].getValue() == data[x][y].getValue()){
			return false;
		    }
		}
	    }
	}
	return true;
    }

    /*
      Checks if box is valid in its current position
     */
    private boolean isValid(int x,int y){
	return isValidRowCol(x,y) && isValidBox(x,y);
    }
    
    /*
      Checks if the board is a valid solution
      Third part is not fully working
     */
    private boolean isSolved(){
	boolean solved = true;
	for(int x = 0;x < 9;x++){
	    for(int y = 0;y < 9;y++){
		solved = solved && isValid(x,y);
	    }
	}
	return solved;
    }

    
}

		  
