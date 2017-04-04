package fiap.com.br.nac02appfaculdade.bean;

import java.io.Serializable;

/**
 * Created by RM75095 on 18/10/2016.
 */
public class Faculdade implements Serializable {

    private Integer id;
    private String nome, curso,duracao, webSite, telefone, endereco;
    private Float preco, rating;
    private String foto;



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

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    @Override
    public String toString() {
        StringBuilder rating = new StringBuilder();
        for (int i=1; i<= this.getRating(); i++){
            rating.append("*");
        }
        return this.getNome() + " " + this.getCurso() + " - Estrelas: " + rating.toString();
    }

}
