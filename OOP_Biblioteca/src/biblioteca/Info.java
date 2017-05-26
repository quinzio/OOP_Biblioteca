package biblioteca;

public class Info implements InfoI {
	private String nome;
	private long valore;

	public Info(String nome, long valore) {
		this.nome = nome;
		this.valore = valore;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public long getValore() {
		return valore;
	}

}
