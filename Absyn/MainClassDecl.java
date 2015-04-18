package Translate.Absyn;

public class MainClassDecl extends Absyn{
	public java.lang.String name;
	public java.lang.String parent;
	public java.util.LinkedList<VarDecl> fields;
	public java.util.LinkedList<Stmt> stmts;

	public MainClassDecl(java.lang.String name, java.lang.String parent, java.util.LinkedList<VarDecl> fields, java.util.LinkedList<Stmt> stmts) { 
			this.name = name;
			this.parent = parent;
			this.fields = fields;
			this.stmts = stmts;
			
			
		}

	public String toString()
	{
		return "";
	}
	public Translate.Translate.Exp accept(Translate.Translator.Translator  v) { return v.visit(this); }
	public void accept(Translate.Visit.Visitor v){         v.visit(this);   }
	//public Translate.Types.Type accept(Translate.Visit.Visitor2 v) { return v.visit(this); }
}
