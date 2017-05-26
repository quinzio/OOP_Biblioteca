package biblioteca;

public class Volume {
	static int prog = 1;
	private int number;
	private Libro libro;
	private boolean disponibile = true;
	private boolean borrowed;

	public Volume(Libro libro) {
		this.number = prog++;
		this.libro = libro;
	}

	public Libro getLibro() {
		return libro;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean preleva() {
		boolean ret = disponibile;
		disponibile = false;
		return ret;
	}

	public boolean restituisci() {
		boolean ret = disponibile;
		disponibile = true;
		return ret;
	}
	
	public boolean isBorrowed() {
		return borrowed;
	}

	public void borrowVolume() {
		borrowed = true;
	}
	
	public void returnVolume() {
		borrowed = false;
	}
	
	
	
	
}
