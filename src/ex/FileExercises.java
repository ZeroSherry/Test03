package ex;
/**
 * 存入或读取一条算式
 * 继承自FileOperation
 * 
 * 认为存储方式为 key=value
 *其中countMax=算式个数
 *	countDone=已做个数
 *	answer+"n"=-1/非-1 第n个用户给的答案
 *	left+"n"=第n个算式 left
 *	right+"n"=第n个算式 right
 *	sign+"n"=第n个算式sign
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
			if(!fp.exists()) {//创建并初始化
				fp.createNewFile();
				super.write("countMax","0");
				super.write("countDone","0");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public boolean isExist() {//判断这个存档是否存在
		return fp.exists();
	}
	public Formula[] getFormulas() {//获取当前文件的算式组
		Formula[] forms=null;
		if(!isExist())//还没有创建的时候不允许获取
			return null;
		int count=Integer.valueOf(super.read("countMax"));//该文件算式个数
		forms=new Formula[count];
		for(int i=0;i<count;i++) {
			forms[i]=getFormula(i);
		}
		return forms;
	}
	public void saveAllFormulas(Exercises exer) {//存储一组算式到当前文件
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
	public void saveAnswer(Exercises exer) {//若仅改变了答案则存储答案和已做数量
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
	public Formula getFormula(int index) {//获取文件中某个算式
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
	public int getUserAnswer(int index) {//获取用户的答案
		if(!isExist())
			return -2;
		return Integer.valueOf(read("answer"+index));
	}
	public int[] getUserAllAnswer() {//获取用户所有的答案
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
	public int getDone() {//获取算式完成数量
		if(!isExist()) {
			return -1;
		}else {
			return Integer.valueOf(super.read("countDone"));
		}
	}
}
