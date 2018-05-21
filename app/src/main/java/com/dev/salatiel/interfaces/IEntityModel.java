package com.dev.salatiel.interfaces;

import java.util.List;

public interface IEntityModel {
    String getTableName();
    int get_id();
    String CreateTable();
    String DropTable();
    List<String> getValues();
    List<String> getColumns();
}
