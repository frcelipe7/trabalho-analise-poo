import br.uepa.models.User;
import br.uepa.models.Group;

import java.io.IOException;
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
        ArrayList<String> permissions = new ArrayList<String>();
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

    }

    public void insertData(Connection conn) {
        // criando permissoes do admin
        // Admin("can_create_user", "can_read_user", "can_update_user", "can_delete_user", "can_read_logs");
        ArrayList<String> adminPermissions = new ArrayList<String>();
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
        // criando permissoes do admin
        // SalesAnalyst("can_read_notifications", "can_download_reports", "can_read_analisys");
        ArrayList<String> salesAnalystPermissions = new ArrayList<String>();
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
