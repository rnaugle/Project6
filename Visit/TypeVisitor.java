package Assem.Semant.Visit;
import Assem.Semant.Absyn.*;
//import Semant.Types.*;
import Assem.Semant.Symbol.*;

public class TypeVisitor implements Visitor2{
	
	Table ct;
	Table ot;
	public void setTable(Table t){
		ct = t;
		ot = new Table();
	}
	
	
		public Semant.Types.Type visit(Absyn a){}
		
	  public Semant.Types.Type visit(AddExpr e)
	  {	
		Semant.Types.Type t1 = e.e1.accept(this);
		Semant.Types.Type t2 = e.e2.accept(this);
		if(t1 instanceof Semant.Types.INT && t2 instanceof Semant.Types.INT)
			t1.accept(this);
		else 
		{
			System.err.println("ERROR operator + cannot be applied to " + t1.toString() + ", " + t2.toString()
			//+ "\n" + Semant.Visit.AbsynPrintVisitor.visit(e) 
			);
		}
	  }
	  
	  public Semant.Types.Type visit(AndExpr e)
	  {
		Semant.Types.Type t1 = e.e1.accept(this);
		Semant.Types.Type t2 = e.e2.accept(this);
		if(t1 instanceof Semant.Types.BOOLEAN && t2 instanceof Semant.Types.BOOLEAN)
			t1.accept(this);
		else 
		{
			System.err.println("ERROR operator && cannot be applied to " + t1.toString() + ", " + t2.toString()
			//+ "\n" + Semant.Visit.AbsynPrintVisitor.visit(e) 
			);
		}
	  }
	  
	  public Semant.Types.Type visit(ArrayExpr e)
	  {	
		Semant.Types.Type t1 = e.target.accept(this);
		Semant.Types.Type t2 = e.index.accept(this);
		if(t1 instanceof Semant.Types.ARRAY && t2 instanceof Semant.Types.INT)
			t1.accept(this);
		else if(!(t1 instanceof Semant.Types.ARRAY))
		{
			System.err.println("ERROR incompatible types: array required, but " + t1.toString() + " found, "
			//+ "\n" + Semant.Visit.AbsynPrintVisitor.visit(e.e2) 
			);
		}
		else	
		{
			System.err.println("ERROR incompatible types: int required, but " + t1.toString() + " found, "
			//+ "\n" + Semant.Visit.AbsynPrintVisitor.visit(e) 
			);
		}
	  }
	  
	  public Semant.Types.Type visit(ArrayType e){
		  Semant.Types.Type t = e.base.accept(this);
		  return new Semant.Types.ARRAY(t);
	  }
	  
	  public Semant.Types.Type visit(AssignableExpr e)
		{ 
			return e.accept(this);
		}
	  
	  public Semant.Types.Type visit(AssignStmt e)
	  {
		Semant.Types.Type t1 = e.lhs.accept(this);
		Semant.Types.Type t2 = e.rhs.accept(this);
		if(!t2.coerceTo(t1))
			System.err.println("ERROR incompatible types: "+ t1.toString() +" required, but " + t2.toString() +" found");
		else 
			return t1;
	  }
	  
	  public Semant.Types.Type visit(BinOpExpr e)
	  {
		Semant.Types.Type t1 = e.e1.accept(this);
		Semant.Types.Type t2 = e.e2.accept(this);
		if (t1 instanceof Semant.Types.BOOLEAN && t2 instanceof Semant.Types.BOOLEAN)
			return t1;
		else
			System.err.println("ERROR operator cannot be applied to " + t1.toString() +", "+t2.toString());
	  }
	  
	  public Semant.Types.Type visit(BlockStmt e){
		for (Stmt s : e.stmts)
		{
			s.accept(this);
		}
	}
		
	  public Semant.Types.Type visit(BooleanType e){
		  return new Semant.Types.BOOLEAN();
	  }
	  
	  public Semant.Types.Type visit(CallExpr e){
		Semant.Types.Type t = e.target.accept(this);
		Object tm1 = ct.get(Symbol.symbol(e.method));
		//TODO move this to where it won't break during runtime
		Semant.Types.FUNCTION tm = (Semant.Types.FUNCTION) tm1;
		
		if (tm1 == null)
			System.err.println("ERROR cannot resolve method " + e.method);
		else if ((countFormals(tm.formals) != e.args.size()))
			System.err.println("ERROR mismatch in number of arguments");
		else if(compareFormals(tm.formals,e.args))
			return t;
		else
			return t;			
	  }

	public int countFormals(Semant.Types.RECORD r)
	{
		return r.fields.size();
	}

	public boolean compareFormals(Semant.Types.RECORD rs, java.util.LinkedList<Expr> fs)
	{
		for (int i = 0; i < rs.fields.size(); i++)
		{
			Semant.Types.Type t1 = rs.fields.get(i).type;
			Semant.Types.Type t2 = fs.get(i).accept(this);
			if (!t2.coerceTo(t1))
				System.err.println("ERROR incompatible types: " + t1.toString()+" required, but "+t2.toString()+" found:");
		}
		return true;
	}
	
	  public Semant.Types.Type visit(ClassDecl e){
	
		  ot.beginScope();
		  Semant.Types.CLASS c = (Semant.Types.CLASS)ct.get(Symbol.symbol(e.name));
		  Semant.Types.CLASS p = (Semant.Types.CLASS)ct.get(Symbol.symbol(e.parent));
		  while(p != null){
			  for(Semant.Types.FIELD f : p.fields){}
		  }
		  for(MethodDecl m: e.methods){
			  Semant.Types.Type func1 = m.accept(this);
			Semant.Types.FUNCTION func = (Semant.Types.FUNCTION) func1;
			ot.put(func.name,func);
		  }
		  
		  ot.endScope();
	  
	  }
	  public Semant.Types.Type visit(DivExpr e)
	{
	Semant.Types.Type t1 = e.e1.accept(this);
	Semant.Types.Type t2 = e.e2.accept(this);
		if(t1 instanceof Semant.Types.INT && t2 instanceof Semant.Types.INT)
			return t1;
		else
			System.err.println("ERROR operator / cannot be applied to " +t1.toString() +", " +t2.toString());
	}

	  public Semant.Types.Type visit(EqualExpr e)
	{
	Semant.Types.Type t1 = e.e1.accept(this);
	Semant.Types.Type t2 = e.e2.accept(this);
		if((t1 instanceof Semant.Types.INT && t2 instanceof Semant.Types.INT) || (t1 instanceof Semant.Types.BOOLEAN && t2 instanceof Semant.Types.BOOLEAN))
			return t1;
		else
			System.err.println("ERROR operator == cannot be applied to " +t1.toString() +", " +t2.toString());
	} 

	  public Semant.Types.Type visit(Expr e){}
	  public Semant.Types.Type visit(FalseExpr e)
	{
		return new Semant.Types.BOOLEAN();
	}
	  public Semant.Types.Type visit(FieldExpr e)
	{
		Semant.Types.Type t = e.target.accept(this);
		return t;
	}
	  public Semant.Types.Type visit(Formal e)
	{
		Semant.Types.Type t = e.type.accept(this);
		if (!(t instanceof Semant.Types.CLASS || t instanceof Semant.Types.NIL || t instanceof Semant.Types.RECORD || t instanceof Semant.Types.VOID))
			return t;
		else 
			System.err.println("ERROR incompatible types");
	}
	  public Semant.Types.Type visit(GreaterExpr e)
	{
	Semant.Types.Type t1 = e.e1.accept(this);
	Semant.Types.Type t2 = e.e2.accept(this);
		if(t1 instanceof Semant.Types.INT && t2 instanceof Semant.Types.INT)
			return t1;
		else
			System.err.println("ERROR operator > cannot be applied to "+t1.toString() +", " +t2.toString());
	} 
	  public Semant.Types.Type visit(IdentifierExpr e)
	{ return new Semant.Types.STRING(); }
	  public Semant.Types.Type visit(IdentifierType e){
		  Object t = ct.get(Symbol.symbol(e.id));
		  if(t == null){
			  System.err.println("ERROR cannot resolve symbol: "+ e.id);
		  }else{
			Semant.Types.OBJECT t1 = new Semant.Types.OBJECT(t);
			  return t1;
		  }
	  }
	  public Semant.Types.Type visit(IfStmt e)
	{
		Semant.Types.Type t1 = e.test1.accept(this);
		Semant.Types.Type t2 = e.thenStmt.accept(this);
		Semant.Types.Type t3 = e.elseStmt.accept(this);

		if (!(t1 instanceof Semant.Types.BOOLEAN))
			System.err.println("ERROR incompatible types: boolean required, but " +t1.toString()+" found:");
		else
			return t1;
	}
	  public Semant.Types.Type visit(IntegerLiteral e)
		{ return new Semant.Types.INT(); }
	  public Semant.Types.Type visit(IntegerType e){
		  return new Semant.Types.INT();
	  }
	  public Semant.Types.Type visit(LesserExpr e)
	{
	Semant.Types.Type t1 = e.e1.accept(this);
	Semant.Types.Type t2 = e.e2.accept(this);
		if(t1 instanceof Semant.Types.INT && t2 instanceof Semant.Types.INT)
			return t1;
		else
			System.err.println("ERROR operator < cannot be applied to "+ t1.toString() +", " +t2.toString());
	} 
	  public Semant.Types.Type visit(MethodDecl e){
		  for(VarDecl v : e.locals){
			  Semant.Types.Type type = v.accept(this);
		  }
		  for(Stmt s : e.stmts){
			  Semant.Types.Type type2 = s.accept(this);
		  }
		  ot.put(new Semant.Types.FUNCTION(e.name, e));
		  //return 
	  }
	  public Semant.Types.Type visit(MulExpr e)
	{
	Semant.Types.Type t1 = e.e1.accept(this);
	Semant.Types.Type t2 = e.e2.accept(this);
		if(t1 instanceof Semant.Types.INT && t2 instanceof Semant.Types.INT)
			return t1;
		else
			System.err.println("ERROR operator * cannot be applied to " +t1.toString() +", " +t2.toString());
	} 
	  public Semant.Types.Type visit(NegExpr e)
	{
	Semant.Types.Type t1 = e.e1.accept(this);
		/*if(!(t1 instanceof Semant.Types.INT))
			//error
		else
			return t1;*/
		//TODO: not sure if this is right
		return new Semant.Types.INT();
	} 
	  public Semant.Types.Type visit(NewArrayExpr e)
	{
	Semant.Types.Type t = e.type.accept(this);
	
	if(t instanceof Semant.Types.OBJECT){
		t = (Semant.Types.OBJECT) t;
		if(ct.get(t.myClass.name) == null)
			System.err.println("ERROR cannot resolve class "+t.myClass.name.toString());
		else 
			return new Semant.Types.OBJECT(t.myClass);
		}
	else
		return t;
	}
	  public Semant.Types.Type visit(NewObjectExpr e)
		{ return e.type.accept(this); }
		
	  public Semant.Types.Type visit(NotEqExpr e)
	{
	Semant.Types.Type t1 = e.e1.accept(this);
	Semant.Types.Type t2 = e.e2.accept(this);
		if((t1 instanceof Semant.Types.INT && t2 instanceof Semant.Types.INT) || (t1 instanceof Semant.Types.BOOLEAN && t2 instanceof Semant.Types.BOOLEAN))
			return t1;
		else
			System.err.println("ERROR operator != cannot be applied to " +t1.toString() +", " +t2.toString());
	} 
	  
	  public Semant.Types.Type visit(NullExpr e)
		{ return new Semant.Types.NIL(); }
		
	  public Semant.Types.Type visit(OrExpr e)
	{
	Semant.Types.Type t1 = e.e1.accept(this);
	Semant.Types.Type t2 = e.e2.accept(this);
		if((t1 instanceof Semant.Types.BOOLEAN) && (t2 instanceof Semant.Types.BOOLEAN))
			return t1;
		else
			System.err.println("ERROR operator || cannot be applied to " +t1.toString() +", " +t2.toString());
	} 
	 	  
	  public Semant.Types.Type visit(Program e){
		  for(ClassDecl c: e.classes){
			  c.accept(this);
		  }
	  }
	  
	  public Semant.Types.Type visit(Stmt e) {}
	  
	  public Semant.Types.Type visit(StringLiteral e)	
	{ return new Semant.Types.STRING(); }
	
	  public Semant.Types.Type visit(SubExpr e)
	  {
	Semant.Types.Type t1 = e.e1.accept(this);
	Semant.Types.Type t2 = e.e2.accept(this);
		if(t1 instanceof Semant.Types.INT && t2 instanceof Semant.Types.INT)
			return t1;
		else
			System.err.println("ERROR operator - cannot be applied to " +t1.toString() +", " +t2.toString());
	}
	
	  public Semant.Types.Type visit(ThisExpr e)
		//TODO how to check and return the current class here?
	  { 
		

		return new Semant.Types.OBJECT(); }
	  public Semant.Types.Type visit(ThreadDecl e){
	
		  ot.beginScope();
		  Semant.Types.CLASS c = (Semant.Types.CLASS)ct.get(Symbol.symbol(e.name));
		  Semant.Types.CLASS p = (Semant.Types.CLASS)ct.get(Symbol.symbol("Thread"));
		  while(p != null){
				//TODO check for duplicates and bad overrides
			  for(Semant.Types.FIELD f : c.fields.fields){
				ct.get
				if (!p.fields.fields.contains(f)){
					f.accept(this);
					c.fields.fields.add(f);
				}
				else
					System.err.println("ERROR " + f.name.toString() + " is already defined in "+p.name.toString());
				}
			for (Semant.Types.FIELD f : c.methods.fields) {
				if (!p.fields.fields.contains(f)){
					f.accept(this);
					c.methods.fields.add(f);
				}
				else
				{	
					//TODO check for formals only
					Semant.Types.FIELD sameF = p.fields.fields.get(p.fields.fields.indexOf(f));
					if (!areSameFormals(sameF,f))
						System.err.println("ERROR " + f.name.toString() + " is already defined in "+p.name.toString());
					else{
					//	f.accept(this);
					//	c.fields.fields.add(f);					
					//}
					}
				}
		  }
		  for(VarDecl v: e.fields){
			  Semant.Types.Type var1 = v.accept(this);
			if( var1 instanceof Semant.Types.ARRAY)
				Semant.Types.ARRAY var = (Semant.Types.ARRAY) var1;
			else if (var1 instanceof Semant.Types.BOOLEAN)
				Semant.Types.BOOLEAN var = (Semant.Types.BOOLEAN) var1;
			else if (var1 instanceof Semant.Types.INT)
				Semant.Types.INT var = (Semant.Types.INT) var1;
			else 
				System.err.
			ot.put(var,var.name);
		  }
		  for(MethodDecl m: e.methods){
			  Semant.Types.Type func1 = m.accept(this);
			Semant.Types.FUNCTION func = (Semant.Types.FUNCTION) func1;
				ot.put(func,func.name);
		  }
		  
		  ot.endScope();
	  
	  }

		public boolean areSameFormals(Semant.Types.FIELD f1, Semant.Types.FIELD f2)
		{
			if(f1.type instanceof Semant.Types.FUNCTION && f2.type instanceof Semant.Types.FUNCTION){
				Semant.Types.RECORD func1r = f1.type.formals;
				Semant.Types.RECORD func2r = f2.type.formals;
				int i = 0;
				while (i < func1r.fields.size() && i < func2r.fields.size())
				{
					if(!(func1r.fields.get(i).type instanceof func2r.fields.get(i).type))
						return false;
					i++;
				}
				if (i < func1r.fields.size() || i < func2r.fields.size())
					return false;
				else
					return true;
			}
			
		}
	  public Semant.Types.Type visit(TrueExpr e)
	  { return new Semant.Types.BOOLEAN(); }
	  public Semant.Types.Type visit(Type e){
		  System.err.println("THIS SHOULDNT HAPPEN");
	  }
	  public Semant.Types.Type visit(VarDecl e){
		  Semant.Types.Type t = e.type.accept(this);
		  Semant.Types.Type t1 = e.init.accept(this);
		  
		  if(ct.get(Symbol.symbol(e.name))!= null){
			  //ERROR
			  //TODO should know what method/ class your in
			  System.err.println("ERROR "+e.name+" is already defined in:");
		  }
		  
		  if(t1.coerceTo(t)){
			  ct.put(Symbol.symbol(e.name), t);
		  }else{
			  if(t instanceof Semant.Types.ARRAY){
				  System.err.println("ERROR incompatible type: array required, but " + t1.toString()+" found");
			  }else{
				  System.err.println("ERROR incompatible type: " + t.toString()+" required, but " + t1.toString()+" found");
			  }
			  AbsynPrintVisitor a = new AbsynPrintVisitor();
			  a.visit(e);
		  }
		  
		  
		  
	  }
	  public Semant.Types.Type visit(VoidDecl e)
	  { return new Semant.Types.VOID(); }
	  public Semant.Types.Type visit(WhileStmt e)
	  {
		e.body.accept(this);
		return e.test.accept(this);
	  }
	  public Semant.Types.Type visit(XinuCallStmt e)
	  {
		Object m1 = ct.get(Symbol.symbol(e.method));
		
		if (m1 == null)
			System.err.println("ERROR cannot resolve method " + e.method);
		else {
			Semant.Types.FUNCTION m = (Semant.Types.FUNCTION) m1;
			return m;
		}
	  }
	  public Semant.Types.Type visit(XinuCallExpr e)
	  {
		
	  }
	  
	
}
