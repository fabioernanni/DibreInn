package br.com.lebrehotel.dibreinn.model;

import java.util.ArrayList;

/**
 *
 * @author Thiago
 */
public class Email {
    private String assunto;
    private String mensagem;
    private ArrayList<String> destinatario;

    public Email() {
        assunto = "";
        mensagem="";
            destinatario = new ArrayList<String>();
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public ArrayList<String> getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(ArrayList<String> destinatario) {
        this.destinatario = destinatario;
    }
    
    public void addDestinatario(String destinatario){
    this.destinatario.add(destinatario);
    }
    
    
    
    
}
