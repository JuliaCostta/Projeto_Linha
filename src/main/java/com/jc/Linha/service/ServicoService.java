package com.jc.Linha.service;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import com.jc.Linha.model.enums.StatusPagamento;
import com.jc.Linha.model.ServicoCostura;
import com.jc.Linha.model.enums.StatusServico;
import com.jc.Linha.repository.ServicoRepository;

@Service
public class ServicoService {

    private final ServicoRepository repository;

    public ServicoService(ServicoRepository repository) {
        this.repository = repository;
    }

    public ServicoCostura salvar(ServicoCostura servico) {

        servico.setCriadoEm(LocalDateTime.now());

        if (servico.getStatus() == null) {
            servico.setStatus(StatusServico.RECEBIDO);
        }

        // REGRA DE PAGAMENTO
        BigDecimal valorPago = servico.getValorPago();
        BigDecimal valorTotal = servico.getValorTotal();

        if (valorPago == null || valorPago.compareTo(BigDecimal.ZERO) == 0) {

            servico.setStatusPagamento(StatusPagamento.PENDENTE);

        } else if (valorPago.compareTo(valorTotal) < 0) {

            servico.setStatusPagamento(StatusPagamento.PARCIAL);

        } else {

            servico.setStatusPagamento(StatusPagamento.PAGO);
        }

        return repository.save(servico);
    }

    public List<ServicoCostura> listar() {
        return repository.findAll();
    }

    public ServicoCostura atualizar(Long id, ServicoCostura novo) {
        return repository.findById(id)
                .map(servico -> {

                    servico.setDescricao(novo.getDescricao());
                    servico.setPeca(novo.getPeca());
                    servico.setDataEntrega(novo.getDataEntrega());
                    servico.setStatus(novo.getStatus());
                    servico.setValorTotal(novo.getValorTotal());

                    return repository.save(servico);
                })
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Serviço não encontrado");
        }
        repository.deleteById(id);
    }

    public List<ServicoCostura> buscarPorCliente(Long clienteId) {
        return repository.findByClienteId(clienteId);
    }

    public List<ServicoCostura> buscarServicosAtrasados(){
        return repository.buscarServicosAtrasados(
                LocalDate.now(),
                StatusServico.ENTREGUE  // Não trazer serviços que já foram entregues na consulta.
        );
    }
}
