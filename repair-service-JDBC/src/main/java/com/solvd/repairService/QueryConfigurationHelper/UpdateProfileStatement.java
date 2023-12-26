package com.solvd.repairService.QueryConfigurationHelper;

import com.solvd.repairService.model.CustomerProfiles;

import java.util.ArrayList;

public class UpdateProfileStatement {
    public static String get(CustomerProfiles from, CustomerProfiles to, Long id)
    {
        StringBuilder builder = new StringBuilder();
        ArrayList<String> fields = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        if (!from.nick().equals(to.nick())) {
            fields.add("nick");
            values.add(to.nick());
        }
        if (!from.phone().equals(to.phone())) {
            fields.add("phone");
            values.add(to.phone());
        }

        builder.append("UPDATE ").append(from.tableName());
        for (int i = 0; i < fields.size(); i++) {

            builder.append(" SET ").append(fields.get(i)).append(" = ").append("'").append(values.get(i)).append("'");
            if(i+1<fields.size())
                builder.append(", ");
        }
        builder.append(" WHERE id = ").append(id);
        return builder.toString();
    }
}
