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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_hospede")
	@SequenceGenerator(name = "seq_id_hospede", sequenceName = "seq_hospede")
	private int codigo;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String documento;
	
	@NotBlank
	private String telefone;
	

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
