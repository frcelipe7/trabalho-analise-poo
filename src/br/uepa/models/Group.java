package br.uepa.models;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Group extends DBConection{
    private int id;
    private String name;
    private ArrayList<String> permissions;
    private String joinedAt;

    public Group(String name, ArrayList permissions, int id) {
        this.name = name;
        this.permissions = permissions;
        this.id = id;
    }

    @Override
    public boolean save(Connection conn) {

        String sql = "INSERT INTO groups (name, permissions, joinedAt) VALUES (?, ?, ?)";

        try {
            var sttmt = conn.prepareStatement(sql);
            sttmt.setQueryTimeout(30);
            sttmt.setString(1, this.name);
            sttmt.setString(2, String.format("%s", this.permissions));
            sttmt.setInt(3, 0);

            sttmt.executeUpdate();

            System.out.println("Grupo salvo com sucesso!");
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao salvar grupo: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String get(Connection conn) {
        var sql = "SELECT * FROM groups";

        try {
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery(sql);

            // Cabeçalho com quebra de linha
            System.out.printf("%-5s%-25s%-20s%n",
                    "ID",
                    "Nome",
                    "Permissões"
            );

            // Dados com quebra de linha
            while (rs.next()) {
                System.out.printf("%-5d%-25s%-20s%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("permissions")
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return "ok";
    }

    public ArrayList getPermissions() {
        return permissions;
    }

    public void setPermissions(ArrayList permissions) {
        this.permissions = permissions;
    }

    public boolean hasPermission(String permission) {
        return this.permissions.contains(permission);
    }

    public String getJoinedAt() {
        return joinedAt;
    }

    public void addUser(User user) {
        user.setGroup_id(this.id);
        this.joinedAt = String.valueOf(LocalDateTime.now());
    }
}
