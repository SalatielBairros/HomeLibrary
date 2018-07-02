package com.dev.salatiel.interfaces;

public interface IBaseFormActivity<T extends IEntityModel> {
    boolean validate();
    void save();
    void populateFields(T model);
     T getFields();
     void getParameters();
     void loadData();
}
