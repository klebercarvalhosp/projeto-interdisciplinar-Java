package com.mycompany.comandavirtual;

import java.io.IOException;
import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Cadastro implements Initializable {

    private String nome;
    private String cpf;
    private String senha;
    private String telefone;
    static String nivelAcesso;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        rbUsuario.setSelected(true);

    }

    public String getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Cadastro(String nome, String cpf, String senha, String telefone, String nivelAcesso) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.telefone = telefone;
        this.nivelAcesso = nivelAcesso;
    }

    public Cadastro() {
    }

    @FXML
    private TextField txt_NomeCadastro;
    @FXML
    private TextField txt_CpfCadastro;
    @FXML
    private TextField txt_TelefoneCadastro;
    @FXML
    private ToggleGroup tipo_cadastro;
    @FXML
    private RadioButton rbUsuario;
    @FXML
    private TextField txt_SenhaCadastro;
    @FXML
    private RadioButton rbGerente;
    @FXML
    private TextField txt_ValidaGerente;
    @FXML
    private Button btnValidaGerente;

    int randomNum = 0;

    @FXML  //Todo processo de validar gerente , gera uma senha aleatoria e manda via sms para dono 
    public void validaGerente() {
        Random r = new Random();
        randomNum = r.nextInt(8000);
        String codigo = String.valueOf(randomNum);
        System.out.println("WARNING: Loading FXML -->" + randomNum + "");
        txt_ValidaGerente.setVisible(true);
        btnValidaGerente.setVisible(true);

        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        dialogoInfo.setTitle("Atenção");
        dialogoInfo.setHeaderText("Digite o codigo enviado para proprietario ");
        dialogoInfo.setContentText("Campo abaixo");
        dialogoInfo.showAndWait();

        SmsDemo.enviaCodigo(codigo);

        rbGerente.setDisable(true);
        rbUsuario.setSelected(true);

    }

    @FXML// se codigo estiver correto ele libera o botao do gerente 
    public void validaCodigo() {
        int valida = Integer.parseInt(txt_ValidaGerente.getText());
        if (valida == randomNum) {
            rbGerente.setSelected(true);
        } else {
            Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
            dialogoInfo.setTitle("Codigo Incorreto");
            dialogoInfo.setHeaderText("Digite o codigo enviado para proprietario ");
            dialogoInfo.setContentText("Digite Novamente ");
            dialogoInfo.showAndWait();
        }
    }

    @FXML // e se tudo la em cima estiver correto ou se o cadastro for somente para usuario ele cai para esse metodo
    public void Cadastrar() throws SQLException, IOException {

        RadioButton radioCadastro = (RadioButton) tipo_cadastro.getSelectedToggle();

        Cadastro cadastro = new Cadastro();

        txt_CpfCadastro.setStyle("-fx-background-color: #ffffff;");
        txt_SenhaCadastro.setStyle("-fx-background-color: #ffffff;");
        txt_TelefoneCadastro.setStyle("-fx-background-color: #ffffff;");

        String nome = txt_NomeCadastro.getText();
        String cpf = txt_CpfCadastro.getText();
        String senha = txt_SenhaCadastro.getText();
        String telefone = txt_TelefoneCadastro.getText();
        String nivelAcesso = radioCadastro.getText();

        boolean veriSenha = false;
        boolean veriCPF = false;
        boolean veriNome = false;
        boolean veriTel = false;
        boolean veriRadio = false;
        boolean aux = false;

        // ------------------------- Verificação Radio Button  -------------------------
        if (radioCadastro == null) {
            veriRadio = false;
        } else {
            veriRadio = true;
        }

        // ------------------------- Verificação Nome -------------------------
        if (ValidaNome.containChar(nome) == true) {
            veriNome = true;
            txt_NomeCadastro.setStyle("-fx-background-color: #ffffff;");
        } else {
            veriNome = false;
            txt_NomeCadastro.setStyle("-fx-background-color: #ff0000;");
        }

        // ------------------------- Verificação Senha -------------------------
        if (ValidaSenha.isPASS(senha) == true) {
//        System.out.printf("Aceito %s\n", senha);;
            veriSenha = true;
        } else {
            //System.out.printf("Erro, Senha invalida !!!\n");

            veriSenha = false;
            txt_SenhaCadastro.setStyle("-fx-background-color: #ff0000;");
        }

        // ------------------------- Verificação CPF -------------------------
        if (ValidaCPF.isCPF(cpf) == true && cpf.length() == 11) {
            //System.out.printf("Aceito %s\n", ValidaCPF.formataCPF(cpf));
            aux = true;
            veriCPF = true;
        } else if (ValidaCPF.isCPF(cpf) == true && cpf.length() == 14) {
            //System.out.printf("Aceito %s\n", cpf);
            veriCPF = true;
        } else {
            //System.out.printf("Erro, CPF invalido !!!\n");
            txt_CpfCadastro.setStyle("-fx-background-color: #ff0000;");
            veriCPF = false;
        }

        // ------------------------- Verificação Tel  -------------------------
        if (!ValidaTelefone.isTelefone(telefone)) {
            veriTel = false;
            txt_TelefoneCadastro.setStyle("-fx-background-color: #ff0000;");
        } else {
            veriTel = true;
        }

        // ------------------------- Verificação Geral -------------------------
        if (veriCPF == true && veriSenha == true && aux == false && veriNome == true && veriTel == true && veriRadio == true) {

            //boolean status;
            cadastro.setCpf(cpf);
            cadastro.setSenha(senha);
            cadastro.setNome(nome);
            cadastro.setTelefone(telefone);
            cadastro.setNivelAcesso(nivelAcesso);

            System.out.printf("Credenciais Cadastradas !!!\n");
            Conexao.insert(cadastro);

            // se tudo estiver correto ele valida o cadastro e insere no banco 
        } else if (veriCPF == true && veriSenha == true && aux == true && veriNome == true && veriTel == true && veriRadio == true) {
            cadastro.setCpf(ValidaCPF.formataCPF(cpf));
            cadastro.setSenha(senha);
            cadastro.setNome(nome);
            cadastro.setTelefone(telefone);
            cadastro.setNivelAcesso(nivelAcesso);
            Conexao.insert(cadastro);

            System.out.printf("Credenciais Cadastradas !!!\n");
            System.out.println("Nivel Acesso " + Cadastro.nivelAcesso);

            Alert dialogoInfo = new Alert(Alert.AlertType.CONFIRMATION);
            dialogoInfo.setTitle("Aviso Importante");
            dialogoInfo.setHeaderText("Cadastrado com Sucesso ");
            dialogoInfo.setContentText("Faça o login ");
            dialogoInfo.showAndWait();

            App.setRoot("Login");

        } else {
            Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
            dialogoInfo.setTitle("Aviso Importante");
            dialogoInfo.setHeaderText("Credenciais erradas ou incompletas ");
            dialogoInfo.setContentText("Analise os campos em vermelho por favor !");
            dialogoInfo.showAndWait();
        }

    }

    @FXML // volta para login 
    public void inicio() throws IOException {
        App.setRoot("Login");

    }

}
