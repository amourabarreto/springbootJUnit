package com.amb.springbootjunit.repositories;

import com.amb.springbootjunit.models.Banco;

import java.util.List;

public interface BancoRepository {
    List<Banco> findAll();

    Banco findeById(Long id);

    void update(Banco banco);
}
