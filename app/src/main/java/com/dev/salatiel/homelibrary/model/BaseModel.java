package com.dev.salatiel.homelibrary.model;

import com.dev.salatiel.interfaces.IEntityModel;

import java.util.Date;

public abstract class BaseModel implements IEntityModel {
    private long _id;
    private Date dataCadastro;
    private Date dataAlteracao;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
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
