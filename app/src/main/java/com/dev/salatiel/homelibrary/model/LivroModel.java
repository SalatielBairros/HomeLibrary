package com.dev.salatiel.homelibrary.model;

import com.dev.salatiel.enums.StatusBase;
import com.dev.salatiel.homelibrary.enums.StatusLeituraLivro;
import com.dev.salatiel.homelibrary.enums.StatusLivro;
import com.dev.salatiel.util.DateTimeUtil;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LivroModel extends BaseModel{
    public static final String TableName = "HOME_LIBRARY_LIVROS";
    public static final String C_ISBN = "ISBN";
    public static final String C_TITULO = "TITULO";
    public static final String C_SINOPSE = "SINOPSE";
    public static final String C_EDITORA = "EDITORA";
    public static final String C_AUTOR = "AUTOR";
    public static final String C_ANO = "ANO";
    public static final String C_PAGINAS = "PAGINAS";
    public static final String C_PRECO = "PRECO";
    public static final String C_STATUS = "STATUS";
    public static final String C_STATUS_LEITURA = "STATUS_LEITURA";
    public static final String C_STATUS_LIVRO = "STATUS_LIVRO";

    private String isbn;
    private String titulo;
    private String sinopse;
    private String editora;
    private String autor;
    private int ano;
    private int paginas;
    private double preco;
    private StatusBase status;
    private StatusLeituraLivro statusLeitura;
    private StatusLivro statusLivro;
    private List<NotaLeituraModel> notasLeitura;
    private List<EmprestimoModel> registrosEmprestimo;
    private EmprestimoModel emprestimoAtual;

    protected LivroModel(int id) {
        super(id);
    }

    protected LivroModel(int id, String isbn) {
        super(id);
        this.isbn = isbn;
    }

    public static LivroModel getInstance(){
        return new LivroModel(0);
    }

    public static LivroModel getInstance(int id){
        return new LivroModel(id);
    }

    public static LivroModel createNew(int id){
        LivroModel livro = getInstance(id);

        if(id == 0){
            livro.setDataCadastro(new Date());
            livro.setStatus(StatusBase.Ativo);
            livro.setStatusLeitura(StatusLeituraLivro.Pendente);
            livro.setStatusLivro(StatusLivro.Disponivel);
        }
        livro.setDataAlteracao(new Date());
        return livro;
    }

    public static LivroModel getInstance(String isbn){
        return new LivroModel(0, isbn);
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
                + C_ISBN + " text,"
                + C_TITULO + " text,"
                + C_SINOPSE + " text,"
                + C_EDITORA + " text,"
                + C_AUTOR + " text,"
                + C_ANO + " integer,"
                + C_PAGINAS + " integer,"
                + C_PRECO + " real,"
                + C_STATUS + " integer,"
                + C_STATUS_LEITURA + " integer,"
                + C_STATUS_LIVRO + " integer"
                +")";
    }

    @Override
    public List<String> getValues() {
        List<String> retList = new ArrayList<>();
        retList.add(Integer.toString(this.get_id()));
        retList.add(DateTimeUtil.toString(this.getDataCadastro(), "yyyy-MM-dd HH:mm:ss"));
        retList.add(DateTimeUtil.toString(this.getDataAlteracao(), "yyyy-MM-dd HH:mm:ss"));
        retList.add(this.getIsbn());
        retList.add(this.getTitulo());
        retList.add(this.getSinopse());
        retList.add(this.getEditora());
        retList.add(this.getAutor());
        retList.add(String.valueOf(this.getAno()));
        retList.add(String.valueOf(this.getPaginas()));
        retList.add(String.valueOf(this.getPreco()));
        retList.add(String.valueOf(this.getStatus().ordinal()));
        retList.add(String.valueOf(this.getStatusLeitura().ordinal()));
        retList.add(String.valueOf(this.getStatusLivro().ordinal()));
        return retList;
    }

    @Override
    public List<String> getColumns() {
        List<String> retList = new ArrayList<>();
        retList.add(C_ID);
        retList.add(C_DATA_CADASTRO);
        retList.add(C_DATA_MODIFICACAO);
        retList.add(C_ISBN);
        retList.add(C_TITULO);
        retList.add(C_SINOPSE);
        retList.add(C_EDITORA);
        retList.add(C_AUTOR);
        retList.add(C_ANO);
        retList.add(C_PAGINAS);
        retList.add(C_PRECO);
        retList.add(C_STATUS);
        retList.add(C_STATUS_LEITURA);
        retList.add(C_STATUS_LIVRO);
        return retList;
    }

    public List<String> getViewColumns() {
        List<String> retList = new ArrayList<>();
        retList.add(C_TITULO);
        retList.add(C_AUTOR);
        retList.add(C_EDITORA);
        return retList;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public StatusBase getStatus() {
        return status;
    }

    public void setStatus(StatusBase status) {
        this.status = status;
    }

    public StatusLeituraLivro getStatusLeitura() {
        return statusLeitura;
    }

    public void setStatusLeitura(StatusLeituraLivro statusLeitura) {
        this.statusLeitura = statusLeitura;
    }

    public StatusLivro getStatusLivro() {
        return statusLivro;
    }

    public void setStatusLivro(StatusLivro statusLivro) {
        this.statusLivro = statusLivro;
    }

    public List<NotaLeituraModel> getNotasLeitura() {
        return notasLeitura;
    }

    public void setNotasLeitura(List<NotaLeituraModel> notasLeitura) {
        this.notasLeitura = notasLeitura;
    }

    public List<EmprestimoModel> getRegistrosEmprestimo() {
        return registrosEmprestimo;
    }

    public void setRegistrosEmprestimo(List<EmprestimoModel> registrosEmprestimo) {
        this.registrosEmprestimo = registrosEmprestimo;
    }

    public EmprestimoModel getEmprestimoAtual() {
        return emprestimoAtual;
    }

    public void setEmprestimoAtual(EmprestimoModel emprestimoAtual) {
        this.emprestimoAtual = emprestimoAtual;
    }
}
