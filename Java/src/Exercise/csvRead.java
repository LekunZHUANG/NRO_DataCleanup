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

//������
//path:�ļ���ڵ�·��          csv:��Ҫ��ȡ��csv�ļ�           Rcsv:�ļ���ȡ������     bf:�ַ���
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
	
	//��ȡcsv����������
	//���ص�AllContent����
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
//		//�������������
//		for(String[] s:AllContent)
//		{
//			for(String s1:s)
//			{
//				System.out.println(s1);
//			}
//		}		
		return AllContent;
	}
	
	//��ȡcsv�е�ĳһ��
	public String[] getRow(int row) throws IOException
	{
		getAllContent();
		String[] Row = AllContent.get(row-1);
		//����һ�е������������
//		for(String s:Row)
//		{
//			System.out.println(s);
//		}
		return Row;
	}
	
	
	//��ȡcsv�е�ĳһ��
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
		//����һ�е������������
//		for(int i=0;i<Column.length;i++)
//		{
//			System.out.println(Column[i]);
//		}
	}
		return Column;
	}
	
	//��ȡCSV��̧ͷΪtitle��ĳһ��
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
	
	//��ȡ̧ͷΪtitle����Ӧ����Ϊword����һ�е��ֵ�
	//�������ҽ���һ��
	public Map<String,String> getHashRow(String title,String word) throws IOException
	{
		Map<String,String> DictMap = new LinkedHashMap<String,String>();
		String[] Column = getColumn(title);    //��ȡ����Ϊtitle��һ��
		int row=0;
		for(int i=0;i<Column.length;i++)       //��ȡ�������ڵ���һ��
		{
			if(Column[i].equals(word))
			{
				row = i+1;                            
			}
		}
		for(int j=0;j<getRow(1).length;j++)       //��������һ�� ���ֵ䷵��hashMap
		{
			DictMap.put(getRow(1)[j], getRow(row)[j]);
		}	
		//���ֵ����
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
