package ex;

import java.util.Random;

/**
 * 习题类
 * */
public class Exercises {
	private Formula[] forms=null;
	private int [] answers=null;
	public Exercises(Formula[] values) {//已有算式直接传入
		this.forms=values;
		this.answers=new int[values.length];
		for(int i=0;i<values.length;i++)
			answers[i]=-1;
	}
	public Exercises(int count) {//count个随机算式
		this.forms=initExercis(count,'*');
		this.answers=new int [count];
		for(int i=0;i<count;i++)
			answers[i]=-1;
		
	}
	public void setAnswer(int [] answer) {//设置用户的答案
		answers=answer;
	}
	public Exercises(int count,char sign) {//count 个 sign符号的算式 
		if(sign=='+') {
			this.forms=initExercis(count,'+');
		}else if(sign=='-') {
			this.forms=initExercis(count,'-');
		}else {
			this.forms=initExercis(count,'*');
		}
		
		this.answers=new int [count];
		for(int i=0;i<count;i++)
			answers[i]=-1;
	}
	public void setAnswer(int index,int answer) {//存入用户的答案
		answers[index]=answer;
	}
	public int getAnswer(int index) {//获取用户的答案
		return answers[index];
	}
	public Formula getFormula(int index) {//获取习题集中的一个算式
		return forms[index];
	}
	public int size() {//习题集中有多少个算式
		return forms.length;
	}
	public Formula[] initExercis(int count,char sign) {
		//用于初始化一个习题集 包含count个算式，sign=+加法 sign=-加法 sign=*随机符号
		Random r=new Random();
		Formula[] linFormula=new Formula[count];
		if(sign=='+') {
			for(int i=0;i<count;i++) {
				linFormula[i]=new Add(r.nextInt(101),r.nextInt(101));
				if(this.isExist(linFormula,linFormula[i], i)||!this.isEqual(linFormula[i])) {
					i--;
					continue;
				}
			}
		}else if(sign=='-'){
			for(int i=0;i<count;i++) {
				linFormula[i]=new Reless(r.nextInt(101),r.nextInt(101));
				if(this.isExist(linFormula,linFormula[i], i)||!this.isEqual(linFormula[i])) {
					i--;
					continue;
				}
			}
		}else {
			if(r.nextInt(2)==1) {//1+  0-
				for(int i=0;i<count;i++) {
					linFormula[i]=new Add(r.nextInt(101),r.nextInt(101));
					if(this.isExist(linFormula,linFormula[i], i)||!this.isEqual(linFormula[i])) {
						i--;
						continue;
					}
				}
			}else {
				for(int i=0;i<count;i++) {
					linFormula[i]=new Reless(r.nextInt(101),r.nextInt(101));
					if(this.isExist(linFormula,linFormula[i], i)||!this.isEqual(linFormula[i])) {
						i--;
						continue;
					}
				}
			}
		}
		return linFormula;
	}
	private boolean isEqual(Formula formula) {//判断合法性
		if(formula.getSign()=='+') {
			if(formula.getLeft()+formula.getRight()>100) {
				return false;
			}
			else {
				return true;
			}
		}else {
			if(formula.getLeft()-formula.getRight()<=0) {
				return false;
			}
			else {
				return true;
			}
		}
	}
	public boolean isExist(Formula []forms,Formula target,int maxIndex) {
		//查找target是否在长度为maxIndex的算式数组forms中
		for(int i=0;i<maxIndex;i++) {
			if(forms[i].getLeft()==target.getLeft()) 
				if(forms[i].getSign()==target.getSign()) 
					if(forms[i].getRight()==target.getRight())
						return true;
		}
		return false;
	}
}
