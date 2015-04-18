package Translate;
import Temp.*;
import Tree.*;

public class IfThenElseExp extends Exp{
	public Exp cond, a, b;
	public Label t = new Label();
	public Label f = new Label();
	public Label join = new Label();
	
	public IfThenElseExp(){
		;
	}
	public void newIfThenElseExp(Exp cc, Exp aa, Exp bb) {
	cond=cc; a=aa; b=bb;
	}
	public Stm unCx(Label tt, Label ff) {
//		Stm tStm = a.unCx(tt, ff);
//		Stm fStm = b.unCx(tt, ff);
//		CJUMP cj = new CJUMP(CJUMP.NE, cond.unEx(), new CONST(0), t, f);
//		SEQ s1 = new SEQ(new LABEL(t), tStm);
//		SEQ s2 = new SEQ(new LABEL(f), fStm);
//	
//		return new SEQ(cj, new SEQ(s1, s2));
		
		
//		return new SEQ(cond.unCx(t, f),new SEQ(new SEQ(new LABEL(t),a.unCx(tt, ff)),
//				new SEQ(new LABEL(f),b.unCx(tt, ff))));
		
		
		Stm aStm = null;
		if(a == null){
			System.out.println("a is null");
		}else{
			aStm = a.unCx(tt, ff);
			if (aStm instanceof JUMP) {
				JUMP aJump = (JUMP) aStm;
				if (aJump.exp instanceof NAME) {
					//System.out.println("HIT");
					NAME aName = (NAME) aJump.exp;
					aStm = null;
					t = aName.label;
				}
			}
		}
		
			
			
		Stm bStm = null;
		
		if(b == null){
			System.out.println("b is null");
		}else{
			bStm = b.unCx(tt, ff);
			if (bStm instanceof JUMP) {
				JUMP bJump = (JUMP) bStm;
				if (bJump.exp instanceof NAME) {
					//System.out.println("HIT");
					NAME bName = (NAME) bJump.exp;
					bStm = null;
					f = bName.label;
				}
			}
//			if (bStm instanceof CJUMP) {
//				CJUMP bCJump = (CJUMP) bStm;
//				if (bCJump.relop == CJUMP.NE) {
//					
//				}
//			}
		}
		
		
		
		
		Stm condStm = cond.unCx(t, f);

		if (aStm == null && bStm == null)
			return condStm;
		if (aStm == null)
			return new SEQ(condStm, new SEQ(new LABEL(f), bStm));
		if (bStm == null)
			return new SEQ(condStm, new SEQ(new LABEL(t), aStm));
		return new SEQ(condStm, new SEQ(new SEQ(new LABEL(t), aStm), new SEQ(new LABEL(f), bStm)));
	}
	public Tree.Exp unEx() {
//		Tree.Exp tExp = a.unEx();
//		Tree.Exp fExp = b.unEx();
//		Stm c = cond.unCx(t, f);
//		TEMP r = new TEMP(new Temp.Temp());
//		
//		SEQ s1 = new SEQ(new LABEL(f), new SEQ(new MOVE(r, fExp), new JUMP(join)));
//		
//		SEQ s2 = new SEQ(c, new SEQ(new LABEL(t), new SEQ(new MOVE(r, tExp), new JUMP(join))), s1);
//		
//		return new ESEQ(new SEQ(s2, new LABEL(join)), r);
		//return new LABEL(new Label("LOSER"));

		//return null;
		//return null;
		//Temp r = new Temp();
		//SEQ s1 = new SEQ(new MOVE(new TEMP(r)n, e2.unEx()), JUMP(new NAME(join), new java.util.LinkedList<Label>()));
		//SEQ s2 = new SEQ()
		Temp r = new Temp();
		Tree.Exp tExp = a.unEx();
		Tree.Exp fExp = b.unEx();
		
		//maybe
		if (tExp == null)
			return null;
	
		if (tExp == null)
			return null;
//		if(tExp instanceof CONST && tExp instanceof CONST){
//			CONST tc = (CONST)tExp;
//			CONST fc = (CONST)fExp;
//			if(tc.value == 0 && fc.value == 0){
//				return r;
//			}else if(tc.value == 1 && fc.value == 1){
//				return r;
//			}
//		}
//		Tree.Exp con = cond.unEx();
//		if(con instanceof BINOP && ((BINOP)con).binop == BINOP.BITXOR){
//			//((BINOP)cond).left.
//			BINOP bcon = (BINOP) con;
//			Tree.Exp conNested = bcon.left;
//			if ((CJUMP)conNested instanceof CJUMP) {
//				CJUMP cj = new CJUMP(conNested.relop,conNested.left,conNested.right,t,f);
//				return new ESEQ(new SEQ(new SEQ(cj,new SEQ(new SEQ(new LABEL(t),
//						new SEQ(new MOVE(new TEMP(r),tExp),
//								new JUMP(join))),
//								new SEQ(new LABEL(f),
//								new SEQ(new MOVE(new TEMP(r),fExp),
//										new JUMP(join))))),
//										new LABEL(join)),new TEMP(r));
//			} else {
//			CJUMP cj =  new CJUMP(CJUMP.NE, ((BINOP)con).left, new CONST(0), f ,t);
//			return new ESEQ(new SEQ(new SEQ(cj,new SEQ(new SEQ(new LABEL(t),
//					new SEQ(new MOVE(new TEMP(r),tExp),
//							new JUMP(join))),
//							new SEQ(new LABEL(f),
//							new SEQ(new MOVE(new TEMP(r),fExp),
//									new JUMP(join))))),
//									new LABEL(join)),new TEMP(r));
//			}
//		}
		
		return new ESEQ(new SEQ(new SEQ(cond.unCx(t, f),new SEQ(new SEQ(new LABEL(t),
				new SEQ(new MOVE(new TEMP(r),tExp),
						new JUMP(join))),
						new SEQ(new LABEL(f),
						new SEQ(new MOVE(new TEMP(r),fExp),
								new JUMP(join))))),
								new LABEL(join)),new TEMP(r));
		
		
		
	}
	public Stm unNx() {
//		CJUMP cj = new CJUMP(1, cond.unEx(), new CONST(0), t, f);
//		SEQ st = new SEQ(new LABEL(t), a.unNx()); 
//		SEQ tj = new SEQ(st, new JUMP(new NAME(join), new java.util.LinkedList<Label>()));
//		SEQ sf = new SEQ(new LABEL(f), b.unNx()); 
//		SEQ fj = new SEQ(sf, new JUMP(new NAME(join), new java.util.LinkedList<Label>()));
//		SEQ tjfj = new SEQ(tj, fj);
//		SEQ s1 = new SEQ(cj, tjfj);
//		return new SEQ(s1, new LABEL(join));
		
//		if (b == null){
//            return new SEQ(cond.unCx(t, join),
//                            new SEQ(new LABEL(t),
//                            new SEQ(a.unNx(),
//                            new LABEL(join))));
//		}else{
//            return new SEQ(cond.unCx(t, f),
//                            new SEQ(new LABEL(t),
//                            new SEQ(a.unNx(),
//                            new SEQ(new JUMP(new NAME(join), new java.util.LinkedList<Label>()),
//                            new SEQ(new LABEL(f),
//                            new SEQ(b.unNx(),
//                            new LABEL(join)))))));
//		}
		
		Stm aStm;
		if (a == null){
		//if (aStm instanceof JUMP){
			t = join;
			aStm = null;
		}else{
			aStm = a.unNx();
			aStm = new SEQ(new SEQ(new LABEL(t), aStm), new JUMP(join));
		}
		Stm bStm;
		if (b == null){
		//if (bStm instanceof JUMP){
			f = join;
			bStm = null;
		}else{
			bStm = b.unNx();
			bStm = new SEQ(new SEQ(new LABEL(f), bStm), new JUMP(join));
		}
//		Tree.Exp con = cond.unEx();
	Stm condStm;
//		System.out.println("HERE");
//		if(con instanceof BINOP && ((BINOP)con).binop == BINOP.BITXOR){
//			System.out.println("INSIDE");
//			//((BINOP)cond).left.
//			//condStm =  new CJUMP(CJUMP.NE, ((BINOP)con).left, new CONST(0), f ,t);
//			Tree.Exp expression = new Ex(((BINOP)con).left);
//			condStm = expression.unCx(f, t);
//		}
//		
//		else{
			condStm = cond.unCx(t, f);
//		}
		
		
		
		if (aStm == null && bStm == null){
			return cond.unNx();
		}
		
		
		if (aStm == null){
			return new SEQ(new SEQ(condStm, bStm), new LABEL(join));
		}
		if (bStm == null){
			return new SEQ(new SEQ(condStm, aStm), new LABEL(join));
		}
		
		
		
		

		
		//if (bStm == null)
		

		

		return new SEQ(new SEQ(condStm, new SEQ(aStm, bStm)), new LABEL(join));
		
		
		
	}
}
