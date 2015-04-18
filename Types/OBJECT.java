package Translate.Types;

//import Translate.Visitor;

public class OBJECT extends Type{
	
	public RECORD fields;
	public RECORD methods;
	public CLASS myClass;
	
	public OBJECT(CLASS m){
		this.myClass = m;
		methods = new RECORD();
		fields = new RECORD();
	}
	
	public OBJECT(CLASS m, RECORD meth, RECORD f){
		this.fields = f;
		this.methods = meth;
		this.myClass = m;
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
