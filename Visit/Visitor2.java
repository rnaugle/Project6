package Semant.Visit;

/**
 * Visitor.java
 *
 *
 * Created: Thu Sep 23 14:18:50 2004
 *
 * @author <a href="mailto:brylow@elsinore.cs.purdue.edu">Dennis Brylow</a>
 * @version 1.0
 */

public interface Visitor2
{
 
  
  
  public Semant.Types.Type visit(Semant.Absyn.Absyn a);
  public Semant.Types.Type visit(Semant.Absyn.AddExpr e);
  public Semant.Types.Type visit(Semant.Absyn.AndExpr e);
  public Semant.Types.Type visit(Semant.Absyn.ArrayExpr e);
  public Semant.Types.Type visit(Semant.Absyn.ArrayType e);
  public Semant.Types.Type visit(Semant.Absyn.AssignableExpr e);
  public Semant.Types.Type visit(Semant.Absyn.AssignStmt e);
  public Semant.Types.Type visit(Semant.Absyn.BinOpExpr e);
  public Semant.Types.Type visit(Semant.Absyn.BlockStmt e);
  public Semant.Types.Type visit(Semant.Absyn.BooleanType e);
  public Semant.Types.Type visit(Semant.Absyn.CallExpr e);
  public Semant.Types.Type visit(Semant.Absyn.ClassDecl e);
  public Semant.Types.Type visit(Semant.Absyn.DivExpr e);
  public Semant.Types.Type visit(Semant.Absyn.EqualExpr e);
  public Semant.Types.Type visit(Semant.Absyn.Expr e);
  public Semant.Types.Type visit(Semant.Absyn.FalseExpr e);
  public Semant.Types.Type visit(Semant.Absyn.FieldExpr e);
  public Semant.Types.Type visit(Semant.Absyn.Formal e);
  public Semant.Types.Type visit(Semant.Absyn.GreaterExpr e);
  public Semant.Types.Type visit(Semant.Absyn.IdentifierExpr e);
  public Semant.Types.Type visit(Semant.Absyn.IdentifierType e);
  public Semant.Types.Type visit(Semant.Absyn.IfStmt e);
  public Semant.Types.Type visit(Semant.Absyn.IntegerLiteral e);
  public Semant.Types.Type visit(Semant.Absyn.IntegerType e);
  public Semant.Types.Type visit(Semant.Absyn.LesserExpr e);
  public Semant.Types.Type visit(Semant.Absyn.MethodDecl e);
  public Semant.Types.Type visit(Semant.Absyn.MulExpr e);
  public Semant.Types.Type visit(Semant.Absyn.NegExpr e);
  public Semant.Types.Type visit(Semant.Absyn.NewArrayExpr e);
  public Semant.Types.Type visit(Semant.Absyn.NewObjectExpr e);
  public Semant.Types.Type visit(Semant.Absyn.NotEqExpr e);
  
  public Semant.Types.Type visit(Semant.Absyn.NullExpr e);
  public Semant.Types.Type visit(Semant.Absyn.OrExpr e);
  //public void visit(Semant.Absyn.PrintVisitor e);
  
  public Semant.Types.Type visit(Semant.Absyn.Program e);
  public Semant.Types.Type visit(Semant.Absyn.Stmt e);
  public Semant.Types.Type visit(Semant.Absyn.StringLiteral e);
  public Semant.Types.Type visit(Semant.Absyn.SubExpr e);
  public Semant.Types.Type visit(Semant.Absyn.ThisExpr e);
  public Semant.Types.Type visit(Semant.Absyn.ThreadDecl e);
  public Semant.Types.Type visit(Semant.Absyn.TrueExpr e);
  public Semant.Types.Type visit(Semant.Absyn.Type e);
  public Semant.Types.Type visit(Semant.Absyn.VarDecl e);
  public Semant.Types.Type visit(Semant.Absyn.VoidDecl e);
  public Semant.Types.Type visit(Semant.Absyn.WhileStmt e);
  public Semant.Types.Type visit(Semant.Absyn.XinuCallStmt e);
  public Semant.Types.Type visit(Semant.Absyn.XinuCallExpr e);
  
  public Semant.Types.Type visit(Semant.Types.ARRAY a);
	public Semant.Types.Type visit(Semant.Types.BOOLEAN a);
	public Semant.Types.Type visit(Semant.Types.CLASS a);
	public Semant.Types.Type visit(Semant.Types.FIELD a);
	public Semant.Types.Type visit(Semant.Types.FUNCTION a);
	public Semant.Types.Type visit(Semant.Types.INT a);
	public Semant.Types.Type visit(Semant.Types.NIL a);
	public Semant.Types.Type visit(Semant.Types.OBJECT a);
	public Semant.Types.Type visit(Semant.Types.RECORD a);
	public Semant.Types.Type visit(Semant.Types.STRING a);
	public Semant.Types.Type visit(Semant.Types.VOID a);
 

}// Visitor
