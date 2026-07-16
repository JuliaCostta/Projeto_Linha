package com.jc.Linha.repository;
import com.jc.Linha.model.enums.StatusServico;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;   //liga os parâmetros do método aos parâmetros da consulta.
import java.time.LocalDate;
import com.jc.Linha.model.ServicoCostura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicoRepository extends JpaRepository<ServicoCostura, Long> {

    List<ServicoCostura>findByClienteId(Long clienteId);

    @Query("""
    SELECT s
    FROM ServicoCostura s
    WHERE s.dataEntrega < :hoje
      AND s.status <> :status
""")
    List<ServicoCostura> buscarServicosAtrasados(
            @Param("hoje") LocalDate hoje,
            @Param("status") StatusServico status);
}