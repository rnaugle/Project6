package Assem.Translate.Visit;

/**
 * Visitable.java
 *
 *
 * Created: Thu Sep 23 14:17:02 2004
 *
 * @author <a href="mailto:brylow@elsinore.cs.purdue.edu">Dennis Brylow</a>
 * @version 1.0
 */

public interface Visitable
{
  public void accept(Visitor v);
  //public Semant.Types.Type accept(Visitor2 v);
}// Visitable
