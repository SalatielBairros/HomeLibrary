package com.dev.salatiel.homelibrary.model;

import com.dev.salatiel.enums.StatusBase;
import com.dev.salatiel.homelibrary.enums.StatusLeituraLivro;
import com.dev.salatiel.homelibrary.enums.StatusLivro;

import java.util.List;

public class LivroModel extends BaseModel{
    String isbn;
    String titulo;
    String sinopse;
    String editora;
    String autor;
    int ano;
    int paginas;
    double preco;
    StatusBase status;
    StatusLeituraLivro statusLeitura;
    StatusLivro statusLivro;
    List<NotaLeituraModel> notasLeitura;
    List<EmprestimoModel> registrosEmprestimo;
    EmprestimoModel emprestimoAtual;

    protected LivroModel(int id) {
        super(id);
    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    public String CreateTable() {
        return null;
    }

    @Override
    public List<String> getValues() {
        return null;
    }

    @Override
    public List<String> getColumns() {
        return null;
    }
}
