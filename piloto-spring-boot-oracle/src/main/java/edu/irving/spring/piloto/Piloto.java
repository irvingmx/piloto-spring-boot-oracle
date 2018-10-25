package edu.irving.spring.piloto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="IRG_PILOTO")
public class Piloto {

	private Long numero;
	private String nombre;
	private String equipo;
	private String moto;
	
	@Id
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEquipo() {
		return equipo;
	}
	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}
	public String getMoto() {
		return moto;
	}
	public void setMoto(String moto) {
		this.moto = moto;
	}
	
	
}
