package br.com.osoficina.restcontroller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.osoficina.model.Marca;
import br.com.osoficina.repository.MarcaRepository;

@RestController
public class RestMarcaController {
	
	@Autowired
	private MarcaRepository mr;
	
	@GetMapping("/api/marcas")
	public List<Object> marcas(){
		ArrayList<Object> marcas = (ArrayList<Object>) this.mr.findAllMarcas();
		//for (Marca marca : this.mr.findAllMarcas()) {
			System.out.println(this.mr.findAllMarcas());
		//}
		return marcas;
	}

}
