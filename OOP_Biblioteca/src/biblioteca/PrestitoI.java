package biblioteca;

import java.time.LocalDate;
import java.util.List;

public interface PrestitoI {
    int getIndice();

    String getTitolo();

    LocalDate getScadenza();

    LocalDate getDataRestituzione();

    String getNomeUtente();

    default String getInfo() {
	return String.format("prestito: utente %s titolo %s volume % d scadenza %s", getNomeUtente(), getTitolo(),
		getIndice(), getScadenza().toString());
    }

    static void stampaListaPrestiti(String info, List<PrestitoI> lista, LocalDate oggi) {
	System.out.println(info + " " + oggi + " n:" + lista.size());
	for (PrestitoI p : lista)
	    System.out.println(" " + p.getInfo());
    }
}
