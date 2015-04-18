package Assem.Semant.Visit;

public class TypesPrintVisitor implements Visitor
{
	 private java.io.PrintStream out;
  private int indent = 0;

  public AbsynPrintVisitor()
  {  this(System.out);  }

  public AbsynPrintVisitor(java.io.PrintStream out)
  {  this.out = out;  }

  private void printIndent()
  {  for (int i = 0; i < indent; i++)  out.print(" ");  }
	
	public void visit (Semant.Types.ARRAY e)
	{		
		out.println("ARRAY(");
		indent += 2; printIndent();
		e.element.accept(this);
		out.println(")");
		
		indent -= 2;	printIndent();
	}
	
	public void visit (Semant.Types.BOOLEAN e)
	{
		out.print("BOOLEAN");
	}
	
	public void visit (Semant.Types.CLASS e)
	{
		out.println("CLASS(" + e.name);
		indent += 2; printIndent();

		if (e.parent == null)
			out.println("null");
		else
			e.parent.accept(this);

		//record of methods
		//printIndent();
		//out.print("RECORD(");
		if (e.methods != null)
			e.methods.accept(this);
		else
			out.println(")");

		//record of vardecls
		//printIndent();
		//out.print("RECORD(");
		if (e.fields != null){
			e.fields.accept(this);
		}
		else
			out.println(")");	
		
		out.print(")");

		//the object associated with the class
		indent -= 2; printIndent();
		e.instance.accept(this);
	}

	public void visit (Semant.Types.FIELD e)
	{
		out.println("FIELD("+e.index+" "+e.name);
		indent += 2; printIndent();
		e.type.accept(this);
		indent -= 2; 
		out.print(")");
	}

	public void visit (Semant.Types.FUNCTION e)
	{
		out.println("FUNCTION("+e.name);
		indent += 2; printIndent();
		e.self.accept(this);
		out.println("OBJECT("+e.self.name*")");
		if (e.formals != null)
			e.formals.accept(this);
		e.result.accept(this);
		out.print(")");
	}

	public void visit (Semant.Types.INT e)
	{
		out.print("INT");
	}
	
	public void visit (Semant.Types.NIL e)
	{
		out.print("null");
	}

	public void visit (Semant.Types.OBJECT e)
	{
		out.print("OBJECT("e.myClass.name);
		//this might print CLASS([name] [parent]
		//e.myClass.accept(this);
		indent += 2; printIndent();
		e.methods.accept(this);
		e.fields.accept(this);
		out.print(")");
		indent -= 2; printIndent();
	}

	public void visit (Semant.Types.RECORD e)
	{
		out.println("RECORD(");
		indent += 2; printIndent();
		
		for (Semant.Types.FIELD f : e.iterator())
			f.accept(this);
		
		out.print(")");
		indent -= 2; printIndent();
	}

	public void visit(Semant.Types.STRING e)
	{
		out.print("STRING");
	}

	public void visit (Semant.Types.VOID e)
	{
		out.print("VOID");
	}
}
