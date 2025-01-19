package com.sbaldasso.crudmemory.model;

import java.util.Objects;

public class Cliente {
    private String nome;
    private Long cpf;
    private Long tel;
    private String end;
    private Integer num;
    private String cidade;
    private String estado;

    public Cliente(String nome, Long cpf, Long tel, Integer num, String end, String cidade, String estado) {
        this.nome = nome;
        this.cpf = cpf;
        this.tel = tel;
        this.num = num;
        this.end = end;
        this.cidade = cidade;
        this.estado = estado;
    }

    public Cliente(Long cpf){
        this.cpf = getCpf();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(cpf, cliente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cpf);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
