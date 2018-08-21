package br.com.osoficina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.osoficina.model.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long>{
	
	@Query(value="SELECT m.id, m.descMarca FROM Marca m")
	public List<Object> findAllMarcas();

}
