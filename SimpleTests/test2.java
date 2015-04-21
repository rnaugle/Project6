class test1{

	public static void main(String[] args){
		yellow y = new yellow();
		int k = y.one(7, 8, 9, 10, 11, 12);
		int j = y.two();
		int h = y.q;
		int a = y.t + h;
		int b = y.p + y.t;
	}


}

class yellow{
	
	int q;
	int t;
	int p;
	public int one(int j, int k, int a, int b, int c, int d){
		t = this.two();
		return j+1;
	}

	public int two(){
		return 7;
	}

}
