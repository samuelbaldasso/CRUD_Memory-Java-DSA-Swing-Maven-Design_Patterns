package com.sbaldasso.crudmemory.dao;

import com.sbaldasso.crudmemory.model.Cliente;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClienteMapDAO implements IClienteDAO {

    private Map<Long, Cliente> map;

    public ClienteMapDAO(){
        this.map = new HashMap<>();
    }
    @Override
    public Boolean cadastrar(Cliente cliente) {
        if(map.containsKey(cliente.getCpf())){
            return false;
        }
        this.map.putIfAbsent(cliente.getCpf(), cliente);
        return true;
    }

    @Override
    public void excluir(Long cpf) {
        Cliente cliente = this.map.get(cpf);
        if(cliente != null){
            this.map.remove(cliente.getCpf(), cliente);
        }
    }

    @Override
    public void alterar(Cliente cliente) {
        Cliente clienteCadastrado = this.map.get(cliente.getCpf());
        if(clienteCadastrado != null){
            clienteCadastrado.setNome(cliente.getNome());
            clienteCadastrado.setCidade(cliente.getCidade());
            clienteCadastrado.setTel(cliente.getTel());
            clienteCadastrado.setEnd(cliente.getEnd());
            clienteCadastrado.setNum(cliente.getNum());
            clienteCadastrado.setEstado(cliente.getEstado());
        }
    }

    @Override
    public Cliente consultar(Long cpf) {
        return this.map.get(cpf);
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        return this.map.values();
    }
}
