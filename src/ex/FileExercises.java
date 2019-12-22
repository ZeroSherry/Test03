package ex;
/**
 * ������ȡһ����ʽ
 * �̳���FileOperation
 * 
 * ��Ϊ�洢��ʽΪ key=value
 *����countMax=��ʽ����
 *	countDone=��������
 *	answer+"n"=-1/��-1 ��n���û����Ĵ�
 *	left+"n"=��n����ʽ left
 *	right+"n"=��n����ʽ right
 *	sign+"n"=��n����ʽsign
 * */
import java.io.File;
public class FileExercises extends FileOperate{
	private File fp=null;
	public FileExercises(String url) {
		super.setUrl(url);
		fp=new File(url);
	}
	public void create() {
		try {
			if(!fp.exists()) {//��������ʼ��
				fp.createNewFile();
				super.write("countMax","0");
				super.write("countDone","0");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public boolean isExist() {//�ж�����浵�Ƿ����
		return fp.exists();
	}
	public Formula[] getFormulas() {//��ȡ��ǰ�ļ�����ʽ��
		Formula[] forms=null;
		if(!isExist())//��û�д�����ʱ�������ȡ
			return null;
		int count=Integer.valueOf(super.read("countMax"));//���ļ���ʽ����
		forms=new Formula[count];
		for(int i=0;i<count;i++) {
			forms[i]=getFormula(i);
		}
		return forms;
	}
	public void saveAllFormulas(Exercises exer) {//�洢һ����ʽ����ǰ�ļ�
		if(!isExist())
			create();
		int done=0;
		for(int i=0;i<exer.size();i++) {
			write("left"+i,String.valueOf(exer.getFormula(i).getLeft()));
			write("right"+i,String.valueOf(exer.getFormula(i).getRight()));
			write("sign"+i,String.valueOf(exer.getFormula(i).getSign()));
			write("answer"+i,String.valueOf(exer.getAnswer(i)));
			if(exer.getAnswer(i)!=-1)
				done++;
		}
		write("countMax",exer.size()+"");
		write("countDone",""+done);
	}
	public void saveAnswer(Exercises exer) {//�����ı��˴���洢�𰸺���������
		if(!isExist())
			return ;
		int done=0;
		for(int i=0;i<exer.size();i++) {
			write("answer"+i,String.valueOf(exer.getAnswer(i)));
			if(exer.getAnswer(i)!=-1)
				done++;
		}
		write("countDone",""+done);
	}
	public Formula getFormula(int index) {//��ȡ�ļ���ĳ����ʽ
		if(!isExist())
			return null;
		Formula form=null;
		if(read("sign"+index).equals("+")) {
			form=new Add(Integer.valueOf(read("left"+index)),Integer.valueOf(read("right"+index)));
		}else {
			form=new Reless(Integer.valueOf(read("left"+index)),Integer.valueOf(read("right"+index)));
		}
		return form;
	}
	public int getUserAnswer(int index) {//��ȡ�û��Ĵ�
		if(!isExist())
			return -2;
		return Integer.valueOf(read("answer"+index));
	}
	public int[] getUserAllAnswer() {//��ȡ�û����еĴ�
		if(!isExist()) {
			return null;
		}else {
			int [] ans=new int[Integer.valueOf(super.read("countMax"))];
			for(int i=0;i<ans.length;i++) {
				ans[i]=Integer.valueOf(super.read("answer"+i));
			}
			return ans;
		}
	}
	public int getDone() {//��ȡ��ʽ�������
		if(!isExist()) {
			return -1;
		}else {
			return Integer.valueOf(super.read("countDone"));
		}
	}
}
