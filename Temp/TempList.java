package Transla.Temp;

public class TempList {
	Temp head;
	TempList tail;
	public TempList(Temp h) {
		head = h;
	}
	public TempList(Temp h, TempList t) {
		head = h;
		tail = t;
	}
}
