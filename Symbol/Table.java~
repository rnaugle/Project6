package Semant.Symbol;

class Bucket {
	public Symbol key; 
	public Object binding;
	public Bucket next;
	
	Bucket(Symbol s, Object o, Bucket b){
		key = s;
		binding = o;
		next = b;
	}
}

public class Table{
	Bucket table[];
	 
	public Table(){
		this.table = new Bucket[256];}

	private int hash(Symbol s) {
		int h = 0;
		for (int i = 0; i <s.toString().length();i++)
			{h = h*65599+s.toString().charAt(i); }
		return h;
	}
	
	public void put(Symbol key, Object value){
		int i = Math.abs(hash(key)%256);
		this.table[i] = new Bucket(key, value, this.table[i]);
	}

	public Object get(Symbol key){
		int i = Math.abs(hash(key)%256);
		for (Bucket b = table[i]; b != null; b = b.next){
			if (key.toString().equals(b.key.toString()))
				return b.binding;
			if (b.key.toString().equals("_"))
				return null;
		}
		return null;

	}

	public void beginScope(){
		for (int i = 0; i < 256; i++)
			this.table[i] = new Bucket(Symbol.symbol("_"),null,this.table[i]);	

	}
	public void endScope(){
		for (int i = 0; i < 256; i++)
		{
			Bucket current = this.table[i];
			while (!current.key.toString().equals("_") && current != null){
				this.table[i] = current.next;
				current = current.next;
			}
			if (current != null)
				this.table[i] = current.next;
		}
	}


	//public java.util.Enumeration keys(){
		

	//}

}
