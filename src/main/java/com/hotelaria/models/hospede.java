package com.hotelaria.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

@Entity
public class hospede {
	@Id
	@SequenceGenerator(name = "seq_id_hospede", sequenceName = "seq_hospede")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_hospede")
	private int codigo;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String documento;
	
	@NotBlank
	private String telefone;
	
	private float vlr_total_gasto;
	
	public float getVlr_total_gasto() {
		return vlr_total_gasto;
	}

	public void setVlr_total_gasto(float vlr_total_gasto) {
		this.vlr_total_gasto = vlr_total_gasto;
	}

	public float getVlr_ultima_diaria() {
		return vlr_ultima_diaria;
	}

	public void setVlr_ultima_diaria(float vlr_ultima_diaria) {
		this.vlr_ultima_diaria = vlr_ultima_diaria;
	}

	private float vlr_ultima_diaria;
	
	
	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
