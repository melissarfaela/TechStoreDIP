package service;

import strategy.ServicoMensagem;

public class ServicoEmail implements ServicoMensagem {
    @Override
    public void enviar(String mensagem) {
        System.out.println("Enviando E-mail SMTP: " + mensagem);
    }
}
