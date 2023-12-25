package com.solvd.repairService.QueryConfigurationHelper;

import java.util.List;

public class InsertValuesHelper {
    public static String values(List<String> values) {
        StringBuilder builder = new StringBuilder();
        builder.append("( ");

        for (int i = 0; i < values.size(); i++) {
            try {
                int tmp = Integer.parseInt(values.get(i));
                builder.append(tmp);
            }
            catch (Exception e) {
                builder.append("'").append(values.get(i)).append("'");
            }
            if (i + 1 < values.size())
                builder.append(", ");
        }

        builder.append(" )");
        return builder.toString();
    }
}
