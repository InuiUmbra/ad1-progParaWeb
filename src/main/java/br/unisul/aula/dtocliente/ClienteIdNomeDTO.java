package br.unisul.aula.dtocliente;

public class ClienteIdNomeDTO {
    long id;
    String nome;

    public ClienteIdNomeDTO(){

    }

    public ClienteIdNomeDTO(long id, String nome){
        setId(id);
        setNome(nome);
    }

    public void setId(long id){
        this.id = id;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
}
