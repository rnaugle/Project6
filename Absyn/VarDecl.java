package Translate.Absyn;

public class VarDecl extends Absyn{
	public Type type;
	public String name;
	public Expr init;
	public Translate.Types.Type checktype;
	public VarDecl(Type type, java.lang.String name, Expr init){
		this.type = type;
		this.name = name;
		this.init = init;
	}
	
	public String toString()
	  {   return "";   }
	public Translate.Translate.Exp accept(Translate.Translator.Translator  v) { return v.visit(this); }
	 public void accept(Translate.Visit.Visitor v)      {          v.visit(this);   }
	 //public Translate.Types.Type accept(Translate.Visit.Visitor2 v) { return v.visit(this); }
}
