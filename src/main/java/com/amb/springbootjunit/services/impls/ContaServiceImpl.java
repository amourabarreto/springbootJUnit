package com.amb.springbootjunit.services.impls;

import com.amb.springbootjunit.models.Banco;
import com.amb.springbootjunit.models.Conta;
import com.amb.springbootjunit.repositories.BancoRepository;
import com.amb.springbootjunit.repositories.ContaRepository;
import com.amb.springbootjunit.services.ContaService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ContaServiceImpl implements ContaService {
    private ContaRepository contaRepository;
    private BancoRepository bancoRepository;

    public ContaServiceImpl(ContaRepository contaRepository, BancoRepository bancoRepository) {
        this.contaRepository = contaRepository;
        this.bancoRepository = bancoRepository;
    }

    @Override
    public Conta findById(Long id) {
        return contaRepository.findeById(id);
    }

    @Override
    public int revisarTotalTransferencias(Long bancoId) {
        Banco banco = bancoRepository.findeById(bancoId);

        return banco.getTotalTransferencia();
    }

    @Override
    public BigDecimal revisarSaldo(Long contaId) {
        Conta conta = contaRepository.findeById(contaId);
        return conta.getSaldo();
    }

    @Override
    public void transferir(Long numContaOrigem, Long numContaDestino, BigDecimal valor, Long idBanco ) {
        Banco banco = bancoRepository.findeById(idBanco);
        int totalTransferencias = banco.getTotalTransferencia();
        banco.setTotalTransferencia(++totalTransferencias);
        bancoRepository.update(banco);

        Conta contaOrigem = contaRepository.findeById(numContaOrigem);
        contaOrigem.debito(valor);
        contaRepository.update(contaOrigem);

        Conta contaDestino = contaRepository.findeById(numContaDestino);
        contaDestino.credito(valor);
        contaRepository.update(contaDestino);

    }
}
