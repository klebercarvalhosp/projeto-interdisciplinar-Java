/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comandavirtual;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author clebe
 */
public class Conexao {

    private String url = "jdbc:mysql://127.0.01/mydb?user=root&password=134754";

    static String senha;
    static String nivelAcesso;
    static int idVenda;
    static float valorTotalVenda;

    public static float getValorTotalVenda() {
        return valorTotalVenda;
    }

    public static void setValorTotalVenda(float valorTotalVenda) {
        Conexao.valorTotalVenda = valorTotalVenda;
    }

    public static int getIdVenda() {
        return idVenda;
    }

    public static void setIdVenda(int idVenda) {
        Conexao.idVenda = idVenda;
    }
    CallableStatement prepareCall;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public Connection abrirConexao() {
        Connection conexao = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexao = DriverManager.getConnection(url);
            System.out.println("Conexao com Banco de Dados establecida.");
        } catch (SQLException erroSQL) {
            System.out.println("Erro na conexão com o Banco " + erroSQL.getMessage());
        } catch (ClassNotFoundException erroClass) {
            System.out.println("Erro ao carregar o Driver " + erroClass.getMessage());
        } catch (Exception e) {
            System.out.println("Ocoreru um erro: " + e.getMessage());
        }
        return conexao;

    }
    // seleciona os dados do cpf cadastrado para validar na tela login 

    public static void select(Login login) throws SQLException {
        Conexao conn = new Conexao();
        Conexao cValida = new Conexao();

        String teste = "select * from cadastro where cpf = '" + login.getCpf() + "';";
        Statement st = (Statement) conn.abrirConexao().createStatement();

        st.executeQuery(teste);

        ResultSet rs = st.getResultSet();

        while (rs.next()) {
            cValida.setSenha(rs.getString("senha"));
            cValida.setNivelAcesso(rs.getString("nivelAcesso"));
        }
        System.out.println(cValida.getNivelAcesso());
        System.out.println(cValida.getSenha());

        st.close();
        rs.close();

    }

    // aqui insere uma venda
    public static void insert(Cadastro cadastro) throws SQLException {
        Conexao conn = new Conexao();
        Statement st = (Statement) conn.abrirConexao().createStatement();

        st.executeUpdate("insert into cadastro (cpf,nome,senha,telefone,nivelAcesso) values ('" + cadastro.getCpf() + "','"
                + cadastro.getNome() + "','" + cadastro.getSenha() + "','" + cadastro.getTelefone() + "','" + cadastro.getNivelAcesso() + "')");

        st.close();
    }
    //aqui insere um Pedido

    public static void insertPedido(int quant, int idProduto) throws SQLException {
        Conexao conn = new Conexao();
        Statement st = (Statement) conn.abrirConexao().createStatement();
        st.executeUpdate("insert into pedido (quantidade,idItem,idVenda) values (" + quant + "," + idProduto + "," + idVenda + ")");
        System.out.println("Entrou aqui ");

    }

    // aqui seleciona a Venda cadastrada no momento do primeiro pedido 
    public static void selectVenda() throws SQLException {

        Conexao conn = new Conexao();
        Conexao cValida = new Conexao();

        System.out.println("CPF GUARDADO " + Login.cpfInsert);
        String teste = "select idVenda from vendas where status_pedido = 'Não Pago';";

        Statement st = (Statement) conn.abrirConexao().createStatement();

        st.executeQuery(teste);

        ResultSet rs = st.getResultSet();

        while (rs.next()) {
            idVenda = (Integer.parseInt(rs.getString("idVenda")));

        }

        if (cValida.getIdVenda() > 0) {

            System.out.println("Tem venda criada = " + cValida.getIdVenda());

        } else {
            st.executeUpdate("insert into vendas  (cpf,valor_total,status_pedido,Data_venda,eco_10) values ('" + Login.cpfInsert + "',0,'Não Pago',now(),0)");
            System.out.println("Nao tem venda Criada");

            selectVenda();

        }

        st.close();
        rs.close();

    }

    //aqui finaliza a venda e faz o calculo dos 10 % para mostrar para usuario 
    public static void finalizaVenda() throws SQLException {
        Conexao conn = new Conexao();
        Statement st = (Statement) conn.abrirConexao().createStatement();

        st.execute("{call calcular_10(" + idVenda + ")}");
        st.close();

    }
    // metodo utilizado para verificar o estoque dos itens no cardapio 

    public static int confereEstoque(String nome) throws SQLException {

        Conexao conn = new Conexao();
        Statement st = (Statement) conn.abrirConexao().createStatement();
        int estoque = 0;
        st.executeQuery("select estoque from item where nomeItem = '" + nome + "';");

        ResultSet rs = st.getResultSet();

        while (rs.next()) {

            estoque = rs.getInt("estoque");
        }

        return estoque;
    }
    //metodo para inserir estoque 
    public static void insereEstoque(int estoque, String nome) throws SQLException {

        Conexao conn = new Conexao();

        Statement st = (Statement) conn.abrirConexao().createStatement();

        st.executeUpdate("update item set estoque = estoque + " + estoque + " where nomeItem  = '" + nome + "'");

    }
    // se caso seja pago no pix , ele da desconto no final da venda 
    public static float decontoPix() throws SQLException {

        Conexao conn = new Conexao();

        float desconto = 0;
        Statement st = (Statement) conn.abrirConexao().createStatement();

        st.executeQuery("select desconto(" + Conexao.idVenda + ")");

        ResultSet rs = st.getResultSet();

        while (rs.next()) {

            desconto = rs.getFloat("desconto(" + Conexao.idVenda + ")");

        }

        return desconto;

    }

}
