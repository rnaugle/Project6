package Translate.Types;

//import Translate.Visitor;

public class FUNCTION extends Type{
	
	public RECORD formals;
	public String name;
	public Type result;
	public Type self;
	
	public FUNCTION(String n, Type s, RECORD f, Type r){
		this.name = n;
		this.formals = f;
		this.result = r;
		this.self = s;
	}
	
	public FIELD addFormal(Type type, Translate.Symbol.Symbol n){
		;
		return null;
	}

	@Override
	public void accept(Translate.Visit.Visitor v) {
		v.visit(this);
	}
	
	public Translate.Translate.Exp accept(Translate.Translator.Translator  v) { return v.visit(this); }

	@Override
	public boolean coerceTo(Type t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
