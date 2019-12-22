package ex;
/**
 * À„ Ω¿‡
 * */
public abstract class Formula {
	private int left;
	private int right;
	private char sign;
	public Formula(int left,int right,char sign) {
		this.left=left;
		this.right=right;
		this.sign=sign;
	}
	public int getLeft() {
		return left;
	}
	public int getRight() {
		return right;
	}
	public char getSign() {
		return sign;
	}
	public void setLeft(int left) {
		this.left=left;
	}
	public void setRight(int right) {
		this.right=right;
	}
	public void setSign(char sign) {
		this.sign=sign;
	}
	public abstract String toString();
	public abstract int getAnswer();
}
