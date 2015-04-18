package Temp;

public class LabelList {
	Label head;
	LabelList tail;
	public LabelList(Label h) {
		head = h;
	}
	public LabelList(Label h, LabelList t) {
		head = h;
		tail = t;
	}
}