/*
Title:Data clean up of NRO and cables
Author:Lekun ZHUANG
Github:https://github.com/LekunZHUANG
*/
package Exercise;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class csvWrite {
	csvRead NRO ;
	csvRead NRO_PM ;
	
	File csv;
	FileWriter Wcsv;
	BufferedWriter bw;

	String[] CB_ID ;
	String[] CB_ND1;
	String[] CB_ND2;
	String[] CB_CAPAFO;
	String[] CB_LONG;
	
	methode mt = new methode();
	
	String[] AllNRO;
	List<String> LinkedNRO;
	List<String> UnLinkedNRO;
	
	public csvWrite(String path) throws IOException {
		
		NRO = new csvRead("NRO.csv");
		NRO_PM = new csvRead("NRO-PM(7,9,10).csv");	
		
		csv = new File(path);
		Wcsv =new FileWriter(csv);
		//Wcsv =new FileWriter(csv,append); //不覆盖原来的内容
		bw = new BufferedWriter(Wcsv);

	    CB_ID = NRO_PM.getColumn("CB_ID");
		CB_ND1 = NRO_PM.getColumn("CB_ND1");
		CB_ND2 = NRO_PM.getColumn("CB_ND2");
		CB_CAPAFO = NRO_PM.getColumn("CB_CAPAFO");
		CB_LONG = NRO_PM.getColumn("CB_LONG");
		CB_LONG = mt.FourFive(CB_LONG);

		AllNRO = NRO.getColumn("pt_id");
		LinkedNRO = mt.FindPresence(AllNRO, CB_ND1);
		UnLinkedNRO = mt.FindAbsence(AllNRO, CB_ND1);				
	}
	
	public void getLinked() throws IOException
	{
		String [] heading = NRO.getRow(1);
		for(String h:heading)
		{
			bw.write(h+",");
		}
		bw.newLine();
		
		for(int i=1;i<AllNRO.length;i++)
		{
			if(LinkedNRO.contains(AllNRO[i]))
			{
				String[] line=NRO.getRow(i); 
				for(String s : line)
				{
					bw.write(s + ",");
				}
				bw.newLine();
			}
		}
		bw.close();
	}
	
	public void getUnLinked() throws IOException
	{
		String [] heading = NRO.getRow(1);
		for(String h:heading)
		{
			bw.write(h+",");
		}
		bw.newLine();
		
		for(int i=0;i<AllNRO.length;i++)
		{
			if(UnLinkedNRO.contains(AllNRO[i]))
			{
				String[] line=NRO.getRow(i+1); 
				for(String s : line)
				{
					bw.write(s + ",");
				}
				//System.out.println(line[0]);
				bw.newLine();
			}
		}
		bw.close();
	}
	
	public void getchemin() throws IOException
	{
		int c = 0;
		int d = -1;
		
		bw.write("NRO,CBname,CBtype,CBlength, LinkedThing, CBname, CBtype, CBlength, LinkedThing,CBname, CBtype, CBlength, LinkedThing, CBname, CBtype, CBlength, LinkedThing, CBname,CBtype,CBlength, LinkedThing, CBname, CBtype, CBlength, LinkedThing");
		for(int i=0;i<LinkedNRO.size();i++)
		{
			List<String> list = new ArrayList<String>();
			list.add(LinkedNRO.get(i)+",");
			Turbo(c,d,LinkedNRO.get(i),list);
		}
		bw.close();
	}
	
	
	
	public void Turbo(int c,int d,String nro,List<String> list) throws IOException
	{
		List<String> CBList = mt.FindCB(CB_ID, CB_ND1, nro);
		for(String s :CBList)
		{
			if(s.equals(CBList.get(0)))
			{
				c = 0;
				d = d+1;
			}
			if(!s.equals(CBList.get(0)))
			{
				c =d;
			}
			if(c!=0)
			{
				for(int k=0;k<4*c;k++)
				{
					list.add(" ,");
				}
			}
			String cc = mt.FindPoint(CB_ID, CB_CAPAFO, CB_LONG, CB_ND2, s,list);
			if(mt.FindFrequency(CB_ND1,cc)>0)
			{
				Turbo(c,d,cc,list);
			}
			if(mt.FindFrequency(CB_ND1,cc)==0)
			{
				for(String ss: list)
				{
					bw.write(ss);
				}
				list.clear();
				bw.newLine();
				list.add(" ,");
			}
		}
	}
	
	public static void Print(List<String> input)
	{
		for(String s: input)
		{
			System.out.println(s);
		}
		System.out.println(input.size());
	}
	
	public static void Print(String[] input)
	{
		for(int i=0;i<input.length;i++)
		{
			System.out.println(input[i]);
		}
		System.out.println(input.length);
	}
}