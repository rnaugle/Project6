package Frame;
import Temp.*;
import Translate.Tree.*;
abstract public class Access { 
	public Access(){}
	abstract public Exp exp(Exp framePtr);
}
