/*
Title:Data clean up of NRO and cables
Author:Lekun ZHUANG
Github:https://github.com/LekunZHUANG
*/

package Exercise;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//构造器
//path:文件入口的路径          csv:所要读取的csv文件           Rcsv:文件读取器对象     bf:字符流
public class csvRead {
	File csv;
	FileReader Rcsv;
	BufferedReader br;
	List<String[]> AllContent;
	public csvRead(String path) throws FileNotFoundException
	{
		csv = new File(path);
		Rcsv = new FileReader(csv);
		br = new BufferedReader(Rcsv);	
		AllContent = new ArrayList<>();
	}
	
	//获取csv的所有内容
	//返回到AllContent里面
	public List<String[]> getAllContent() throws IOException
	{
		//StringBuilder line;
		String line = null;
		String[] Line = null;
		while((line = br.readLine())!=null)
		{
			//System.out.println(line);
			Line = line.split(",");
			AllContent.add(Line);
		}
//		//将所有内容输出
//		for(String[] s:AllContent)
//		{
//			for(String s1:s)
//			{
//				System.out.println(s1);
//			}
//		}		
		return AllContent;
	}
	
	//获取csv中的某一行
	public String[] getRow(int row) throws IOException
	{
		getAllContent();
		String[] Row = AllContent.get(row-1);
		//将这一行的所有内容输出
//		for(String s:Row)
//		{
//			System.out.println(s);
//		}
		return Row;
	}
	
	
	//获取csv中的某一列
	public String[] getColumn(int column) throws IOException
	{
		getAllContent();
		String[] Column = new String[AllContent.size()];
		if(column<0)
		{
			System.out.println("the column you find is not exist");
		}else{
		for(int i=0;i<AllContent.size();i++)
		{
			Column[i] = AllContent.get(i)[column-1];
		}
		//将这一列的所有内容输出
//		for(int i=0;i<Column.length;i++)
//		{
//			System.out.println(Column[i]);
//		}
	}
		return Column;
	}
	
	//获取CSV中抬头为title的某一列
	public String[] getColumn(String title) throws IOException
	{
		getAllContent();
		int column =-1;
		for(int i=0;i<AllContent.get(0).length;i++)
		{
			if(title.equals(AllContent.get(0)[i]))
				column = i + 1;
		}
		return getColumn(column);
	}
	
	public String getContent(int row, int column) throws IOException
	{
		String content ="";
		String[] Row = getRow(row);
		content = Row[column-1];
		//System.out.println(content);
		return content;
	}
	
	//获取抬头为title，对应内容为word的那一行的字典
	//假设有且仅有一行
	public Map<String,String> getHashRow(String title,String word) throws IOException
	{
		Map<String,String> DictMap = new LinkedHashMap<String,String>();
		String[] Column = getColumn(title);    //获取标题为title的一列
		int row=0;
		for(int i=0;i<Column.length;i++)       //获取单词所在的那一行
		{
			if(Column[i].equals(word))
			{
				row = i+1;                            
			}
		}
		for(int j=0;j<getRow(1).length;j++)       //将所在那一行 按字典返回hashMap
		{
			DictMap.put(getRow(1)[j], getRow(row)[j]);
		}	
		//将字典输出
		for(Map.Entry<String,String> entry:DictMap.entrySet())
		{
			System.out.print(entry.getKey() + " ");
		}
		System.out.print("\n");
		for(Map.Entry<String,String> entry:DictMap.entrySet())
		{
			System.out.print(entry.getValue() + " ");
		}
		return DictMap;
	}
	
	
	
	
}
