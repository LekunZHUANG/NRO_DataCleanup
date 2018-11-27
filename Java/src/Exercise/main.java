/*
Title:Data clean up of NRO and cables
Author:Lekun ZHUANG
Github:https://github.com/LekunZHUANG
*/

package Exercise;

import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		//дNROChemin
		csvWrite NROChemin = new csvWrite("NROChemin.csv");
		NROChemin.getchemin();
		
		//дLinkedNRO
		csvWrite LinkedNRO = new csvWrite("LinkedNRO.csv");
		LinkedNRO.getLinked();
		
		//дUnLinkedNRO
		csvWrite UnLinkedNRO = new csvWrite("UnLinkedNRO.csv");
		UnLinkedNRO.getUnLinked();		
	}

}
