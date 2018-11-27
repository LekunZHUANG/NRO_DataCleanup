package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Exercise.csvRead;
import Exercise.methode;

public class test {
	
	public static void main(String[] args) throws IOException {
		csvRead NRO_PM = new csvRead("NRO-PM(7,9,10).csv");
		String[] CB =NRO_PM.getColumn("CB_LONG");
		//String[] CB = {"458.3814815","2308.741129","872.5891734","463.2349731","2310.287754","1.9E-09"};
		methode mt = new methode();
		//CB = mt.FourFive(CB);
		for(String s:CB)
		{
			System.out.println(s);
		}
//		for(int i=0;i<CB.length;i++)
//		{
//			float f = Float.parseFloat(CB[i]);
//			int n =(int)(f+0.5);
//			System.out.println(n);
//		}
	}
}


