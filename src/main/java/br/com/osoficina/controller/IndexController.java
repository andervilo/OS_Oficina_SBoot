package br.com.osoficina.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.osoficina.model.Cliente;
import br.com.osoficina.model.Marca;
import br.com.osoficina.model.Veiculo;
import br.com.osoficina.repository.ClienteRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
public class IndexController {
	@Autowired
	private ClienteRepository cr;
	
	@Autowired
	private DataSource dataSource;;
	
	@GetMapping("/")
	public String index() {
		
		Cliente c = new Cliente();
		c.setNome("Anderson Oliveira");
		c.setCelular("91 982366407");
		c.setRg("4079552");
		c.setCpf("77658230278");
		c.setEmail("andervilo@gmail.com");
		
		Veiculo v = new Veiculo();
		v.setChassi("ABS12321354");
		v.setCliente(c);
		v.setModelo("GOL");
		v.setPlaca("TBJ-2345");
		
		Marca m = new Marca();
		m.setDescMarca("Volkswagen");
		
		v.setMarca(m);
		
		List<Veiculo> lv = c.getVeiculo();
		lv.add(v);
		c.setVeiculo(lv);
		
		cr.save(c);
		
		
		return "layout";
	}
	
	@GetMapping("/convidados")
	public String convidados() {
		return "Lista";
	}
	
	@GetMapping("/frag1")
	public String frag1(Model model) {
		model.addAttribute("content", "hello");
        return "fragments/frag1 :: content";
	}
	@GetMapping("/frag2")
	public String frag2(Model model) {
		model.addAttribute("content", "world");
        return "fragments/frag2 :: content3";
	}
	
	@GetMapping("/relatorio")
	public void relatorio(HttpServletResponse response) throws JRException, SQLException, IOException {	
		Map<String, Object> parametros = new HashMap();
		Resource esource = new ClassPathResource("/reports/teste.jasper");
		InputStream jasperStream = esource.getInputStream();  
		JasperReport jasperRepor = (JasperReport) JRLoader.loadObject(jasperStream);
		JasperPrint jasperPrint =  JasperFillManager.fillReport(jasperRepor, parametros, dataSource.getConnection());
		
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename=Marcas.pdf");
		
		final ServletOutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		
	}

}
