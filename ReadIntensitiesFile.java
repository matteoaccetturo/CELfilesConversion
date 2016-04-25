import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class ReadIntensitiesFile {

	
	public static Set<String> setcoordU133ADaIntensitiesfile() throws IOException{
		Set<String>coordU133ADaIntensitiesfile =new TreeSet<String>();
		BufferedReader Intensitiesfile = new BufferedReader(
				new FileReader(
				"D:\\Nuovacartella\\intensities\\CD8+_IV_T35_(HG-U133A)-2.txt"));
		for (int i = 0; i < 7; i++) {
			Intensitiesfile.readLine();
		}
		String lineIntensitiesfile;
		while ((lineIntensitiesfile = Intensitiesfile.readLine()) != null) {
			String[] field = lineIntensitiesfile.split("\t");
			coordU133ADaIntensitiesfile.add(field[1]+"\t"+field[2]+"\t"+field[4]);
			}
		Intensitiesfile.close();
		System.out.println(coordU133ADaIntensitiesfile.size());
	return coordU133ADaIntensitiesfile;
}
	/*
	public static void associaCoordU133AaNomiProbes() throws IOException{
		Set<String> setCoordU133AdaCELfile = ReadCELfile.setcoordU133ADaCELfile();
		List<String>coordU133ADaIntensitiesfile = ReadIntensitiesFile.setcoordU133ADaIntensitiesfile();
		BufferedWriter bw1 = new BufferedWriter(new FileWriter("D:\\Nuovacartella\\tmp\\fileCoordU133A-nomiProbes.txt"));
		for (String string : coordU133ADaIntensitiesfile) {
			String[] field1 = string.split("\t");
			if (setCoordU133AdaCELfile.contains(field1[0]+"\t"+field1[1])) {
				bw1.write(field1[0]+"\t"+field1[1]+"\t"+field1[2]+"\r\n");
				bw1.flush();
			}
			
		}bw1.close();
	}
	*/
	public static Set<String>associaCoordU133AaNomiProbes() throws IOException{
		Set<String> setCoordU133AconNomiProbes = new TreeSet<String>();
		Set<String> setCoordU133AdaCELfile = ReadCELfile.setcoordU133ADaCELfile();
		Set<String>coordU133ADaIntensitiesfile = ReadIntensitiesFile.setcoordU133ADaIntensitiesfile();
		for (String string : coordU133ADaIntensitiesfile) {
			String[] field1 = string.split("\t");
			if (setCoordU133AdaCELfile.contains(field1[0]+"\t"+field1[1])) {
				setCoordU133AconNomiProbes.add(field1[0]+"\t"+field1[1]+"\t"+field1[2]);
				}			
		}		
		System.out.println(setCoordU133AconNomiProbes.size());
		return setCoordU133AconNomiProbes;		
	}
	
	
	public static Set<String> setcoordNonU133ADaIntensitiesfile() throws IOException{
		Set<String> coordU133AwithProbes = ReadIntensitiesFile.associaCoordU133AaNomiProbes();
		Set<String>U133Aprobes = new TreeSet<String>();
		for (String string : coordU133AwithProbes) {
			String[] campo = string.split("\t"); //System.out.println(campo[2]);
			U133Aprobes.add(campo[2]);
		}
		System.out.println(U133Aprobes.size());
		Set<String>coordNonU133ADaIntensitiesfile = new TreeSet<String>();
		BufferedReader Intensitiesfile = new BufferedReader(
				new FileReader(
				"D:\\Nuovacartella\\intensities\\CD8+_IV_T35_(HG-Plus2.0)-1.txt"));
		for (int i = 0; i < 7; i++) {
			Intensitiesfile.readLine();
		}
		String lineIntensitiesfile;
		while ((lineIntensitiesfile = Intensitiesfile.readLine()) != null) {
			String[] field = lineIntensitiesfile.split("\t");
			//System.out.println(field[4]);
			if (U133Aprobes.contains(field[4])) {
				//System.out.println("ok");
				coordNonU133ADaIntensitiesfile.add(field[1]+"\t"+field[2]+"\t"+field[4]);
			}
			
		}
		Intensitiesfile.close();
		System.out.println(coordNonU133ADaIntensitiesfile.size());
				
		return coordNonU133ADaIntensitiesfile;		
	}
	
	public static void stampaSetcoordNonU133ADaIntensitiesfile() throws IOException{
		Set<String> tmp = ReadIntensitiesFile.setcoordNonU133ADaIntensitiesfile();
		BufferedWriter bw1 = new BufferedWriter(new FileWriter("D:\\Nuovacartella\\tmp\\fileCoordNonU133A-nomiProbes.txt"));
		for (String string : tmp) {
			bw1.write(string+"\r\n");
			bw1.flush();
		}bw1.close();
	}
	
	
	public static Set<String> coordU133AcoordNonU133A() throws IOException{
		String line4;
		Set<String> setCoordU133AcoorNonU133A = new TreeSet<String>();
		Set<String> tmp = ReadIntensitiesFile.setcoordNonU133ADaIntensitiesfile();
		Set<String>tmp1 = ReadIntensitiesFile.setcoordU133ADaIntensitiesfile();
		for (String string : tmp1) {
			String[] field = string.split("\t");
			Iterator<String> it1 = tmp.iterator();
			while (it1.hasNext()) {
				line4 = it1.next();
				String[] field1 = line4.split("\t");
				// System.out.println("*********");
								
			if (field1[2].equalsIgnoreCase(field[2])) {
				//System.out.println("ok");
				setCoordU133AcoorNonU133A.add(string+"\t"+field1[0]+"\t"+field1[1]+"\t"+field1[2]);
			}
			}
		}
		System.out.println("ciao"+setCoordU133AcoorNonU133A.size());
		return setCoordU133AcoorNonU133A;		
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//ReadIntensitiesFile.setcoordU133ADaIntensitiesfile();
//ReadIntensitiesFile.associaCoordU133AaNomiProbes();
//ReadIntensitiesFile.setcoordNonU133ADaIntensitiesfile();
ReadIntensitiesFile.coordU133AcoordNonU133A();
	}

}
