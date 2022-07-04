/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comandavirtual;

import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author clebe
 */
public class Login extends Conexao {

    static String cpfInsert;
    private String Cpf;
    private String Senha;
    private String nivelAcesso;

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String Cpf) {
        this.Cpf = Cpf;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    public String getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    @FXML
    private Button btnAcessarLogin;
    @FXML
    private TextField txtCpfLogin;
    @FXML
    private TextField txtSenhaLogin;
    @FXML
    private Button btnCadastrarLogin;
    @FXML
    private Label lbValida;

    @FXML //acessa cadastro
    private void Acessar() throws IOException {
        App.setRoot("Cadastro");

    }
    // aqui começa toda validação de cpf e senha se estao corretos , os que vieram do banco 
    // e tambem verifica se o usuario e Gerente ou Usuario normal 
    public void AcessoCadastro() throws IOException, SQLException {

        Login login = new Login();

        Cpf = txtCpfLogin.getText();

        boolean veriCPF = false;
        boolean aux = false;

        if (ValidaCPF.isCPF(Cpf) == true && Cpf.length() == 11) {
            System.out.printf("Aceito %s\n", ValidaCPF.formataCPF(Cpf));
            aux = true;
            veriCPF = true;
        } else if (ValidaCPF.isCPF(Cpf) == true && Cpf.length() == 14) {
            System.out.printf("Aceito %s\n", Cpf);
            veriCPF = true;
        } else {
            System.out.printf("Erro, CPF invalido !!!\n");
            veriCPF = false;
            Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
            dialogoInfo.setTitle("Aviso Importante");
            dialogoInfo.setHeaderText("Credenciais erradas ou incompletas ");
            dialogoInfo.setContentText("Analise os campos por favor !");
            dialogoInfo.showAndWait();
        }

        if (veriCPF == true) {

            login.setCpf(ValidaCPF.formataCPF(Cpf));
            cpfInsert = ValidaCPF.formataCPF(Cpf);
            login.setSenha(txtSenhaLogin.getText());

            System.out.println("ENTROU LOGIN " + login.getCpf());

            Conexao.select(login);

            System.out.println("Senha " + Conexao.senha);
            String compara = Conexao.nivelAcesso;
            System.out.println("Nivel Acesso " + compara);

            if (Conexao.senha.equals(login.getSenha())) {
                System.out.println("Entrou Aqui");

                if (compara.equals("Usuario")) {
                    App.setRoot("Cardapio");

                } else if (compara.equals("Gerente")) {
                    App.setRoot("Gerente");
                }

            } else {
                Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
                dialogoInfo.setTitle("Aviso Importante");
                dialogoInfo.setHeaderText("Credenciais erradas ou incompletas ");
                dialogoInfo.setContentText("Analise os campos por favor !");
                dialogoInfo.showAndWait();

            }
        }
    }
}
