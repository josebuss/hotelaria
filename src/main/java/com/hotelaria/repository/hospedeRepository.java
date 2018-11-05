package com.hotelaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hotelaria.models.hospede;

public interface hospedeRepository extends JpaRepository<hospede, String> {
	
	Iterable<hospede> findByNomeContainingIgnoreCase(String nome);
	Iterable<hospede> findByDocumento(String documento);
	Iterable<hospede> findByTelefone(String telefone);

}
