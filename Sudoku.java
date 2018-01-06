public class Sudoku{
    public static void main(String[] args){
	
    }
    private Box[][] data;
    private void display(){
	for(int i = 0;i < 9;i++){
	    String line = "";
	    for(int x = 0;x < 9;x++){
		line += data[i*9+x];
	    }
	    System.out.println(line);
	}
    }
}

		    
