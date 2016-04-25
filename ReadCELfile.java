import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;



public class ReadCELfile {
	
	/*public static boolean check(String regex, String input){
		  if (Pattern.matches(regex, input))
		    return true;
		  else
		    return false;
		} 
*/

	public static Set<String> setcoordU133ADaCELfile() throws IOException{
			Set<String>coordU133ADaCELfile =new TreeSet<String>();
			BufferedReader CELfile = new BufferedReader(
					new FileReader(
					"D:\\Nuovacartella\\Filesconvertiti\\CD8+_IV_T35_(HG-U133A)-2.CEL"));
			for (int i = 0; i < 24; i++) {
				CELfile.readLine();
			}
			String lineCELfile;
			while ((lineCELfile = CELfile.readLine()) != null) {
				
				String text1 = lineCELfile.replace(" ","");
				
				String[] lineCELfilemod = text1.split("\t");
				//System.out.println(lineCELfilemod.length);
				if (lineCELfilemod.length==5 || lineCELfilemod.length==2) {
					//if (lineCELfilemod[0].startsWith("1")||lineCELfilemod[0].startsWith("1")||lineCELfilemod[0].startsWith("2")||lineCELfilemod[0].startsWith("3")||lineCELfilemod[0].startsWith("4")||lineCELfilemod[0].startsWith("5")||lineCELfilemod[0].startsWith("6")||lineCELfilemod[0].startsWith("7")||lineCELfilemod[0].startsWith("8")||lineCELfilemod[0].startsWith("9")||lineCELfilemod[0].startsWith("0")) {
					coordU133ADaCELfile.add(lineCELfilemod[0]+"\t"+lineCELfilemod[1]);
					//}		
				}
			
		}CELfile.close();
			System.out.println(coordU133ADaCELfile.size());
			BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\Nuovacartella\\tmp\\fileCELprova.txt"));
			for (String string : coordU133ADaCELfile) {
				bw.write(string+"\r\n"); bw.flush();
			}bw.close();
		return coordU133ADaCELfile;
	}
	public static void removeWhiteSpaces() throws IOException{
		
		//toglie una cifra decimale al file dei probes comuni e elimina i nomi dei probes
		String line8;
		List<String>listaProbesComuni = new ArrayList<String>();
		BufferedReader file1 = new BufferedReader(
				new FileReader(
						"C:\\Documents and Settings\\Administrator\\Desktop\\Nuovacartella\\tmp\\ProbesComuniU133A-Plus2(coordPlus2).txt"));
		while ((line8 = file1.readLine()) != null) {
			String[] campo = line8.split("_at\t");
			String text2 = campo[1].replace(".00",".0");
			listaProbesComuni.add(text2);
					
		}	System.out.println(listaProbesComuni.size());
		file1.close();
		
	
		
	//riscrive il CEL file senza spazi bianchi superflui
		
	BufferedWriter bw8 = new BufferedWriter(new FileWriter("C:\\Documents and Settings\\Administrator\\Desktop\\Nuovacartella\\tmp\\ProbesComuniU133A-Plus2(coordPlus2)completo.txt"));
	BufferedWriter bw9 = new BufferedWriter(new FileWriter("C:\\Documents and Settings\\Administrator\\Desktop\\Nuovacartella\\tmp\\fileCELtmp.txt"));
	BufferedReader CELfile = new BufferedReader(
			new FileReader(
			"C:\\Documents and Settings\\Administrator\\Desktop\\Nuovacartella\\Files convertiti\\CD8+_IV_T35_(HG-Plus 2.0)-1.CEL"));
	for (int i = 0; i < 24; i++) {
		CELfile.readLine();
	}
	String lineCELfile;
	
	while ((lineCELfile = CELfile.readLine()) != null) {
		
		String text1 = lineCELfile.replace(" ","");
		String[] lineCELfilemod = text1.split("\t");
		if (lineCELfilemod.length>2) {
			//if (lineCELfilemod[0].startsWith("1")||lineCELfilemod[0].startsWith("1")||lineCELfilemod[0].startsWith("2")||lineCELfilemod[0].startsWith("3")||lineCELfilemod[0].startsWith("4")||lineCELfilemod[0].startsWith("5")||lineCELfilemod[0].startsWith("6")||lineCELfilemod[0].startsWith("7")||lineCELfilemod[0].startsWith("8")||lineCELfilemod[0].startsWith("9")||lineCELfilemod[0].startsWith("0")) {
			bw9.write(lineCELfilemod[0]+"\t"+lineCELfilemod[1]+"\t"+lineCELfilemod[2]+" ciao "+"\t"+lineCELfilemod[3]+"\t"+lineCELfilemod[4]+"\r\n");
		bw9.flush();
			//}		
		}
		else{
			bw9.write(text1);
			bw9.flush();
			System.out.println(lineCELfilemod[0]);
			}
		}CELfile.close(); bw9.close();
	
		//seleziona dal CEL file solo i probes in comune con U133A
		String nuovalineCELfile;
		BufferedReader CELfilemod = new BufferedReader(
				new FileReader(
				"C:\\Documents and Settings\\Administrator\\Desktop\\Nuovacartella\\tmp\\fileCELtmp.txt"));
		while ((nuovalineCELfile = CELfilemod.readLine()) != null) {
			
			String[] newlineCELfile = nuovalineCELfile.split(" ciao ");
			if (listaProbesComuni.contains(newlineCELfile[0])) {
				//System.out.println("ok");
				bw8.write(newlineCELfile[0]+newlineCELfile[1]+"\r\n");
				bw8.flush();
			
			}
			
		}CELfilemod.close(); bw8.close();
	
	
	}
	private static void setCELfile() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//ReadCELfile.removeWhiteSpaces();
ReadCELfile.setcoordU133ADaCELfile();
	}

}
