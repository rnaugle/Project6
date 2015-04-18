package Translate.Types;

//import Translate.Types.*;





public interface Visitor {

	public void visit(ARRAY a);
	public void visit(BOOLEAN a);
	public void visit(CLASS a);
	public void visit(FIELD a);
	public void visit(FUNCTION a);
	public void visit(INT a);
	public void visit(NIL a);
	public void visit(OBJECT a);
	public void visit(RECORD a);
	public void visit(STRING a);
	public void visit(VOID a);
}
