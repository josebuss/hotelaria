package com.hotelaria.models;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class checkin {
	@Id
	@SequenceGenerator(name = "seq_id_checkin", sequenceName = "seq_checkin")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_checkin")
	private int codigo;
	
	private int cod_hospede;
	
	private Instant data_entrada;
	
	private Instant data_saida;
	
	private boolean adicional_veiculo;
	
	private float vlr_total;
	

	public float getVlr_total() {
		return vlr_total;
	}

	public void setVlr_total(float vlr_total) {
		this.vlr_total = vlr_total;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCod_hospede() {
		return cod_hospede;
	}

	public void setCod_hospede(int cod_hospede) {
		this.cod_hospede = cod_hospede;
	}

	public Instant getData_entrada() {
		return data_entrada;
	}

	public void setData_entrada(Instant data_entrada) {
		this.data_entrada = data_entrada;
	}

	public Instant getData_saida() {
		return data_saida;
	}

	public void setData_saida(Instant data_saida) {
		this.data_saida = data_saida;
	}

	public boolean isAdicional_veiculo() {
		return adicional_veiculo;
	}

	public void setAdicional_veiculo(boolean adicional_veiculo) {
		this.adicional_veiculo = adicional_veiculo;
	}
}
