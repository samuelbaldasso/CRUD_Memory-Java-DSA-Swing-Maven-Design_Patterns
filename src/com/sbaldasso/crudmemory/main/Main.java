package com.sbaldasso.crudmemory.main;

import com.sbaldasso.crudmemory.dao.ClienteMapDAO;
import com.sbaldasso.crudmemory.dao.IClienteDAO;
import com.sbaldasso.crudmemory.model.Cliente;

import javax.swing.*;

public class Main {

    private static IClienteDAO iClienteDAO;

    public static void main(String[] args) {
        iClienteDAO = new ClienteMapDAO();

        String opcao;
        do {
            opcao = JOptionPane.showInputDialog(null, "1: Cadastro, 2: Consultar, 3: exclusão, 4: alteração ou 5: sair", "Teste 1", JOptionPane.INFORMATION_MESSAGE);

            if ("".equals(opcao)) {
                sair();
            } else if (!isOpcaoValida(opcao)) {
                opcao = JOptionPane.showInputDialog(null, "Opção inválida. 1: Cadastro, 2: Consultar, 3: exclusão, 4: alteração ou 5: sair", "Teste 1", JOptionPane.INFORMATION_MESSAGE);
            }
            if (isOpcaoSair(opcao)) {
                sair();
            } else if (isOpcaoCadastro(opcao)) {
                String dados = JOptionPane.showInputDialog(null, "Digite os dados como nome e CPF.", "1", JOptionPane.INFORMATION_MESSAGE);
                cadastrar(dados);
            } else if (isOpcaoConsulta(opcao)) {
                String dados = JOptionPane.showInputDialog(null, "Digite seu CPF.", "2", JOptionPane.INFORMATION_MESSAGE);
                consultar(dados);
            } else if (isOpcaoExclusao(opcao)) {
                String dados = JOptionPane.showInputDialog(null, "Digite seu CPF.", "3", JOptionPane.INFORMATION_MESSAGE);
                excluir(dados);
            } else if (isOpcaoAlteracao(opcao)) {
                String dados = JOptionPane.showInputDialog(null, "Digite os dados como nome e CPF.", "4", JOptionPane.INFORMATION_MESSAGE);
                alterar(dados);
            }
        } while (!isOpcaoSair(opcao));

    }

    private static void alterar(String dados) {
        String[] dadosSeparados = dados.split(",");
        Cliente cliente = getCliente(dadosSeparados);
        iClienteDAO.alterar(cliente);
        JOptionPane.showMessageDialog(null, "Alterado com sucesso", "Alterar", JOptionPane.INFORMATION_MESSAGE);
    }

    private static Cliente getCliente(String[] dadosSeparados) {
        String nome = !dadosSeparados[0].trim().isEmpty() ? dadosSeparados[0] : null;
        String cpf = !dadosSeparados[1].trim().isEmpty() && validarCPF(dadosSeparados[1].trim()) ? dadosSeparados[1] : null;
        String tel = !dadosSeparados[2].trim().isEmpty() ? dadosSeparados[2] : null;
        String num = !dadosSeparados[3].trim().isEmpty() ? dadosSeparados[3] : null;
        String end = !dadosSeparados[4].trim().isEmpty() ? dadosSeparados[4] : null;
        String cidade = !dadosSeparados[5].trim().isEmpty() ? dadosSeparados[5] : null;
        String estado = !dadosSeparados[6].trim().isEmpty() ? dadosSeparados[6] : null;
        assert cpf != null;
        return new Cliente(nome, Long.parseLong(cpf), Long.parseLong(tel), Integer.parseInt(num), end, cidade, estado);
    }

    private static void excluir(String dados) {
        iClienteDAO.excluir(Long.parseLong(dados));
        JOptionPane.showMessageDialog(null, "Cliente excluído.", "Excluir", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void cadastrar(String dados) {
        String[] dadosSeparados = dados.split(",");
        Cliente cliente = getCliente(dadosSeparados);
        Boolean isCadastrado = iClienteDAO.cadastrar(cliente);
        if (isCadastrado) {
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Já está cadastrado", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static boolean validarCPF(String cpf) {
        return cpf.matches("\\d{3}");
    }

    private static void consultar(String dados) {
        String[] dadosSeparados = dados.split(",");
        if (dadosSeparados.length == 1) {
            String cpf = dadosSeparados[0].trim();
            if (validarCPF(cpf)) {
                Cliente cliente = iClienteDAO.consultar(Long.valueOf(cpf));
                JOptionPane.showMessageDialog(null, cliente.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado", "Consultar", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private static boolean isOpcaoCadastro(String opcao) {
        return "1".equals(opcao);
    }

    private static boolean isOpcaoConsulta(String opcao) {
        return "2".equals(opcao);
    }

    private static boolean isOpcaoExclusao(String opcao) {
        return "3".equals(opcao);
    }

    private static boolean isOpcaoAlteracao(String opcao) {
        return "4".equals(opcao);
    }

    private static boolean isOpcaoSair(String opcao) {
        return "5".equals(opcao);
    }

    private static void sair() {
        StringBuilder clientes = new StringBuilder();
        for (Cliente cliente : iClienteDAO.buscarTodos()) {
            clientes.append(cliente.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, clientes.toString(), "Clientes", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private static boolean isOpcaoValida(String o) {
        return "1".equals(o) || "2".equals(o) || "3".equals(o) || "4".equals(o) || "5".equals(o);
    }
}