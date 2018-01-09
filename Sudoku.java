public class Sudoku{
    
    public static void main(String[] args){
	Sudoku a = new Sudoku();
	String str = a.makeString();
        do{

	} while{
	    
	}
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
		line += data[i][x].getValue() + " ";
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

    private Box[][] convert(int[][] ary){
	Box[][] a = new Box[ary.length][ary[0].length];
	for(int x = 0; x < ary.length; x++){
	    for(int y = 0; y < ary[].length){
		a[x][y] = newBox(ary[x][y]);
	    }
	}
    }
}

		    
