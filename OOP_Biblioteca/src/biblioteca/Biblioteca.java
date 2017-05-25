package biblioteca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class Biblioteca {
    Map<String, Libro> libri = new TreeMap<>();
    List<Volume> volumi = new ArrayList<>();
    Map<String, Utente> utenti = new TreeMap<>();


    public void addLibro(String titolo, int nVolumi, String... autori) throws BiblioEccezione {
	Libro l = new Libro(titolo, nVolumi, autori);
	libri.put(titolo,  l);
	int i;
	for (i=0; i<nVolumi; i++) {
	    volumi.add(new Volume(l));
	}
    }

    public void addUtente(String nome, int maxPrestiti, int durata) throws BiblioEccezione {
	utenti.put(nome, new Utente(nome, maxPrestiti, durata));
    }

    public PrestitoI addPrestito(String utente, String titolo) throws BiblioEccezione {
	return null;
    }

    public void restituzione(String utente, int indice) throws BiblioEccezione {
    }

    public List<PrestitoI> prestitiScaduti() {
	return null;
    }

    public void addGiorni(int giorni) {
    };
    
    public LocalDate getOggi() {
	return null;
    }

    public SortedMap<String, Long> nPrestitiXTitolo() {
	return null;
    }

    public SortedMap<Long, Set<String>> titoliXnPrestiti() {
	return null;
    }

    public List<InfoI> utentiNPrestiti() {
	return null;
    }

    public List<InfoI> autoriNPrestiti() {
	return null;
    }

}
