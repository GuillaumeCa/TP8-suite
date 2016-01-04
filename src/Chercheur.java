import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chercheur {
	
	public static String interdit="";
	
	public static String recherche(String évalué, String mot) {
		String généré="";
		for (int i = 0; i < 7; i++) {
			if (évalué.charAt(i) == 'x' && mot.charAt(i) != '-') {
				interdit += mot.charAt(i);
			}
		}
		for (int i = 0; i < 7; i++) {
			if (mot.charAt(i) != '-') {
				if (évalué.charAt(i) == 'o') {
					généré += mot.charAt(i);
				}
				if (évalué.charAt(i) == '-') {
					généré += "[^"+mot.charAt(i)+interdit+"]";
				}
			}
			if (évalué.charAt(i) == 'x') {
				généré += "[^"+interdit+"]";
			}
		}
		return généré;
	}
	
	public static String parcours(Pattern pattern) {
		Matcher match;
		Path p = Paths.get(Dictionnaire.dicPath);
		Charset charset = Charset.forName("UTF-8");
		try (BufferedReader reader = Files.newBufferedReader(p, charset)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				match = pattern.matcher(line);
				if (match.find()) {
					break;
				}
			}
			return line;
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		return "";
	}
	
	public static void main(String[] args) {
		
		Pattern pattern;
		
		Dictionnaire.dicPath = "/Users/Guillaume/Documents/Dev/Java/TP8/dict.txt";
		
		String mot = Dictionnaire.choose();
		int lettres = mot.length();
		char plettre = mot.charAt(0);
		
		System.out.println("Le mot a "+lettres+" lettres et commence par \""+plettre+"\".");
		System.out.println("Entrez une proposition:");
		
		String prop = Dictionnaire.choose();
		
		System.out.println(prop);
		System.out.println(Dictionnaire.evaluer(mot, prop));
		System.out.println("");
		
		
		int cout=0;
		boolean egal = false;
		while (!egal) {
			pattern = Pattern.compile(recherche(Dictionnaire.evaluer(mot, prop), prop));
			prop = parcours(pattern);
			System.out.println(prop);
			System.out.println(Dictionnaire.evaluer(mot, prop));
			System.out.println("");
			if (prop.equals(mot)) {
				egal = true;
			}
			cout++;
		}
		System.out.println("Bravo ! Vous avez trouvé le mot en "+cout+" essais !");
	}
}
