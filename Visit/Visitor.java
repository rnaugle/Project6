package Assem.Translate.Visit;

/**
 * Visitor.java
 *
 *
 * Created: Thu Sep 23 14:18:50 2004
 *
 * @author <a href="mailto:brylow@elsinore.cs.purdue.edu">Dennis Brylow</a>
 * @version 1.0
 */

public interface Visitor
{
 
  
  
  public void visit(Assem.Translate.Absyn.Absyn a);
  public void visit(Assem.Translate.Absyn.AddExpr e);
  public void visit(Assem.Translate.Absyn.AndExpr e);
  public void visit(Assem.Translate.Absyn.ArrayExpr e);
  public void visit(Assem.Translate.Absyn.ArrayType e);
  public void visit(Assem.Translate.Absyn.AssignableExpr e);
  public void visit(Assem.Translate.Absyn.AssignStmt e);
  public void visit(Assem.Translate.Absyn.BinOpExpr e);
  public void visit(Assem.Translate.Absyn.BlockStmt e);
  public void visit(Assem.Translate.Absyn.BooleanType e);
  public void visit(Assem.Translate.Absyn.CallExpr e);
  public void visit(Assem.Translate.Absyn.ClassDecl e);
  public void visit(Assem.Translate.Absyn.DivExpr e);
  public void visit(Assem.Translate.Absyn.EqualExpr e);
  public void visit(Assem.Translate.Absyn.Expr e);
  public void visit(Assem.Translate.Absyn.FalseExpr e);
  public void visit(Assem.Translate.Absyn.FieldExpr e);
  public void visit(Assem.Translate.Absyn.Formal e);
  public void visit(Assem.Translate.Absyn.GreaterExpr e);
  public void visit(Assem.Translate.Absyn.IdentifierExpr e);
  public void visit(Assem.Translate.Absyn.IdentifierType e);
  public void visit(Assem.Translate.Absyn.IfStmt e);
  public void visit(Assem.Translate.Absyn.IntegerLiteral e);
  public void visit(Assem.Translate.Absyn.IntegerType e);
  public void visit(Assem.Translate.Absyn.LesserExpr e);
  public void visit(Assem.Translate.Absyn.MethodDecl e);
  public void visit(Assem.Translate.Absyn.MulExpr e);
  public void visit(Assem.Translate.Absyn.NegExpr e);
  public void visit(Assem.Translate.Absyn.NewArrayExpr e);
  public void visit(Assem.Translate.Absyn.NewObjectExpr e);
  public void visit(Assem.Translate.Absyn.NotEqExpr e);
  
  public void visit(Assem.Translate.Absyn.NullExpr e);
  public void visit(Assem.Translate.Absyn.OrExpr e);
  //public void visit(Translate.Absyn.PrintVisitor e);
  
  public void visit(Translate.Absyn.Program e);
  public void visit(Translate.Absyn.Stmt e);
  public void visit(Translate.Absyn.StringLiteral e);
  public void visit(Translate.Absyn.SubExpr e);
  public void visit(Translate.Absyn.ThisExpr e);
  public void visit(Translate.Absyn.ThreadDecl e);
  public void visit(Translate.Absyn.TrueExpr e);
  public void visit(Translate.Absyn.Type e);
  public void visit(Translate.Absyn.VarDecl e);
  public void visit(Translate.Absyn.VoidDecl e);
  public void visit(Translate.Absyn.WhileStmt e);
  public void visit(Translate.Absyn.XinuCallStmt e);
  public void visit(Translate.Absyn.XinuCallExpr e);
  
  public void visit(Translate.Types.ARRAY a);
	public void visit(Translate.Types.BOOLEAN a);
	public void visit(Translate.Types.CLASS a);
	public void visit(Translate.Types.FIELD a);
	public void visit(Translate.Types.FUNCTION a);
	public void visit(Translate.Types.INT a);
	public void visit(Translate.Types.NIL a);
	public void visit(Translate.Types.OBJECT a);
	public void visit(Translate.Types.RECORD a);
	public void visit(Translate.Types.STRING a);
	public void visit(Translate.Types.VOID a);
 

}// Visitor
