package com.hotelaria.models;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;

@Entity
public class checkin {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_checkin")
	@SequenceGenerator(name = "seq_id_checkin", sequenceName = "seq_checkin", allocationSize=1)
	private int codigo;
	
	@NotNull
	private int cod_hospede;
	
	@NotNull
	private Instant data_entrada;
	
	@NotNull
	private Instant data_saida;
	
	@NotNull
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
