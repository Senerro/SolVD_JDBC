package com.solvd.repairService.helpers;

public enum GlobalEnum {
    JSON("json"),
    CONSOLE("console"),
  ;private String way;
    GlobalEnum(String way)
    {
        this.way = way;
    }
}
