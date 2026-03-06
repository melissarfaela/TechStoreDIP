import service.RecuperSenha;
import service.ServicoEmail;
import strategy.ServicoMensagem;

public class Main {
    public static void main(String[] args) {


        ServicoMensagem servico = new ServicoEmail();

        RecuperSenha recuperador = new RecuperSenha(servico);

        recuperador.recuperar("cliente@email.com");

    }
}