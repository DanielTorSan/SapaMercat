package model;

public class Textil extends Producte {
	private String composicio;
	private String codificacio;

	public Textil(float preu, String nom, String codi, String compo) {
		super(preu, nom, codi);
		composicio = compo;
		codificacio = preu + codi;
	}

	public String getComposicio() {
		return composicio;
	}

	@Override
	public String toString() {
		return getNom()  + "" + getCode() + "" + getComposicio() + " " + getPreu();
	}

	public String getCode (){
		return codificacio;
	}

}
