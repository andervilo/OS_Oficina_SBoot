package br.com.osoficina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.osoficina.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
