package org.bancodigital;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestContaDigital {

    private ContaDigital contaDigital;
    private ContaDigital contaDigitalAux;

    private double VALOR_MINIMO = 0.0;

    @BeforeEach
    public void setUp() {
        contaDigital = new ContaDigital();
        contaDigitalAux = new ContaDigital();
    }

    @Test
    public void testarDepositoContaCorrente(){
        double deposito = 10.0;

        contaDigital.depositarContaCorrente(deposito);

        assertEquals(deposito, contaDigital.getSaldoCorrente());
        assertEquals(VALOR_MINIMO, contaDigital.getSaldoPoupanca());
    }

    @Test
    public void testarDepositoPoupanca(){
        double deposito = 10.0;

        contaDigital.depositarPoupanca(deposito);

        assertEquals(deposito, contaDigital.getSaldoPoupanca());
        assertEquals(VALOR_MINIMO, contaDigital.getSaldoCorrente());
    }

    @Test
    public void testarSaqueSucesso(){
        double deposito = 10.0;
        double saque = 7.5;

        contaDigital.depositarContaCorrente(deposito);
        contaDigital.sacar(saque);

        assertEquals(VALOR_MINIMO, contaDigital.getSaldoPoupanca());
        assertEquals(deposito-saque, contaDigital.getSaldoCorrente());
    }

    @Test
    public void testarSaqueFalha(){
        double deposito = 10.0;
        double saque = 11.0;

        contaDigital.depositarContaCorrente(deposito);
        contaDigital.sacar(saque);

        assertEquals(VALOR_MINIMO, contaDigital.getSaldoPoupanca());
        assertEquals(deposito, contaDigital.getSaldoCorrente());
    }

    @Test
    public void testarGuardarPoupancaSucesso(){
        double deposito = 10.0;
        double valor = 7.5;

        contaDigital.depositarContaCorrente(deposito);
        contaDigital.guardarPoupanca(valor);

        assertEquals(valor, contaDigital.getSaldoPoupanca());
        assertEquals(deposito-valor, contaDigital.getSaldoCorrente());
    }

    @Test
    public void testarGuardarPoupancaFalha(){
        double deposito = 10.0;
        double valor = 11.0;

        contaDigital.depositarContaCorrente(deposito);
        contaDigital.guardarPoupanca(valor);

        assertEquals(VALOR_MINIMO, contaDigital.getSaldoPoupanca());
        assertEquals(deposito, contaDigital.getSaldoCorrente());
    }

    @Test
    public void testarResgatarPoupancaSucesso(){
        double deposito = 10.0;
        double valor = 7.5;

        contaDigital.depositarPoupanca(deposito);
        contaDigital.resgatarPoupanca(valor);

        assertEquals(deposito-valor, contaDigital.getSaldoPoupanca());
        assertEquals(valor, contaDigital.getSaldoCorrente());
    }

    @Test
    public void testarResgatarPoupancaFalha(){
        double deposito = 10.0;
        double valor = 11.0;

        contaDigital.depositarPoupanca(deposito);
        contaDigital.guardarPoupanca(valor);

        assertEquals(deposito, contaDigital.getSaldoPoupanca());
        assertEquals(VALOR_MINIMO, contaDigital.getSaldoCorrente());
    }

    @Test
    public void testarTransferirSucesso(){
        double deposito = 10.0;
        double valorTransferencia = 7.5;

        contaDigital.depositarContaCorrente(deposito);
        contaDigital.transferirParaOutraConta(valorTransferencia, contaDigitalAux);

        assertEquals(deposito-valorTransferencia, contaDigital.getSaldoCorrente());
        assertEquals(valorTransferencia, contaDigitalAux.getSaldoCorrente());
    }

    @Test
    public void testarTransferirFalha(){
        double deposito = 10.0;
        double valorTransferencia = 11.0;

        contaDigital.depositarContaCorrente(deposito);
        contaDigital.transferirParaOutraConta(valorTransferencia, contaDigitalAux);

        assertEquals(deposito, contaDigital.getSaldoCorrente());
        assertEquals(VALOR_MINIMO, contaDigitalAux.getSaldoCorrente());
    }

    @Test
    public void testarPagarBoletoSucesso(){
        double deposito = 1000.0;
        String codigoBoleto = "adaggee897far46RS12050";

        contaDigital.depositarContaCorrente(deposito);
        contaDigital.pagarBoleto(codigoBoleto);

        assertEquals(deposito-120.50, contaDigital.getSaldoCorrente());
        assertEquals(VALOR_MINIMO, contaDigitalAux.getSaldoCorrente());
    }

    @Test
    public void testarPagarBoletoFalha(){
        double deposito = 1000.0;
        String codigoBoleto = "adaggee897far46RS120050";

        contaDigital.depositarContaCorrente(deposito);
        contaDigital.pagarBoleto(codigoBoleto);

        assertEquals(deposito, contaDigital.getSaldoCorrente());
        assertEquals(VALOR_MINIMO, contaDigitalAux.getSaldoCorrente());
    }

    @Test
    public void testarConsultarSaldoContaCorrente(){
        double deposito = 10.0;
        String depositoStr = "R$ 10,00";

        contaDigital.depositarContaCorrente(deposito);

        assertEquals(depositoStr, contaDigital.consultarSaldoContaCorrente());
    }

    @Test
    public void testarConsultarSaldoContaPoupanca(){
        double deposito = 10.0;
        String depositoStr = "R$ 10,00";

        contaDigital.depositarPoupanca(deposito);

        assertEquals(depositoStr, contaDigital.consultarSaldoPoupanca());
    }

}
