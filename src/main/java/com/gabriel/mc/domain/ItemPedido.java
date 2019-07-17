package com.gabriel.mc.domain;

public class ItemPedido {

    private ItemPedidoPK id = new ItemPedidoPK();

    private Double desconto;
    private Double preco;
    private Integer quantidade;

    public ItemPedido(Pedido pedido, Produto produto, Double desconto, Double preco, Integer quantidade) {
        this.id.setPedido(pedido);
        this.id.setProduto(produto);
        this.desconto = desconto;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public ItemPedido() {
    }

    public Pedido getPedido() {
        return id.getPedido();
    }

    public Produto getProduto() {
        return id.getProduto();
    }


    public ItemPedidoPK getId() {
        return id;
    }

    public void setId(ItemPedidoPK id) {
        this.id = id;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
