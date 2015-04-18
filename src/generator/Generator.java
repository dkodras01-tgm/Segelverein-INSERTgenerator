package generator;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;


public class Generator {
	
	private static ArrayList<String> pnamen = new ArrayList<String>();
	
	public static void main(String[] args) throws IOException {
		person();
		segler_trainer();
		boot();
	}
	
	private static void boot() {
		try {
			System.setOut(new PrintStream("insert-boot.sql"));
			BufferedReader reader1 = new BufferedReader(new FileReader("Bootsnamen.txt"));
			String n;
			ArrayList<String> name = new ArrayList<String>();
			
			while((n = reader1.readLine()) != null) {
				name.add(n.trim());
			}
			int i1;
			for(int i=0; i<10000; i++) {
				i1 = (int) Math.floor(Math.random()*name.size());
				System.out.println("INSERT INTO boot (name, personen, tiefgang) VALUES ('" + name.get(i1) + "', " + (int) (Math.random()*20+1) + ", " + (int) (Math.random()*300+1) + ");");
			}
			reader1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void segler_trainer() {
		
		try {
			PrintStream p1 = new PrintStream("insert-segler.sql");
			PrintStream p2 = new PrintStream("insert-trainer.sql");
			
			for(int i=1; i<=10000; i++) {
				if(Math.random() < 0.75) {
					System.setOut(p1);
					System.out.println("INSERT INTO segler (personkey) VALUES (" + i + ");");
				} else {
					System.setOut(p2);
					System.out.println("INSERT INTO trainer (personkey) VALUES (" + i + ");");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static void person() {
		try {
			System.setOut(new PrintStream("insert-person.sql"));
			
			pNamenGen();
			for(int i=0; i<10000; i++) {
				System.out.println("INSERT INTO person (name, geburtsdatum) VALUES ('" + pnamen.get(i) + "', '" + gebDatGen() + "');");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static String gebDatGen() {
		int jahr = (int)Math.floor(Math.random()*20+1950);
		int monat = (int)Math.floor(Math.random()*12+1);
		int tag = (int)Math.floor(Math.random()*28+1);
		return jahr + "-" + monat + "-" + tag;
	}
	
	private static void pNamenGen() {
		try {
			BufferedReader reader1 = new BufferedReader(new FileReader("Vornamen.txt"));
			BufferedReader reader2 = new BufferedReader(new FileReader("Nachnamen.txt"));
			String vorn = "";
			String nachn = "";
			ArrayList<String> vorarray = new ArrayList<String>();
			ArrayList<String> nacharray = new ArrayList<String>();
			while((nachn = reader2.readLine()) != null) {
				vorarray.add(nachn.trim());
			}
			while((vorn = reader1.readLine()) != null) {
				nacharray.add(vorn.trim());
			}
			int i1, i2;
			for(int i=0; i<10000; i++) {
				i1 = (int) Math.floor(Math.random()*vorarray.size());
				i2 = (int) Math.floor(Math.random()*nacharray.size());
				pnamen.add(vorarray.get(i1) + " " + nacharray.get(i2));
			}
			reader1.close();
			reader2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}