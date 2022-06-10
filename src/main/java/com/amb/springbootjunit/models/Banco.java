package com.amb.springbootjunit.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Banco {
    private Long id;
    private String nome;
    private Integer totalTransferencia;

    public Banco() {
    }

    public Banco(Long id, String nome, Integer totalTransferencia) {
        this.id = id;
        this.nome = nome;
        this.totalTransferencia = totalTransferencia;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getTotalTransferencia() {
        return totalTransferencia;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTotalTransferencia(Integer totalTransferencia) {
        this.totalTransferencia = totalTransferencia;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Banco banco = (Banco) o;
        return Objects.equals(getId(), banco.getId()) && Objects.equals(getNome(), banco.getNome()) && Objects.equals(getTotalTransferencia(), banco.getTotalTransferencia());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getTotalTransferencia());
    }
}
