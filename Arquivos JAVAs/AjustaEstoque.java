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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author clebe
 */
public class AjustaEstoque implements Initializable {

    int maiscountBatata;
    int quantXsalada = 0;
    int quantPorcaoCamarao = 0;
    int quantHambuSoja = 0;
    int quantOnion = 0;
    int quantRefri = 0;

    @FXML
    private AnchorPane IVcamarao;
    @FXML
    private TextField countBatata;
    @FXML
    private TextField countPorcaoCamarao;
    @FXML
    private TextField countXsalada;
    @FXML
    private TextField countHamburgueSoja;
    @FXML
    private TextField countOnion;
    @FXML
    private TextField countRefri;
    @FXML
    private Button btnAddEstoque;
    @FXML
    private TextField addBatata;
    @FXML
    private TextField addPorcaoCamarao;
    @FXML
    private TextField addXSalada;
    @FXML
    private TextField addHamburgue;
    @FXML
    private TextField addOnion;
    @FXML
    private TextField addRefri;

    @Override //Aqui inicializa automaticamentes todos os estoques disponiveis na tela 
    public void initialize(URL url, ResourceBundle rb) {

        try {
            countBatata.setText(String.valueOf(Conexao.confereEstoque("P_BatataFrita")));
            countXsalada.setText(String.valueOf(Conexao.confereEstoque("X_Salada")));
            countPorcaoCamarao.setText(String.valueOf(Conexao.confereEstoque("Porcao_Camarao")));
            countHamburgueSoja.setText(String.valueOf(Conexao.confereEstoque("Hamburguer_Soja")));
            countOnion.setText(String.valueOf(Conexao.confereEstoque("Onion")));
            countRefri.setText(String.valueOf(Conexao.confereEstoque("Refri_lata")));

        } catch (SQLException ex) {
            Logger.getLogger(Cardapio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML // Toda vez que for acionado sera pego todos os dados que gerente incluir nos TextField incluido no estoque no banco de dados
    private void FinalizaAjusteEstoque(ActionEvent event) throws SQLException, IOException {

        try {

            maiscountBatata = Integer.parseInt(addBatata.getText());
            quantXsalada = Integer.parseInt(addXSalada.getText());
            quantPorcaoCamarao = Integer.parseInt(addPorcaoCamarao.getText());
            quantHambuSoja = Integer.parseInt(addHamburgue.getText());
            quantOnion = Integer.parseInt(addOnion.getText());
            quantRefri = Integer.parseInt(addRefri.getText());

            // Toda conferencia se esta vazio , e quais itens ele adicionou para somente adcionar os que ele pediu
            if (maiscountBatata == 0 && quantXsalada == 0 && quantPorcaoCamarao == 0 && quantHambuSoja == 0 && quantOnion == 0 && quantRefri == 0) {
                Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                dialogoInfo.setTitle("Aviso Importante");
                dialogoInfo.setHeaderText("Nenhum Incluido ");
                dialogoInfo.setContentText("Por Favor Adicione estoque ");
                dialogoInfo.showAndWait();

            } else {
                if (maiscountBatata > 0) {
                    Conexao.insereEstoque(maiscountBatata, "P_BatataFrita");
                    maiscountBatata = 0;

                }
                if (quantXsalada > 0) {
                    Conexao.insereEstoque(quantXsalada, "X_Salada");
                    quantXsalada = 0;

                }
                if (quantPorcaoCamarao > 0) {
                    Conexao.insereEstoque(quantPorcaoCamarao, "Porcao_Camarao");
                    quantPorcaoCamarao = 0;

                }
                if (quantHambuSoja > 0) {
                    Conexao.insereEstoque(quantHambuSoja, "Hamburguer_Soja");
                    quantHambuSoja = 0;

                }
                if (quantOnion > 0) {
                    Conexao.insereEstoque(quantOnion, "Onion");

                }
                if (quantRefri > 0) {
                    Conexao.insereEstoque(quantRefri, "Refri_Lata");

                }

                Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                dialogoInfo.setTitle("Aviso Importante");
                dialogoInfo.setHeaderText("Estoques Atualizados ");
                dialogoInfo.setContentText("Com Sucesso !!! ");
                dialogoInfo.showAndWait();

                addBatata.setText("0");
                addXSalada.setText("0");
                addPorcaoCamarao.setText("0");
                addHamburgue.setText("0");
                addOnion.setText("0");
                addRefri.setText("0");

                App.setRoot("Gerente");

            }

        } catch (NumberFormatException ex) {
            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("Aviso Importante");
            dialogoInfo.setHeaderText("Por favor adicione numeros !");
            dialogoInfo.setContentText("Voce Digitou letras em algum campo \n Análise todos ");
            dialogoInfo.showAndWait();
        }
    }

    @FXML //Somente volta para Menu Gerente 
    public void voltaReceitas() throws IOException {
        App.setRoot("Gerente");

    }

}
