package com.jc.Linha.controller;

import com.jc.Linha.model.ServicoCostura;
import org.springframework.web.bind.annotation.*;
import com.jc.Linha.service.ServicoService;

import java.util.List;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    private final ServicoService service;

    public ServicoController(ServicoService service) {
        this.service = service;
    }
    //endpoints:
    @PostMapping
    public ServicoCostura salvar(@RequestBody ServicoCostura servico) {
        return service.salvar(servico);
    }

    @GetMapping
    public List<ServicoCostura> listar() {
        return service.listar();
    }

    @PutMapping("/{id}")
    public ServicoCostura atualizar(@PathVariable Long id,
                                    @RequestBody ServicoCostura servico) {
        return service.atualizar(id, servico);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @GetMapping("/cliente/{id}")
    public List<ServicoCostura> buscarPorCliente(@PathVariable Long id) {
        return service.buscarPorCliente(id);
    }

    @GetMapping("/atrasados")
    public List<ServicoCostura> buscarServicosAtrasados(){
        return service.buscarServicosAtrasados();
    }

}