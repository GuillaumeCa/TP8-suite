import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Dictionnaire {
	
	public static String dicPath;
	
	public static boolean find(String mot) {
		Path p = Paths.get(dicPath);
		Charset charset = Charset.forName("UTF-8");
		try (BufferedReader reader = Files.newBufferedReader(p, charset)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (mot.equals(line)) {
					return true;
				}
			}
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		return false;
	}
	
	
	public static String choose() {
		Path p = Paths.get(dicPath);
		Charset charset = Charset.forName("UTF-8");
		try (BufferedReader reader = Files.newBufferedReader(p, charset)) {
			String line = null;
			int taille =0;
			while ((line = reader.readLine()) != null) {
				taille++;
			}
			int l = (int) (Math.random()*(taille-1));
			int count=0;
			BufferedReader reader1 = Files.newBufferedReader(p, charset);
			while ((line = reader1.readLine()) != null) {
				if (count == l) {
					return line;
				}
				count++;
			}
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		
		return "";
	}
	
	public static String evaluer(String recherche, String proposé) {
		if (proposé.length() == recherche.length()) {
			if (find(proposé)) {
				String barre = "";
				for (int i = 0; i < proposé.length(); i++) {
					if (proposé.charAt(i) == recherche.charAt(i)) {
						barre += "o";
					}
					if (proposé.charAt(i) != recherche.charAt(i) && recherche.indexOf(proposé.charAt(i)) != -1) {
						barre += "-";
					}
					if (recherche.indexOf(proposé.charAt(i)) == -1) {
						barre += "x";
					}
				}
				return barre;
			} else {
				return "Le mot proposé n'existe pas.";
			}
		} else {
			return "Le mot proposé ne fait pas 7 caractères.";
		}
	}
	
	
	public static void main(String[] args) {
		
		dicPath = "/Users/Guillaume/Documents/Dev/Java/TP8/dict.txt";
		Scanner sc = new Scanner(System.in);
		
		String mot = choose();
		int lettres = mot.length();
		char plettre = mot.charAt(0);
		System.out.println("Le mot a "+lettres+" lettres et commence par \""+plettre+"\".");
		System.out.println("Entrez une proposition:");
		
		boolean egal = false;
		boolean abandonner = false;
		int score = 1;
		
		while (!egal) {
			System.out.print("> ");
			String proposition = sc.nextLine();
			if (proposition.equals("quit")) {
				abandonner = true;
				break;
			}
			System.out.println("  "+evaluer(mot, proposition));
			if (proposition.equals(mot)) {
				egal = true;
			} else {
				score++;
			}
			
		}
		if (abandonner) {
			System.out.println("\nVous avez choisi d'abandonner:");
			System.out.println("Le mot était "+mot+".");
		} else {
			System.out.println("\nGagné !");
			System.out.println("Score: "+score);
			System.out.println("Nombre d'essais: "+score);
		}
		
	}
}
