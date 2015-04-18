package Assem.Semant.Visit;

/**
 * AbsynPrintVisitor.java
 *
 * Given an AST of the program, prints and abstract syntax tree dump of
 *  the tree, suitable for completely rebuilding the tree in the next stage
 *  of the example.
 *
 * Created: Thu Sep 23 14:18:50 2004
 *
 * @author <a href="mailto:brylow@elsinore.cs.purdue.edu">Dennis Brylow</a>
 * @version 1.0
 */

public class AbsynPrintVisitor implements Visitor
{
  private java.io.PrintStream out;
  private int indent = 0;

  public AbsynPrintVisitor()
  {  this(System.out);  }

  public AbsynPrintVisitor(java.io.PrintStream out)
  {  this.out = out;  }

  private void printIndent()
  {  for (int i = 0; i < indent/2; i++)  out.print(" ");  }

  public void visit(Semant.Absyn.Program g)
  {
    out.println("Program(");
    indent += 2;    printIndent();
    out.print("AbstractList(");
    indent += 2;    //printIndent();
    //TODO handle Lists
    //g.classes.accept(this);
    for(Semant.Absyn.ClassDecl c : g.classes){
    	out.println();
	printIndent();
	c.accept(this);
    }

    indent -= 2;
    out.print(")");
    indent -= 2;    printIndent();
    out.println(")");
  }

  public void visit(Semant.Absyn.Expr e)    {  }
 

  

  public void visit(Semant.Absyn.AddExpr e)
  {
    out.println("AddExpr(");
    indent += 2;    printIndent();
    
    e.e1.accept(this);
    out.println();
    printIndent();
    e.e2.accept(this);
    
    indent -= 2;   // printIndent();
    out.print(")");
  }
  
  public void visit(Semant.Absyn.SubExpr e)
  {
    out.println("SubExpr(");
    indent += 2;    printIndent();
    
    e.e1.accept(this);
    out.println();
    printIndent();
    e.e2.accept(this);
    
    indent -= 2;    //printIndent();
    out.print(")");
  }
  
  public void visit(Semant.Absyn.MulExpr e)
  {
    out.println("MulExpr(");
    indent += 2;    printIndent();
    
    e.e1.accept(this);
    out.println();
    printIndent();
    e.e2.accept(this);
    
    indent -= 2;    //printIndent();
    out.print(")");
  }
  
  public void visit(Semant.Absyn.DivExpr e)
  {
    out.println("DivExpr(");
    indent += 2;    printIndent();
    
    e.e1.accept(this);
    out.println();
    printIndent();
    e.e2.accept(this);
    
    indent -= 2;    //printIndent();
    out.print(")");
  }

  
  public void visit(Semant.Absyn.IntegerLiteral e)
  {
    out.print("IntegerLiteral(" + e.value + ")");
  }

  public void visit(Semant.Absyn.IdentifierExpr e)
  {
    out.print("IdentifierExpr(" + e.id + ")");
  }
  
  public void visit(Semant.Absyn.Absyn e)    
  {  
	  
  }
  
  public void visit(Semant.Absyn.AndExpr e)
  {
	  out.println("AndExpr(");
	    indent += 2;    printIndent();
	    
	    e.e1.accept(this);
	    out.println();
	    printIndent();
	    e.e2.accept(this);
	    
	    indent -= 2;    //printIndent();
	    out.print(")");
  }
  
  public void visit(Semant.Absyn.ArrayExpr e)
  {
	  out.println("ArrayExpr(");
	    indent += 2;    printIndent();
	    
	    e.target.accept(this);
	    out.println();
	    printIndent();
	    e.index.accept(this);
	    
	    indent -= 2;   // printIndent();
	    out.print(")");
	    
	    
	    
  }
  public void visit(Semant.Absyn.ArrayType e)
  {
	  out.print("ArrayType(");
	  e.base.accept(this);
	  
	  out.print(")");
  }
  public void visit(Semant.Absyn.AssignableExpr e)
  {
	  
  }
  public void visit(Semant.Absyn.AssignStmt e)
  {
	  out.println("AssignStmt(");
		indent+=2;
		printIndent();
	  e.lhs.accept(this);
		out.println();
		printIndent();
	  e.rhs.accept(this);
		indent-=2;
	  out.print(")");
  }
  public void visit(Semant.Absyn.BinOpExpr e)
  {
	  
  }
  public void visit(Semant.Absyn.BlockStmt e)
  {
	  out.println("BlockStmt(");
	  indent += 2;    
	    printIndent();
	  out.print("AbstractList(");
	    indent += 2;    
	    //printIndent();
	    
	    
	  for(Semant.Absyn.Stmt c : e.stmts){
		  out.println();
		  printIndent();
	    	c.accept(this);
	    }
	  
	  indent -= 2;

	    out.print(")");
	    indent -= 2;
		  //printIndent();
	  out.print(")");
  }
  public void visit(Semant.Absyn.BooleanType e)
  {
	  out.print("BooleanType");
  }

  public void visit(Semant.Absyn.CallExpr e)
  {
	  out.println("CallExpr(");
	  indent += 2;    
	    printIndent();
	  e.target.accept(this);
	  //e.rhs.accept(this);
	  out.println();
		printIndent();
	  out.print(e.method);
	out.println();
	printIndent();
	  out.print("AbstractList(");
	    indent += 2;    
	    //printIndent();
	    
	    
	  for(Semant.Absyn.Expr c : e.args){
	    	out.println();
		printIndent();
		c.accept(this);
	    }
	  
	  indent -= 2;
	    out.print(")");
	    
	    

	    indent -= 2;    
	    
	  out.print(")");
	 // printIndent();
  }
  public void visit(Semant.Absyn.ClassDecl e)
  {
	  out.println("ClassDecl("+ e.name + " " + e.parent);
	  
	  indent += 2;    
	    printIndent();
	  out.print("AbstractList(");
	  indent += 2;    
	    //printIndent();
	    
	    
	    
	  for(Semant.Absyn.VarDecl c : e.fields){
	    	out.println();
		printIndent();
		c.accept(this);
	    }
	  
	  indent -= 2;
	    out.println(")");
	    
	    printIndent();
	    out.print("AbstractList(");
	    indent += 2;    
	    //printIndent();
	    
	    
	  for(Semant.Absyn.MethodDecl c : e.methods){
	    	out.println();
		printIndent();
		c.accept(this);
	    }
	  
	  indent -= 2;
	  //printIndent();
	    out.print(")");
	    
	    indent -= 2;
	//	  printIndent();
	    
	  out.print(")");
  }
  
  public void visit(Semant.Absyn.EqualExpr e)
  {
	  out.println("EqualExpr(");
	    indent += 2;    printIndent();
	    
	    e.e1.accept(this);
	    out.println();
	    printIndent();
	    e.e2.accept(this);
	    
	    indent -= 2;    //printIndent();
	    out.print(")");
  }
 
  public void visit(Semant.Absyn.FalseExpr e)
  {
    out.print("FalseExpr");
  }
  
  //TODO: dont know
  public void visit(Semant.Absyn.FieldExpr e)
  {
    
  }
  public void visit(Semant.Absyn.Formal e)
  {
	  out.print("Formal(");
		//indent+=2; printIndent();
	  e.type.accept(this);
		//indent-=2;
	  out.print(" " + e.name + ")");
  }
  public void visit(Semant.Absyn.GreaterExpr e)
  {
	  out.println("GreaterExpr(");
	    indent += 2;    printIndent();
	    
	    e.e1.accept(this);
	    out.println();
	    printIndent();
	    e.e2.accept(this);
	    
	    indent -= 2;   // printIndent();
	    out.print(")");
  }
  public void visit(Semant.Absyn.IdentifierType e)
  {
	  out.print("IdentifierType(" + e.id + ")");
  }
  public void visit(Semant.Absyn.IfStmt e)
  {
	  out.println("IfStmt(");
	    indent += 2;    printIndent();
	    
	    e.test1.accept(this);
	    out.println();
	    printIndent();
	    e.thenStmt.accept(this);
	    out.println();
	    printIndent();
	    e.elseStmt.accept(this);
	    indent -= 2;   // printIndent();
	    out.print(")");
	    
  }
  public void visit(Semant.Absyn.IntegerType e)
  {
	  out.print("IntegerType");
  }
  public void visit(Semant.Absyn.LesserExpr e)
  {
	  out.println("LesserExpr(");
	    indent += 2;    printIndent();
	    
	    e.e1.accept(this);
	    out.println();
	    printIndent();
	    e.e2.accept(this);
	    
	    indent -= 2;   // printIndent();
	    out.print(")");
  }
  public void visit(Semant.Absyn.MethodDecl e)
  {
	  out.print("MethodDecl(");
	  if(e.returnType == null){
		  out.print("public_static_void");
	  }else{
		  e.returnType.accept(this);
	  }
	 if(e.synced){
		 out.println(" synchronized "+e.name);
	 }else{
		 out.println(" " + e.name);
	 }
	 
	 indent += 2;    
	    printIndent();
	 out.print("AbstractList(");
	    indent += 2;    
	    //printIndent();
	    
	    
	    if(e.params == null){
	    	
	    }else{
	    	for(Semant.Absyn.Formal c : e.params){
		    	out.println();
			printIndent();
			c.accept(this);
		    }
	    }
	    
	  
	  
	  indent -= 2;
	 // printIndent();
	    out.println(")");
	    
		printIndent();
	    out.print("AbstractList(");
	    indent += 2;    
	    //printIndent();
	    
	    if(e.locals != null){
	    	for(Semant.Absyn.VarDecl c : e.locals){
		    	out.println();
			printIndent();
			c.accept(this);
		    }
	    }
	    
	  
	  
	  indent -= 2;
	  out.println(")");
	printIndent();
	    
	    out.print("AbstractList(");
	    indent += 2;    
	    //printIndent();
	    
	    if(e.stmts !=null){
	    	for(Semant.Absyn.Stmt c : e.stmts){
		    	out.println();
			printIndent();
			c.accept(this);
		    }
	    }
	    
	    
	  
	  
	  indent -= 2;
	    out.println(")");
	printIndent();
	    

	   // if(e.returnVal != null){
	    	e.returnVal.accept(this);
	    //}else{
	//	Semant.Absyn.IntegerLiteral zero = new Semant.Absyn.IntegerLiteral(0);
	//	//TODO NOT SURE HERE
	//	e.returnVal = zero;
	//	e.returnVal.accept(this);
	  //  } 


	    
	    indent -= 2;
		 // printIndent();
	    
	  out.print(")");
	  
  }
  public void visit(Semant.Absyn.NegExpr e)
  {
      out.print("NegExpr(");
      indent += 2;    printIndent();
      
      e.e1.accept(this);
      
      indent -= 2;  // printIndent();
      out.print(")");
      
  }
  public void visit(Semant.Absyn.NewArrayExpr e)
  {
      out.print("NewArrayExpr(");
      indent += 2;    //printIndent();
      
      e.type.accept(this);
      out.println();
      printIndent();
      
      out.print("AbstractList(");
      indent += 2;   // printIndent();

      for(Semant.Absyn.Expr d : e.dimensions){
    	  out.println();
    	  printIndent();
          d.accept(this);
      }
      
      indent -= 2;
      out.print(")");
      indent -= 2;   // printIndent();
      out.print(")");
  }
  public void visit(Semant.Absyn.NewObjectExpr e)
  {
      out.print("NewObjectExpr(");
      indent += 2;//    printIndent();
      
      e.type.accept(this);
      
      indent -= 2;  //  printIndent();
      out.print(")");
  }
  public void visit(Semant.Absyn.NotEqExpr e)
  {
      out.print("NotEqExpr(");
      indent += 2;    printIndent();
      
      e.e1.accept(this);
      out.println();
      printIndent();
      e.e2.accept(this);
      
      indent -= 2;    //printIndent();
      out.print(")");
  }
  public void visit(Semant.Absyn.NotExpr e)
  {
      out.print("NotExpr(");
      indent += 2;    printIndent();
      
      e.e1.accept(this);
      
      indent -= 2;   // printIndent();
      out.print(")");
  }
  public void visit(Semant.Absyn.NullExpr e)
  {
      out.print("NullExpr");
  }
  public void visit(Semant.Absyn.OrExpr e)
  {
      out.println("OrExpr(");
      indent += 2;    printIndent();
      
      e.e1.accept(this);
      out.println();
      printIndent();
      e.e2.accept(this);
      
      indent -= 2;   // printIndent();
      out.print(")");
  }
 
  public void visit(Semant.Absyn.Stmt e)
  {
      e.accept(this);
  }
  public void visit(Semant.Absyn.StringLiteral e)
  {
      out.print("StringLiteral(" +  e.value+")");
  }
  public void visit(Semant.Absyn.ThisExpr e)
  {
      out.print("ThisExpr");
  }
  public void visit(Semant.Absyn.ThreadDecl e)
  {
      out.println("ThreadDecl("+ e.name+ " Thread");
      indent += 2;    
	
	printIndent();
      
      out.print("AbstractList(");
      indent += 2; //   printIndent();
      
      for(Semant.Absyn.VarDecl v : e.fields){
       
	out.println();
	 printIndent();
	v.accept(this);
      }
      indent -= 2;    //printIndent();
      out.println(")");
      printIndent();
      out.print("AbstractList(");
      indent += 2; // printIndent();
      
      for(Semant.Absyn.MethodDecl v : e.methods){
        out.println();
	printIndent();
	v.accept(this);
      }
    
      indent -= 2;    //printIndent();
      out.print(")");
      
      indent -= 2;    //printIndent();
      out.print(")");
  }
  public void visit(Semant.Absyn.TrueExpr e)
  {
      out.print("TrueExpr");
  }
  public void visit(Semant.Absyn.Type e)
  {
      e.accept(this);
  }
  public void visit(Semant.Absyn.VarDecl e)
  {
      out.print("VarDecl(");
      e.type.accept(this);
      out.print(" "+ e.name);
      //indent += 2;  //  printIndent();
     if(e.init != null){
	out.println();
	printIndent(); 
      e.init.accept(this);
	}else{
		out.print(" null");
	}
      
    //  indent -= 2;    //printIndent();
      out.print(")");
  }
  public void visit(Semant.Absyn.VoidDecl e)
  {
    out.println("VoidDecl("+ e.name);
    indent += 2;    printIndent();
    
    out.print("AbstractList(");
    indent += 2;   // printIndent();
    
    for(Semant.Absyn.VarDecl v : e.locals){
        out.println();
	printIndent();
	v.accept(this);
    }
    
    indent -= 2;   // printIndent();
    out.println(")");
    printIndent(); 
    out.print("AbstractList(");
    indent += 2;  //  printIndent();
    
    for(Semant.Absyn.Stmt v : e.stmts){
        out.println();
	printIndent();
	v.accept(this);
    }
    
    indent -= 2;   // printIndent();
    out.print(")");
    
    indent -= 2;   // printIndent();
    out.print(")");
  }
  public void visit(Semant.Absyn.WhileStmt e)
  {
      out.println("WhileStmt(");
      indent += 2;    printIndent();

      e.test.accept(this);
      out.println();
      printIndent();
      e.body.accept(this);

      indent -= 2;   // printIndent();
      out.print(")");
  }
  public void visit(Semant.Absyn.XinuCallExpr e)
  {
      out.println("XinuCallExpr("+ e.method);
      indent += 2;    printIndent();

      out.println("AbstractList(");
      indent += 2;    printIndent();

      for(Semant.Absyn.Expr expr : e.args){
          expr.accept(this);
      }

      indent -= 2;
      out.print(")");

      indent -= 2;   // printIndent();
      out.print(")");
  }
  public void visit(Semant.Absyn.XinuCallStmt e)
  {
      out.println("XinuCallStmt("+ e.method);
      indent += 2;    printIndent();

      out.print("AbstractList(");
      indent += 2;   // printIndent();

      for(Semant.Absyn.Expr expr : e.args){
    	  out.println();
    	  printIndent();
          expr.accept(this);
      }

      indent -= 2;
      out.print(")");

      indent -= 2;   // printIndent();
      out.print(")");
  }
  
  public void visit(Semant.Types.ARRAY a){
	  ;
  }
	public void visit(Semant.Types.BOOLEAN a){
		  ;
	  }
	public void visit(Semant.Types.CLASS a){
		  ;
	  }
	public void visit(Semant.Types.FIELD a){
		  ;
	  }
	public void visit(Semant.Types.FUNCTION a){
		  ;
	  }
	public void visit(Semant.Types.INT a){
		  ;
	  }
	public void visit(Semant.Types.NIL a){
		  ;
	  }
	public void visit(Semant.Types.OBJECT a){
		  ;
	  }
	public void visit(Semant.Types.RECORD a){
		  ;
	  }
	public void visit(Semant.Types.STRING a){
		  ;
	  }
	public void visit(Semant.Types.VOID a){
		  ;
	  }
  

}// Visitor

    
