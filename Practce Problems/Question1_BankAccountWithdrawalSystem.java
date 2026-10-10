import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Question1_BankAccountWithdrawalSystem {
    static abstract class Account {
        protected String id;
        protected double balance;

        Account(String id, double balance) {
            this.id = id;
            this.balance = balance;
        }

        abstract boolean withdraw(double amount);

        String getId() {
            return id;
        }

        double getBalance() {
            return balance;
        }
    }

    static class SavingsAccount extends Account {
        static final double MIN_BALANCE = 1000;

        SavingsAccount(String id, double balance) {
            super(id, balance);
        }

        boolean withdraw(double amount) {
            double updatedBalance = balance - amount;
            if (updatedBalance < MIN_BALANCE) {
                return false;
            }
            balance = updatedBalance;
            return true;
        }
    }

    static class CurrentAccount extends Account {
        static final double OVERDRAFT_LIMIT = 5000;

        CurrentAccount(String id, double balance) {
            super(id, balance);
        }

        boolean withdraw(double amount) {
            double updatedBalance = balance - amount;
            if (updatedBalance < -OVERDRAFT_LIMIT) {
                return false;
            }
            balance = updatedBalance;
            return true;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Account> accounts = new HashMap<>();
        String line;

        while ((line = reader.readLine()) != null) {
            String trimmed = line.trim();
            if (trimmed.isEmpty()) {
                continue;
            }

            String[] parts = trimmed.split("\\s+");
            String command = parts[0];

            if (command.equals("Savings")) {
                String id = parts[1];
                double balance = Double.parseDouble(parts[2]);
                accounts.put(id, new SavingsAccount(id, balance));
            } else if (command.equals("Current")) {
                String id = parts[1];
                double balance = Double.parseDouble(parts[2]);
                accounts.put(id, new CurrentAccount(id, balance));
            } else if (command.equals("WITHDRAW")) {
                String id = parts[1];
                double amount = Double.parseDouble(parts[2]);
                Account account = accounts.get(id);

                if (account == null) {
                    System.out.println("Rejected: account not found");
                } else if (account.withdraw(amount)) {
                    System.out.println("Success: " + account.getId() + " balance=" + account.getBalance());
                } else {
                    System.out.println("Rejected: invalid withdrawal");
                }
            }
        }
    }
}
