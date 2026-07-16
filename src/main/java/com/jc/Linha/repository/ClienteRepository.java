package com.jc.Linha.repository;

import com.jc.Linha.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
}

    //Spring cria automaticamente save(), findAll(),findById(),delete()