package model;

public class Electronica extends Producte {
	private int diesGarantia;

	public Electronica(float preu, String nom, String codi, int diesGarantia) {
		super(preu, nom, codi);
		this.diesGarantia = diesGarantia;
	}

	@Override
	public float getPreu() {
		float preu = super.getPreu();
		return (float) (preu + preu * (diesGarantia / 365.0) * 0.1);
	}

	@Override
	public String toString() {
		return getNom() + "," + getPreu();
	}
}