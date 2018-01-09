public class Box{
    
    public boolean mutable;
    public int value;
    
    /**
     *Creates an empty box.
     */
    
    public Box(){
	value = 0;
	mutable = true;
    }
    
    /**
     *Creates a filled box that is immutable.
     *Used to create starting boxes and hinted boxes.
     */
    
    public Box(int num){
	if(num < 0 || num > 9){
	    throw new IndexOutOfBoundsException("You put in an unsupported value, this command takes integers 0 - 9");
	}
	value = num;
	mutable = false;
    }
    
    /**
     *changes the value of a mutable box.
     */

    public void setValue(int num){
	if(mutable = true){
	    value = num;
	}else{
	    System.out.println("This square is not changable");
	}
    }
    
    /**
     *returns the value of the current box.
     *returns 0 if the box is empty.
     */
    
    public int getValue(){
	return value;
    }

    /**
     *It will auto solves a box later.
     *Could you explain it to me. Thanks, Mr. Edward.
     */

    public void hinted(int ans){
	if(mutable){
	    value = ans;
	}
    }



    
}
