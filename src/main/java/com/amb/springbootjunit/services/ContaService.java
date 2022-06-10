package com.amb.springbootjunit.services;

import com.amb.springbootjunit.models.Conta;

import java.math.BigDecimal;

public interface ContaService {
    Conta findById(Long id);
    int revisarTotalTransferencias(Long bancoId);

    BigDecimal revisarSaldo(Long contaId);

    void transferir(Long contaOrigem, Long contaDestino,BigDecimal valor, Long idBanco);
}