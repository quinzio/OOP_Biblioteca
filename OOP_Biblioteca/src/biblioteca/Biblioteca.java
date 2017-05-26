package biblioteca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Biblioteca {
	private Map<String, Libro> libri = new TreeMap<>();
	private List<Volume> volumi = new ArrayList<>();
	private Map<String, Utente> utenti = new TreeMap<>();
	private List<Prestito> prestiti = new ArrayList<>();
	private LocalDate dataBiblioteca = LocalDate.now();

	public void addLibro(String titolo, int nVolumi, String... autori)
			throws BiblioEccezione {
		if (libri.containsKey(titolo)) throw new BiblioEccezione("Libro gia' presente.");
		Libro l = new Libro(titolo, nVolumi, autori);
		libri.put(titolo, l);
		int i;
		for (i = 0; i < nVolumi; i++) {
			volumi.add(new Volume(l));
		}
	}

	public void addUtente(String nome, int maxPrestiti, int durata)
			throws BiblioEccezione {
		utenti.put(nome, new Utente(nome, maxPrestiti, durata));
	}

	public PrestitoI addPrestito(String utente, String titolo)
			throws BiblioEccezione {
		if (!libri.containsKey(titolo))
			throw new BiblioEccezione("Titolo non trovato" + titolo);
		if (!utenti.containsKey(utente))
			throw new BiblioEccezione("Utente non trovato " + utente);
		if (!utenti.get(utente).userCanBorrow())
			throw new BiblioEccezione("User can not borrow");
		if (!libri.get(titolo).canBeBorrowed())
			throw new BiblioEccezione("Book can not be borrowed");
		utenti.get(utente).userBorrowsBook();
		libri.get(titolo).borrowCopy();

		// Ricerca Volume
		Optional<Volume> vol = volumi.stream()
				.filter(v -> v.getLibro().getNome().equals(titolo))
				.filter(v -> v.isBorrowed() == false).collect(Collectors
						.minBy(Comparator.comparing(Volume::getNumber)));
		if (!vol.isPresent())
			throw new BiblioEccezione("No volumes to borrow");

		Prestito pi = new Prestito(vol.get(), utenti.get(utente));
		prestiti.add(pi);
		return pi;
	}

	public void restituzione(String utente, int indice) throws BiblioEccezione {
		List<Prestito> op = prestiti.stream()
				.filter(p -> p.getIndice() == indice)
				.filter(p -> p.getNomeUtente().equals(utente))
				.collect(Collectors.toList());

		if (op.size() != 1)
			throw new BiblioEccezione("Prestito non trovato");
		op.get(0).restituisci();
	}

	public List<PrestitoI> prestitiScaduti() {
		List<PrestitoI> scaduti = 
		prestiti
		.stream()
		.filter(p -> p.getDataRestituzione().isBefore(LocalDate.now()))
		.collect(Collectors.toList());
		return scaduti;
	}

	public void addGiorni(int giorni) {
		dataBiblioteca.plusDays(giorni);
	};

	public LocalDate getOggi() {
		return dataBiblioteca;
	}

	public SortedMap<String, Long> nPrestitiXTitolo() {
		return 
				prestiti
				.stream()
				.collect(Collectors.groupingBy(p -> p.getTitolo(), TreeMap::new, Collectors.counting()));
	}

	public SortedMap<Long, Set<String>> titoliXnPrestiti() {
		return 
				prestiti
				.stream()
				.collect(Collectors.groupingBy(p -> p.getTitolo(), TreeMap::new, Collectors.counting()))
				.entrySet()
				.stream()
				.collect(Collectors.groupingBy(
						e -> e.getValue(), 
						TreeMap::new, 
						Collectors.mapping(e -> e.getKey(), Collectors.toCollection(TreeSet::new)))
				);
	}

	public List<InfoI> utentiNPrestiti() {
		return 
				prestiti.stream()
				.collect(Collectors.groupingBy(p -> p.getNomeUtente(), Collectors.counting()))
				.entrySet()
				.stream()
				.map(e -> {return new Info(e.getKey(), e.getValue());})
				.collect(Collectors.toList());
	}

	public List<InfoI> autoriNPrestiti() {
		return 
				prestiti.stream()
				.map(p -> libri.get(p.getTitolo()).getAutori())
				.<String>flatMap(autori -> Stream.of(autori))
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet()
				.stream()
				.map(e -> {return new Info(e.getKey(), e.getValue());})
				.collect(Collectors.toList());
	}
}
