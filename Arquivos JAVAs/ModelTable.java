/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comandavirtual;

/**
 *
 * @author clebe
 */
public class ModelTable {

    private String Nome;
    private String preco;
    private String quantidade;
    private String valorTotal;
    private String numeroVenda;

    public ModelTable(String Nome, String preco, String quantidade, String valorTotal, String numeroVenda) {
        this.Nome = Nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.numeroVenda = numeroVenda;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getNumeroVenda() {
        return numeroVenda;
    }

    public void setNumeroVenda(String numeroVenda) {
        this.numeroVenda = numeroVenda;
    }

}

