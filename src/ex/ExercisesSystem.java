package ex;

import java.util.Scanner;

/**
 * 习题集系统
 * 对文件操作时：
 * countMax=算式个数
 *	countDone=已做个数
 *	answer+"n"=-1/非-1 第n个用户给的答案
 *	left+"n"=第n个算式 left
 *	right+"n"=第n个算式 right
 *	sign+"n"=第n个算式sign
 * */
public class ExercisesSystem {
	private Exercises exer=null;
	private FileExercises fp=null;
	void printMenu() {//打印系统菜单
		System.out.print("\n*****************************");
		System.out.print("\n**  欢迎使用习题集生成系统 **");
		System.out.print("\n*****************************");
		System.out.print("\n-----------------------------");
		System.out.print("\n1.新的习题");
		System.out.print("\n2.继续做题");
		System.out.print("\n3.批改习题");
		System.out.print("\n0.退出系统");
		System.out.print("\n-----------------------------");
		System.out.print("\n*****************************");
		System.out.print("\n请选择:\n");
	
	}
	public static void main(String [] arge) {
		ExercisesSystem sys=new ExercisesSystem();
		System.out.println("欢迎使用习题集系统!");
		int chose=0;
		Scanner sc=new Scanner(System.in);
		
		while(true) {
			sys.printMenu();
			chose=sc.nextInt();
			if(chose==0) {
				System.out.print("\n感谢您的使用...");
				break;
			}
			else if(chose==1){
				sys.setExercises(sc);
			}else if(chose==2){
				sys.undoExercises(sc);
			}else if(chose==3) {
				sys.checkAnswer(sc);
			}else {
				System.out.print("\n非法操作\n");
			}
		}
		sc.close();
	}
	void setExercises(Scanner sc) {//生成算式
		System.out.print("\n请选择习题类型:\n1.加法\n2.减法\n3.混合\n0.退出\n");
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
			System.out.print("\n非法输入\n");
			setExercises(sc);
			return;
		}
		System.out.println("请输入习题集的名字吧:");
		sc.nextLine();
		String url=sc.nextLine();
		fp=new FileExercises(url);
		System.out.print("\n存储中请稍后...\n");
		fp.create();
		fp.saveAllFormulas(exer);
		System.out.print("存储完成\n");
	}
	void undoExercises(Scanner sc) {//读取算式并开始做题
		Formula[] forms=null;
		System.out.print("\n请输入习题集名字:\n");
		sc.nextLine();
		String url=sc.nextLine();
		fp=new FileExercises(url);
		if(fp.isExist()) {
			System.out.print("\n载入中...\n");
			forms=fp.getFormulas();
			exer=new Exercises(forms);
			exer.setAnswer(fp.getUserAllAnswer());
			int done=fp.getDone();
			int max=exer.size();
			System.out.print("\n载入完成:\n完成数量:"+done);
			for(int i=0;i<forms.length;i++) {
				if(i%5==0)System.out.print("\n");
				System.out.format("%4d.%7s=%3d ", i+1,exer.getFormula(i).toString(),exer.getAnswer(i));
			}
			while(true) {
				System.out.format("\n请输入第%3d个算式的答案:(输入-1退出)\n", done+1);
				int useranswer=sc.nextInt();
				if(useranswer==-1)
					break;
				exer.setAnswer(done, useranswer);
				done++;
				if(done>=max) {
					System.out.print("\n本习题已经全部完成\n");
					break;
				}
			}
			System.out.print("\n正在保存做题进度请稍后...\n");
			fp.saveAnswer(exer);
			System.out.print("保存完毕\n");
			
		}else {
			System.out.print("\n请检查习题名字是否正确\n");
		}
	}
	void checkAnswer(Scanner sc) {//批改习题
		Formula[] forms=null;
		System.out.print("\n请输入要批改的习题集名:\n");
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
				String isRight="错误";
				if(userAnswer[i]==forms[i].getAnswer()) {
					rightAnswerCount++;
					isRight="正确";
				}
				if(i%5==0)
					System.out.print("\n");
				System.out.format("%4d.%7s=%3d -%s ", i+1,forms[i].toString(),userAnswer[i],isRight);
			}
			System.out.print("\n正确率:"+((rightAnswerCount+0.0)/done)*100+"%\n");
		}else {
			System.out.print("\n请检查输入的名字是否正确\n");
		}
	}
}
