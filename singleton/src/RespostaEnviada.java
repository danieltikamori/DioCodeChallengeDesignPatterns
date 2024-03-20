import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    private final int id;
    private final String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

class UserManager {

    private UserManager() {}

    private static UserManager instance;

    public static synchronized UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }
}

public class RespostaEnviada {
    private static final List<User> users = new ArrayList<>();


    public static void addUser(String name) {

        int nextId = users.isEmpty() ? 1 : users.get(users.size() - 1).getId() + 1;
        users.add(new User(nextId, name));

    }

    public static void listUsers() {

        if (users.isEmpty()) {
            System.out.println("There are no registered users.");
            return;
        }
        for (User user : users) {
            System.out.println(user.getId() + " - " + user.getName());
        }

    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int quantity = scanner.nextInt();
        scanner.nextLine();  // Consumir a quebra de linha após a leitura do número

        for (int i = 1; i <= quantity; i++) {
            String name = scanner.nextLine();
            addUser(name);
        }

        listUsers();
    }
}