package ex;

import java.util.Random;

/**
 * ϰ����
 * */
public class Exercises {
	private Formula[] forms=null;
	private int [] answers=null;
	public Exercises(Formula[] values) {//������ʽֱ�Ӵ���
		this.forms=values;
		this.answers=new int[values.length];
		for(int i=0;i<values.length;i++)
			answers[i]=-1;
	}
	public Exercises(int count) {//count�������ʽ
		this.forms=initExercis(count,'*');
		this.answers=new int [count];
		for(int i=0;i<count;i++)
			answers[i]=-1;
		
	}
	public void setAnswer(int [] answer) {//�����û��Ĵ�
		answers=answer;
	}
	public Exercises(int count,char sign) {//count �� sign���ŵ���ʽ 
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
	public void setAnswer(int index,int answer) {//�����û��Ĵ�
		answers[index]=answer;
	}
	public int getAnswer(int index) {//��ȡ�û��Ĵ�
		return answers[index];
	}
	public Formula getFormula(int index) {//��ȡϰ�⼯�е�һ����ʽ
		return forms[index];
	}
	public int size() {//ϰ�⼯���ж��ٸ���ʽ
		return forms.length;
	}
	public Formula[] initExercis(int count,char sign) {
		//���ڳ�ʼ��һ��ϰ�⼯ ����count����ʽ��sign=+�ӷ� sign=-�ӷ� sign=*�������
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
	private boolean isEqual(Formula formula) {//�жϺϷ���
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
		//����target�Ƿ��ڳ���ΪmaxIndex����ʽ����forms��
		for(int i=0;i<maxIndex;i++) {
			if(forms[i].getLeft()==target.getLeft()) 
				if(forms[i].getSign()==target.getSign()) 
					if(forms[i].getRight()==target.getRight())
						return true;
		}
		return false;
	}
}
