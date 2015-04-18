package Frame;
import Util.BoolList;
import java.util.ArrayList;
import Temp.*;

public abstract class Frame {
	abstract public Frame newFrame(Label name, int numOfBools);
	public Label name;
	public ArrayList<Access> formals;
	abstract public Access allocLocal(boolean escape);
	abstract public Access allocFormal(boolean escape);
	abstract public void printFrame(java.io.PrintWriter writer);
	
}
