/**
 * BankAccount represents the user's bank account.
 * Data is private (encapsulation); it can only be changed through methods.
 */
public class BankAccount {

    // Private data members (Encapsulation)
    private String accountHolderName;
    private String accountNumber;
    private double balance;

    // Constructor: sets the initial values when an object is created
    public BankAccount(String accountHolderName, String accountNumber, double balance) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Getters (read-only access to private data)
    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    // Shows the current balance
    public void checkBalance() {
        System.out.printf("Your current balance is: ₹%.2f%n", balance);
    }

    // Withdraws money. Returns true if successful, false otherwise.
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount! Withdrawal amount must be greater than 0.");
            return false;
        }
        if (amount > balance) {
            System.out.println("Insufficient balance!");
            System.out.printf("Your current balance is: ₹%.2f%n", balance);
            return false;
        }
        balance = balance - amount;   // deduct the amount
        System.out.println("Withdrawal successful!");
        System.out.println("Please collect your cash.");
        System.out.printf("Remaining balance: ₹%.2f%n", balance);
        return true;
    }

    // Deposits money. Returns true if successful, false otherwise.
    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount! Deposit amount must be greater than 0.");
            return false;
        }
        balance = balance + amount;   // add the amount
        System.out.println("Deposit successful!");
        System.out.printf("Updated balance: ₹%.2f%n", balance);
        return true;
    }
}
