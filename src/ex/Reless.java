package ex;
/**
 * ¼õ·¨
 * */
public class Reless extends Formula{
	public Reless(int left, int right) {
		super(left, right, '-');
	}
	public String toString() {
		String str="";
		str+=super.getLeft();
		str+=super.getSign();
		str+=super.getRight();
		return str;
	}
	public int getAnswer() {
		int answer=0;
		answer+=super.getLeft();
		answer-=super.getRight();
		return answer;
	}
}
