import br.uepa.models.*;
import com.google.gson.Gson;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;

import static br.uepa.jdbc.Main.connect;
import static br.uepa.jdbc.Main.createTables;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        // conexões com o banco de dados
        var url = "jdbc:sqlite:src/sqlite.db";  // este é o caminho para o arquivo sqlite.db
        Connection conn = connect(url);         // criando uma instancia de conexão
        createTables(url, conn);                // criando tabelas (se não existir)

        // criando permissoes do admin
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("gerenciar o grupo de desenvolvimento");
        permissions.add("negar o pull request");
        permissions.add("fazer deploy na sexta feira");

        // criando um grupo e um usuário
        Group group = new Group("Tech lead", permissions, 2);
        User user = new User("gabryel", "12/05/2026");

        System.out.println("\nExibindo Group.serialize():");
        System.out.println(group.serialize());

        System.out.println("\nExibindo User.serialize():");
        System.out.println(user.serialize());

        //System.out.println("\nSalvando grupo:");
        //group.save(conn);           // precisa salvar o grupo antes de salvar o usuário no db

        // adicionando o usuário no grupo
        group.addUser(user);

        //System.out.println("\nSalvando usuário:");
        //user.save(conn);

        System.out.println("\nExibindo grupos:");
        group.get(conn);

        System.out.println("\nExibindo usuários:");
        user.get(conn);


        // insertData(conn);
        buy();
    }

    public static void buy() {
        System.out.println("\nRealizando venda...");
        Product product1 = new Product(1, "SSD 512gb", new BigDecimal("250"));
        Product product2 = new Product(2, "Teclado Razer", new BigDecimal("300"));

        SaleItem saleItem1 = new SaleItem(1, 1, 1, 5, product1.getPrice());
        SaleItem saleItem2 = new SaleItem(2, 1, 2, 10, product2.getPrice());

        ArrayList<SaleItem> itensList = new ArrayList<>();
        itensList.add(saleItem1);
        itensList.add(saleItem2);

        Sale sale1 = new Sale(1, itensList, 1);

        System.out.println("Venda realizada com sucesso!");

        System.out.println("\nExibindo produtos:");


        System.out.println(String.format("Sale: " + sale1));

        Gson gson2 = new Gson();
        String jsonItem1 = gson2.toJson(saleItem1);
        System.out.println(String.format("Item 1: " + jsonItem1));

        Gson gson3 = new Gson();
        String jsonItem2 = gson3.toJson(saleItem2);
        System.out.println(String.format("Item 2: " + jsonItem2));

    }

    public void insertData(Connection conn) {
        // criando permissoes do admin
        // Admin("can_create_user", "can_read_user", "can_update_user", "can_delete_user", "can_read_logs");
        ArrayList<String> adminPermissions = new ArrayList<>();
        adminPermissions.add("can_create_user");
        adminPermissions.add("can_read_user");
        adminPermissions.add("can_update_user");
        adminPermissions.add("can_delete_user");
        adminPermissions.add("can_read_logs");

        // criando um grupo e um usuário
        Group adminGroup = new Group("Admin", adminPermissions, 4);
        User user = new User("JP", "12/05/2026");

        System.out.println("\nExibindo Group.serialize():");
        System.out.println(adminGroup.serialize());

        System.out.println("\nExibindo User.serialize():");
        System.out.println(user.serialize());

        //System.out.println("\nSalvando grupo:");
        adminGroup.save(conn);           // precisa salvar o grupo antes de salvar o usuário no db

        // adicionando o usuário no grupo
        adminGroup.addUser(user);

        //System.out.println("\nSalvando usuário:");
        user.save(conn);


        /// ////////////////////////////////////////////////////////////////////
        // criando permissoes do salesAnalyst
        // SalesAnalyst("can_read_notifications", "can_download_reports", "can_read_analisys");
        ArrayList<String> salesAnalystPermissions = new ArrayList<>();
        salesAnalystPermissions.add("can_read_notifications");
        salesAnalystPermissions.add("can_download_reports");
        salesAnalystPermissions.add("can_read_analisys");

        // criando um grupo e um usuário
        Group salesAnalystGroup = new Group("SalesAnalyst", salesAnalystPermissions, 5);
        User salesAnalystUser = new User("", "12/05/2026");

        System.out.println("\nExibindo Group.serialize():");
        System.out.println(salesAnalystGroup.serialize());

        System.out.println("\nExibindo User.serialize():");
        System.out.println(salesAnalystUser.serialize());

        //System.out.println("\nSalvando grupo:");
        salesAnalystGroup.save(conn);           // precisa salvar o grupo antes de salvar o usuário no db

        // adicionando o usuário no grupo
        salesAnalystGroup.addUser(salesAnalystUser);

        //System.out.println("\nSalvando usuário:");
        salesAnalystUser.save(conn);
    }
}
