package br.uepa.models;

import com.google.gson.Gson;

import java.sql.Connection;

public abstract class DBConection {
    public abstract boolean save(Connection conn);

    public abstract String get();

    public String serialize() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }
}
