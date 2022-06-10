package com.amb.springbootjunit;

import com.amb.springbootjunit.exceptions.DinheiroInsuficienteException;
import com.amb.springbootjunit.models.Conta;
import com.amb.springbootjunit.repositories.BancoRepository;
import com.amb.springbootjunit.repositories.ContaRepository;
import com.amb.springbootjunit.services.ContaService;
import com.amb.springbootjunit.services.Dados;
import com.amb.springbootjunit.services.impls.ContaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

@SpringBootTest
class SpringbootJUnitApplicationTests {
    @MockBean
    ContaRepository contaRepository;
    @MockBean
    BancoRepository bancoRepository;

    @Autowired
    ContaService service;


    @BeforeEach
    void setUp() {
        /*contaRepository = mock(ContaRepository.class);
        bancoRepository = mock(BancoRepository.class);
        service = new ContaServiceImpl(contaRepository,bancoRepository);*/
    }

   // @Test
    void contextLoads() {
        when(contaRepository.findeById(1L)).thenReturn(Dados.CONTA_001);
        when(contaRepository.findeById(2L)).thenReturn(Dados.CONTA_002);
        when(bancoRepository.findeById(1L)).thenReturn(Dados.BANCO);

        BigDecimal saldoOrigm = service.revisarSaldo(1L);
        BigDecimal saldoDestino = service.revisarSaldo(2L);
        assertEquals("1000",saldoOrigm.toPlainString());
        assertEquals("2000",saldoDestino.toPlainString());


        service.transferir(1L,2L,new BigDecimal(100),1L);

         saldoOrigm = service.revisarSaldo(1L);
         saldoDestino = service.revisarSaldo(2L);

         assertEquals("900",saldoOrigm.toPlainString());
         assertEquals("2100",saldoDestino.toPlainString());

        //verify(contaRepository,times(3)).findeById(1L);
        //verify(contaRepository,times(3)).findeById(2L);
        //verify(contaRepository,times(2)).update(any(Conta.class));
    }

    @Test
    void testThrows() {
        when(contaRepository.findeById(1L)).thenReturn(Dados.CONTA_001);
        when(contaRepository.findeById(2L)).thenReturn(Dados.CONTA_002);
        when(bancoRepository.findeById(1L)).thenReturn(Dados.BANCO);

        BigDecimal saldoOrigm = service.revisarSaldo(1L);
        BigDecimal saldoDestino = service.revisarSaldo(2L);
        assertEquals("1000",saldoOrigm.toPlainString());
        assertEquals("2000",saldoDestino.toPlainString());

        assertThrows(DinheiroInsuficienteException.class, ()->
                service.transferir(1L,2L,new BigDecimal(1200),1L)
        );
        saldoOrigm = service.revisarSaldo(1L);
        saldoDestino = service.revisarSaldo(2L);

        assertEquals("1000",saldoOrigm.toPlainString());
        assertEquals("2000",saldoDestino.toPlainString());

        //verify(contaRepository,times(3)).findeById(1L);
        //verify(contaRepository,times(3)).findeById(2L);
        //verify(contaRepository,times(2)).update(any(Conta.class));
    }

    @DisplayName("Teste igual Same")
    @Test
    void testSame() {
        when(contaRepository.findeById(1L)).thenReturn(Dados.CONTA_001);

        Conta conta1 = service.findById(1L);
        Conta conta2 = service.findById(1L);
        assertSame(conta1,conta2);
        assertTrue(conta1 == conta2);
        assertEquals("Andre",conta1.getPessoa());
        assertEquals("Andre",conta2.getPessoa());
        verify(contaRepository,times(2)).findeById(1L);

    }

}
