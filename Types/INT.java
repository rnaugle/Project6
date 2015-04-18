package Translate.Types;

//import Translate.Visitor;

public class INT extends Type{
	
	public INT(){
		
	}

	@Override
	public void accept(Translate.Visit.Visitor v) {
		v.visit(this);
		
	}
	
	public Translate.Translate.Exp accept(Translate.Translator.Translator  v) { return v.visit(this); }

	@Override
	public boolean coerceTo(Type t) {
		// TODO Auto-generated method stub
		if( t.getClass().getName() == this.getClass().getName() ){
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new String("int");
	}

}
