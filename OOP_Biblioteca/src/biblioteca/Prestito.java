package biblioteca;

import java.time.LocalDate;

public class Prestito implements PrestitoI {
	private Volume volume;
	private Utente utente;
	private LocalDate dataPrestito;
	private LocalDate dataRestituzione;

	public Prestito(Volume volume, Utente utente) {
		this.volume = volume;
		this.utente = utente;
		this.dataPrestito = LocalDate.now();
		this.dataRestituzione = null;
	}

	@Override
	public int getIndice() {
		return volume.getNumber();
	}

	@Override
	public String getTitolo() {
		return volume.getLibro().getNome();
	}

	@Override
	public LocalDate getScadenza() {
		LocalDate dataRestituzione = dataPrestito.plusDays(utente.getDurata());
		return dataRestituzione;
	}

	@Override
	public LocalDate getDataRestituzione() {
		return dataRestituzione;
	}

	@Override
	public String getNomeUtente() {
		return utente.getName();
	}
	
	public void restituisci() {
		dataRestituzione = LocalDate.now();
	}

}
