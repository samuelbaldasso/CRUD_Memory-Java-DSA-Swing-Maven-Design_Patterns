package com.sbaldasso.crudmemory.dao;

import com.sbaldasso.crudmemory.model.Cliente;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ClienteSetDAO implements IClienteDAO {
    private Set<Cliente> map;

    public ClienteSetDAO() {
        this.map = new HashSet<>();
    }

    @Override
    public Boolean cadastrar(Cliente cliente) {
        return this.map.add(cliente);
    }

    @Override
    public void excluir(Long cpf) {
        Cliente clienteEncontrado = null;
        for (Cliente cliente : this.map) {
            if (cliente.getCpf().equals(cpf)) {
                clienteEncontrado = cliente;
                break;
            }
        }

        if (clienteEncontrado != null) {
            this.map.remove(clienteEncontrado);
        }
    }

    @Override
    public void alterar(Cliente cliente) {
        Cliente clienteCadastrado = null;
        for (Cliente c : this.map) {
            if (c.equals(cliente)) {
                clienteCadastrado = c;
                break;
            }
        }

        if (clienteCadastrado != null) {
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
        Cliente clienteCadastrado = null;
        for (Cliente cliente : this.map) {
            if (cliente.getCpf().equals(cpf)) {
                clienteCadastrado = cliente;
            }
        }
        return clienteCadastrado;
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        return this.map;
    }
}