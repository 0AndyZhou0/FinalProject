public class Sudoku{
    public static void main(String[] args){
	Sudoku a = new Sudoku();
	a.display();
    }
    private Box[][] data;

    public Sudoku(){
	for(int i = 0;i < 9;i++){
	    for(int x = 0;x < 9;x++){
		data[i][x] = new Box(1);
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

		    
