package com.hotelaria.resources;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/consulta")
public class consultas {
	@Autowired
	EntityManager em;

	//@GetMapping("/naohospedado/{data}")
	@GetMapping("/naohospedado")
	public @ResponseBody String listaHospedeNaoHospedado(/*@PathVariable String data*/) {
		return buscaHospede(/*data*/"", true);
	}

	//@GetMapping("/hospedado/{data}")
	@GetMapping("/hospedado")
	public @ResponseBody String listaHospedeHospedado(/*@PathVariable String data*/) {
		return buscaHospede(/*data*/"", false);
	}

	private String buscaHospede(String data, Boolean soNaoHospedados) {
		if (!data.trim().isEmpty())
			data = "\'" + data + "\'";
		else
			data = "current_date";
		
		String consulta = 
				  "select a.cod_hospede, b.nome nome_hospede,\r\n"
				+ "       (select sum(vlr_total) from checkin where cod_hospede = a.cod_hospede) vlr_total_gasto,\r\n"
				+ "	   (select vlr_total \r\n" 
				+ "		  from checkin \r\n"
				+ "		 where cod_hospede = a.cod_hospede\r\n"
				+ "	       and data_entrada = (select max(data_entrada)\r\n"
				+ "							     from checkin\r\n"
				+ "							    where cod_hospede = a.cod_hospede)) vlr_ultima_diaria\r\n"
				+ "  from checkin a,\r\n" 
				+ "       hospede b\r\n" 
				+ " where a.cod_hospede = b.codigo \r\n";
		
		String WhereHospedado = 
				  "   and '2018-11-04' between a.data_entrada and a.data_saida\r\n"; 
		
		String WhereNaoHospedado = 
				  "   and not exists (select 1 from checkin \r\n"
				+ "				   	where cod_hospede = a.cod_hospede \r\n" 
				+ "				      and " + data + " between data_entrada and data_saida)\r\n";
				
		String GroupBy = " group by a.cod_hospede, b.nome";
		
		ArrayList<String> sql = new ArrayList<String>();
		sql.add(consulta);
		if (soNaoHospedados)
			sql.add(WhereNaoHospedado);
		else				
			sql.add(WhereHospedado);
		sql.add(GroupBy);
		
		Query query = em.createNativeQuery(consulta);
		List<Object[]> itens = query.getResultList();
		JsonArray jArray = new JsonArray();
		
		for(Object[] item : itens) {
			JsonObject jo = new JsonObject();
			jo.addProperty("cod_hospede", 	    (int)    item[0]);
			jo.addProperty("nome_hospede",	    (String) item[2]);
			jo.addProperty("vlr_total_gasto",   (float)  item[3]);
			jo.addProperty("vlr_ultima_diaria", (float)  item[4]);
			
			jArray.add(jo);
		}
	return jArray.toString();
	}
}
