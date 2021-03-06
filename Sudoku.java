import java.util.*;
public class Sudoku{
    
    public static void main(String[] args){
	Scanner input = new Scanner(System.in);
        Sudoku a = new Sudoku();
	try{
	    int seed = Integer.parseInt(args[0]);
	    a = new Sudoku(seed);
	}catch(Exception e){
	}
	a.display();
	do{
	    String in = input.nextLine();
	    int len = in.length();
	    if(len == 9 && in.substring(0,3).equals("set")){
		int row = Integer.parseInt(in.substring(4,5));
		int col = Integer.parseInt(in.substring(6,7));
		int num = Integer.parseInt(in.substring(8,9));
		a.set(row,col,num);
	    }
	    if(len == 5 && in.substring(0,5).equals("solve")){
		a.reset();
	        a.solve();
	    }
	    if(len == 5 && in.substring(0,5).equals("reset")){
		a.reset();
	    }
	    if(len == 8 && in.substring(0,4).equals("hint")){
		int row = Integer.parseInt(in.substring(5,6));
		int col = Integer.parseInt(in.substring(7,8));
	        a.setPerm(row,col,a.getSol(row,col));
	    }
	    a.display();
	}while(!a.isSolved());
    }

    private Box[][] data,solution;
    private int[][] numVariety;
    private boolean moved;
    private Random rand;

    /*
      Creates a random Sudoku Board
     */
    
    public Sudoku(){
	rand = new Random();
	data = new Box[9][9];
	for(int x = 0;x < 9;x++){
	    for(int y = 0;y < 9;y++){
		data[x][y] = new Box();
	    }
	}
	data[0][0] = new Box(rand.nextInt(9)+1);
	for(int i = 0;i < 10;i++){
	    int x = rand.nextInt(9);
	    int y = rand.nextInt(9);
	    while(get(x,y) != 0){
		x = rand.nextInt(9);
	        y = rand.nextInt(9);
	    }
	    addNum(x,y);
	}
	solve();
	for(int i = 0;i < 20;i++){
	    int x = rand.nextInt(9);
	    int y = rand.nextInt(9);
	    data[x][y] = new Box(get(x,y));
	}
	dataToSol();
	reset();
    }

    public Sudoku(int seed){
	rand = new Random(seed);
	data = new Box[9][9];
	for(int x = 0;x < 9;x++){
	    for(int y = 0;y < 9;y++){
		data[x][y] = new Box();
	    }
	}
	data[0][0] = new Box(rand.nextInt(9)+1);
	for(int i = 0;i < 10;i++){
	    int x = rand.nextInt(9);
	    int y = rand.nextInt(9);
	    while(get(x,y) != 0){
		x = rand.nextInt(9);
	        y = rand.nextInt(9);
	    }
	    addNum(x,y);
	}
	solve();
	for(int i = 0;i < 20;i++){
	    int x = rand.nextInt(9);
	    int y = rand.nextInt(9);
	    data[x][y] = new Box(get(x,y));
	}
	dataToSol();
	reset();
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

    /*
      The recursive solve function
    */
    private boolean solve(){	
	if(isSolved()){
	    return true;
	}
	int row = 0;
	int col = 9;
	// Finds next empty cell
	for(int x = 0;x < 9;x++){
	    for(int y = 0;y < 9;y++){
		if(get(x,y) == 0){
		    row = x;
		    col = y;
		    x = 9;
		    y = 9;
		}
	    }
	}
	// Finds every possiblity after that cell
	for(int value = 1;value < 10;value++){
	    if(isValid(row,col,value)){
		set(row,col,value);
		if(solve()){
		    return true;
		}else{
		    set(row,col,0);
		}
	    }
	}
	return false;
    }

    /*
      adds a valid number to the puzzle
     */
    private boolean addNum(int x,int y){
        if(get(x,y) != 0){
	    return true;
	}
	int value = rand.nextInt(9) + 1;
	if(isValid(x,y,value)){
	    data[x][y] = new Box(value);
	    if(solve()){
		reset();
		return true;
	    }
	}
	data[x][y] = new Box();
	if(addNum(x,y)){
	    return true;
	}
	return false;
    }
    
    /*
      prints the puzzle in an elementary fashion
    */
    private void display(){
	System.out.println("  \033[4m0 1 2 3 4 5 6 7 8\033[0m");
	for(int x = 0;x < 9;x++){
	    String line = "";
	    for(int y = 0;y < 9;y++){
		if(get(x,y) == 0){
		    line += " |";
		}else{
		line += data[x][y] + "|";
		}
	    }
	    if(x % 3 == 0){
		line = line;
	    }
	    System.out.println(x + "|\033[4m" + line.substring(0,17) + "\033[0m|");
	}
    }
    /*
      a toString method left in for testing purposes
    */

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

    /*
      intitializes solution by making it a copy of data
    */

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
	else{
	    //System.out.println("You attempted to change the value of an immutable number");
	}
    }

    private void setPerm(int row,int col,int num){
	data[row][col] = new Box(num);
    }
    
    /*
      get value in the solution
    */
    
    private int getSol(int row,int col){
	return solution[row][col].getValue();
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
		if (get(x,y) == get(x,num)){
		    return false;
		}
	    }
	    if(num != x){
		if (get(x,y) == get(num,y)){
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
		    if(get(row,col) == get(x,y)){
			return false;
		    }
		}
	    }
	}
	return true;
    }

    /*
      Checks if box would be valid in a position
     */
    private boolean isValid(int x,int y,int value){
	boolean isValid = true;
	set(x,y,value);
	isValid = isValidRowCol(x,y) && isValidBox(x,y);
        set(x,y,0);
	return isValid;
    }
    
    /*
      Checks if the board is a valid solution
      Third part is not fully working
     */

    private boolean isSolved(){
	for(int i = 0;i < 9;i++){
 	    int sum = 0;
 	    for (int x = 0;x < 9;x++){
 		sum += get(i,x);
 	    }
 	    if(sum != 45){
 		return false;
 	    }
	    sum = 0;
 	    for (int x = 0;x < 9;x++){
 		sum += get(x,i);
 	    }
 	    if(sum != 45){
 		return false;
 	    }
	    sum = 0;
	    for(int x = 0;x < 3;x++){
		for(int w = 0;w < 3;w++){
		    sum += get(i/3*3+w,x);
		}
	    }
	    if(sum != 45){
		return false;
	    }
	}
	for(int x = 0;x < 9;x++){
	    for(int y = 0;y < 9;y++){
		if(!isValidRowCol(x,y) || !isValidBox(x,y)){
		    return false;
		}
	    }
	}
	return true;
    }

    /*
      convenient reset method
    */

    public void reset(){
	for (int x = 0; x < 9; x++){
	    for (int y = 0; y < 9; y++){
		set(x,y,0);
	    }
	}
    }

    public static int[][] choose(){
	Random num = new Random();
	int x = num.nextInt(2);
	int[][] a =     {{0,0,0,3,9,0,0,1,0},
			 {5,0,1,0,0,0,0,4,0},
			 {9,0,0,7,0,0,5,0,0},
			 {6,0,2,5,3,0,0,7,0},
			 {0,0,0,0,7,0,0,0,8},
			 {7,0,0,8,0,0,9,0,3},
			 {8,0,3,0,1,0,0,9,0},
			 {0,9,0,2,0,6,0,0,7},
			 {4,0,0,0,0,3,0,6,1}};

	int[][] b =     {{5,0,6,0,2,0,9,0,3},
			 {0,0,8,0,0,0,5,0,0},
			 {0,0,0,0,0,0,0,0,0},
			 {6,0,0,2,8,5,0,0,9},
			 {0,0,0,9,0,3,0,0,0},
			 {8,0,0,7,6,1,0,0,4},
			 {0,0,0,0,0,0,0,0,0},
			 {0,0,4,0,0,0,3,0,0},
			 {2,0,1,0,5,0,6,0,7}};


	if(x == 0){
	    return a;
	}
	if(x == 1){
	    return b;
	}
	else{
	     return a;
	}
	
    }




}

		  
