public class Box{
    public boolean mutable;
    public int value;
    /**
     *Creates an empty box
     */
    public Box{
	value = 0;
	mutable = true;
    }
    /**
     *Creates a filled box that is immutable
     *Used to create starting boxes and hinted boxes
     */
    public Box(int num){
	if(num < 0 || num > 9){
	    throw IndexOutOfBoundsException("You put in an unsupported value, baka");
	}
	value = num;
	mutable = false;
    }
    public void setValue(int num){
	if(mutable = true){
	    value = num;
	}
    }
    public int getValue(){
	return value;
    }
}
