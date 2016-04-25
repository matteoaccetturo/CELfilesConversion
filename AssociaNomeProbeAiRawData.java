import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AssociaNomeProbeAiRawData {

	public static void AssociaNomeProbe() throws IOException {
	
	//legge il file dei probes comuni senza nome del probe e lo ordina
	List<String> listaProbesComuniConRawData = new ArrayList<String>();
	String line;
	BufferedReader CommonU133APlus2CoordPlus2completo = new BufferedReader(
			new FileReader(
					"C:\\Documents and Settings\\Administrator\\Desktop\\Nuovacartella\\tmp\\ProbesComuniU133A-Plus2(coordPlus2)completo.txt"));
	while ((line = CommonU133APlus2CoordPlus2completo.readLine()) != null) {
		listaProbesComuniConRawData.add(line);
	}
	CommonU133APlus2CoordPlus2completo.close();
	Collections.sort(listaProbesComuniConRawData);
	System.out.println("numero probes con raw data: "+listaProbesComuniConRawData.size());
	
	
	//legge il file dei probes comuni col nome del probe e lo riscrive mettendo la colonna dei nomi alla fine
	String line1;
	BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Documents and Settings\\Administrator\\Desktop\\Nuovacartella\\tmp\\ProbesComuniU133A-Plus2(coordPlus2)daOrdinare.txt"));
	BufferedReader CommonU133APlus2CoordPlus2WithNameDaOrdinare = new BufferedReader(
			new FileReader(
					"C:\\Documents and Settings\\Administrator\\Desktop\\Nuovacartella\\tmp\\ProbesComuniU133A-Plus2(coordPlus2).txt"));
	while ((line1 = CommonU133APlus2CoordPlus2WithNameDaOrdinare.readLine()) != null) {
		String[] riga = line1.split("\t");
		bw.write(riga[1]+"\t"+riga[2]+"\t"+riga[3]+"\t"+riga[0]+"\r\n");
		bw.flush();
	}CommonU133APlus2CoordPlus2WithNameDaOrdinare.close(); bw.close();
	
	
	//legge il file con la colonna dei nomi alla fine e lo ordina
	List<String> listaProbesComuniConNomeProbe = new ArrayList<String>();
	String line2;
	BufferedReader CommonU133APlus2CoordPlus2WithName = new BufferedReader(
			new FileReader(
					"C:\\Documents and Settings\\Administrator\\Desktop\\Nuovacartella\\tmp\\ProbesComuniU133A-Plus2(coordPlus2)daOrdinare.txt"));
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
	BufferedWriter bw1 = new BufferedWriter(new FileWriter("C:\\Documents and Settings\\Administrator\\Desktop\\Nuovacartella\\tmp\\ProbesComuniU133A-Plus2(coordPlus2-intensitaPlus2)ordinati.txt"));
	for(i=0; i<listaProbesComuniConNomeProbe.size(); i++) {
		
		riga1 = listaProbesComuniConNomeProbe.get(i).toString();
		riga2 = listaProbesComuniConRawData.get(i).toString();
		String[] field1 = riga1.split("\t");
		String[] field2 = riga2.split("\t");
		if (field1[0].equalsIgnoreCase(field2[0]) && field1[1].equalsIgnoreCase(field2[1])) {
			System.out.println(i);
			bw1.write(field1[3]+"\t"+field2[0]+"\t"+field2[1]+"\t"+field2[2]+"\t"+field2[3]+"\t"+field2[4]+"\r\n");
			bw1.flush();
		}		
	}
	bw1.close();	
}
	
	
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
AssociaNomeProbeAiRawData.AssociaNomeProbe();
	}

}
