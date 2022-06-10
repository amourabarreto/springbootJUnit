package com.amb.springbootjunit.models;

import com.amb.springbootjunit.exceptions.DinheiroInsuficienteException;

import java.math.BigDecimal;
import java.util.Objects;

public class Conta {
    private Long id;
    private String pessoa;
    private BigDecimal saldo;

    public Conta() {
    }

    public Conta(Long id, String pessoa, BigDecimal saldo) {
        this.id = id;
        this.pessoa = pessoa;
        this.saldo = saldo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPessoa(String pessoa) {
        this.pessoa = pessoa;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
    }

    public String getPessoa() {
        return pessoa;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }
    public void debito(BigDecimal valor) {
        BigDecimal novoSaldo = this.saldo.subtract(valor);
        if(novoSaldo.compareTo(BigDecimal.ZERO) < 0){
            throw new DinheiroInsuficienteException("Saldo insuficiente");
        }
        this.saldo =  novoSaldo;
    }
    public void credito(BigDecimal valor){
        this.saldo = this.saldo.add(valor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return Objects.equals(getId(), conta.getId()) && Objects.equals(getPessoa(), conta.getPessoa()) && Objects.equals(getSaldo(), conta.getSaldo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPessoa(), getSaldo());
    }
}
