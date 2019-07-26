package com.gabriel.mc.domain.enums;

public enum Perfil {

    ADMIN(1,"ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE");

    private Integer cod;
    private String descricao;

    private Perfil(Integer codigo, String descricao) {
        this.cod = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (Perfil x: Perfil.values()) {
            if (cod.equals(x.getCodigo())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido " + cod);
    }
}
