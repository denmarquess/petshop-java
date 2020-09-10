package model;

import java.time.LocalDate;

public class Animal {

    private int id;
    private String nome;
    private LocalDate dataNascimento;
    private String raca;
    private float altura;
    private float peso;
    private byte[] foto;

    public Animal() {
    }

    public Animal(int id, String nome, LocalDate dataNascimento, String raca, float altura, float peso, byte[] foto) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.raca = raca;
        this.altura = altura;
        this.peso = peso;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    
    
   
    
    
}
