import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Trainer trainer = new Trainer();
        SpamFilter spamFilter = new SpamFilter(trainer);

        System.out.println("Welcome to the Java Spam Filter!");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Check if an email is spam");
            System.out.println("2. Train a new spam email");
            System.out.println("3. Train a new ham (good) email");
            System.out.println("4. Exit");
            System.out.print("Enter choice (1-4): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter email content to check: ");
                    String emailContent = scanner.nextLine();
                    Email email = new Email(emailContent);
                    boolean isSpam = spamFilter.isSpam(email);
                    if (isSpam) {
                        System.out.println("Result: 🚫 This is likely SPAM.");
                    } else {
                        System.out.println("Result: ✅ This seems safe (HAM).");
                    }
                    break;
                case 2:
                    System.out.print("Enter spam email content to train: ");
                    String spamText = scanner.nextLine();
                    trainer.trainSpam(spamText);
                    System.out.println("Spam training complete!");
                    break;
                case 3:
                    System.out.print("Enter ham (good) email content to train: ");
                    String hamText = scanner.nextLine();
                    trainer.trainHam(hamText);
                    System.out.println("Ham training complete!");
                    break;
                case 4:
                    System.out.println("Thank you for using the Spam Filter. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please select 1-4.");
            }
        }
    }
}
