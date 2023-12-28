package com.solvd.repairService.helpers.queryConfigurationHelper;

import com.solvd.repairService.model.AbstractModel;

import java.util.List;

public class InsertValuesHelper {
    public static String values(List<String> values) {
        StringBuilder builder = new StringBuilder();
        builder.append("( ");

        for (int i = 0; i < values.size(); i++) {
            try {
                int tmp = Integer.parseInt(values.get(i));
                builder.append(tmp);
            } catch (Exception e) {
                builder.append("'").append(values.get(i)).append("'");
            }
            if (i + 1 < values.size())
                builder.append(", ");
        }

        builder.append(" )");
        return builder.toString();
    }

    public static String get(AbstractModel model, List<String> fields, List<String> values) {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(model.tableName());
        if ( fields != null) {
            if(!fields.isEmpty()) {
                builder.append("(");
                for (int i = 0; i < fields.size(); i++) {
                    builder.append(fields.get(i));
                    if (i + 1 < fields.size())
                        builder.append(", ");
                }
                builder.append(") ");
            }
        }
        builder.append(" VALUES ").append("( ");
        for (int i = 0; i < values.size(); i++) {
            try {
                int tmp = Integer.parseInt(values.get(i));
                builder.append(tmp);
            } catch (Exception e) {
                builder.append("'").append(values.get(i)).append("'");
            }
            if (i + 1 < values.size())
                builder.append(", ");
        }

        builder.append(" )");
        return builder.toString();
    }
}
