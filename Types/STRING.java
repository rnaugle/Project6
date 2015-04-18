package Translate.Types;

//import Translate.Visitor;

public class STRING extends Type{
	
	public STRING(){
		;
	}

	@Override
	public void accept(Translate.Visit.Visitor v) {
		v.visit(this);
		
	}

	@Override
	public boolean coerceTo(Type t) {
		// TODO Auto-generated method stub
		if( t.getClass().getName() == this.getClass().getName() ){
			return true;
		}
		return false;
	}
	
	public Translate.Translate.Exp accept(Translate.Translator.Translator  v) { return v.visit(this); }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
