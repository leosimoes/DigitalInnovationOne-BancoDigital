package org.bancodigital;

public class ContaDigital {

    private double saldoCorrente;
    private double saldoPoupanca;

    public ContaDigital(){
        this.saldoCorrente = 0.0;
        this.saldoPoupanca = 0.0;
    }

    public double getSaldoCorrente() {
        return saldoCorrente;
    }

    public double getSaldoPoupanca() {
        return saldoPoupanca;
    }

    public void depositarContaCorrente(double deposito){
        this.saldoCorrente += deposito;
    }

    public void depositarPoupanca(double deposito){
        this.saldoPoupanca += deposito;
    }

    public void sacar(double saque){
        if(this.saldoCorrente >= saque){
            this.saldoCorrente -= saque;
        }
    }

    public void guardarPoupanca(double valor){
        if(this.saldoCorrente >= valor){
            this.saldoCorrente -= valor;
            this.saldoPoupanca += valor;
        }
    }

    public void resgatarPoupanca(double valor){
        if(this.saldoPoupanca >= valor){
            this.saldoPoupanca -= valor;
            this.saldoCorrente += valor;
        }
    }

    public void transferirParaOutraConta(double valorTransferencia, ContaDigital contaDestino){
        if(this.saldoCorrente >= valorTransferencia){
            this.saldoCorrente -= valorTransferencia;
            contaDestino.depositarContaCorrente(valorTransferencia);
        }
    }
    private double calcularValorBoleto(String codigoBoleto){
        // Para este caso considere que o valor do boleto é obtido após "RS" no código
        // Por exemplo: para "adaggee897far46RS12050" o valor é 120.50
        String valorStr = codigoBoleto.split("RS")[1];
        return Double.parseDouble(valorStr)/100;
    }
    public void pagarBoleto(String codigoBoleto){
        double valorBoleto = calcularValorBoleto(codigoBoleto);
        if(this.saldoCorrente >= valorBoleto){
            this.saldoCorrente -= valorBoleto;
        }
    }

    private String formatarValorEmReal(double valor){
        return "R$ " + String.format("%.2f" , valor).replace(".", ",");
    }
    public String consultarSaldoContaCorrente(){
        return formatarValorEmReal(this.saldoCorrente);
    }

    public String consultarSaldoPoupanca(){
        return formatarValorEmReal(this.saldoPoupanca);
    }

}