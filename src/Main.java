import br.uepa.models.User;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;

import static br.uepa.jdbc.Main.connect;
import static br.uepa.jdbc.Main.createTables;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        var url = "jdbc:sqlite:src/sqlite.db";
        Connection conn = connect(url);
        createTables(url, conn);

        User user = new User("felipe", "10/03/2004");

        System.out.println(user);
        System.out.println(user.serialize());

        user.save(conn);
    }
}
