package Translate.Absyn;

import java.util.LinkedList;

public class Program extends Absyn{
	public LinkedList<ClassDecl> classes;
	public java.util.LinkedList<Translate.Types.CLASS> ctl;
	
	public Program(LinkedList<ClassDecl> a2,java.util.LinkedList<Translate.Types.CLASS>classtypes){

		classes = a2;
		ctl = classtypes;
	}
	
	public Program(LinkedList<ClassDecl> a2){

		classes = a2;
	
	}
	
	public String toString(){
		if(classes != null){
			return classes.toString();
		}else{
			return null;
		}
	}
	public Translate.Translate.Exp accept(Translate.Translator.Translator  v) { return v.visit(this); }
	public void accept(Translate.Visit.Visitor v){v.visit(this);}
	//public Translate.Types.Type accept(Translate.Visit.Visitor2 v) { return v.visit(this); }
}
