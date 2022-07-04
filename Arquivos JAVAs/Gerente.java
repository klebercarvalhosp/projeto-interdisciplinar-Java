/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comandavirtual;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author clebe
 */
public class Gerente {

    @FXML
    private TableView<ModelTable> tabelaCliente;
    @FXML
    private TableColumn<ModelTable, String> tbNome;
    @FXML
    private TableColumn<ModelTable, String> tbPreco;
    @FXML
    private TableColumn<ModelTable, String> tbQuantidade;
    @FXML
    private TableColumn<ModelTable, String> tbValorTotal;
    @FXML
    private TableColumn<ModelTable, String> tbNumeroV;
    @FXML
    private Button btnConsulta;
    @FXML
    private DatePicker date_dataescolhida;
    @FXML
    private TextField tbValorTotalVenda;

    String date;

    float valorTotal;

    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
    
    
    //nesta metodo ele vizuliza a receita de qualquer dia, e mostra na tabela 
    public void verReceitaDiaria() {

        date = String.valueOf(date_dataescolhida.getValue());

        System.out.println("date " + date);
        Conexao conn = new Conexao();
        try {

            String teste = "call ver_receita_dia('" + date + "')";
            Statement st = (Statement) conn.abrirConexao().createStatement();

            st.execute(teste);

            ResultSet rs = st.getResultSet();

            while (rs.next()) {
                oblist.add(new ModelTable(rs.getString("nomeItem"), rs.getString("preço"), rs.getString("quantidade"), rs.getString("valor_total"), rs.getString("idVenda")));

            }

        } catch (SQLException ex) {
            System.out.println("Deu errado");

        }

        tbNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tbPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tbQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        tbValorTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        tbNumeroV.setCellValueFactory(new PropertyValueFactory<>("numeroVenda"));

        tabelaCliente.setItems(oblist);

        try {
            String teste2 = "select sum(valor_Total) from vendas where Data_venda =('" + date + "')";
            Statement sp = (Statement) conn.abrirConexao().createStatement();

            sp.executeQuery(teste2);
            ResultSet rt = sp.getResultSet();
            while (rt.next()) {

                tbValorTotalVenda.setText("R$ " + (rt.getFloat("sum(valor_Total)")));
                //valorTotal = Integer.parseInt(rt.getString("valor_total"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SeusPedidos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML // nesse campo ele limpa a tabela para mostrar outro dia selecionado
    public void chamaReceitaDiaria() {

        for (int i = 0; i < tabelaCliente.getItems().size(); i++) {
            tabelaCliente.getItems().clear();
        }

        verReceitaDiaria();

    }

    @FXML // vai para tela de estoque 
    public void ajustaEstoque() throws IOException {

        App.setRoot("AjustaEstoque");
    }

    @FXML // volta para tela de login 
    public void voltaLogin() throws IOException {

        App.setRoot("Login");
    }

}
