package biblioteca;

public class Utente {
    private String name;
    private int nMaxPrest;
    private int nInPrestito;
    private int durata;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getnMaxPrest() {
        return nMaxPrest;
    }

    public void setnMaxPrest(int nMaxPrest) {
        this.nMaxPrest = nMaxPrest;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public Utente(String name, int nMaxPrest, int durata) {
	super();
	this.name = name;
	this.nMaxPrest = nMaxPrest;
	this.durata = durata;
    }
    
    public void userReturnsBook() {
    	if (nInPrestito != 0) nInPrestito--;
    }

    public void userBorrowsBook() {
    	if (nInPrestito < nMaxPrest) nInPrestito++;
    }
    
    public boolean userCanBorrow() {
    	return nInPrestito < nMaxPrest;
    }
    
    public boolean userHasBooks() {
    	return nInPrestito > 0;
    }
    
    

}
