package Mips;
import Tree.*;
import Assem.*;
import Temp.*;
import java.util.ArrayList;
public class Codegen implements CodeVisitor{
	
	Mips.MipsFrame frame;
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
	
	public Codegen(List<Stm> stms){
		instructions = new ArrayList<Instr>();
		//frame = (MipsFrame)f;
		for(Stm s : stms){
			s.accept(this);
		}
	}
	
	
	
	public ArrayList<Instr> instructions;
	
	public Temp visit(BINOP n){
		Temp t  = new Temp();
		int k = n.binop;
		int j;
		OPER o = null;
		switch(k){
			case BINOP.PLUS:
				if(n.left instanceof CONST ^ n.right instanceof CONST){
					
					if(n.left instanceof CONST){
						j = ((CONST)n.left).value;
						o = new OPER("addi\t'd0,\t's0,\t"+j, new Temp[]{t}, new Temp[]{n.right.accept(this)}, null);
					}else{
						j = ((CONST)n.right).value;
						o = new OPER("addi\t'd0,\t's0,\t"+j, new Temp[]{t}, new Temp[]{n.left.accept(this)}, null);
					}
					
				}else{
					o = new OPER("add\t'd0,\t's0,\t's1", new Temp[]{t}, new Temp[]{n.left.accept(this), n.right.accept(this)}, null);
				}
				break;
			case BINOP.MINUS:
				if(n.right instanceof CONST && !(n.left instanceof CONST)){
					
					j = ((CONST)n.left).value;
					o = new OPER("addi\t'd0,\t's0,\t"+-j, new Temp[]{t}, new Temp[]{n.left.accept(this)}, null);
					
					
				}else{
					o = new OPER("sub\t'd0,\t's0,\t's1", new Temp[]{t}, new Temp[]{n.left.accept(this), n.right.accept(this)}, null);
				}
				break;
			case BINOP.MUL:
				if(n.left instanceof CONST ^ n.right instanceof CONST){
					
					if(n.left instanceof CONST  && (((j = ((CONST)n.right).value) > 0) && ((j & (j - 1)) == 0))){
						j = Integer.bitCount(j-1);
						o = new OPER("sll\t'd0,\t's0,\t"+j, new Temp[]{t}, new Temp[]{n.right.accept(this)}, null);
					}else if(n.right instanceof CONST && (((j = ((CONST)n.right).value) > 0) && ((j & (j - 1)) == 0))){
						j = Integer.bitCount(j);
						
						o = new OPER("sll\t'd0,\t's0,\t"+j, new Temp[]{t}, new Temp[]{n.left.accept(this)}, null);
					}else{
						o = new OPER("mulo\t'd0,\t's0,\t's1", new Temp[]{t}, new Temp[]{n.left.accept(this), n.right.accept(this)}, null);
					}
					
				}else{
					o = new OPER("mulo\t'd0,\t's0,\t's1", new Temp[]{t}, new Temp[]{n.left.accept(this), n.right.accept(this)}, null);
				}
				break;
				
			case BINOP.DIV:
				
				
				if(n.right instanceof CONST  && (((j = ((CONST)n.right).value) > 0) && ((j & (j - 1)) == 0))){
					j = Integer.bitCount(j-1);
					o = new OPER("sra\t'd0,\t's0,\t"+j, new Temp[]{t}, new Temp[]{n.right.accept(this)}, null);
				
				}else{
					o = new OPER("div\t'd0,\t's0,\t's1", new Temp[]{t}, new Temp[]{n.left.accept(this), n.right.accept(this)}, null);
				}
					
				
				break;
			case BINOP.AND:
				if(n.left instanceof CONST ^ n.right instanceof CONST){
					
					if(n.left instanceof CONST){
						j = ((CONST)n.left).value;
						o = new OPER("andi\t'd0,\t's0,\t"+j, new Temp[]{t}, new Temp[]{n.right.accept(this)}, null);
					}else{
						j = ((CONST)n.right).value;
						o = new OPER("andi\t'd0,\t's0,\t"+j, new Temp[]{t}, new Temp[]{n.left.accept(this)}, null);
					}
					
				}else{
					o = new OPER("and\t'd0,\t's0,\t's1", new Temp[]{t}, new Temp[]{n.left.accept(this), n.right.accept(this)}, null);
				}
				break;
				
			case BINOP.OR:
				if(n.left instanceof CONST ^ n.right instanceof CONST){
					
					if(n.left instanceof CONST){
						j = ((CONST)n.left).value;
						o = new OPER("ori\t'd0,\t's0,\t"+j, new Temp[]{t}, new Temp[]{n.right.accept(this)}, null);
					}else{
						j = ((CONST)n.right).value;
						o = new OPER("ori\t'd0,\t's0,\t"+j, new Temp[]{t}, new Temp[]{n.left.accept(this)}, null);
					}
					
				}else{
					o = new OPER("or\t'd0,\t's0,\t's1", new Temp[]{t}, new Temp[]{n.left.accept(this), n.right.accept(this)}, null);
				}
				break;
				
			case BINOP.LSHIFT:
				if(n.right instanceof CONST && !(n.left instanceof CONST)){
					
					j = ((CONST)n.left).value;
					o = new OPER("sll\t'd0,\t's0,\t"+-j, new Temp[]{t}, new Temp[]{n.left.accept(this)}, null);
					
					
				}else{
					o = new OPER("sllv\t'd0,\t's0,\t's1", new Temp[]{t}, new Temp[]{n.left.accept(this), n.right.accept(this)}, null);
				}
				break;
			case BINOP.RSHIFT:
				if(n.right instanceof CONST && !(n.left instanceof CONST)){
					
					j = ((CONST)n.left).value;
					o = new OPER("srl\t'd0,\t's0,\t"+-j, new Temp[]{t}, new Temp[]{n.left.accept(this)}, null);
					
					
				}else{
					o = new OPER("srlv\t'd0,\t's0,\t's1", new Temp[]{t}, new Temp[]{n.left.accept(this), n.right.accept(this)}, null);
				}
				break;
				
			case BINOP.ARSHIFT:
				if(n.right instanceof CONST && !(n.left instanceof CONST)){
					
					j = ((CONST)n.left).value;
					o = new OPER("sra\t'd0,\t's0,\t"+-j, new Temp[]{t}, new Temp[]{n.left.accept(this)}, null);
					
					
				}else{
					o = new OPER("srav\t'd0,\t's0,\t's1", new Temp[]{t}, new Temp[]{n.left.accept(this), n.right.accept(this)}, null);
				}
				break;
				
			case BINOP.BITXOR:
				if(n.left instanceof CONST ^ n.right instanceof CONST){
					
					if(n.left instanceof CONST){
						j = ((CONST)n.left).value;
						o = new OPER("xori\t'd0,\t's0,\t"+j, new Temp[]{t}, new Temp[]{n.right.accept(this)}, null);
					}else{
						j = ((CONST)n.right).value;
						o = new OPER("xori\t'd0,\t's0,\t"+j, new Temp[]{t}, new Temp[]{n.left.accept(this)}, null);
					}
					
				}else{
					o = new OPER("xor\t'd0,\t's0,\t's1", new Temp[]{t}, new Temp[]{n.left.accept(this), n.right.accept(this)}, null);
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
//		Assem.MOVE move = new Assem.MOVE("move\t'd0,\t's0", new Temp(4), t);
//		instructions.add(move);
//		offset += 4;
		ArrayList<Temp> args = new ArrayList<Temp>();
		int i = 0;
		int offset = 0;
		for(Exp e : n.args){
			offset += 4;
			if(i < 4){				
				Temp t = e.accept(this);
				Assem.MOVE move = new Assem.MOVE("move\t'd0,\t's0", new Temp(4 + i), t);
				instructions.add(move);
				args.add(new Temp(4 + i));
			}else{
				Temp t2 = e.accept(this);
				OPER o2 = new OPER("sw\t's0,\t" + offset + "('s2)", null, new Temp[]{t2, new Temp(29)}, null);
				instructions.add(o2);
				//args.add(new Temp(4 + i));
				//TODO see what happens with multiple args for contructor
			}
			i++;
		}
		
		if(frame.maxArgOffset < offset){
			frame.maxArgOffset = offset;
		}
		
		if(n.func instanceof NAME){
			NAME name = (NAME)n.func;
			OPER o3 = new OPER("jal\t"+name.label.name, caller, args.toArray(new Temp[args.size()]), null);
			instructions.add(o3);
		}else{
			Temp t3 = n.func.accept(this);
			args.add(0, t3);
			OPER o4 = new OPER("jalr\t's0", caller, args.toArray(new Temp[args.size()]), null);
			instructions.add(o4);
		}
		
		
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
				o = new OPER("beq\t's0,\t's1,\t,'j0\t", null, new Temp[]{n.left.accept(this), n.right.accept(this)}, list);
				break;
			case CJUMP.NE:
				o = new OPER("bne\t's0,\t's1,\t,'j0\t", null, new Temp[]{n.left.accept(this), n.right.accept(this)}, list);
				break;
			case CJUMP.LT:
				o = new OPER("blt\t's0,\t's1,\t,'j0\t", null, new Temp[]{n.left.accept(this), n.right.accept(this)}, list);
				break;
			case CJUMP.GT:
				o = new OPER("bgt\t's0,\t's1,\t,'j0\t", null, new Temp[]{n.left.accept(this), n.right.accept(this)}, list);
				break;
			case CJUMP.LE:
				o = new OPER("ble\t's0,\t's1,\t,'j0\t", null, new Temp[]{n.left.accept(this), n.right.accept(this)}, list);
				break;
			default:
				o = new OPER("bge\t's0,\t's1,\t,'j0\t", null, new Temp[]{n.left.accept(this), n.right.accept(this)}, list);
				break;
		}
		instructions.add(o);
	}
	public Temp visit(CONST n){
		if(n.value == 0){
			return new Temp(0);
		}else{
			Temp t = new Temp();
			OPER o = new OPER("addi\t'd0,\t's0,\t"+n.value,new Temp[]{t}, new Temp[]{new Temp(0)}, null);
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
			
			OPER o = new OPER("b\t"+name.label.toString(),null, null, list);
			instructions.add(o);
		}else{
			OPER o = new OPER("jr\t's0",null, new Temp[]{n.exp.accept(this)}, list);
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
			if(b.binop == BINOP.PLUS && b.right instanceof CONST){
				int k = ((CONST)b.right).value;
				Temp t = new Temp();
				OPER o = new OPER("lw\t'd0,\t"+ k + "('s0)", new Temp[]{t}, new Temp[]{b.left.accept(this)}, null);
				instructions.add(o);
				return t;
			}else if(b.binop == BINOP.PLUS && b.left instanceof CONST){
				int k = ((CONST)b.left).value;
				Temp t = new Temp();
				OPER o = new OPER("lw\t'd0,\t"+ k + "('s0)", new Temp[]{t}, new Temp[]{b.right.accept(this)}, null);
				instructions.add(o);
				return t;
			}else{
				Temp t = new Temp();
				OPER o = new OPER("lw\t'd0,\t0('s0)", new Temp[]{t}, new Temp[]{ n.exp.accept(this)}, null);
				instructions.add(o);
				return t;
			}
			
		}else{
			Temp t = new Temp();
			OPER o = new OPER("lw\t'd0,\t0('s0)", new Temp[]{t}, new Temp[]{ n.exp.accept(this)}, null);
			instructions.add(o);
			return t;
		}
	}
	public void visit(Tree.MOVE n){
		if(n.dst instanceof MEM){
			MEM m = (MEM)n.dst;
			if(m.exp instanceof BINOP){
				BINOP b = (BINOP)m.exp;
				if(b.binop == BINOP.PLUS && b.right instanceof CONST){
					int k = ((CONST)b.right).value;
					OPER o = new OPER("sw\t'd0,\t"+ k + "('s0)", null, new Temp[]{n.src.accept(this), b.left.accept(this)}, null);
					instructions.add(o);
				}else if(b.binop == BINOP.PLUS && b.left instanceof CONST){
					int k = ((CONST)b.left).value;
					OPER o = new OPER("sw\t'd0,\t"+ k + "('s0)", null, new Temp[]{n.src.accept(this), b.right.accept(this)}, null);
					instructions.add(o);
				}else{
					//TODO not sure here
					OPER o = new OPER("sw\t'd0,\t0('s0)", new Temp[]{n.src.accept(this)}, new Temp[]{ n.dst.accept(this)}, null);
					instructions.add(o);
				}
				
			}else{
				//not BINOP
				OPER o = new OPER("sw\t'd0,\t0('s0)", new Temp[]{n.src.accept(this)}, new Temp[]{ n.dst.accept(this)}, null);
				instructions.add(o);
				
			}
		}else{
			//not MEM
			Assem.MOVE move = new Assem.MOVE("move\t'd0,\t's0", n.dst.accept(this), n.src.accept(this));
			instructions.add(move);
		}
	}
	public Temp visit(NAME n){
		Temp t = new Temp();
		OPER o = new OPER("la\t'd0,\t"+n.label, new Temp[]{t}, null, null);
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
