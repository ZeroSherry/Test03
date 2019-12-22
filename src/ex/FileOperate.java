package ex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * ÎÄ¼þ²Ù×÷
 * */
public class FileOperate {
	private Properties pro=null;
	private String url="";
	public FileOperate() {
		
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url=url;
	}
	public FileOperate(String url){
		File fp=new File(url);
		this.url=url;
		try {
			if(!fp.exists()) {
				fp.createNewFile();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public String read(String key) {
		pro=new Properties();
		try {
			FileInputStream lin=new FileInputStream(url);
			pro.load(lin);
			lin.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return pro.getProperty(key);
	}
	public void write(String key,String value) {
		pro=new Properties();
		try {
			FileInputStream lin=new FileInputStream(url);
			pro.load(lin);
			lin.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		pro.setProperty(key, value);
		try {
			FileOutputStream lin=new FileOutputStream(url);
			pro.store(lin, "date");
			lin.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
