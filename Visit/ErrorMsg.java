class Assem.ErrorMsg 
{
	boolean anyErrors;
	void complain(String msg)
	{
		anyErrors = true;
		System.out.println(msg);
	}
}
