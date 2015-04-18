package Translate.Absyn;

public class Formal extends Absyn{

	public Type type;
	public String name;
	public Translate.Types.Type checktype;
	public Formal(Type type, String name){
		this.type = type;
		this.name = name;
	}
	
	public String toString()
	  {   return "";   }
	public Translate.Translate.Exp accept(Translate.Translator.Translator  v) { return v.visit(this); }
	public void accept(Translate.Visit.Visitor v)      {          v.visit(this);   }
	//public Translate.Types.Type accept(Translate.Visit.Visitor2 v) { return v.visit(this); }
}
