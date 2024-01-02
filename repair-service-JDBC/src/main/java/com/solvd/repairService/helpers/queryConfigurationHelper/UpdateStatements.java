package com.solvd.repairService.helpers.queryConfigurationHelper;

import com.solvd.repairService.model.*;

import java.util.ArrayList;

public class UpdateStatements {
    private final static ArrayList<String> fields = new ArrayList<>();
    private final static ArrayList<String> values = new ArrayList<>();

    public static String get(CustomerProfiles from, CustomerProfiles to, Long id) {
        if (!from.nick().equals(to.nick())) {
            fields.add("nick");
            values.add(to.nick());
        }
        if (!from.phone().equals(to.phone())) {
            fields.add("phone");
            values.add(to.phone());
        }
       return makeQuery(from.tableName(), id);
    }
    public static String get(ServiceCenters from, ServiceCenters to, Long id) {
        if (!from.name().equals(to.name())) {
            fields.add("name");
            values.add(to.name());
        }
        if (!from.address().equals(to.address())) {
            fields.add("address");
            values.add(to.address());
        }
        if (!from.description().equals(to.description())) {
            fields.add("description");
            values.add(to.description());
        }
        return makeQuery(from.tableName(), id);
    }

    public static String get(Users from, Users to) {
        if (!from.login().equals(to.login())) {
            fields.add("login");
            values.add(to.login());
        }
        if (!from.password().equals(to.password())) {
            fields.add("password");
            values.add(to.password());
        }
        return makeQuery(from.tableName(), from.id());
    }
    public static String get(EmployerPosts from, EmployerPosts to) {
        if (!from.role().equals(to.role())) {
            fields.add("role");
            values.add(to.role());
        }
        if (!from.description().equals(to.description())) {
            fields.add("description");
            values.add(to.description());
        }
        return makeQuery(from.tableName(), from.id());
    }
    private static String makeQuery(String tableName, Long id) {
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ").append(tableName).append(" SET ");
        for (int i = 0; i < fields.size(); i++) {
            builder.append(fields.get(i)).append(" = ").append("'").append(values.get(i)).append("'");
            if (i + 1 < fields.size())
                builder.append(", ");
        }
        builder.append(" WHERE id = ").append(id);
        fields.clear();
        values.clear();

        return builder.toString();
    }
}
