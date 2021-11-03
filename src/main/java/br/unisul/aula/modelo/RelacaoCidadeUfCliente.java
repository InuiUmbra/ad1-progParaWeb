package br.unisul.aula.modelo;

import br.unisul.aula.dtocliente.ClienteIdNomeDTO;

import java.util.List;

public class RelacaoCidadeUfCliente {
    String cidade;
    UnidadeFederativa uf;
    List<ClienteIdNomeDTO> clientes;

    public void setCidade(String cidade){
        this.cidade = cidade;
    }

    public void setUf(UnidadeFederativa uf){
        this.uf = uf;
    }

    public void setClientesIdNome(List<ClienteIdNomeDTO> clientes){
        this.clientes = clientes;
    }
}
