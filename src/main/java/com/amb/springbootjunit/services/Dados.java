package com.amb.springbootjunit.services;

import com.amb.springbootjunit.models.Banco;
import com.amb.springbootjunit.models.Conta;

import java.math.BigDecimal;

public class Dados {
    public static final Conta CONTA_001 = new Conta(1L,"Andre",new BigDecimal(1000));
    public static final Conta CONTA_002 = new Conta(2L,"Jhon",new BigDecimal(2000));
    public static final Banco BANCO = new Banco(1L,"Banco financeira",0);
}
