public class Sudoku{
    public static void main(String[] args){
	
    }
    private Box[][] data;

    public Sudoku(){
	for(int i = 0;i < 9;i++){
	    for(int x = 0;x < 9;x++){
		data[i][x] = 0;
	    }
	}
    }
    
    private void display(){
	for(int i = 0;i < 9;i++){
	    String line = "";
	    for(int x = 0;x < 9;x++){
		line += data[i][x];
	    }
	    System.out.println(line);
	}
    }
}

		    
