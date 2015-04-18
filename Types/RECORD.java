package Translate.Types;

import java.util.Iterator;

//import Translate.Visitor;

public class RECORD extends Type implements java.lang.Iterable<FIELD>{
	
	public java.util.LinkedList<FIELD> fields;
	public int counter;

	public RECORD(){
		this.fields = new java.util.LinkedList<FIELD>();
		counter = 0;
	}
	
	public FIELD get(String name){
		for (int i = 0; i < fields.size(); i++)
		{
			FIELD f = fields.get(i);
			if (f.name.equals(name))
				return f;
		}
		return null;
	}
	
	public Iterator<FIELD> iterator(){
		
		return fields.descendingIterator();
		
	}
	
	public FIELD put(Type type, String name){
		//tb.put(name,type);
		FIELD field = new FIELD(type,name,counter++);
		this.fields.add(field);
		return field;
	}

	@Override
	public void accept(Translate.Visit.Visitor v) {
		v.visit(this);
		
	}
	
	public Translate.Translate.Exp accept(Translate.Translator.Translator  v) { return v.visit(this); }

	@Override
	public boolean coerceTo(Type t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
