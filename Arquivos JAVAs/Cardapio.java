/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comandavirtual;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Cardapio implements Initializable {

    @FXML
    private AnchorPane IVcamarao;
    @FXML
    private Button menosBatata;
    @FXML
    private Button maisBatata;
    @FXML
    private TextField countBatata;
    @FXML
    private Button dimiXsalada;
    @FXML
    private Button addXsalada;
    @FXML
    private TextField countXsalada;
    @FXML
    private Button menosPorcaoCamarao;
    @FXML
    private Button addPorcaoCamarao;
    @FXML
    private TextField countPorcaoCamarao;
    @FXML
    private Button menosHambuSoja;
    @FXML
    private Button addHambuSoja;
    @FXML
    private TextField countHamburgueSoja;
    @FXML
    private Button menosOnion;
    @FXML
    private Button addOnion;
    @FXML
    private TextField countOnion;
    @FXML
    private Button menoRefri;
    @FXML
    private Button addRefri;
    @FXML
    private TextField countRefri;
    @FXML
    private CheckBox idConfirmacao;

    int maiscountBatata;
    int EstoqueBatata = 0;

    int EstoqueXsalada = 0;
    int quantXsalada = 0;

    int EstoquePorcaoCamarao = 0;
    int quantPorcaoCamarao = 0;

    int EstoqueHambuSoja = 0;
    int quantHambuSoja = 0;

    int EstoqueOnion = 0;
    int quantOnion = 0;

    int EstoqueRefri = 0;
    int quantRefri = 0;

    @Override //nesse caso ele inicializa automaticamente todos valores de estoque para caso for 0 ele disabilitaos botoes 
    public void initialize(URL url, ResourceBundle rb) {

        try {
            disparaIdvenda();
            EstoqueBatata = Conexao.confereEstoque("P_BatataFrita");
            EstoqueXsalada = Conexao.confereEstoque("X_Salada");
            EstoquePorcaoCamarao = Conexao.confereEstoque("Porcao_Camarao");
            EstoqueHambuSoja = Conexao.confereEstoque("Hamburguer_Soja");
            EstoqueOnion = Conexao.confereEstoque("Onion");
            EstoqueRefri = Conexao.confereEstoque("Refri_lata");

            if (EstoqueBatata == 0) {
                menosBatata.setDisable(true);
                maisBatata.setDisable(true);
                String aviso = "Estoque Batata Zerado!";
                SmsDemo.enviaCodigo(aviso);
            }
            if (EstoqueXsalada == 0) {
                dimiXsalada.setDisable(true);
                addXsalada.setDisable(true);
                String aviso = "Estoque XSalada Zerado!";
                SmsDemo.enviaCodigo(aviso);
            }
            if (EstoquePorcaoCamarao == 0) {
                menosPorcaoCamarao.setDisable(true);
                addPorcaoCamarao.setDisable(true);
                String aviso = "Estoque Porcao Camrao Zerado!";
                SmsDemo.enviaCodigo(aviso);

            }
            if (EstoqueHambuSoja == 0) {
                addHambuSoja.setDisable(true);
                menosHambuSoja.setDisable(true);
                String aviso = "Estoque Hamburgue Soja Zerado!";
                SmsDemo.enviaCodigo(aviso);
            }
            if (EstoqueOnion == 0) {
                menosOnion.setDisable(true);
                addOnion.setDisable(true);
                String aviso = "Estoque Onion Zerado!";
                SmsDemo.enviaCodigo(aviso);

            }
            if (EstoqueRefri == 0) {
                menoRefri.setDisable(true);
                addRefri.setDisable(true);
                String aviso = "Estoque Refrigerante Zerado!";
                SmsDemo.enviaCodigo(aviso);

            }

        } catch (SQLException ex) {
            Logger.getLogger(Cardapio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void disparaIdvenda() throws SQLException {

        Conexao.selectVenda();

    }

    @FXML
    private void AcessaPedido() throws IOException {
        App.setRoot("SeusPedidos");

    }
    // sao todos os botoes de adicionar e retirar quantidade que voce quer de algum item , 
    // ele tambem valida se voce esta colocando mais que o estoque tem ;

    @FXML
    private void addBatata() throws SQLException {
        System.out.println("Batata " + EstoqueBatata);

        maiscountBatata++;
        if (maiscountBatata > EstoqueBatata) {
            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("Aviso Importante");
            dialogoInfo.setHeaderText("Estoque Maximo !!!");
            dialogoInfo.setContentText("Por Favor nao Insista ");
            dialogoInfo.showAndWait();

            maiscountBatata--;
        }

        countBatata.setText("" + maiscountBatata);

    }

    @FXML
    private void dimiBatata() throws SQLException {

        maiscountBatata--;
        if (maiscountBatata < 0) {
            maiscountBatata++;
        }
        countBatata.setText("" + maiscountBatata);
    }

    @FXML
    private void addXsalada() throws SQLException {
        System.out.println("XSalada " + EstoqueXsalada);

        quantXsalada++;
        if (quantXsalada > EstoqueXsalada) {
            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("Aviso Importante");
            dialogoInfo.setHeaderText("Estoque Maximo !!!");
            dialogoInfo.setContentText("Por Favor nao Insista ");
            dialogoInfo.showAndWait();

            quantXsalada--;
        }

        countXsalada.setText("" + quantXsalada);

    }

    @FXML
    private void dimiXsalada() throws SQLException {

        quantXsalada--;
        if (quantXsalada < 0) {
            quantXsalada++;
        }
        countXsalada.setText("" + quantXsalada);
    }

    @FXML
    private void addPorcaoCamarao() throws SQLException {
        System.out.println("PorcaoCamarao " + EstoquePorcaoCamarao);

        quantPorcaoCamarao++;
        if (quantPorcaoCamarao > EstoquePorcaoCamarao) {
            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("Aviso Importante");
            dialogoInfo.setHeaderText("Estoque Maximo !!!");
            dialogoInfo.setContentText("Por Favor nao Insista ");
            dialogoInfo.showAndWait();

            quantPorcaoCamarao--;
        }

        countPorcaoCamarao.setText("" + quantPorcaoCamarao);

    }

    @FXML
    private void dimiPorcaoCamarao() throws SQLException {

        quantPorcaoCamarao--;
        if (quantPorcaoCamarao < 0) {
            quantPorcaoCamarao++;
        }
        countPorcaoCamarao.setText("" + quantPorcaoCamarao);
    }

    @FXML
    private void addHambuSoja() throws SQLException {
        System.out.println("Hambusoja " + EstoqueHambuSoja);

        quantHambuSoja++;
        if (quantHambuSoja > EstoqueHambuSoja) {
            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("Aviso Importante");
            dialogoInfo.setHeaderText("Estoque Maximo !!!");
            dialogoInfo.setContentText("Por Favor nao Insista ");
            dialogoInfo.showAndWait();

            quantHambuSoja--;
        }

        countHamburgueSoja.setText("" + quantHambuSoja);

    }

    @FXML
    private void dimiHambuSoja() throws SQLException {

        quantHambuSoja--;
        if (quantHambuSoja < 0) {
            quantHambuSoja++;
        }
        countHamburgueSoja.setText("" + quantHambuSoja);
    }

    @FXML
    private void addOnion() throws SQLException {
        System.out.println("Onions " + EstoqueOnion);

        quantOnion++;
        if (quantOnion > EstoqueOnion) {
            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("Aviso Importante");
            dialogoInfo.setHeaderText("Estoque Maximo !!!");
            dialogoInfo.setContentText("Por Favor nao Insista ");
            dialogoInfo.showAndWait();

            quantOnion--;
        }

        countOnion.setText("" + quantOnion);

    }

    @FXML
    private void menosOnion() throws SQLException {

        quantOnion--;
        if (quantOnion < 0) {
            quantOnion++;
        }
        countOnion.setText("" + quantOnion);
    }

    @FXML
    private void addRefri() throws SQLException {
        System.out.println("Refri " + EstoqueRefri);

        quantRefri++;
        if (quantRefri > EstoqueRefri) {
            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("Aviso Importante");
            dialogoInfo.setHeaderText("Estoque Maximo !!!");
            dialogoInfo.setContentText("Por Favor nao Insista ");
            dialogoInfo.showAndWait();

            quantRefri--;
        }

        countRefri.setText("" + quantRefri);

    }

    @FXML
    private void menosRefri() throws SQLException {

        quantRefri--;
        if (quantRefri < 0) {
            quantRefri++;
        }
        countRefri.setText("" + quantRefri);
    }

    // aqui ele valida se existe algum textfield vazio , se nao tiver ele procura qual esta com alguma quantidade 
    // guardada 
    @FXML
    private void FinalizaPedidoCozinha() throws SQLException, IOException {

        if (maiscountBatata == 0 && quantXsalada == 0 && quantPorcaoCamarao == 0 && quantHambuSoja == 0 && quantOnion == 0 && quantRefri == 0) {
            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("Aviso Importante");
            dialogoInfo.setHeaderText("Nenhum Item selecionado");
            dialogoInfo.setContentText("Por Favor faça algum pedido ");
            dialogoInfo.showAndWait();

        } else {
            if (!idConfirmacao.isSelected()) {
                Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                dialogoInfo.setTitle("Aviso Importante");
                dialogoInfo.setHeaderText("Selecione CheckBox !!");
                dialogoInfo.setContentText("Analise seus pedidos e confirme");
                dialogoInfo.showAndWait();

            } else {

                if (maiscountBatata > 0) {
                    Conexao.insertPedido(maiscountBatata, 1);
                    maiscountBatata = 0;

                }
                if (quantXsalada > 0) {
                    Conexao.insertPedido(quantXsalada, 2);
                    quantXsalada = 0;

                }
                if (quantPorcaoCamarao > 0) {
                    Conexao.insertPedido(quantPorcaoCamarao, 3);
                    quantPorcaoCamarao = 0;

                }
                if (quantHambuSoja > 0) {
                    Conexao.insertPedido(quantHambuSoja, 4);
                    quantHambuSoja = 0;

                }
                if (quantOnion > 0) {
                    Conexao.insertPedido(quantOnion, 5);

                }
                if (quantRefri > 0) {
                    Conexao.insertPedido(quantRefri, 6);

                }

                Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                dialogoInfo.setTitle("Pedido ");
                dialogoInfo.setHeaderText("Pedido Realizado ");
                dialogoInfo.setContentText("Agora e so aguardar , Muito Obrigado!!");
                dialogoInfo.showAndWait();

                countBatata.setText("0");
                countXsalada.setText("0");
                countPorcaoCamarao.setText("0");
                countHamburgueSoja.setText("0");
                countOnion.setText("0");
                countRefri.setText("0");
                idConfirmacao.setSelected(false);
                
                App.setRoot("SeusPedidos");
            }
        }

    }
}
