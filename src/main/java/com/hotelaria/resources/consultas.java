package com.hotelaria.resources;

import java.beans.Statement;
import java.sql.Connection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jca.cci.connection.ConnectionFactoryUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotelaria.models.checkin;
import com.hotelaria.models.hospede;

@RestController
@RequestMapping("/consulta")
public class consultas {

	@Autowired
	private EntityManager em;
	
	@GetMapping("/NaoHospedado")
	public @ResponseBody List<hospede> listaHospedeNaoHospedado() {
		String consulta = "select b.codigo, b.nome,\r\n" + 
				"       (select sum(vlr_tolal) from checkin where cod_hospede = a.cod_hospede) vlr_total_gasto,\r\n" + 
				"	   (select vlr_tolal \r\n" + 
				"		  from checkin \r\n" + 
				"		 where cod_hospede = a.cod_hospede\r\n" + 
				"	       and data_entrada = (select max(data_entrada)\r\n" + 
				"							     from checkin\r\n" + 
				"							    where cod_hospede = a.cod_hospede)) vlr_ultima_diaria\r\n" + 
				"  from checkin a,\r\n" + 
				"       hospede b\r\n" + 
				" where a.cod_hospede = b.codigo \r\n" + 
				"   and a.data_saida < current_date --não está hospedado\r\n" + 
				"   --and current_date between a.data_entrada and a.data_saida--está hospedado\r\n" + 
				" group by a.cod_hospede, b.nome";
		TypedQuery<hospede> query = em.createQuery(consulta, hospede.class);
		List<hospede> resultado = query.getResultList();
		return resultado;
	}

}
