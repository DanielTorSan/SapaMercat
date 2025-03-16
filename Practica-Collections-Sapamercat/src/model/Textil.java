package model;

public class Textil extends Producte {
	private String composicio;

	public Textil(float preu, String nom, String codi, String composicio) {
		super(preu, nom, codi);
		this.composicio = composicio;
	}

	public String getComposicio() {
		return composicio;
	}

	@Override
	public String toString() {
		return getNom() + " " + getCodibarres() + " " + getComposicio() + " " + getPreu();
	}
}