package com.gabriel.mc.domain.enums;

public enum EstadoPagamento {

    PENDENTE(1,"Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    private Integer cod;
    private String descricao;

    private EstadoPagamento(Integer codigo, String descricao) {
        this.cod = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EstadoPagamento toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (EstadoPagamento x: EstadoPagamento.values()) {
            if (cod.equals(x.getCodigo())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido " + cod);
    }
}
