package com.jc.Linha.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 20)
    private String telefone;

    public Cliente() {
    }

    // getters e setters

    public enum StatusServico {
        EM_ANDAMENTO,
        PRONTO,
        ENTREGUE
    }
}
