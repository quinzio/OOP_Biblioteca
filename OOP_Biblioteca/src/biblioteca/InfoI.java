package biblioteca;

import java.util.List;

public interface InfoI {
    String getNome();

    long getValore();

    default String getInfo() {
	return getNome() + ": " + getValore() + " ";
    }

    static void stampaListaInfoI(String nomeLista, List<InfoI> lista) {
	System.out.println(nomeLista);
	for (InfoI i : lista)
	    System.out.print(i.getInfo());
	System.out.println();
    }
}
