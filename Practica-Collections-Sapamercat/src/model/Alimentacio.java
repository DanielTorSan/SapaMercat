package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Alimentacio extends Producte {
	private LocalDate dataCaducitat;

	public Alimentacio(float preu, String nom, String codi, LocalDate dataCaducitat) {
		super(preu, nom, codi);
		this.dataCaducitat = dataCaducitat;
	}

	@Override
	public float getPreu() {
		float preu = super.getPreu();
		long diesFinsCaducitat = ChronoUnit.DAYS.between(LocalDate.now(), dataCaducitat);
		return (float) (preu - preu * (1.0 / (diesFinsCaducitat + 1)) + (preu * 0.1));
	}

	@Override
	public String toString() {
		return getNom() + " " + getPreu();
	}
}