package Mips;
import Tree.*;
import Assem.*;
import Temp.*;
import java.util.ArrayList;
import java.util.List;
public class Codegen implements CodeVisitor{
	
	MipsFrame frame;
	Temp caller[] = {
			new Temp(31),
			new Temp(4),
			new Temp(5),
			new Temp(6),
			new Temp(7),
			new Temp(8),
			new Temp(9),
			new Temp(10),
			new Temp(11),
			new Temp(12),
			new Temp(13),
			new Temp(14),
			new Temp(15),
			new Temp(24),
			new Temp(25),
			new Temp(2),
			new Temp(3),
	};
	
	public Codegen(){
		instructions = new ArrayList<Instr>();
	}
	
	public Codegen(Frame.Frame f){
		instructions = new ArrayList<Instr>();
		frame = (MipsFrame)f;
	}
	
	public Codegen(List<Stm> stms, MipsFrame f){
		instructions = new ArrayList<Instr>();
		frame = f;
		for(Stm s : stms){
			s.accept(this);
		}
	}
	
	
	
	public ArrayList<Instr> instructions;
	
	public Temp visit(BINOP n){
		Temp t = new Temp();
		int k = n.binop;
		int j;
		OPER o = null;
		switch(k){
			case BINOP.PLUS:
				if(n.left instanceof CONST || n.right instanceof CONST){
					
					if(n.left instanceof CONST){
						j = ((CONST)n.left).value;
						if(j == 0){
							//t.count--;
						
							return n.right.accept(this);
						}else{
							o = new OPER("\taddi\t`d0,\t`s0,\t"+j, new Temp[]{t}, new Temp[]{n.right.accept(this)}, null);
						}
						
						
					}else{
						j = ((CONST)n.right).value;
						if(j == 0){
							//t.count--;
							return n.left.accept(this);
						}else{
						
							o = new OPER("\taddi\t`d0,\t`s0,\t"+j, new Temp[]{t}, new Temp[]{n.left.accept(this)}, null);
						}
					}
					
				}else{
					o = new OPER("\tadd\t`d0,\t`s0,\t`s1", new Temp[]{t}, new Temp[]{n.left.accept(this), n.right.accept(this)}, null);
				}
				break;
			case BINOP.MINUS:
				if(n.right instanceof CONST){
					
					j = ((CONST)n.right).value;
					
					o = new OPER("\taddi\t`d0,\t`s0,\t"+-j, new Temp[]{t}, new Temp[]{n.left.accept(this)}, null);
					
					
				}else{
					o = new OPER("\tsub\t`d0,\t`s0,\t`s1", new Temp[]{t}, new Temp[]{n.left.accept(this), n.right.accept(this)}, null);
				}
				break;
			case BINOP.MUL:
				if(n.left instanceof CONST || n.right instanceof CONST){
					
					if(n.left instanceof CONST  && (((j = ((CONST)n.left).value) > 0) && ((j & (j - 1)) == 0))){
						
						j = Integer.bitCount(j-1);
						o = new OPER("\tsll\t`d0,\t`s0,\t"+j, new Temp[]{t}, new Temp[]{n.right.accept(this)}, null);
					}else if(n.right instanceof CONST && (((j = ((CONST)n.right).value) > 0) && ((j & (j - 1)) == 0))){
						j = Integer.bitCount(j-1);
						
						o = new OPER("\tsll\t`d0,\t`s0,\t"+j, new Temp[]{t}, new Temp[]{n.left.accept(this)}, null);
					}else{
						o = new OPER("\tmulo\t`d0,\t`s0,\t`s1", new Temp[]{t}, new Temp[]{n.left.accept(this), n.right.accept(this)}, null);
					}
					
				}else{
					o = new OPER("\tmulo\t`d0,\t`s0,\t`s1", new Temp[]{t}, new Temp[]{n.left.accept(this), n.right.accept(this)}, null);
				}
				break;
				
			case BINOP.DIV:
				
				
				if(n.right instanceof CONST  && (((j = ((CONST)n.right).value) > 0) && ((j & (j - 1)) == 0))){
					j = Integer.bitCount(j-1);
					o = new OPER("\tsra\t`d0,\t`s0,\t"+j, new Temp[]{t}, new Temp[]{n.right.accept(this)}, null);
				
				}else{
					o = new OPER("\tdiv\t`d0,\t`s0,\t`s1", new Temp[]{t}, new Temp[]{n.left.accept(this), n.right.accept(this)}, null);
				}
					
				
				break;
			case BINOP.AND:
				if(n.left instanceof CONST || n.right instanceof CONST){
					
					if(n.left instanceof CONST){
						j = ((CONST)n.left).value;
						o = new OPER("\tandi\t`d0,\t`s0,\t"+j, new Temp[]{t}, new Temp[]{n.right.accept(this)}, null);
					}else{
						j = ((CONST)n.right).value;
						o = new OPER("\tandi\t`d0,\t`s0,\t"+j, new Temp[]{t}, new Temp[]{n.left.accept(this)}, null);
					}
					
				}else{
					o = new OPER("\tand\t`d0,\t`s0,\t`s1", new Temp[]{t}, new Temp[]{n.left.accept(this), n.right.accept(this)}, null);
				}
				break;
				
			case BINOP.OR:
				if(n.left instanceof CONST || n.right instanceof CONST){
					
					if(n.left instanceof CONST){
						j = ((CONST)n.left).value;
						o = new OPER("\tori\t`d0,\t`s0,\t"+j, new Temp[]{t}, new Temp[]{n.right.accept(this)}, null);
					}else{
						j = ((CONST)n.right).value;
						o = new OPER("\tori\t`d0,\t`s0,\t"+j, new Temp[]{t}, new Temp[]{n.left.accept(this)}, null);
					}
					
				}else{
					o = new OPER("\tor\t`d0,\t`s0,\t`s1", new Temp[]{t}, new Temp[]{n.left.accept(this), n.right.accept(this)}, null);
				}
				break;
				
			case BINOP.LSHIFT:
				if(n.right instanceof CONST && !(n.left instanceof CONST)){
					
					j = ((CONST)n.left).value;
					o = new OPER("\tsll\t`d0,\t`s0,\t"+-j, new Temp[]{t}, new Temp[]{n.left.accept(this)}, null);
					
					
				}else{
					o = new OPER("\tsllv\t`d0,\t`s0,\t`s1", new Temp[]{t}, new Temp[]{n.left.accept(this), n.right.accept(this)}, null);
				}
				break;
			case BINOP.RSHIFT:
				if(n.right instanceof CONST && !(n.left instanceof CONST)){
					
					j = ((CONST)n.left).value;
					o = new OPER("\tsrl\t`d0,\t`s0,\t"+-j, new Temp[]{t}, new Temp[]{n.left.accept(this)}, null);
					
					
				}else{
					o = new OPER("srlv\t`d0,\t`s0,\t`s1", new Temp[]{t}, new Temp[]{n.left.accept(this), n.right.accept(this)}, null);
				}
				break;
				
			case BINOP.ARSHIFT:
				if(n.right instanceof CONST && !(n.left instanceof CONST)){
					
					j = ((CONST)n.left).value;
					o = new OPER("\tsra\t`d0,\t`s0,\t"+-j, new Temp[]{t}, new Temp[]{n.left.accept(this)}, null);
					
					
				}else{
					o = new OPER("\tsrav\t`d0,\t`s0,\t`s1", new Temp[]{t}, new Temp[]{n.left.accept(this), n.right.accept(this)}, null);
				}
				break;
				
			case BINOP.BITXOR:
				if(n.left instanceof CONST || n.right instanceof CONST){
					
					if(n.left instanceof CONST){
						j = ((CONST)n.left).value;
						o = new OPER("\txori\t`d0,\t`s0,\t"+j, new Temp[]{t}, new Temp[]{n.right.accept(this)}, null);
					}else{
						j = ((CONST)n.right).value;
						o = new OPER("\txori\t`d0,\t`s0,\t"+j, new Temp[]{t}, new Temp[]{n.left.accept(this)}, null);
					}
					
				}else{
					o = new OPER("\txor\t`d0,\t`s0,\t`s1", new Temp[]{t}, new Temp[]{n.left.accept(this), n.right.accept(this)}, null);
				}
				break;
			default:
				break;
		
		}
		
		instructions.add(o);
		return t;
	}
	public Temp visit(CALL n){
		//TODO not sure here
		//Temp
//		Assem.MOVE move = new Assem.MOVE("move\t`d0,\t`s0", new Temp(4), t);
//		instructions.add(move);
//		offset += 4;
		ArrayList<Temp> args = new ArrayList<Temp>();
		int i = 0;
		int offset = 0;
		for(Exp e : n.args){
			
			if(i < 4){				
				Temp t = e.accept(this);
				Assem.MOVE move = new Assem.MOVE("\tmove\t`d0,\t`s0", new Temp(4 + i), t);
				instructions.add(move);
				args.add(new Temp(4 + i));
			}else{
				Temp t2 = e.accept(this);
				OPER o2 = new OPER("\tsw\t`s0,\t" + offset + "(`s1)", null, new Temp[]{t2, new Temp(29)}, null);
				instructions.add(o2);
				//args.add(new Temp(4 + i));
				//TODO see what happens with multiple args for contructor
			}
			offset += 4;
			i++;
		}
		
		if(frame.maxArgOffset < offset){
			frame.maxArgOffset = offset;
		}
		
		if(n.func instanceof NAME){
			NAME name = (NAME)n.func;
			OPER o3 = new OPER("\tjal\t"+name.label.name, caller, args.toArray(new Temp[args.size()]), null);
			instructions.add(o3);
		}else{
			Temp t3 = n.func.accept(this);
			args.add(0, t3);
			OPER o4 = new OPER("\tjalr\t`s0", caller, args.toArray(new Temp[args.size()]), null);
			instructions.add(o4);
		}
		
		OPER o5 = new OPER("\t// Call sink", null, caller, null);
		instructions.add(o5);
		
		
		return new Temp(2);
	}
	public void visit(CJUMP n){
		int k = n.relop;
		ArrayList<Label> list = new ArrayList<Label>();
		list.add(n.iftrue);
		list.add(n.iffalse);
		OPER o = null;
		switch(k){
			case CJUMP.EQ:				
				o = new OPER("\tbeq\t`s0,\t`s1,\t`j0", null, new Temp[]{n.left.accept(this), n.right.accept(this)}, list);
				break;
			case CJUMP.NE:
				o = new OPER("\tbne\t`s0,\t`s1,\t`j0", null, new Temp[]{n.left.accept(this), n.right.accept(this)}, list);
				break;
			case CJUMP.LT:
				o = new OPER("\tblt\t`s0,\t`s1,\t`j0", null, new Temp[]{n.left.accept(this), n.right.accept(this)}, list);
				break;
			case CJUMP.GT:
				o = new OPER("\tbgt\t`s0,\t`s1,\t`j0", null, new Temp[]{n.left.accept(this), n.right.accept(this)}, list);
				break;
			case CJUMP.LE:
				o = new OPER("\tble\t`s0,\t`s1,\t`j0", null, new Temp[]{n.left.accept(this), n.right.accept(this)}, list);
				break;
			default:
				o = new OPER("\tbge\t`s0,\t`s1,\t`j0", null, new Temp[]{n.left.accept(this), n.right.accept(this)}, list);
				break;
		}
		instructions.add(o);
	}
	public Temp visit(CONST n){
		if(n.value == 0){
			return new Temp(0);
		
		}else if(n.value >= 65536/2){
			Temp t = new Temp();
			OPER o = new OPER("\tli\t`d0,\t"+n.value,new Temp[]{t}, null, null);
			instructions.add(o);
			return t;
		}else{
			Temp t = new Temp();
//			if(t.num == 126){
//				System.out.println("HERE Const");
//			}
			OPER o = new OPER("\taddi\t`d0,\t`s0,\t"+n.value,new Temp[]{t}, new Temp[]{new Temp(0)}, null);
			instructions.add(o);
			return t;
		}
		
	}
	public Temp visit(ESEQ n){
		return null;
	}
	
	public void visit(EXP n){
		Temp t = n.exp.accept(this);
		return;
	}
	public Temp visit(Exp n){
		return null;
	}
	
	public void visit(Stm n){
		return;
	}
	public void visit(JUMP n){
		ArrayList<Label> list = new ArrayList<Label>();
		for(Label l : n.targets){
			list.add(l);
		}
		
		if(n.exp instanceof NAME){
			NAME name = (NAME)n.exp;
			
			OPER o = new OPER("\tb\t`j0",null, null, list);
			instructions.add(o);
		}else{
			OPER o = new OPER("\tjr\t`s0",null, new Temp[]{n.exp.accept(this)}, list);
			instructions.add(o);
			
		}
	}
	public void visit(Tree.LABEL n){
		Assem.LABEL l = new Assem.LABEL(null, n.label);
		instructions.add(l);
	}
	public Temp visit(MEM n){
		if(n.exp instanceof BINOP){
			
			BINOP b = (BINOP)n.exp;
			if(b.binop == BINOP.PLUS && b.right instanceof CONST && ((CONST)b.right).value < 65536/2){
				int k = ((CONST)b.right).value;
				Temp t = new Temp();
				
				OPER o = new OPER("\tlw\t`d0,\t"+ k + "(`s0)", new Temp[]{t}, new Temp[]{b.left.accept(this)}, null);
				
				//OPER o = new OPER("\tlw\t`d0,\t"+ Integer.toString(k) + "+" + frame.name+"_framesize(`s0)", new Temp[]{t}, new Temp[]{b.left.accept(this)}, null);
				instructions.add(o);
				return t;
			}else if(b.binop == BINOP.PLUS && b.left instanceof CONST && ((CONST)b.right).value < 65536/2){
				int k = ((CONST)b.left).value;
				Temp t = new Temp();
				
				OPER o = new OPER("\tlw\t`d0,\t"+ k + "(`s0)", new Temp[]{t}, new Temp[]{b.right.accept(this)}, null);
				
				//OPER o = new OPER("\tlw\t`d0,\t"+Integer.toString(k) + "+" + frame.name+"_framesize(`s0)", new Temp[]{t}, new Temp[]{b.right.accept(this)}, null);
				instructions.add(o);
				return t;
			}else{
				Temp t = new Temp();
				
				OPER o = new OPER("\tlw\t`d0,\t(`s0)", new Temp[]{t}, new Temp[]{ n.exp.accept(this)}, null);
				instructions.add(o);
				return t;
				//return n.exp.accept(this);
			}
		}else if(n.exp instanceof CONST){
			int k = ((CONST)n.exp).value;
			Temp t = new Temp();
			
			
			//OPER o = new OPER("\tlw\t`d0,\t"+ k + "(`s0)", new Temp[]{t}, new Temp[]{b.right.accept(this)}, null);
			
			OPER o = new OPER("\tlw\t`d0,\t"+Integer.toString(k) + "+" + frame.name+"_framesize(`s0)", new Temp[]{t}, new Temp[]{new Temp(29)}, null);
			instructions.add(o);
			return t;
			
		}else{ //(n.exp instanceof TEMP){
			
			//OPER o = new OPER("\tlw\t`d0,\t0(`s0)", new Temp[]{t}, new Temp[]{ n.exp.accept(this)}, null);
			//instructions.add(o);
			return n.exp.accept(this);
		//}else{
		//	Temp t = new Temp();
			
		//	OPER o = new OPER("\tlw\t`d0,\t(`s0)", new Temp[]{t}, new Temp[]{ n.exp.accept(this)}, null);
		//	instructions.add(o);
		//	return t;
		}
	}
	public void visit(Tree.MOVE n){
		if(n.dst instanceof MEM){
			MEM m = (MEM)n.dst;
			if(m.exp instanceof BINOP){
				BINOP b = (BINOP)m.exp;
				if(b.binop == BINOP.PLUS && b.right instanceof CONST){
					int k = ((CONST)b.right).value;
					OPER o = new OPER("\tsw\t`s0,\t"+ k + "(`s1)", null, new Temp[]{n.src.accept(this), b.left.accept(this)}, null);
					instructions.add(o);
				}else if(b.binop == BINOP.PLUS && b.left instanceof CONST){
					int k = ((CONST)b.left).value;
					OPER o = new OPER("\tsw\t`s0,\t"+ k + "(`s1)", null, new Temp[]{n.src.accept(this), b.right.accept(this)}, null);
					instructions.add(o);
				}else{
					//TODO not sure here
					OPER o = new OPER("\tsw\t`s0,\t(`s1)", null, new Temp[]{n.src.accept(this), m.exp.accept(this)}, null);
					instructions.add(o);
				}
				
			}else{
				//not BINOP
				OPER o = new OPER("\tsw\t`s0,\t(`s1)", null, new Temp[]{n.src.accept(this), n.dst.accept(this) }, null);
				instructions.add(o);
				
			}
		}else{
			//not MEM
			Assem.MOVE move = new Assem.MOVE("\tmove\t`d0,\t`s0", n.dst.accept(this), n.src.accept(this));
			instructions.add(move);
		}
	}
	public Temp visit(NAME n){
		Temp t = new Temp();
		OPER o = new OPER("\tla\t`d0,\t"+n.label, new Temp[]{t}, null, null);
		instructions.add(o);
		return t;
	}
	public void visit(SEQ n){
		//n.left.accept(this);
		//n.right.accept(this);
	}
	public Temp visit(TEMP n){
		return n.temp;
	}
	
}
