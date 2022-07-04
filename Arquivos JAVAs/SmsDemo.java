/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comandavirtual;

import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;

public class SmsDemo {

    public static final String ACCOUNT_SID = "AC489142ac125a0f1fe4d0e6f83ef0013b";
    public static final String AUTH_TOKEN = "c51f81d06aea227f9a682897ac00496c";
    //metodo responsável pelo envio do SMS
    public static void enviaCodigo(String codigo) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+5519982743581"),
                "MG8b5d5a16f87e596c5d301e7d68ece786",
                codigo)
                .create();

        System.out.println(message.getSid());
    }
}
