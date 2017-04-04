package br.com.fiap.persistencia.modelo;

import java.io.Serializable;

public class Restaurante implements Serializable {


    private Integer id;
    private String nome, especialidade, prato, webSite, telefone, endereco;
    private Float preco, rating;
    private String foto;

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEspecialidade() {
        return especialidade;
    }
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    public String getPrato() {
        return prato;
    }
    public void setPrato(String prato) {
        this.prato = prato;
    }
    public Float getPreco() {
        return preco;
    }
    public void setPreco(Float preco) {
        this.preco = preco;
    }
    public Float getRating() {
        return rating;
    }
    public void setRating(Float rating) {
        this.rating = rating;
    }
    @Override
    public String toString() {
        StringBuilder rating = new StringBuilder();
        for (int i=1; i<= this.getRating(); i++){
            rating.append("*");
        }
        return this.getNome() + " " + this.getEspecialidade() + " - Estrelas: " + rating.toString();
    }
}

