package ex;

import java.util.Scanner;

/**
 * ϰ�⼯ϵͳ
 * ���ļ�����ʱ��
 * countMax=��ʽ����
 *	countDone=��������
 *	answer+"n"=-1/��-1 ��n���û����Ĵ�
 *	left+"n"=��n����ʽ left
 *	right+"n"=��n����ʽ right
 *	sign+"n"=��n����ʽsign
 * */
public class ExercisesSystem {
	private Exercises exer=null;
	private FileExercises fp=null;
	void printMenu() {//��ӡϵͳ�˵�
		System.out.print("\n*****************************");
		System.out.print("\n**  ��ӭʹ��ϰ�⼯����ϵͳ **");
		System.out.print("\n*****************************");
		System.out.print("\n-----------------------------");
		System.out.print("\n1.�µ�ϰ��");
		System.out.print("\n2.��������");
		System.out.print("\n3.����ϰ��");
		System.out.print("\n0.�˳�ϵͳ");
		System.out.print("\n-----------------------------");
		System.out.print("\n*****************************");
		System.out.print("\n��ѡ��:\n");
	
	}
	public static void main(String [] arge) {
		ExercisesSystem sys=new ExercisesSystem();
		System.out.println("��ӭʹ��ϰ�⼯ϵͳ!");
		int chose=0;
		Scanner sc=new Scanner(System.in);
		
		while(true) {
			sys.printMenu();
			chose=sc.nextInt();
			if(chose==0) {
				System.out.print("\n��л����ʹ��...");
				break;
			}
			else if(chose==1){
				sys.setExercises(sc);
			}else if(chose==2){
				sys.undoExercises(sc);
			}else if(chose==3) {
				sys.checkAnswer(sc);
			}else {
				System.out.print("\n�Ƿ�����\n");
			}
		}
		sc.close();
	}
	void setExercises(Scanner sc) {//������ʽ
		System.out.print("\n��ѡ��ϰ������:\n1.�ӷ�\n2.����\n3.���\n0.�˳�\n");
		int chose=sc.nextInt();
		if(chose==0) {
			return;
		}else if(chose==1){
			exer=new Exercises(50,'+');
		}else if(chose==2) {
			exer=new Exercises(50,'-');
		}else if(chose==3) {
			exer=new Exercises(50,'*');
		}else {
			System.out.print("\n�Ƿ�����\n");
			setExercises(sc);
			return;
		}
		System.out.println("������ϰ�⼯�����ְ�:");
		sc.nextLine();
		String url=sc.nextLine();
		fp=new FileExercises(url);
		System.out.print("\n�洢�����Ժ�...\n");
		fp.create();
		fp.saveAllFormulas(exer);
		System.out.print("�洢���\n");
	}
	void undoExercises(Scanner sc) {//��ȡ��ʽ����ʼ����
		Formula[] forms=null;
		System.out.print("\n������ϰ�⼯����:\n");
		sc.nextLine();
		String url=sc.nextLine();
		fp=new FileExercises(url);
		if(fp.isExist()) {
			System.out.print("\n������...\n");
			forms=fp.getFormulas();
			exer=new Exercises(forms);
			exer.setAnswer(fp.getUserAllAnswer());
			int done=fp.getDone();
			int max=exer.size();
			System.out.print("\n�������:\n�������:"+done);
			for(int i=0;i<forms.length;i++) {
				if(i%5==0)System.out.print("\n");
				System.out.format("%4d.%7s=%3d ", i+1,exer.getFormula(i).toString(),exer.getAnswer(i));
			}
			while(true) {
				System.out.format("\n�������%3d����ʽ�Ĵ�:(����-1�˳�)\n", done+1);
				int useranswer=sc.nextInt();
				if(useranswer==-1)
					break;
				exer.setAnswer(done, useranswer);
				done++;
				if(done>=max) {
					System.out.print("\n��ϰ���Ѿ�ȫ�����\n");
					break;
				}
			}
			System.out.print("\n���ڱ�������������Ժ�...\n");
			fp.saveAnswer(exer);
			System.out.print("�������\n");
			
		}else {
			System.out.print("\n����ϰ�������Ƿ���ȷ\n");
		}
	}
	void checkAnswer(Scanner sc) {//����ϰ��
		Formula[] forms=null;
		System.out.print("\n������Ҫ���ĵ�ϰ�⼯��:\n");
		sc.nextLine();
		String url=sc.nextLine();
		int rightAnswerCount=0;
		int[] userAnswer=null;
		int done=0;
		fp=new FileExercises(url);
		if(fp.isExist()) {
			forms=fp.getFormulas();
			userAnswer=fp.getUserAllAnswer();
			done=fp.getDone();
			for(int i=0;i<done;i++) {
				String isRight="����";
				if(userAnswer[i]==forms[i].getAnswer()) {
					rightAnswerCount++;
					isRight="��ȷ";
				}
				if(i%5==0)
					System.out.print("\n");
				System.out.format("%4d.%7s=%3d -%s ", i+1,forms[i].toString(),userAnswer[i],isRight);
			}
			System.out.print("\n��ȷ��:"+((rightAnswerCount+0.0)/done)*100+"%\n");
		}else {
			System.out.print("\n��������������Ƿ���ȷ\n");
		}
	}
}
