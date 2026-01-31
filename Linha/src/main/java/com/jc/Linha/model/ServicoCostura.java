package com.jc.Linha.model;

import com.jc.Linha.model.enums.StatusServico;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "servico_costura")
public class ServicoCostura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String peca;

    @Column(name = "data_entrega", nullable = false)
    private LocalDate dataEntrega;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusServico status;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    public ServicoCostura() {
    }

    // getters e setters
}
