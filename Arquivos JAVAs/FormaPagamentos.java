/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comandavirtual;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;

/**
 *
 * @author clebe
 */
public class FormaPagamentos {

    //caso seja pago pelo pix ele da um desconto de 5% no valor final e mostra o quanto ele economizou em 10%
    public void pagPix() throws IOException, SQLException {
        Conexao conn = new Conexao();

        Conexao.finalizaVenda();
        float desconto = Conexao.decontoPix();

        int idvendaa = Conexao.idVenda;

        float valor = 0;
        String teste = "select eco_10 from vendas where idVenda = " + idvendaa + "";
        Statement st = (Statement) conn.abrirConexao().createStatement();

        st.executeQuery(teste);

        ResultSet rs = st.getResultSet();

        while (rs.next()) {

            valor = Float.parseFloat(rs.getString("eco_10"));
        }

        System.out.println("valor" + valor);

        String total = String.format("%.2f", (desconto + valor));
        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        dialogoInfo.setTitle("Aviso Pagamento");
        dialogoInfo.setHeaderText("Pagamento feito com Pix \nParabens você ganhou dois desconto ");
        dialogoInfo.setContentText("Valor economizado em 10% R$ " + valor + " \nDesconto em pix R$ " + desconto + "\nTotal economizado de R$ " + total + "");
        dialogoInfo.showAndWait();

        Conexao.idVenda = 0;
        App.setRoot("Login");

    }
    // nesse caso somente fala o quanto ele economizou em 10% de garçom
    public void pagPayPal() throws IOException, SQLException {

        Conexao.finalizaVenda();

        Conexao conn = new Conexao();

        String teste = "select eco_10 from vendas where idVenda = " + Conexao.idVenda + "";
        Statement st = (Statement) conn.abrirConexao().createStatement();

        st.execute(teste);

        ResultSet rs = st.getResultSet();

        float valor = 0;
        while (rs.next()) {

            valor = rs.getFloat("eco_10");
        }

        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        dialogoInfo.setTitle("Aviso Pagamento");
        dialogoInfo.setHeaderText("Pagamento feito com PayPal ");
        dialogoInfo.setContentText("Valor economizado em 10% R$ " + valor);
        dialogoInfo.showAndWait();

        Conexao.idVenda = 0;

        App.setRoot("Login");

    }

}
