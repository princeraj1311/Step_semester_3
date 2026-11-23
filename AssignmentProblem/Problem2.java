import java.util.Scanner;

class Team {
    String name;
    int size;

    Team(String name, int size) {
        this.name = name;
        this.size = size;
    }
}

public class Problem2 {
    public static void sortTeams(Team[] teams) {
        for (int i = 1; i < teams.length; i++) {
            Team key = teams[i];
            int j = i - 1;

            while (j >= 0 && teams[j].size > key.size) {
                teams[j + 1] = teams[j];
                j--;
            }
            teams[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Team[] teams = {
            new Team("Pixel", 4),
            new Team("Byte", 2),
            new Team("Nova", 4),
            new Team("Echo", 3),
            new Team("Atom", 2)
        };

        if (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().trim().split("\\s+");
            if (parts.length >= 10 && parts.length % 2 == 0) {
                teams = new Team[parts.length / 2];
                for (int i = 0; i < teams.length; i++) {
                    teams[i] = new Team(parts[2 * i],
                            Integer.parseInt(parts[2 * i + 1].replace(",", "")));
                }
            }
        }

        sortTeams(teams);
        for (int i = 0; i < teams.length; i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(teams[i].name + "(" + teams[i].size + ")");
        }
        System.out.println();
        scanner.close();
    }
}
