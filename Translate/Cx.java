package Translate;

import Temp.Label;
import Temp.Temp;
import Tree.*;

public abstract class Cx extends Exp{
	
	Cx(){
		;
	}

	//@Override
	//abstract Stm unCx(Label t, Label f);
	
	@Override
	public Tree.Exp unEx() {
		Temp r = new Temp();
		Label t = new Label();
		Label f = new Label();
		return new ESEQ(
				new SEQ(new MOVE(new TEMP(r),new CONST(1)),
						new SEQ(unCx(t,f),
								new SEQ(new LABEL(f),
										new SEQ(new MOVE(new TEMP(r),
												new CONST(0)),
												new LABEL(t))))),
												new TEMP(r));
	}
	//@Override
	//Tree.Stm unNx(){
	//	return null;
	//}

}
