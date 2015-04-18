package Translate.Absyn;

public class ArrayType extends Type{
	public Type base;
	public ArrayType(Type e1){
		this.base = e1;
	
	}
	
	public String toString()
	  {   return "";   }
	public Translate.Translate.Exp accept(Translate.Translator.Translator  v) { return v.visit(this); }
	 public void accept(Translate.Visit.Visitor v)      {          v.visit(this);   }
	 //public Translate.Types.Type accept(Translate.Visit.Visitor2 v) { return v.visit(this); }
}
