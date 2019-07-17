package com.gabriel.mc.domain;

import com.gabriel.mc.domain.enums.EstadoPagamento;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PagamentoComBoleto extends Pagamento {
    private static final long serialVersionUID = 1L;

    private Date dataVencimento;
    private Date dataPagamento;

    public PagamentoComBoleto() {
    }

    public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataPagamento, Date dataVencimento) {
        super(id, estado, pedido);
        this.dataPagamento = dataPagamento;
        this.dataVencimento = dataVencimento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
