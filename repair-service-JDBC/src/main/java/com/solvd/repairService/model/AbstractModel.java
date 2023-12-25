package com.solvd.repairService.model;

import java.util.ArrayList;

public class AbstractModel {
    private Long id;
    private final String tableName;
    private final Class<?> clazz;

    public AbstractModel(Long id, Class<?> clazz) {
        this.id = id;
        this.clazz = clazz;
        this.tableName = classTransform(clazz);
    }

    private String classTransform(Class<?> clazz) {
        var array = clazz.getSimpleName().toCharArray();
        var charList = new ArrayList<Character>();
        charList.add(Character.toLowerCase(array[0]));
        for (int i = 1; i < array.length-1; i++) {
            if(array[i] != Character.toUpperCase(array[i]))
                charList.add(array[i]);
            else {
                charList.add('_');
                charList.add(Character.toLowerCase(array[i]));
            }
        }

        charList.add(array[array.length-1]);

        StringBuilder builder = new StringBuilder();
        for (var element:charList) {
            builder.append(element);
        }
        return builder.toString();

    }

    public Class<?> clazz() {
        return this.clazz;
    }

    public String tableName() {
        return this.tableName;
    }

    public Long id() {
        return id;
    }

    public void id(Long id) {
        this.id = id;
    }
}
