package Translate.Absyn;

import java.util.LinkedList;

public class BlockStmt extends Stmt{

	public LinkedList<Stmt> stmts;
	public BlockStmt(LinkedList<Stmt> stmts){
		this.stmts = stmts;
		
		
	}
	
	public String toString()
	  {   return "";   }
	public Translate.Translate.Exp accept(Translate.Translator.Translator  v) { return v.visit(this); }
	 public void accept(Translate.Visit.Visitor v)      {          v.visit(this);   }
	// public Translate.Types.Type accept(Translate.Visit.Visitor2 v) { return v.visit(this); }

	
}
