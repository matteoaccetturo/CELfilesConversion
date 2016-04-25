import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;



public class CelFileConvert {
	
	public static String fileIntensitaU133A() throws IOException{
		//String nomeFileIntensità;
		BufferedReader stdin1 = new BufferedReader(
				new InputStreamReader(System.in));
				System.out.print("file intensità U133A: ");
				//System.out.println(stdin1.readLine());
				String nomeFileIntensitaU133A = stdin1.readLine();
				//stdin1.close();
				System.out.println("ok: "+nomeFileIntensitaU133A);
				return nomeFileIntensitaU133A;
	}
	
	public static String fileIntensitaNonU133A() throws IOException{
		//String nomeFileIntensità;
		BufferedReader stdin2 = new BufferedReader(
				new InputStreamReader(System.in));
				System.out.print("file intensità non U133A: ");
				//System.out.println(stdin1.readLine());
				String nomeFileIntensitaNonU133A = stdin2.readLine();
				//stdin2.close();
				System.out.println("ok: "+nomeFileIntensitaNonU133A);
				return nomeFileIntensitaNonU133A;
	}
	
	public static String CELfile() throws IOException{
		//String nomeFileIntensità;
		BufferedReader stdin3 = new BufferedReader(
				new InputStreamReader(System.in));
				System.out.print("CEL file: ");
				//System.out.println(stdin1.readLine());
				String nomeCELfile = stdin3.readLine();
				//stdin3.close();
				System.out.println("ok: "+nomeCELfile);
				return nomeCELfile;
	}
	
	public static void makeU133A() throws IOException {
		
		//carica set probes U133A
		//#########################cambio file (file intensità U133A)
		Set<String> setProbesU133A = new TreeSet<String>();
		String line;
		String fileIntensitaU133A = CelFileConvert.fileIntensitaU133A();
		//BufferedWriter bw1 = new BufferedWriter(new FileWriter("C:\\Users\\Matteo\\Desktop\\Nuovacartella\\tmp\\ProbesU133A.txt"));
		BufferedReader U133A = new BufferedReader(
				new FileReader(
						"D:\\Nuovacartella\\intensities\\"+fileIntensitaU133A));
		U133A.readLine();
		U133A.readLine();
		U133A.readLine();
		U133A.readLine();
		U133A.readLine();
		U133A.readLine();
		U133A.readLine();
		while ((line = U133A.readLine()) != null) {
			String[] riga = line.split("\t");				
			//bw1.write(field[])	
			setProbesU133A.add(riga[4]);
		}
		U133A.close();
		System.out.println("numero probes U133A: "+setProbesU133A.size());


		//Crea file probes comuni coord non U133A
		//#################################cambio file (file intensità non U133A)
		String fileIntensitaNonU133A = CelFileConvert.fileIntensitaNonU133A();
		BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\Nuovacartella\\tmp\\ProbesComuni(coordNonU133A).txt"));
		Set<String> setPlus2 = new TreeSet<String>();
		Set<String> setCommonU133APlus2 = new TreeSet<String>();
		String linePlus2;		
		BufferedReader Plus2 = new BufferedReader(
				new FileReader(
						"D:\\Nuovacartella\\intensities\\"+fileIntensitaNonU133A));
		Plus2.readLine();
		Plus2.readLine();
		Plus2.readLine();
		Plus2.readLine();
		Plus2.readLine();
		Plus2.readLine();
		Plus2.readLine();
		while ((linePlus2 = Plus2.readLine()) != null) {
			String[] field = linePlus2.split("\t");
			//for (String riga : setProbesU133A) {
			//String[] field1 = riga.split("\t");
			if (setProbesU133A.contains(field[4])) {
				//System.out.println("trovato");
				bw.write(field[4]+"\t"+field[1]+"\t"+field[2]+"\t"+field[7]+"\r\n");
				bw.flush();
				setCommonU133APlus2.add(field[4]);
			}
			//}
			setPlus2.add(linePlus2);
		}
		Plus2.close();
		bw.close();
		System.out.println("numero righe file non U133A: "+setPlus2.size());
		System.out.println("numero di probes comuni: "+setCommonU133APlus2.size());

		//Crea file probes comuni coord U133A
		//#########################cambio file (file intensità U133A)
		String line4;
		BufferedWriter bw2 = new BufferedWriter(new FileWriter("D:\\Nuovacartella\\tmp\\ProbesComuni(coordU133A).txt"));

		BufferedReader fileU133A = new BufferedReader(
				new FileReader(
						"D:\\Nuovacartella\\intensities\\"+fileIntensitaU133A));
		fileU133A.readLine();
		fileU133A.readLine();
		fileU133A.readLine();
		fileU133A.readLine();
		fileU133A.readLine();
		fileU133A.readLine();
		fileU133A.readLine();		
		while ((line4 = fileU133A.readLine()) != null) {
			String[] field3 = line4.split("\t");			
			if (setCommonU133APlus2.contains(field3[4])) {
				bw2.write(field3[4]+"\t"+field3[1]+"\t"+field3[2]+"\r\n");
				bw2.flush();
			}			
		}
		fileU133A.close();
		bw2.close();


		/*
		String line3;
		List<String> listaCommonU133APlus2CoordPlus2 = new ArrayList<String>();
		BufferedReader CommonU133APlus2CoordPlus2 = new BufferedReader(
				new FileReader(
						"C:\\Documents and Settings\\Administrator\\Desktop\\Nuovacartella\\tmp\\ProbesComuniU133A-Plus2(coordPlus2).txt"));
		while ((line3 = CommonU133APlus2CoordPlus2.readLine()) != null) {
			listaCommonU133APlus2CoordPlus2.add(line3);
		}
		CommonU133APlus2CoordPlus2.close();
		System.out.println("numero righe file setCommonU133APlus2CoordPlus2: "+listaCommonU133APlus2CoordPlus2.size());


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


String line6;
List<String> listaCommonU133APlus2CoordPlus2daOrdinare = new ArrayList<String>(); 
BufferedReader filecoordPlus2daOrdinare = new BufferedReader(
		new FileReader(
				"C:\\Documents and Settings\\Administrator\\Desktop\\Nuovacartella\\tmp\\ProbesComuniU133A-Plus2(coordPlus2).txt"));
while ((line6 = filecoordPlus2daOrdinare.readLine()) != null) {
	listaCommonU133APlus2CoordPlus2daOrdinare.add(line6);			
}
filecoordPlus2daOrdinare.close();
Collections.sort(listaCommonU133APlus2CoordPlus2daOrdinare);
BufferedWriter bw4 = new BufferedWriter(new FileWriter("C:\\Documents and Settings\\Administrator\\Desktop\\Nuovacartella\\tmp\\ProbesComuniU133A-Plus2(coordPlus2)ordinati.txt"));
for (String string1 : listaCommonU133APlus2CoordPlus2daOrdinare) {
bw4.write(string1+"\r\n");
bw4.flush();	
}
bw4.close();


		 BufferedReader fileU133A = new BufferedReader(
				new FileReader(
						"C:\\Documents and Settings\\Administrator\\Desktop\\Nuovacartella\\intensities\\CD8+_IV_T35_(HG-U133A)-2.txt"));
		fileU133A.readLine();
		fileU133A.readLine();
		fileU133A.readLine();
		fileU133A.readLine();
		fileU133A.readLine();
		fileU133A.readLine();
		fileU133A.readLine();
		while ((line = fileU133A.readLine()) != null) {

			listaU133A.add(line);
		}
		fileU133A.close();
		System.out.println("numero righe file U133A: "+listaU133A.size());

		BufferedWriter bw1 = new BufferedWriter(new FileWriter("C:\\Documents and Settings\\Administrator\\Desktop\\Nuovacartella\\tmp\\ProbesComuniU133A-Plus2(coordU133A).txt"));

		int i;
		Object o;
		String riga1;
		String riga2;
		BufferedWriter bw5 = new BufferedWriter(new FileWriter("C:\\Documents and Settings\\Administrator\\Desktop\\Nuovacartella\\tmp\\ProbesComuniU133A-Plus2(coordU133A-intensitaPlus2)ordinati.txt"));
		for(i=0; i<listaCommonU133APlus2CoordU133AdaOrdinare.size(); i++) {
			o=listaCommonU133APlus2CoordU133AdaOrdinare.get(i);
			riga1 = listaCommonU133APlus2CoordU133AdaOrdinare.get(i).toString();
			riga2 = listaCommonU133APlus2CoordPlus2daOrdinare.get(i).toString();
			String[] field1 = riga1.split("\t");
			String[] field2 = riga2.split("\t");
			if (field1[0].equalsIgnoreCase(field2[0])) {
				System.out.println(i);
				bw5.write(field1[1]+"\t"+field1[2]+"\t"+field1[0]+"\t"+field2[3]+"\r\n");
				bw5.flush();
			}
			//System.out.println(o.toString());
		}
		bw5.close();

		 */


		//toglie una cifra decimale al file dei probes comuni (coord non U133A) e elimina i nomi dei probes
		String line8;
		//List<String>listaProbesComuni = new ArrayList<String>();
		Set<String>setProbesComuni = new TreeSet<String>();
		BufferedReader file1 = new BufferedReader(
				new FileReader(
						"D:\\Nuovacartella\\tmp\\ProbesComuni(coordNonU133A).txt"));
		while ((line8 = file1.readLine()) != null) {
			String[] campo = line8.split("_at\t");
			String text2 = campo[1].replace(".00",".0");
			//listaProbesComuni.add(text2);
			setProbesComuni.add(text2);

		}	//System.out.println(listaProbesComuni.size());
		System.out.println("numero probes comuni dopo aver tolto i nomi e la doppia cifra decimale: "+setProbesComuni.size());
		file1.close();



		//riscrive il CEL file senza spazi bianchi superflui
		//################################################cambio file (CEL file)
		String fileCELconvertito = CelFileConvert.CELfile();
		BufferedWriter bw9 = new BufferedWriter(new FileWriter("D:\\Nuovacartella\\tmp\\fileCELtmp.txt"));
		BufferedReader CELfile = new BufferedReader(
				new FileReader(
						"D:\\Nuovacartella\\Filesconvertiti\\"+fileCELconvertito));
		for (int i = 0; i < 24; i++) {
			CELfile.readLine();
		}
		String lineCELfile;

		while ((lineCELfile = CELfile.readLine()) != null) {

			String text1 = lineCELfile.replace(" ","");
			String[] lineCELfilemod = text1.split("\t");
			if (lineCELfilemod.length>2) {
				if (lineCELfilemod[0].startsWith("1")||lineCELfilemod[0].startsWith("1")||lineCELfilemod[0].startsWith("2")||lineCELfilemod[0].startsWith("3")||lineCELfilemod[0].startsWith("4")||lineCELfilemod[0].startsWith("5")||lineCELfilemod[0].startsWith("6")||lineCELfilemod[0].startsWith("7")||lineCELfilemod[0].startsWith("8")||lineCELfilemod[0].startsWith("9")||lineCELfilemod[0].startsWith("0")) {
				bw9.write(lineCELfilemod[0]+"\t"+lineCELfilemod[1]+"\t"+lineCELfilemod[2]+" ciao "+"\t"+lineCELfilemod[3]+"\t"+lineCELfilemod[4]+"\r\n");
				bw9.flush();
				}		
			}
			else{
				bw9.write(text1);
				bw9.flush();
				System.out.println(lineCELfilemod[0]);
			}
		}CELfile.close(); bw9.close();

		//seleziona dal CEL file solo i probes in comune con U133A
		//step lento!!!
		String nuovalineCELfile;
		BufferedWriter bw8 = new BufferedWriter(new FileWriter("D:\\Nuovacartella\\tmp\\ProbesComuni(coordNonU133A)completo.txt"));
		BufferedReader CELfilemod = new BufferedReader(
				new FileReader(
						"D:\\Nuovacartella\\tmp\\fileCELtmp.txt"));
		while ((nuovalineCELfile = CELfilemod.readLine()) != null) {

			String[] newlineCELfile = nuovalineCELfile.split(" ciao ");
			/*if (listaProbesComuni.contains(newlineCELfile[0])) {
				//System.out.println("ok");
				bw8.write(newlineCELfile[0]+newlineCELfile[1]+"\r\n");
				bw8.flush();

			}*/
			if (setProbesComuni.contains(newlineCELfile[0])) {
				//System.out.println("ok");
				bw8.write(newlineCELfile[0]+newlineCELfile[1]+"\r\n");
				bw8.flush();

			}
		}CELfilemod.close(); bw8.close();


		//legge il file dei probes comuni senza nome del probe (proviene dal CEL file da cui sono estratti solo i probes comuni) e lo ordina
		List<String> listaProbesComuniConRawData = new ArrayList<String>();
		String line3;
		BufferedReader CommonU133APlus2CoordPlus2completo = new BufferedReader(
				new FileReader(
						"D:\\Nuovacartella\\tmp\\ProbesComuni(coordNonU133A)completo.txt"));
		while ((line3 = CommonU133APlus2CoordPlus2completo.readLine()) != null) {
			listaProbesComuniConRawData.add(line3);
		}
		CommonU133APlus2CoordPlus2completo.close();
		Collections.sort(listaProbesComuniConRawData);
		System.out.println("numero probes con raw data: "+listaProbesComuniConRawData.size());


		//legge il file dei probes comuni col nome del probe e lo riscrive mettendo la colonna dei nomi alla fine
		String line1;
		BufferedWriter bw10 = new BufferedWriter(new FileWriter("D:\\Nuovacartella\\tmp\\ProbesComuni(coordNonU133A)daOrdinare.txt"));
		BufferedReader CommonU133APlus2CoordPlus2WithNameDaOrdinare = new BufferedReader(
				new FileReader(
						"D:\\Nuovacartella\\tmp\\ProbesComuni(coordNonU133A).txt"));
		while ((line1 = CommonU133APlus2CoordPlus2WithNameDaOrdinare.readLine()) != null) {
			String[] riga = line1.split("\t");
			bw10.write(riga[1]+"\t"+riga[2]+"\t"+riga[3]+"\t"+riga[0]+"\r\n");
			bw10.flush();
		}CommonU133APlus2CoordPlus2WithNameDaOrdinare.close(); bw10.close();


		//legge il file con la colonna dei nomi alla fine e lo ordina
		List<String> listaProbesComuniConNomeProbe = new ArrayList<String>();
		String line2;
		BufferedReader CommonU133APlus2CoordPlus2WithName = new BufferedReader(
				new FileReader(
						"D:\\Nuovacartella\\tmp\\ProbesComuni(coordNonU133A)daOrdinare.txt"));
		while ((line2 = CommonU133APlus2CoordPlus2WithName.readLine()) != null) {
			//String[] riga = line.split("\t");			
			listaProbesComuniConNomeProbe.add(line2);

		}CommonU133APlus2CoordPlus2WithName.close();
		Collections.sort(listaProbesComuniConNomeProbe);

		System.out.println("numero probes con nome: "+listaProbesComuniConNomeProbe.size());

		//associa i nomi dei probes al file dei probes comuni con i raw data (intensita, dev standard e num pixel)
		int i;
		String riga1;
		String riga2;
		BufferedWriter bw1 = new BufferedWriter(new FileWriter("D:\\Nuovacartella\\tmp\\ProbesComuni(coordNonU133A-intensitaNonU133A).txt"));
		for(i=0; i<listaProbesComuniConNomeProbe.size(); i++) {

			riga1 = listaProbesComuniConNomeProbe.get(i).toString();
			riga2 = listaProbesComuniConRawData.get(i).toString();
			String[] field1 = riga1.split("\t");
			String[] field2 = riga2.split("\t");
			if (field1[0].equalsIgnoreCase(field2[0]) && field1[1].equalsIgnoreCase(field2[1])) {
				//System.out.println(i);
				bw1.write(field1[3]+"\t"+field2[0]+"\t"+field2[1]+"\t"+field2[2]+"\t"+field2[3]+"\t"+field2[4]+"\r\n");
				bw1.flush();
			}		
		}
		bw1.close();


		//legge il file con le coord U133A e lo riscrive ordinato
		String line5;
		List<String> listaCommonU133APlus2CoordU133AdaOrdinare = new ArrayList<String>(); 
		BufferedReader filecoordU133AdaOrdinare = new BufferedReader(
				new FileReader(
						"D:\\Nuovacartella\\tmp\\ProbesComuni(coordU133A).txt"));
		while ((line5 = filecoordU133AdaOrdinare.readLine()) != null) {
			listaCommonU133APlus2CoordU133AdaOrdinare.add(line5);			
		}
		filecoordU133AdaOrdinare.close();		
		Collections.sort(listaCommonU133APlus2CoordU133AdaOrdinare);
		BufferedWriter bw3 = new BufferedWriter(new FileWriter("D:\\Nuovacartella\\tmp\\ProbesComuni(coordU133A)ordinati.txt"));
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
						"D:\\Nuovacartella\\tmp\\ProbesComuni(coordNonU133A-intensitaNonU133A).txt"));
		while ((line6 = filecoordPlus2daOrdinare.readLine()) != null) {
			listaCommonU133APlus2CoordPlus2daOrdinare.add(line6);			
		}
		filecoordPlus2daOrdinare.close();
		Collections.sort(listaCommonU133APlus2CoordPlus2daOrdinare);
		BufferedWriter bw4 = new BufferedWriter(new FileWriter("D:\\Nuovacartella\\tmp\\ProbesComuni(coordNonU133A-intensitaNonU133A)ordinati.txt"));
		for (String string1 : listaCommonU133APlus2CoordPlus2daOrdinare) {
			bw4.write(string1+"\r\n");
			bw4.flush();	
		}
		bw4.close();

		//associa le coord U133A al file con i raw data
		int j;
		String riga3;
		String riga4;
		BufferedWriter bw5 = new BufferedWriter(new FileWriter("D:\\Nuovacartella\\tmp\\ProbesComuni(coordU133A-intensitaNonU133A).txt"));
		for(j=0; j<listaCommonU133APlus2CoordU133AdaOrdinare.size(); j++) {
			riga3 = listaCommonU133APlus2CoordU133AdaOrdinare.get(j).toString();
			riga4 = listaCommonU133APlus2CoordPlus2daOrdinare.get(j).toString();
			String[] field1 = riga3.split("\t");
			String[] field2 = riga4.split("\t");
			if (field1[0].equalsIgnoreCase(field2[0])) {
				//System.out.println(j);
				bw5.write(field1[1]+"\t"+field1[2]+"\t"+field1[0]+"\t"+field2[3]+"\t"+field2[4]+"\t"+field2[5]+"\r\n");
				bw5.flush();
			}
			//System.out.println(o.toString());
		}
		bw5.close();

		//toglie i nomi dei probes al file con le coord U133A e i raw data (pseudo CEL file)
		String line7;
		List<String> listaCommonU133APlus2CoordU133AintensitaPlus2daOrdinare = new ArrayList<String>(); 
		BufferedReader fileCoordU133AintensitaPlus2daOrdinare = new BufferedReader(
				new FileReader(
						"D:\\Nuovacartella\\tmp\\ProbesComuni(coordU133A-intensitaNonU133A).txt"));
		while ((line7 = fileCoordU133AintensitaPlus2daOrdinare.readLine()) != null) {
			listaCommonU133APlus2CoordU133AintensitaPlus2daOrdinare.add(line7);			
		}
		fileCoordU133AintensitaPlus2daOrdinare.close();
		Collections.sort(listaCommonU133APlus2CoordU133AintensitaPlus2daOrdinare);
		String nomeFileOutput=fileCELconvertito.replace(".CEL", "_");
		BufferedWriter bw6 = new BufferedWriter(new FileWriter("D:\\Nuovacartella\\tmp\\"+nomeFileOutput+"pseudoCEL.txt"));
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
		CelFileConvert.makeU133A();

	}

}
