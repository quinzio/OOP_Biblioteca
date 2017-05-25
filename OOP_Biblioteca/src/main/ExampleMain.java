package main;

import java.util.List;
import java.util.Set;
import java.util.SortedMap;

import biblioteca.BiblioEccezione;
import biblioteca.Biblioteca;
import biblioteca.InfoI;
import biblioteca.PrestitoI;

public class ExampleMain {

    public static void main(String[] args) throws BiblioEccezione {
	Biblioteca b = new Biblioteca();
	b.addLibro("rosso", 1, "falco");
	b.addLibro("giallo", 2, "falco", "rondine");
	b.addLibro("verde", 3, "aquila");
	b.addLibro("nero", 4, "corvo");
	try {
	    b.addLibro("giallo", 2);
	} catch (BiblioEccezione e) {
	    {
		System.out.println(e.getMessage());
	    };
	}
	b.addUtente("quercia", 2, 7);
	b.addUtente("faggio", 1, 10);
	b.addUtente("acero", 3, 14);
	System.out.println("oggi " + b.getOggi()); // oggi 2016-05-12
	PrestitoI p = b.addPrestito("faggio", "giallo");
	System.out.println(p.getInfo());
	// prestito: utente faggio titolo giallo volume 1 scadenza 2016-05-22
	b.addPrestito("quercia", "rosso");
	b.addPrestito("acero", "giallo");
	try {
	    b.addPrestito("faggio", "nero");
	} catch (BiblioEccezione e) {
	    {
		System.out.println(e.getMessage());
	    }
	    ;
	}
	b.addGiorni(1); // domani
	b.addPrestito("quercia", "verde");
	b.addPrestito("acero", "verde");
	b.restituzione("quercia", 0);

	b.addGiorni(20);
	List<PrestitoI> prestitiScaduti = b.prestitiScaduti();
	PrestitoI.stampaListaPrestiti("prestitiScaduti", prestitiScaduti, b.getOggi());
	b.restituzione("quercia", 3);
	b.restituzione("faggio", 1);
	b.restituzione("acero", 2);
	b.restituzione("acero", 4);
	List<InfoI> lista = b.utentiNPrestiti();
	InfoI.stampaListaInfoI("utentiNPrestiti", lista);
	InfoI.stampaListaInfoI("autoriNPrestiti", b.autoriNPrestiti());
	SortedMap<String, Long> mappa = b.nPrestitiXTitolo();
	System.out.println("nPrestitiXTitolo " + mappa);
	SortedMap<Long, Set<String>> mappa2 = b.titoliXnPrestiti();
	System.out.println("titoliXnPrestiti " + mappa2);
	b.addGiorni(20);
	PrestitoI.stampaListaPrestiti("prestitiScaduti", b.prestitiScaduti(), b.getOggi());
    }

}
