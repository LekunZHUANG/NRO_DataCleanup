package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV {

	public static List<String> run() {
		List<String> allString = new ArrayList<>();
		try 
		{
			File csv = new File("score.csv");
			FileReader Rcsv = new FileReader(csv);
			BufferedReader br =  new BufferedReader(Rcsv);
			String line = "";
			//List<String> allString = new ArrayList<>();
			while((line=br.readLine())!=null)    //br.readLine�Զ�����һ��  
			{
					//line = br.readLine();      //�����line��br.reader�ֿ��Ļ� �ͻ�����
					System.out.println(line);
					allString.add(line);
			}
			return allString;
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}catch (IOException e)
		{
			e.printStackTrace();
		}
		return allString;




	}

}
