package com.dev.salatiel.homelibrary.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsuarioModel extends BaseModel {
    private static final String TableName = "HOME_LIBRARY_USER";
    public static final String C_NOME = "NOME";
    public static final String C_SENHA = "SENHA";
    public static final String C_EMAIL = "EMAIL";

    private String nome;
    private String senha;
    private String email;

    protected UsuarioModel(int id) {
        super(id);
    }

    private UsuarioModel(int id, String nome, String senha, String email) {
        super(id);
        this.nome = nome;
        this.senha = senha;
        this.email = email;
    }

    private UsuarioModel(String nome, String senha, String email) {
        super(0);
        this.nome = nome;
        this.senha = senha;
        this.email = email;
    }

    public static UsuarioModel getInstance(){
        return new UsuarioModel(0);
    }

    public static UsuarioModel getInstance(int id){
        return new UsuarioModel(id);
    }

    public static UsuarioModel getInstance(int id, String nome, String senha, String email){
        return new UsuarioModel(id, nome, senha, email);
    }

    public static UsuarioModel createNew(String nome, String senha, String email){
        UsuarioModel um = new UsuarioModel(nome, senha, email);
        um.setDataAlteracao(new Date());
        um.setDataCadastro(new Date());
        return um;
    }

    @Override
    public String getTableName() {
        return TableName;
    }

    @Override
    public String CreateTable() {
        return "CREATE TABLE "+ getTableName() +" ("
                + C_ID + " integer primary key autoincrement,"
                + C_DATA_CADASTRO + " datetime,"
                + C_DATA_MODIFICACAO + " datetime,"
                + C_NOME + " text,"
                + C_SENHA + " text,"
                + C_EMAIL + " text"
                +")";
    }

    @Override
    public List<String> getValues() {
        List<String> retList = new ArrayList<>();
        retList.add(Integer.toString(this.get_id()));
        retList.add(this.getDataCadastro().toString());
        retList.add(this.getDataAlteracao().toString());
        retList.add(this.getNome());
        retList.add(this.getSenha());
        retList.add(this.getEmail());
        return retList;
    }

    @Override
    public List<String> getColumns() {
        List<String> retList = new ArrayList<>();
        retList.add(C_ID);
        retList.add(C_DATA_CADASTRO);
        retList.add(C_DATA_MODIFICACAO);
        retList.add(C_NOME);
        retList.add(C_SENHA);
        retList.add(C_EMAIL);
        return retList;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
