package Translate.Types;

//import Translate.Types.Visitable;
//import Translate.Types.Visitor;

public class ARRAY extends Type  {
	public Type element;
	
	public ARRAY(Type e){
		this.element = e;
	}

	@Override
	public void accept(Translate.Visit.Visitor v) {
		v.visit(this);
		
	}

	public Translate.Translate.Exp accept(Translate.Translator.Translator  v) { return v.visit(this); }
	@Override
	public
	boolean coerceTo(Type t) {
		if( t.getClass().getName() == element.getClass().getName() ){
			return true;
		}
		return false;

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	//public abstract void accept(Visitor v);
}
