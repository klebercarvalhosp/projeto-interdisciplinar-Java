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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author clebe
 */
public class SeusPedidos implements Initializable {

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
    private TextField valorTotalPedidos;

    float valorTotal;

    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();

    @Override // nesta parte inicializa automaticamente todos os pedidos feito na tela para usuario 
    public void initialize(URL url, ResourceBundle rb) {
        Conexao conn = new Conexao();
        try {

            String teste = "call ver_comanda(" + Conexao.idVenda + ")";
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
            String teste2 = "select valor_total from vendas where idVenda = " + Conexao.idVenda + "";
            Statement sp = (Statement) conn.abrirConexao().createStatement();

            sp.executeQuery(teste2);
            ResultSet rt = sp.getResultSet();
            while (rt.next()) {

                valorTotalPedidos.setText("R$ " + (rt.getFloat("valor_total")));
                valorTotal = rt.getFloat("valor_total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SeusPedidos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML // aqui caso usuario queira finalizar pedido e fazer pagamento 
    public void fazerPagamentos() throws IOException {

        if (valorTotal == 0) {

            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("Aviso Importante");
            dialogoInfo.setHeaderText("Não existe Pedidos pedentes ");
            dialogoInfo.setContentText("Por favor Realize um pedido ");
            dialogoInfo.showAndWait();

        } else {
            App.setRoot("FormaPagamento");

        }

    }

    @FXML // aqui volta para menu novamente 
    public void voltaMenu() throws IOException {

        App.setRoot("Cardapio");

    }

}
