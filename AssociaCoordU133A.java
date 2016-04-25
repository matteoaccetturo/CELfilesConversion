import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AssociaCoordU133A {

	
	public static void AssociaCoord() throws IOException{
		
		//legge il file con le coord U133A e lo riscrive ordinato
		String line5;
		List<String> listaCommonU133APlus2CoordU133AdaOrdinare = new ArrayList<String>(); 
		BufferedReader filecoordU133AdaOrdinare = new BufferedReader(
				new FileReader(
						"C:\\Documents and Settings\\Administrator\\Desktop\\Nuovacartella\\tmp\\ProbesComuniU133A-Plus2(coordU133A).txt"));
		while ((line5 = filecoordU133AdaOrdinare.readLine()) != null) {
			listaCommonU133APlus2CoordU133AdaOrdinare.add(line5);			
		}
		filecoordU133AdaOrdinare.close();		
		Collections.sort(listaCommonU133APlus2CoordU133AdaOrdinare);
BufferedWriter bw3 = new BufferedWriter(new FileWriter("C:\\Documents and Settings\\Administrator\\Desktop\\Nuovacartella\\tmp\\ProbesComuniU133A-Plus2(coordU133A)ordinati.txt"));
for (String string : listaCommonU133APlus2CoordU133AdaOrdinare) {
	bw3.write(string+"\r\n");
	bw3.flush();	
}
bw3.close();

//legge il file dei probes comuni con i raw data e lo riscrive ordinato
String line6;
List<String> listaCommonU133APlus2CoordPlus2daOrdinare = new ArrayList<String>(); 
BufferedReader filecoordPlus2daOrdinare = new BufferedReader(
		new FileReader(
				"C:\\Documents and Settings\\Administrator\\Desktop\\Nuovacartella\\tmp\\ProbesComuniU133A-Plus2(coordPlus2-intensitaPlus2).txt"));
while ((line6 = filecoordPlus2daOrdinare.readLine()) != null) {
	listaCommonU133APlus2CoordPlus2daOrdinare.add(line6);			
}
filecoordPlus2daOrdinare.close();
Collections.sort(listaCommonU133APlus2CoordPlus2daOrdinare);
BufferedWriter bw4 = new BufferedWriter(new FileWriter("C:\\Documents and Settings\\Administrator\\Desktop\\Nuovacartella\\tmp\\ProbesComuniU133A-Plus2(coordPlus2-intensitaPlus2)ordinati.txt"));
for (String string1 : listaCommonU133APlus2CoordPlus2daOrdinare) {
bw4.write(string1+"\r\n");
bw4.flush();	
}
bw4.close();

		//associa le coord U133A al file con i raw data
		int i;
		String riga1;
		String riga2;
		BufferedWriter bw5 = new BufferedWriter(new FileWriter("C:\\Documents and Settings\\Administrator\\Desktop\\Nuovacartella\\tmp\\ProbesComuniU133A-Plus2(coordU133A-intensitaPlus2).txt"));
		for(i=0; i<listaCommonU133APlus2CoordU133AdaOrdinare.size(); i++) {
			riga1 = listaCommonU133APlus2CoordU133AdaOrdinare.get(i).toString();
			riga2 = listaCommonU133APlus2CoordPlus2daOrdinare.get(i).toString();
			String[] field1 = riga1.split("\t");
			String[] field2 = riga2.split("\t");
			if (field1[0].equalsIgnoreCase(field2[0])) {
				System.out.println(i);
				bw5.write(field1[1]+"\t"+field1[2]+"\t"+field1[0]+"\t"+field2[3]+"\t"+field2[4]+"\t"+field2[5]+"\r\n");
				bw5.flush();
			}
			//System.out.println(o.toString());
		}
		bw5.close();

		//toglie i nomi dei probes al file con le coord U133A e i raw data
		String line7;
		List<String> listaCommonU133APlus2CoordU133AintensitaPlus2daOrdinare = new ArrayList<String>(); 
		BufferedReader fileCoordU133AintensitaPlus2daOrdinare = new BufferedReader(
				new FileReader(
						"C:\\Documents and Settings\\Administrator\\Desktop\\Nuovacartella\\tmp\\ProbesComuniU133A-Plus2(coordU133A-intensitaPlus2).txt"));
		while ((line7 = fileCoordU133AintensitaPlus2daOrdinare.readLine()) != null) {
			listaCommonU133APlus2CoordU133AintensitaPlus2daOrdinare.add(line7);			
		}
		fileCoordU133AintensitaPlus2daOrdinare.close();
		Collections.sort(listaCommonU133APlus2CoordU133AintensitaPlus2daOrdinare);
		BufferedWriter bw6 = new BufferedWriter(new FileWriter("C:\\Documents and Settings\\Administrator\\Desktop\\Nuovacartella\\tmp\\ProbesComuniU133A-Plus2(coordU133A-intensitaPlus2)ordinati.txt"));
		for (String string2 : listaCommonU133APlus2CoordU133AintensitaPlus2daOrdinare) {
			String[] field3 = string2.split("\t");
			bw6.write(field3[0]+"\t"+field3[1]+"\t"+field3[3]+"\t"+field3[4]+"\t"+field3[5]+"\r\n");
		bw6.flush();	
		}
		bw6.close();
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
AssociaCoordU133A.AssociaCoord();
	}

}
