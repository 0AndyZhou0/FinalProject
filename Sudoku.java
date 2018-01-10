public class Sudoku{
    
    public static void main(String[] args){
<<<<<<< HEAD
	int[][] ary  = new int[9][9];	
	for(int x = 0; x < ary.length; x++){
	    for(int y = 0; y < ary[0].length; y++){
		ary[x][y] = x;
	    }
	}
	Box[][] items = convert(ary);
	String str = "";
	for(int x = 0; x < items.length; x++){
	    for(int y = 0; y < items[0].length; y++){
		str = str + items[x][y] + "";
	    }
	}
	System.out.println(str);

	// Sudoku a = new Sudoku();
	// String str = a.makeString();
	// System.out.println(str);
	
        // do{

	// } while{
	    
	// }
=======
	Sudoku a = new Sudoku();
	a.display();
	/*
        do{
	    
	} while{
	    
	}
	*/
>>>>>>> origin/Andy
    }
    
    private Box[][] data;    

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
    
    private void display(){
	for(int i = 0;i < 9;i++){
	    String line = "";
	    for(int x = 0;x < 9;x++){
		line += data[i][x] + " ";
	    }
	    System.out.println(line);
	}
    }

    private String makeString(){
    	String line = "";
    	for(int i = 0;i < 9;i++){
    	    for(int x = 0;x < 9;x++){
    		line += data[i][x].getValue() + " ";
    	    }
    	    line = line + "\n";
    	}
	return line;
    }

    private void set(int row,int col,int num){
	data[row][col].setValue(num);
    }

    private int get(int row,int col){
	return data[row][col].getValue();
    }

<<<<<<< HEAD
    private static  Box[][] convert(int[][] ary){
	Box[][] a = new Box[ary.length][ary[0].length];
	for(int x = 0; x < ary.length; x++){
	    for(int y = 0; y < ary[0].length; y++){
		a[x][y] = new Box(ary[x][y]);
	    }
	}
	return a;
=======
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
	return true;
>>>>>>> origin/Andy
    }
}

		    
