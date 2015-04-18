package Translate.Absyn;

public class IntegerLiteral extends Expr{
	public int value;
	public IntegerLiteral( int value){
		this.value = value;
	}
	public IntegerLiteral( Integer value){
		this.value = value;
	}
	
	public String toString()
	  {   return "";   }
	public Translate.Translate.Exp accept(Translate.Translator.Translator  v) { return v.visit(this); }
	public void accept(Translate.Visit.Visitor v)      {          v.visit(this);   }
	//public Translate.Types.Type accept(Translate.Visit.Visitor2 v) { return v.visit(this); }
}
