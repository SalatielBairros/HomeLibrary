package com.dev.salatiel.homelibrary.model;

import java.util.ArrayList;
import java.util.List;

public class NotaLeituraModel extends BaseModel {
    private static final String TableName = "HOME_LIBRARY_READING_NOTES";
    public static final String C_TEXTO_NOTA = "TEXTO_NOTA";
    public static final String C_PAGINA = "PAGINA";
    public static final String C_AVALIACAO = "AVALIACAO";

    private String textoNota;
    private int pagina;
    private int avaliacao;

    private NotaLeituraModel(int id) {
        super(id);
    }

    public static NotaLeituraModel getInstance(){
        return new NotaLeituraModel(0);
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
                + C_TEXTO_NOTA + " text,"
                + C_PAGINA + " integer,"
                + C_AVALIACAO + " integer"
                +")";
    }

    @Override
    public List<String> getValues() {
        List<String> retList = new ArrayList<>();
        retList.add(Integer.toString(this.get_id()));
        retList.add(this.getDataCadastro().toString());
        retList.add(this.getDataAlteracao().toString());
        retList.add(this.getTextoNota());
        retList.add(String.valueOf(this.getPagina()));
        retList.add(String.valueOf(this.getAvaliacao()));
        return retList;
    }

    @Override
    public List<String> getColumns() {
        List<String> retList = new ArrayList<>();
        retList.add(C_ID);
        retList.add(C_DATA_CADASTRO);
        retList.add(C_DATA_MODIFICACAO);
        retList.add(C_TEXTO_NOTA);
        retList.add(C_PAGINA);
        retList.add(C_AVALIACAO);
        return retList;
    }

    public String getTextoNota() {
        return textoNota;
    }

    public void setTextoNota(String textoNota) {
        this.textoNota = textoNota;
    }

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }
}
