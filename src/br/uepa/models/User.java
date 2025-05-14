package br.uepa.models;

import java.sql.Connection;
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

    @Override
    public String get(Connection conn) {
        var sql = "SELECT * FROM users";

        try {
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery(sql);

            System.out.printf("%-5s%-25s%-20s%-15s%n",
                    "ID",
                    "Nome",
                    "Data de nascimento",
                    "ID do Grupo"
            );

            while (rs.next()) {
                System.out.printf("%-5d%-25s%-20s%-15d%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("birthday"),
                        rs.getInt("group_id")
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return "ok";
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
