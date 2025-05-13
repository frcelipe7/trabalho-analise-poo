package br.uepa.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends DBConection {
    private int id;
    private String name;
    private String birthday;
    private int group_id;

    public User(String name, String birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    @Override
    public boolean save(Connection conn) {
        String sql = "INSERT INTO users (name, birthday, group_id) VALUES (?, ?, ?)";

        try {
            var sttmt = conn.prepareStatement(sql);
            sttmt.setQueryTimeout(30);
            sttmt.setString(1, this.name);
            sttmt.setString(2, this.birthday);

            if (this.group_id == 0) {
                sttmt.setNull(3, java.sql.Types.INTEGER);
            } else {
                sttmt.setInt(3, this.group_id);
            }

            sttmt.executeUpdate();

            System.out.println("Usuário salvo com sucesso!");
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao salvar usuário: " + e.getMessage());
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }
}
