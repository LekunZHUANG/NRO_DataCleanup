/*
Title:Data clean up of NRO and cables
Author:Lekun ZHUANG
Github:https://github.com/LekunZHUANG
*/

package Exercise;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class methode {
//	List<String> Presence;
//	List<String> Absence;
	public methode()
	{
//		Presence = new ArrayList<>();
//		Absence = new ArrayList<>();
	}
	//找出字符串数组A中在字符串数组B中出现过的字符串
	public List<String> FindPresence(String[] A, String[] B)
	{
		List<String> Presence=new ArrayList<>();
		for(int i=0;i<A.length;i++)
		{
			for(int j=0;j<B.length;j++)
			{
				if(A[i].equals(B[j]))
				{
					Presence.add(A[i]);
					break;
				}
			}
		}
		return Presence;		
	}
	
	//找出字符串数组A中在字符串数组B中没出现过的字符串
	public List<String> FindAbsence(String[] A, String[] B)
	{
		List<String> Absence=new ArrayList<>();
		int flag =0 ;
		for(int i=1;i<A.length;i++)
		{
			for(int j=0;j<B.length;j++)
			{
				if(A[i].equals(B[j]))
				{
					flag++;
					break;
				}else{
					flag =0;
				}
			}
			if(flag==0)
			{
				Absence.add(A[i]);
			}
		}
		return Absence;		
	}

	//找出字符串b在数组中出现多少次
	public int FindFrequency(String[] B, String b)
	{
		int number = 0;
		for(int i=0;i<B.length;i++)
		{
			if(B[i].equals(b))
				number++;
		}
		return number;		
	}
	
	//由基站找线缆
	public List<String> FindCB(String[] A, String[] B, String b)
	{
		List<String> cblist =new ArrayList<>();;
		for(int i=0;i<B.length;i++)
		{
			if(B[i].equals(b))
				cblist.add(A[i]);
		}		
		return cblist;		
	}
	
	//找到线缆的信息
	public String FindPoint(String[] A, String[] D, String[] E, String[] C, String a,List<String> list) throws IOException
	{
		String c = null;
		for(int i=0;i<A.length;i++)
		{
			if(A[i].equals(a))
			{
				list.add(A[i]+",");
				list.add(D[i]+",");
				list.add(E[i]+",");
				list.add(C[i]+",");	
				c = C[i];
				break;
			}
		}
		return c;		
	}

	public String[] FourFive(String[] input)
	{
		String[] output =new String[input.length];
		output[0] = input[0];
		for(int i=1;i<input.length;i++)
		{
			float f =Float.parseFloat(input[i]);
			int n =(int) (f+0.5);
			output[i]=String.valueOf(n);
		}
		return output;		
	}
}
