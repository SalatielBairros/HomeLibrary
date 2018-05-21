package com.dev.salatiel.homelibrary.model;

import com.dev.salatiel.interfaces.IEntityModel;

import java.util.Date;

public abstract class BaseModel implements IEntityModel {
    public static final String C_ID = "_id";
    public static final String C_DATA_CADASTRO = "dt_cadastro";
    public static final String C_DATA_MODIFICACAO = "dt_alteracao";
    private int _id;
    private Date dataCadastro;
    private Date dataAlteracao;

    protected BaseModel(int id){
        this._id = id;
    }

    public int get_id() {
        return _id;
    }

    @Override
    public String DropTable() {
        return "DROP TABLE IF EXISTS " + getTableName();
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
}
