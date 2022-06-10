package com.amb.springbootjunit.repositories;

import com.amb.springbootjunit.models.Conta;

import java.util.List;

public interface ContaRepository {
    List<Conta> findAll();

    Conta findeById(Long id);

    void update(Conta conta);
}
