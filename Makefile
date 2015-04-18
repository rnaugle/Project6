PARSER   = ReadFrags

JAVAC    = javac
JAVA     = java
JAVACC   = javacc
JJDOC    = jjdoc


JAVAC_FLAGS = -Xdepend

JAVACCGEN = Parser/SimpleCharStream.java Parser/ParseException.java \
        Parser/$(PARSER).java Parser/$(PARSER)Constants.java \
        Parser/$(PARSER)TokenManager.java Parser/Token.java \
        Parser/TokenMgrError.java

ASSEM	= Assem/Instr.java Assem/LABAL.java \
	Assem/MOVE.java Assem/OPER.java
	
CANON	= Canon/BasicBlocks.java Canon/Canon.java \
	Canon/TraceSchedule.java 
	
FRAME	= Frame/Access.java Frame/Frame.java

MIPS	= Mips/InFrame.java Mips/InReg.java \
	Mips/MipsFrame.java
	
TRANSLATE	=	Translate/Cx.java Translate/DataFrag.java \
	Translate/Ex.java Translate/Exp.java \
	Translate/Frag.java Translate/IfThenElseExp.java \
	Translate/Nx.java Translate/ProcFrag.java \
	Translate/RelCx.java
	
TREE	=	Tree/BINOP.java Tree/CALL.java \
	Tree/CJUMP.java Tree/CONST.java \
	Tree/ESEQ.java Tree/Exp.java \
	Tree/EXP.java Tree/Hospitable.java \
	Tree/IntVisitor.java Tree/JUMP.java \
	Tree/LABEL.java Tree/MEM.java \
	Tree/MOVE.java Tree/NAME.java \
	Tree/Print.java Tree/SEQ.java \
	Tree/Stm.java Tree/TEMP.java

SYMBOL	= Symbol/Symbol.java Symbol/Table.java

SRCS     =   $(ASSEM) $(SYMBOL) $(FRAME) $(MIPS) $(TRANSLATE) $(TREE) $(CANON) Parser/$(PARSER).java Assem/Main.java  Util/BoolList.java

all:  $(SRCS:.java=.class)

%.class : %.java
	$(JAVAC) $<



Parser/$(PARSER).java: Parser/$(PARSER).jj
	$(JAVACC)  $<

parser: $(PARSER).class $(PARSER)Constants.class \
	$(PARSER)TokenManager.class ParseException.class

main: $(PARSER).class Main.class

clean:
	rm -f $(SRCS:.java=.class) ${JAVACCGEN} $(JAVACCGEN:.java=.class)
