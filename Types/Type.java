package Translate.Types;

//import Translate.Visitable;
//simport Translate.Visitor;

public abstract class Type implements Translate.Visit.Visitable{
	
	public Type(){
		
	}
	
	public abstract void accept(Translate.Visit.Visitor v);
	public abstract Translate.Translate.Exp accept(Translate.Translator.Translator  v);
	public abstract boolean coerceTo(Type t);
	public abstract String toString();
	
}
