package Mips;
import Frame.*;
import Temp.*;
import Tree.Exp;
public class InReg extends Access{
	public Temp temp;
	
	public InReg(Temp t){
	 temp = t;
	}
	
	@Override
	public Exp exp(Exp framePtr) {
		// TODO Auto-generated method stub
		return null;
	}

}
