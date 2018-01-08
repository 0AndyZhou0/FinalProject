public class Sudoku{

    private Box[][] data;    

    public Sudoku(){
	data = new Box[9][9];
	for(int i = 0;i < 9;i++){
	    for(int x = 0;x < 9;x++){
		data[i][x] = new Box(x);
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

    public static void main(String[] args){
	Sudoku a = new Sudoku();
	String str = a.makeString();
	System.out.println(str);
    }

    private void set(int row,int col,int num){
	data[row][col].setValue(num);
    }
}

		    
