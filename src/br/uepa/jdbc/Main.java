package br.uepa.jdbc;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.concurrent.CompletableFuture;

public class Main {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";

    public static void loading(CompletableFuture<?> future, String message) throws IOException, InterruptedException {
        while(!future.isDone()) {
            System.out.print("\r" + message + "-");
            Thread.sleep(200);
            System.out.print("\r" + message + "\\");
            Thread.sleep(200);
            System.out.print("\r" + message + "|");
            Thread.sleep(200);
            System.out.print("\r" + message + "/");
            Thread.sleep(200);
            System.out.print("\r" + message + "-");
            Thread.sleep(200);
            System.out.print("\r" + message + "r/");
            Thread.sleep(200);
        }
    }

    public static Connection connect(String url) throws IOException, InterruptedException {
        CompletableFuture<Connection> future = CompletableFuture.supplyAsync(() -> {
            try {
                Connection conn = DriverManager.getConnection(url);
                System.out.println("\rConexão com o banco de dados concluída!");
                return conn;
            } catch (SQLException e) {
                System.out.println(RED + "Erro na conexão: " + e.getMessage() + RESET);
                return null;
            }
        });

        loading(future, "Conectando com o banco de dados  ");

        try {
            return future.get();
        } catch (Exception e) {
            System.out.println(RED + "Erro ao aguardar conexão: " + e.getMessage() + RESET);
            return null;
        }
    }

    public static boolean createTables(String url, Connection conn) throws IOException, InterruptedException {
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

        try {
            if (conn == null) return false;

            var sttmt = conn.createStatement();

            sttmt.execute("PRAGMA foreign_keys = ON");

            sttmt.execute(groupTable);
            System.out.println("\rCriando tabela: \"Group\"..................." + GREEN + "OK" + RESET);
            sttmt.execute(userTable);
            System.out.println("Criando tabela: \"User\"...................." + GREEN + "OK" + RESET);
            sttmt.execute(categoryTable);
            System.out.println("Criando tabela: \"Category\"................" + GREEN + "OK" + RESET);
            sttmt.execute(productTable);
            System.out.println("Criando tabela: \"Product\"................." + GREEN + "OK" + RESET);

            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabelas: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        var url = "jdbc:sqlite:src/sqlite.db";

        Connection conn = connect(url);
        createTables(url, conn);
    }
}
