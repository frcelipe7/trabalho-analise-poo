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
}
