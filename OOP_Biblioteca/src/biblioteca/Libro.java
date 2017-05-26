package biblioteca;

public class Libro {
	private String nome;
	private String[] autori;
	private int nCopie;
	private int borrowedCopies;

	public Libro(String nome, int nCopie, String... autori) {
		super();
		this.nome = nome;
		this.autori = autori;
		this.nCopie = nCopie;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String[] getAutori() {
		return autori;
	}

	public void setAutori(String... autori) {
		this.autori = autori;
	}

	public int getnCopie() {
		return nCopie;
	}

	public void setnCopie(int nCopie) {
		this.nCopie = nCopie;
	}
	
	public boolean canBeBorrowed() {
		return borrowedCopies < nCopie;
	}
	
	public void borrowCopy() {
		if (this.canBeBorrowed()) borrowedCopies++;
	}

	public void returnCopy() {
		borrowedCopies--;
		if (borrowedCopies < 0) borrowedCopies = 0;
	}
	
	
	
}
