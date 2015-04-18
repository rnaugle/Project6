package Translate.Types;

//import Translate.Visitor;

public class CLASS extends Type{
	
	public RECORD fields;
	public OBJECT instance;
	public RECORD methods;
	public String name;
	public CLASS parent;
	
	public CLASS(String s){
		this.name = s;
		
	}

	@Override
	public void accept(Translate.Visit.Visitor v) {
		v.visit(this);
	}
	
	public Translate.Translate.Exp accept(Translate.Translator.Translator  v) { return v.visit(this); }

	@Override
	public
	boolean coerceTo(Type t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
