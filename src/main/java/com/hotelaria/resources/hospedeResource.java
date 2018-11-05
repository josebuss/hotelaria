package com.hotelaria.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotelaria.models.hospede;
import com.hotelaria.repository.hospedeRepository;

@RestController
@RequestMapping("/hospede")
public class hospedeResource {

	@Autowired
	private hospedeRepository hr;
	
	@GetMapping()
	public @ResponseBody Iterable<hospede> listaHospede() {
		Iterable<hospede> lista = hr.findAll();
		return lista;
	}
	
	@GetMapping("/nome/{nome}")
	public @ResponseBody Iterable<hospede> buscaPorNome(@PathVariable String nome) {
		Iterable<hospede> lista = hr.findByNomeContainingIgnoreCase(nome);
		return lista;
	}
	
	@GetMapping("/documento/{doc}")
	public @ResponseBody Iterable<hospede> buscaPorDocumento(@PathVariable String doc) {
		Iterable<hospede> lista = hr.findByDocumento(doc);
		return lista;
	}
	
	@GetMapping("/telefone/{tel}")
	public @ResponseBody Iterable<hospede> buscaPorTelefone(@PathVariable String tel) {
		Iterable<hospede> lista = hr.findByTelefone(tel);
		return lista;
	}
	
	@PostMapping()
	public hospede cadastroHospede(@RequestBody @Valid hospede hosp) {
		return hr.save(hosp);
	}
	
	@PutMapping
	public hospede atualizaHospede(@RequestBody @Valid hospede hosp) {
		return hr.save(hosp);
	}
	
	@DeleteMapping
	public hospede deleteHospede(@RequestBody hospede hosp) {
		hr.delete(hosp);
		return hosp;
	}
}
