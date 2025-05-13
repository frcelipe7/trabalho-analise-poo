package br.uepa.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static boolean createTables(String url) {
        String groupTable = "CREATE TABLE IF NOT EXISTS groups("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " name TEXT NOT NULL,"
                + " permissions TEXT,"
                + " joinedAt INTEGER"
                + ");";

        String userTable = "CREATE TABLE IF NOT EXISTS users("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " name TEXT NOT NULL,"
                + " birthday TEXT,"
                + " group_id INTEGER,"
                + " FOREIGN KEY (group_id) REFERENCES groups(id)"
                + ");";

        String categoryTable = "CREATE TABLE IF NOT EXISTS categories("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " name TEXT NOT NULL"
                + ");";

        String productTable = "CREATE TABLE IF NOT EXISTS products("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " name TEXT NOT NULL,"
                + " brand TEXT,"
                + " category_id INTEGER,"
                + " description TEXT,"
                + " price TEXT,"
                + " discountPrice TEXT,"
                + " quantityInStock TEXT,"
                + " isAvailable TEXT,"
                + " FOREIGN KEY (category_id) REFERENCES categories(id)"
                + ");";

        try (var conn = DriverManager.getConnection(url);
             var sttmt = conn.createStatement()) {

            sttmt.execute("PRAGMA foreign_keys = ON");

            sttmt.execute(groupTable);
            System.out.println("Tabela \"Group\" criada com sucesso!");
            sttmt.execute(userTable);
            System.out.println("Tabela \"User\" criada com sucesso!");
            sttmt.execute(categoryTable);
            System.out.println("Tabela \"Category\" criada com sucesso!");
            sttmt.execute(productTable);
            System.out.println("Tabela \"Product\" criada com sucesso!");

            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabelas: " + e.getMessage());
            return false;
        }
    }

    public static boolean connect(String url) {
        try (var conn = DriverManager.getConnection(url)) {
            System.out.println("Conexão com o banco de dados concluída!");
            return true;
        } catch (SQLException e) {
            System.out.println("Erro na conexão: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        var url = "jdbc:sqlite:src/sqlite.db";

        if (connect(url)) {
            createTables(url);
        }
    }
}
