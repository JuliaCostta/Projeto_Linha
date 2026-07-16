package com.jc.Linha.controller;
import com.jc.Linha.model.Cliente;
import com.jc.Linha.service.ClienteService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {

        this.service = service;
    }

    @PostMapping
    public Cliente salvar(@RequestBody Cliente cliente) {

        return service.salvar(cliente);
    }

    @GetMapping
    public List<Cliente> listar() {

        return service.findAll();
    }
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable("id") Long id) {

        service.deleteById(id);
    }

    @PutMapping("/{id}")
    public Cliente atualizar(@PathVariable("id") Long id,
                             @RequestBody Cliente clienteAtualizado) {

        return service.atualizar(id, clienteAtualizado);
    }

}



