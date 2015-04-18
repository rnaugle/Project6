package Translate;

import Temp.Label;
import Temp.Temp;
import Tree.CONST;
import Tree.ESEQ;
import Tree.LABEL;
import Tree.MOVE;
import Tree.SEQ;
import Tree.Stm;
import Tree.TEMP;
import Tree.CJUMP;

public class RelCx extends Cx{
	public  Tree.Exp le;
	public  int operator;
	public  Tree.Exp re;
	
	public RelCx(int op, Tree.Exp l, Tree.Exp r){
		operator = op;
		le = l;
		re = r;
	}
	@Override
	public Stm unCx(Label t, Label f) {
		// TODO Auto-generated method stub
		return new CJUMP(operator, le , re, t, f);
	}
	
	@Override
	public Stm unNx() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
