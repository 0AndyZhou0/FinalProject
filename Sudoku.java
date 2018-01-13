import java.util.*;
public class Sudoku{
    
    public static void main(String[] args){
	Scanner input = new Scanner(System.in);
	/*int[][] stuff = {{2,4,8,3,9,5,7,1,6},
			 {5,7,1,6,2,8,3,4,9},
			 {9,3,6,7,4,1,5,8,2},
			 {6,8,2,5,3,9,1,7,4},
			 {3,5,9,1,7,4,6,2,8},
			 {7,1,4,8,6,2,9,5,3},
			 {8,6,3,4,1,7,2,9,5},
			 {1,9,5,2,8,6,4,3,7},
			 {4,2,7,9,5,3,8,6,1}};
	*/
	int[][] stuff = {{0,0,0,3,9,0,0,1,0},
			 {5,0,1,0,0,0,0,4,0},
			 {9,0,0,7,0,0,5,0,0},
			 {6,0,2,5,3,0,0,7,0},
			 {0,0,0,0,7,0,0,0,8},
			 {7,0,0,8,0,0,9,0,3},
			 {8,0,3,0,1,0,0,9,0},
			 {0,9,0,2,0,6,0,0,7},
			 {4,0,0,0,0,3,0,6,1}};
	Sudoku a = new Sudoku(stuff);
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
    
    private Box[][] data;
    private boolean moved;

    public Sudoku(){
	data = new Box[9][9];
	for(int i = 0;i < 9;i++){
	    for(int x = 0;x < 9;x++){
		data[i][x] = new Box(x);
	    }
	}
    }

    public Sudoku(Sudoku a){
	data = new Box[9][9];
	for(int i = 0;i < 9;i++){
	    for(int x = 0;x < 9;x++){
		data[i][x] = new Box(a.get(i,x));
	    }
	}
    }

    public Sudoku(int[][] a){
	data = new Box[9][9];
	for(int i = 0;i < 9;i++){
	    for(int x = 0;x < 9;x++){
		data[i][x] = new Box(a[i][x]);
	    }
	}
    }

    
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

    private void set(int row,int col,int num){
	if(data[row][col].isMutable()){
	    data[row][col].setValue(num);
	}
	else{
	    System.out.println("You attempted to change the value of an immutable number");
	}
    }

    private int get(int row,int col){
	return data[row][col].getValue();
    }

    private static  Box[][] convert(int[][] ary){
	Box[][] a = new Box[ary.length][ary[0].length];
	for(int x = 0; x < ary.length; x++){
	    for(int y = 0; y < ary[0].length; y++){
		a[x][y] = new Box(ary[x][y]);
	    }
	}
	return a;
    }


    private boolean isValidRow(int x, int y){
	for(int col = 0; col < 9; col++){
	    if(col != y){
		if (data[x][y].getValue() == data[x][col].getValue()){
		    return false;
		}
	    }
	}
	return true;
    }

     private boolean isValidCol(int x, int y){
	for(int row = 0; row < 9; row++){
	    if(row != x){
		if (data[x][y].getValue() == data[row][y].getValue()){
		    return false;
		}
	    }
	}
	return true;
    }

    private boolean isSolved(){
	for(int i = 0;i < 9;i++){
	    int sum = 0;
	    for (int x = 0;x < 9;x++){
		sum += data[i][x].getValue();
	    }
	    if(sum != 45){
		return false;
	    }
	}
	for(int i = 0;i < 9;i++){
	    int sum = 0;
	    for (int x = 0;x < 9;x++){
		sum += data[x][i].getValue();
	    }
	    if(sum != 45){
		return false;
	    }
	}
	for(int i = 0;i < 3;i++){
	    int sum = 0;
	    for(int x = 0;x < 3;x++){
		sum += data[i*3][x].getValue();
	    }
	    for(int x = 0;x < 3;x++){
		sum += data[i*3+1][x].getValue();
	    }
	    for(int x = 0;x < 3;x++){
		sum += data[i*3+2][x].getValue();
	    }
	    if(sum != 45){
		return false;
	    }
	}
	return true;
    }


}

		  
