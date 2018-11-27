package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteCSV {
	public static void main(String[] args)
	{
		try
		{
			File csv = new File("write.csv");
			FileWriter Wcsv = new FileWriter(csv, true);
			BufferedWriter bw = new BufferedWriter(Wcsv);
			List<String> allString = ReadCSV.run();
			for(int i=0;i<allString.size();i++)
			{
				bw.write(allString.get(i));
				bw.newLine();
			}
			bw.close();
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
