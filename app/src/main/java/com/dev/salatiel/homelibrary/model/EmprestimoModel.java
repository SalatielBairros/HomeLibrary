package com.dev.salatiel.homelibrary.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmprestimoModel extends BaseModel {
    public static final String TableName = "HOME_LIBRARY_EMPRESTIMOS";
    public static final String C_NOME_PESSOA = "NOME_PESSOA";
    public static final String C_DATA_EMPRESTIMO = "DATA_EMPRESTIMO";
    public static final String C_DATA_DEVOLUCAO_PREVISTA = "DATA_DEVOLUCAO_PREVISTA";
    public static final String C_DEVOLVIDO = "DEVOLVIDO";
    public static final String C_DATA_DEVOLUCAO = "DATA_DEVOLUCAO";

    private String nomePessoa;
    private Date dataEmprestimo;
    private Date dataDevolucaoPrevista;
    private boolean devolvido;
    private Date dataDevolucao;

    protected EmprestimoModel(int id) {
        super(id);
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public static EmprestimoModel getInstance(){
        return new EmprestimoModel(0);
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
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
                + C_NOME_PESSOA + " text,"
                + C_DATA_EMPRESTIMO + " date,"
                + C_DATA_DEVOLUCAO_PREVISTA + " date,"
                + C_DEVOLVIDO + " integer,"
                + C_DATA_DEVOLUCAO + " date"
                +")";
    }

    @Override
    public List<String> getValues() {
        List<String> retList = new ArrayList<>();
        retList.add(Integer.toString(this.get_id()));
        retList.add(this.getDataCadastro().toString());
        retList.add(this.getDataAlteracao().toString());
        retList.add(this.getNomePessoa());
        retList.add(this.getDataEmprestimo().toString());
        retList.add(this.getDataDevolucaoPrevista().toString());
        retList.add(String.valueOf(this.isDevolvido()));
        retList.add(this.getDataDevolucao().toString());
        return retList;
    }

    @Override
    public List<String> getColumns() {
        List<String> retList = new ArrayList<>();
        retList.add(C_ID);
        retList.add(C_DATA_CADASTRO);
        retList.add(C_DATA_MODIFICACAO);
        retList.add(C_NOME_PESSOA);
        retList.add(C_DATA_EMPRESTIMO);
        retList.add(C_DATA_DEVOLUCAO_PREVISTA);
        retList.add(C_DEVOLVIDO);
        retList.add(C_DATA_DEVOLUCAO);
        return retList;
    }

    public Date getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(Date dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }
}
