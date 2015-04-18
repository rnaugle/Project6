package Assem.Translate.Translator;
import Assem.Translate.Absyn.*;
import Assem.Translate.Types.*;
import Assem.Translate.Translate.*;
import Assem.Translate.Frame.*;
import Assem.Translate.Mips.*;
import Assem.Translate.Symbol.*;
import Assem.Translate.Tree.*;
import Assem.Translate.Temp.*;
import Assem.Translate.Symbol.*;
import Assem.Translate.*;
import java.util.*;
public class Translator
{
	MipsFrame type;
	MipsFrame currFrame;
	ArrayList<Frag> frags;
	ClassDecl currClass;
	MethodDecl currMeth;
	Table tb;
	Table objectTb;
	public ArrayList<InReg> regs;
	

	public Translator(MipsFrame f){
		type = f;
		frags = new ArrayList<Frag>();
		tb = new Table();
		objectTb = new Table();
		regs = new ArrayList<InReg>();
		for(int i = 0; i < 32; i++){
			regs.add(new InReg(new Temp()));
		}
	}

	public ArrayList<Frag> results(){
		return frags;
	}
	



	public Translate.Translate.Exp visit(Absyn a){
		return null; 
	}
	public Translate.Translate.Exp visit(AddExpr e){
		Translate.Tree.Exp left = e.e1.accept(this).unEx();
		Translate.Tree.Exp right = e.e2.accept(this).unEx();
		return new Ex(new BINOP(BINOP.PLUS, left, right));

	}
	public Translate.Translate.Exp visit(AndExpr e){
		//if else stuff
		
		IfThenElseExp ift = new IfThenElseExp();
		ift.newIfThenElseExp(e.e1.accept(this), e.e2.accept(this),
				new Ex(new CONST(0)));
		return ift;
		//return null;
	}
	public Translate.Translate.Exp visit(ArrayExpr e){
		//Translate.Tree.Exp left = e.target.accept(this).unEx();
		//Translate.Tree.Exp right = e.index.accept(this).unEx();
		//return new Ex(new BINOP(BINOP.PLUS, left, right));
		//InReg r = (InReg)tb.get(((IdentifierExpr)e.target).id) ;
		TEMP t = new TEMP(new Temp());
		TEMP t1 = new TEMP(new Temp());
		LABEL l0 = new LABEL(new Label());
		LABEL l1 = new LABEL(new Label());
		LABEL l2 = new LABEL(new Label());
		SEQ s1 = new SEQ(l1, new CJUMP(5, t1,new MEM(new BINOP(0, t, new CONST(-4))) ,currFrame.badSub(), l2.label ));
		SEQ s2 = new SEQ(new CJUMP(CJUMP.LT, t1, new CONST(0), currFrame.badSub(), l1.label) , s1);
		SEQ s3 = new SEQ(l0, s2);
		SEQ s4 = new SEQ(new CJUMP(0, t, new CONST(0),currFrame.badPtr(), l0.label), s3);
		SEQ s5 = new SEQ(new MOVE(t1, e.index.accept(this).unEx()), s4);
		SEQ s6 = new SEQ(new MOVE(t, e.target.accept(this).unEx()), s5);
		BINOP b = new BINOP(0, t, new BINOP(2, t1, new CONST(4)));
		ESEQ e1 = new ESEQ(l2 , new MEM(b));
		ESEQ e2 = new ESEQ(s6, e1);
		return new Ex(e2);

	}
	public Translate.Translate.Exp  visit(ArrayType e){
		return new Ex(new CONST(0));
	}
	public Translate.Translate.Exp visit(AssignableExpr e){
		return null; 
	}
	public Translate.Translate.Exp visit(AssignStmt e){
		Translate.Tree.Exp left = e.lhs.accept(this).unEx();
		Translate.Tree.Exp right = e.rhs.accept(this).unEx();
		return new Nx(new MOVE(left,right)); 
	}
	public Translate.Translate.Exp visit(BinOpExpr e){
		return null; 
	}
	public Translate.Translate.Exp visit(BlockStmt e){
		//if (e.stmts.size() > 1)
		//return new Nx(new SEQ(e.stmts.get(0).accept(this).unEx(),sequenceSubTree(e.stmts,1))); 
		//else if (e.stmts.size() == 0)
		Stm s = e.stmts.get(0).accept(this).unNx();
		if (e.stmts.size() > 1){
			for(int i = 1; i < e.stmts.size(); i++){
				s = new SEQ(s, e.stmts.get(i).accept(this).unNx());
			}
		}
		return new Nx(s);
		//return null;
	}  
	//public Translate.Tree.Exp sequenceSubTree(java.util.LinkedList<Stmt> list, int index)
	//{
	//	if (index < list.size()-1)
	//		return new SEQ(e.stmts.get(index).accept(this).unEx(),sequenceSubTree(list,index++));
	//	else
	//		return e.stmts.get(index).accept(this).unEx();
	//}
	public Translate.Translate.Exp visit(BooleanType e){
		return new Ex(new CONST(0));
	}


	public Translate.Translate.Exp visit(CallExpr e){
		//TODO taking for granted that target is identifier
				MOVE m;
				Translate.Tree.Exp before = e.target.accept(this).unEx();
				TEMP t = new TEMP(new Temp());
				//if(e.target instanceof IdentifierExpr){
					m = new MOVE(t, before);
				//}else{
					///do something
				//}
				Label notBad = new Label();
				//InReg r = (InReg)currFrame.allocLocal(false);
				SEQ s = new SEQ(new CJUMP(0,t, new CONST(0), currFrame.badPtr(), notBad), new LABEL(notBad));
				SEQ s1 = new SEQ(m, s);
				
				
				
				//Translate.Tree.Exp func = e.target.accept(this).unEx();
				java.util.ArrayList<Translate.Tree.Exp> argsList = new java.util.ArrayList<Translate.Tree.Exp>();
				argsList.add(t);
				for (int i=0;i<e.args.size();i++)
				{ argsList.add(e.args.get(i).accept(this).unEx()); }
				if(e.typeIndex*4 == 28){
					System.out.println(currMeth.name);
				}
				CALL c = new CALL(new MEM(new BINOP(0, new MEM(new BINOP(0, t, new CONST(-4))), new CONST(e.typeIndex*4))),argsList);
				return new Ex(new ESEQ(s1, c));
	}

	public Translate.Translate.Exp visit(ClassDecl e){
		//checktype, should make datafrag from instance
		currClass = e;
		//e.checktype.accept(this);

		for(MethodDecl m : e.methods){
			m.accept(this);
		}
		return null;

	}
	public Translate.Translate.Exp visit(DivExpr e){
		Translate.Tree.Exp left = e.e1.accept(this).unEx();
		Translate.Tree.Exp right = e.e2.accept(this).unEx();
		return new Ex(new BINOP(BINOP.DIV, left, right));

	}
	public Translate.Translate.Exp visit(EqualExpr e){
		Translate.Tree.Exp left = e.e1.accept(this).unEx();
		Translate.Tree.Exp right = e.e2.accept(this).unEx();
		return new Ex(new BINOP(BINOP.AND, left, right)); 
	}
	public Translate.Translate.Exp visit(Expr e){
		return null; 
	}
	public Translate.Translate.Exp visit(FalseExpr e){
		return new Ex(new CONST(0)); 
	}
	public Translate.Translate.Exp visit(FieldExpr e){
		int index = e.typeIndex;
		Translate.Tree.Exp exp = e.target.accept(this).unEx();
		Label bad = currFrame.badPtr();
		Label ok = new Label();
		Temp result = new Temp();
		return new Ex(new ESEQ(new SEQ( new MOVE(new TEMP(result), exp), new CJUMP(0, new TEMP(result), new CONST(0),
				bad, ok))
		, new ESEQ(new LABEL(ok), new MEM(new BINOP(0,new TEMP(result), new CONST(index*currFrame.wordsize()))))));
	}
	public Translate.Translate.Exp visit(Formal e){
		//TODO
		return null; 
	}
	public Translate.Translate.Exp visit(GreaterExpr e){
		Translate.Tree.Exp left = e.e1.accept(this).unEx();
		Translate.Tree.Exp right = e.e2.accept(this).unEx();
		return new RelCx(CJUMP.GT, left, right);
	}

	public Translate.Translate.Exp visit(IdentifierExpr e){
		Access a = (Access)tb.get(e.id);
		
		
		
		if(a instanceof InReg){
			return new Ex( new TEMP(((InReg)a).temp));
		}else if(a instanceof InFrame){
				//System.out.println("in frame");
			return new Ex(new MEM (new BINOP(0, new TEMP(currFrame.FP), new CONST(((InFrame)a).offset))));
		}
		MOVE m;
		//
		TEMP t = new TEMP(new Temp());
		//if(e.target instanceof IdentifierExpr){
			m = new MOVE(t, new TEMP(currFrame.FP));
		//}else{
			///do something
		//}
		Label notBad = new Label();
		//InReg r = (InReg)currFrame.allocLocal(false);
		int pos = 0;
		OBJECT o = currClass.checktype.instance;
		for(FIELD f: o.fields){
			if(e.id.equals(f.name)){
				pos = f.index;
				break;
			}
		}
		
		SEQ s = new SEQ(m, new CJUMP(0,t, new CONST(0), currFrame.badPtr(), notBad));
		ESEQ e1 = new ESEQ(s, new ESEQ(new LABEL(notBad), new MEM(new BINOP(0, t, new CONST(pos*4)))));
		return new Ex(e1);
		
		
		
		//Translate.Tree.Exp func = e.target.accept(this).unEx();
		//java.util.ArrayList<Translate.Tree.Exp> argsList = new java.util.ArrayList<Translate.Tree.Exp>();
		//argsList.add(t);
		//for (int i=0;i<e.args.size();i++)
		//{ argsList.add(e.args.get(i).accept(this).unEx()); }
		//CALL c = new CALL(new MEM(new BINOP(0, new MEM(new BINOP(0, t, new CONST(-4))), new CONST(e.typeIndex*4))),argsList);
		//return new Ex(new ESEQ(s1, c));
	}
	public Translate.Translate.Exp visit(IdentifierType e){
		return new Ex(new CONST(0));
	}
	public Translate.Translate.Exp visit(IfStmt e){
		//Label tr = new Label();
		//Label fl = new Label();
		//Label join = new Label();

		//return new Nx(new SEQ(new CJUMP(CJUMP.EQ,e.test1.accept(this).unEx(),new CONST(1),tr,fl), 
		//	new SEQ(new LABEL(fl), new SEQ(e.elseStmt.accept(this).unNx(), new SEQ(new JUMP(join)))
		//		))); 
		//return new IfThenElse();
		//unCx the newly created ifthenelse TODO
		//return new Nx();
		
		//Label tru = new Label();
		//Label fal = new Label();
		//Label join = new Label();
		//Translate.Tree.Stm cj = e.test.accept(this).unCx(tru, fal);
		//SEQ s1 = new SEQ(new LABEL(tru), );
		IfThenElseExp ift = new IfThenElseExp();
		if(e.elseStmt == null && e.thenStmt == null){
			ift.newIfThenElseExp(e.test.accept(this), null,
					null);
		}else if(e.elseStmt == null){
			ift.newIfThenElseExp(e.test.accept(this), e.thenStmt.accept(this),
					null);
		}else if(e.thenStmt == null){
			ift.newIfThenElseExp(e.test.accept(this), null,
					e.elseStmt.accept(this));
		
			
		}else{
			ift.newIfThenElseExp(e.test.accept(this), e.thenStmt.accept(this),
					e.elseStmt.accept(this));
		}
		
		
		
		return ift;
		
		
		
		//return null;
	}
	public Translate.Translate.Exp visit(IntegerLiteral e){
		return new Ex(new CONST(e.value));

	}
	public Translate.Translate.Exp visit(IntegerType e){
		return new Ex(new CONST(0));
	}
	public Translate.Translate.Exp visit(LesserExpr e){
		Translate.Tree.Exp left = e.e1.accept(this).unEx();
		Translate.Tree.Exp right = e.e2.accept(this).unEx();
		return new RelCx(CJUMP.LT, left, right);
		//return new Nx(new CJUMP(CJUMP.LT, left, right, new Label(), new Label()));
	}
	public Translate.Translate.Exp visit(MethodDecl e){
		currMeth = e;
		//System.out.println(e.name);
		Translate.Tree.Stm varStms = null;
		Translate.Tree.Stm bodyStms = null;
		if(e.name.equals("main")){
			//currFrame = (MipsFrame)type.newFrame(new Label(e.name), e.params.size());
			currFrame = new MipsFrame(new Label(e.name));
		}else{
			//currFrame = (MipsFrame)type.newFrame(new Label(currClass.name + "." + e.name), e.params.size());
			currFrame = new MipsFrame(new Label(currClass.name + "." + e.name));
		}
		
		for(int i = 0; i < e.params.size(); i++){
			tb.put(e.params.get(i).name, currFrame.allocFormal(false));
		}
		//This should add the list of formals and actuals
		e.checktype.accept(this);
		if(e.locals.size() != 0){
			varStms = e.locals.get(0).accept(this).unNx();
			for(int i = 1; i < e.locals.size(); i++){
				varStms = new SEQ(varStms, e.locals.get(i).accept(this).unNx());
			}

		}

		if(e.stmts.size() != 0){
			if(varStms == null){
				bodyStms = e.stmts.get(0).accept(this).unNx();
			}else{
				bodyStms = new SEQ(varStms, e.stmts.get(0).accept(this).unNx());
			}

			for(int i = 1; i < e.stmts.size(); i++){
				//System.out.println(e.stmts.size());
				Translate.Translate.Exp ch = e.stmts.get(i).accept(this);
				bodyStms = new SEQ(bodyStms , ch.unNx());
			}

		}else{
			bodyStms = varStms;
		}
		boolean flag = false;
		

		if(varStms == null && bodyStms == null){
			flag = true;
			//dont know what to do here
			//System.out.println("wierd case");
			bodyStms = new MOVE(new TEMP(regs.get(2).temp), e.returnVal.accept(this).unEx());
			
		}
		
		if(!flag){
			//uhhh
			bodyStms = new SEQ(bodyStms, new MOVE(new TEMP(regs.get(2).temp), e.returnVal.accept(this).unEx()));
		}
		

		frags.add(new ProcFrag(bodyStms, currFrame));

		return null;


	}
	public Translate.Translate.Exp visit(MulExpr e){
		Translate.Tree.Exp left = e.e1.accept(this).unEx();
		Translate.Tree.Exp right = e.e2.accept(this).unEx();
		return new Ex( new BINOP(BINOP.MUL, left, right));

	}
	public Translate.Translate.Exp visit(NegExpr e){
		return new Ex(new BINOP(1, new CONST(0), e.e1.accept(this).unEx()));
	}
	public Translate.Translate.Exp visit(NewArrayExpr e){
		ArrayList<Translate.Tree.Exp> args = new ArrayList<Translate.Tree.Exp>();
		
		TEMP t = new TEMP(new Temp());
		args.add(t);
		args.add(t);
		return new Ex(new ESEQ(new MOVE(t, e.dimensions.get(0).accept(this).unEx() ), new CALL(new NAME(new Label("_new")), args)));
	}
	public Translate.Translate.Exp visit(NewObjectExpr e){
		String s = ((IdentifierType)e.type).id;
		
		//System.out.println(s);
		OBJECT o = (OBJECT)objectTb.get(s);
		//if(o.myClass.name.equals()){
		int k;
		if(o.fields == null){
			k = 0;
		}else{
			k = o.fields.fields.size();
		}
		
		ArrayList<Translate.Tree.Exp> t = new ArrayList<Translate.Tree.Exp>();
		t.add(new CONST(k));
		t.add(new NAME(new Label(s+"_vtable")));
		return new Ex(new CALL(new NAME(new Label("_new")), t));
		
	}
	public Translate.Translate.Exp visit(NotEqExpr e){
		Translate.Tree.Exp left = e.e1.accept(this).unEx();
		Translate.Tree.Exp right = e.e2.accept(this).unEx();
		return new RelCx(CJUMP.NE, left, right); 
	}
	
	public Translate.Translate.Exp visit(NotExpr e){
		Translate.Tree.Exp ex = e.e1.accept(this).unEx();
		//Translate.Tree.Exp right = e.e2.accept(this).unEx();
		
		
		return new Ex(new BINOP(BINOP.BITXOR, ex, new CONST(1))); 
	}

	public Translate.Translate.Exp visit(NullExpr e){
		return new Ex(new CONST(0)); 
	}
	public Translate.Translate.Exp visit(OrExpr e){
		IfThenElseExp ift = new IfThenElseExp();
		Translate.Translate.Exp e1 = e.e1.accept(this);
		Translate.Translate.Exp e2 = e.e2.accept(this);
		
		ift.newIfThenElseExp(e1, new Ex(new CONST(1)),
				e2);
		return ift;
  }
 
  public Translate.Translate.Exp visit(Program e){
	  
	  for(ClassDecl c : e.classes){
		  		c.checktype.accept(this);
		  	}

		for(ClassDecl c : e.classes){
			c.accept(this);
		}
		return null;

	}
	public Translate.Translate.Exp visit(Stmt e){
		return null; 
	}
	public Translate.Translate.Exp visit(StringLiteral e){
		//TODO: make new datafrag
		Label l = new Label();
		frags.add(new DataFrag(e.value, l));
		return new Ex(new NAME(l));
	}
	public Translate.Translate.Exp visit(SubExpr e){
		Translate.Tree.Exp left = e.e1.accept(this).unEx();
		Translate.Tree.Exp right = e.e2.accept(this).unEx();
		return new Ex(new BINOP(BINOP.MINUS, left, right));

	}
	public Translate.Translate.Exp visit(ThisExpr e){
		return new Ex(new TEMP(currFrame.FP));
//		TEMP t = new TEMP(new Temp());
//		Label l = new Label();
//		SEQ s = new SEQ(new MOVE(new TEMP(currFrame.FP.temp), t), new SEQ(
//				new CJUMP(0, t, new CONST(0), currFrame.badPtr(), l),
//				new LABEL(l)));
//		java.util.ArrayList<Translate.Tree.Exp> argsList = new java.util.ArrayList<Translate.Tree.Exp>();
//		argsList.add(t);
//		CALL c = new CALL(new MEM(new BINOP(0, new MEM(new BINOP(0, t, new CONST(-4))), new CONST(e.typeIndex*4))),t);
		
	}
	public Translate.Translate.Exp visit(ThreadDecl e){
		currClass = e;
		//System.out.println("threaddecl calls type");
		//e.checktype.accept(this);

		for(MethodDecl m : e.methods){
			m.accept(this);
		}
		return null;
	}
	public Translate.Translate.Exp visit(TrueExpr e){
		return new Ex(new CONST(1));
	}

	public Translate.Translate.Exp visit(VarDecl e){
		InReg r = (InReg)currFrame.allocLocal(false);
		tb.put(e.name, r);
		if(e.init == null){
			return new Nx(new MOVE(new TEMP(r.temp), new CONST(0)));
		}
		
		return new Nx(new MOVE(new TEMP(r.temp), e.init.accept(this).unEx()));
		 
	}
	public Translate.Translate.Exp visit(VoidDecl e){
		currMeth = e;
		//System.out.println(e.name);
		Translate.Tree.Stm varStms = null;
		Translate.Tree.Stm bodyStms = null;
		if(e.name.equals("main")){
			//currFrame = (MipsFrame)type.newFrame(new Label(e.name), e.params.size());
			currFrame = new MipsFrame(new Label(e.name));
		}else{
			//currFrame = (MipsFrame)type.newFrame(new Label(currClass.name + "." + e.name), e.params.size());
			currFrame = new MipsFrame(new Label(currClass.name + "." + e.name));
		}
		
		//for(int i = 0; i < e.params.size(); i++){
			//tb.put(e.params.get(i).name, currFrame.allocFormal(false));
		//}
		//This should add the list of formals and actuals
		e.checktype.accept(this);
		if(e.locals.size() != 0){
			varStms = e.locals.get(0).accept(this).unNx();
			for(int i = 1; i < e.locals.size(); i++){
				varStms = new SEQ(varStms, e.locals.get(i).accept(this).unNx());
			}

		}

		if(e.stmts.size() != 0){
			if(varStms == null){
				bodyStms = e.stmts.get(0).accept(this).unNx();
			}else{
				bodyStms = new SEQ(varStms, e.stmts.get(0).accept(this).unNx());
			}

			for(int i = 1; i < e.stmts.size(); i++){
				bodyStms = new SEQ(bodyStms , e.stmts.get(i).accept(this).unNx());
			}

		}else{
			bodyStms = varStms;
		}
		//boolean flag = false;
		

		//if(varStms == null && bodyStms == null){
			//flag = true;
			//dont know what to do here
			//System.out.println("wierd case");
			//bodyStms = new MOVE(new TEMP(regs.get(2).temp), e.returnVal.accept(this).unEx());
			
		//}
		
		//if(!flag){
			//uhhh
		//	bodyStms = new SEQ(bodyStms, new MOVE(new TEMP(regs.get(2).temp), e.returnVal.accept(this).unEx()));
		//}
		

		frags.add(new ProcFrag(bodyStms, currFrame));

		return null; 
	}
	public Translate.Translate.Exp visit(WhileStmt e){
		Label done = new Label();
		Label start = new Label();
		Label body = new Label();
		Stm bodyCon = e.test.accept(this).unCx(body, done);
		SEQ s = new SEQ(new LABEL(start),bodyCon);
		SEQ s2 = new SEQ(new SEQ(new LABEL(body), e.body.accept(this).unNx()), new JUMP(start));
		return new Nx (new SEQ(new SEQ(s, s2), new LABEL(done)));
		
		//return new Nx();
	}
	public Translate.Translate.Exp visit(XinuCallStmt e){
		 
		ArrayList<Translate.Tree.Exp> args = new ArrayList<Translate.Tree.Exp>();
		for(Expr e1 : e.args){
			args.add(e1.accept(this).unEx());
		}
		
		return new Ex(new CALL(new NAME(new Label("_"+e.method)), args));
	}
	public Translate.Translate.Exp visit(XinuCallExpr e){
		ArrayList<Translate.Tree.Exp> args = new ArrayList<Translate.Tree.Exp>();
		for(Expr e1 : e.args){
			args.add(e1.accept(this).unEx());
		}
		
		return new Ex(new CALL(new NAME(new Label("_"+e.method)), args));
	}

	public Translate.Translate.Exp visit(ARRAY a){
		return null; 
	}

	public Translate.Translate.Exp visit(BOOLEAN a){
		return null; 
	}

	public Translate.Translate.Exp visit(CLASS a){
		//Label cname = new Label(a.name);
		String s = a.name;
		Translate.Translate.DataFrag dfrag = new Translate.Translate.DataFrag(s);
		dfrag.c = a;
		for(FIELD f: a.instance.methods.fields){
			dfrag.flist.add(((FUNCTION)f.type));
		}
		//System.out.println(a.name);
	
		objectTb.put(a.name, a.instance);
		frags.add(dfrag);
		return null;
	}

	public Translate.Translate.Exp visit(FIELD a){
		return null; 
	}

	public Translate.Translate.Exp visit(FUNCTION a){
		return null; 
	}

	public Translate.Translate.Exp visit(INT a){
		return null; 
	}

	public Translate.Translate.Exp visit(NIL a){
		return null; 
	}

	public Translate.Translate.Exp  visit(OBJECT a){
		return null; 
	}

	public Translate.Translate.Exp visit(RECORD a){
		return null; 
	}

	public Translate.Translate.Exp visit(STRING a){
		return null; 
	}

	public Translate.Translate.Exp visit(VOID a){
		return null; 
	}



}// Visitor