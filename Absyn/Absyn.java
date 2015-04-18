package Assem.Translate.Absyn;

public abstract class Absyn implements Assem.Translate.Visit.Visitable{

	public abstract String toString();
	
	public void accept(Assem.Translate.Visit.Visitor v){v.visit(this);}
	

	//public Translate.Types.Type accept(Translate.Visit.Visitor2 v) { return v.visit(this); }
	
	public Assem.Translate.Translate.Exp accept(Assem.Translate.Translator.Translator  v) { return v.visit(this); }
}
