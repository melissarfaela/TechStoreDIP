package service;

import strategy.ServicoMensagem;

public class RecuperSenha {

    private ServicoMensagem servicoMensagem;

    public RecuperSenha(ServicoMensagem servicoMensagem) {
        this.servicoMensagem = servicoMensagem;
    }

    public void recuperar(String email) {
        String link = "http://techstore.com/reset?token=123";
        servicoMensagem.enviar("Clique no link para resetar sua senha: " + link);
    }

}
