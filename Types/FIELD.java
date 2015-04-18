package Translate.Types;

//import Translate.Visitor;

public class FIELD extends Type{
	public int index;
	public String name;
	public Type type;

	public FIELD(Type t, int i, String n){
		this.index = i;
		this.name = n;
		this.type = t;
	}
	
	public FIELD(Type t, String n, Integer i){
		this.index = i;
		this.name = n;
		this.type = t;
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
