Program(
 AbstractList(
  ClassDecl(test null
   AbstractList()
   AbstractList(
    MethodDecl(public_static_void main
     AbstractList(
      Formal(ArrayType(IdentifierType(String)) args))
     AbstractList(
      VarDecl(IntegerType b
      IntegerLiteral(5))
      VarDecl(IntegerType c
      IntegerLiteral(6)))
     AbstractList(
      IfStmt(
       LesserExpr(
        IdentifierExpr(b)
        IdentifierExpr(c))
       XinuCallStmt(print
        AbstractList(
         StringLiteral("b < c")))
       XinuCallStmt(print
        AbstractList(
         StringLiteral("b >= c")))))
     IntegerLiteral(0))))
  ClassDecl(cat null
   AbstractList(
    VarDecl(IntegerType r null))
   AbstractList())))
