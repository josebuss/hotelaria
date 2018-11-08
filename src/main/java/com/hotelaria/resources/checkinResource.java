package com.hotelaria.resources;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.hotelaria.models.checkin;
import com.hotelaria.repository.checkinRepository;

@RestController
@RequestMapping("/checkin")
public class checkinResource {

	@Autowired
	private checkinRepository cr;

	@GetMapping()
	public @ResponseBody Iterable<checkin> listaCheckIn() {
		Iterable<checkin> lista = cr.findAll();
		return lista;
	}

	@PostMapping()
	public checkin cadastroCheckIn(@RequestBody @Valid checkin ckin) {
		float vlr = 0;
		Calendar cal = Calendar.getInstance();
		/*
		 * Uma diária no hotel de segunda à sexta custa R$120,00; Uma diária no hotel em
		 * finais de semana custa R$150,00; Caso a pessoa precise de uma vaga na garagem
		 * do hotel há um acréscimo diário, sendo R$15,00 de segunda à sexta e R$20,00
		 * nos finais de semana; 
		 * Caso o horário da saída seja após às 16:30h deve ser cobrada uma diária extra;
		 */
		Date dia = Date.from(ckin.getData_entrada());
		do {
			cal.setTime(dia);
			vlr += CalculaValorDiaria(cal.get(cal.DAY_OF_WEEK), ckin.isAdicional_veiculo());
			cal.add(Calendar.DATE, 1);
			dia = cal.getTime();
		} while (!dia.after(Date.from(ckin.getData_saida())));
		
		cal.setTime(Date.from(ckin.getData_saida()));
		if ((cal.get(cal.HOUR_OF_DAY) > 16) || ((cal.get(cal.HOUR_OF_DAY) == 16) && (cal.get(cal.MINUTE) > 30))) {
			vlr += CalculaValorDiaria(cal.DAY_OF_WEEK, false);
		}

		ckin.setVlr_total(vlr);
		//return cr.save(ckin);
		return ckin;
	}

	private float CalculaValorDiaria(int DiaSemana, Boolean utilizaGaragem) {
		float vlr = 0;
		switch (DiaSemana) {
		case 1:
		case 7:
			vlr += 150;
			if (utilizaGaragem) {
				vlr += 20;
			}
			break;
		default:
			vlr += 120;
			if (utilizaGaragem) {
				vlr += 15;
			}
			break;
		}
		return vlr;
	}

}
