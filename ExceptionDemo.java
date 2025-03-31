import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionDemo {
    private double divisor;
    private double dividend;
    private double result;

    public void divide() throws InputMismatchException, ArithmeticException {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a number to store in divisor :");
        divisor = scanner.nextInt();
        System.out.println("Please enter a number to store in dividend :");
        dividend = scanner.nextInt();
        result = divisor/dividend;
        scanner.close();
    }

    public void goToDivideMethod() throws InputMismatchException, ArithmeticException {
        divide();
    }

    public void displayChoices() throws MissingExtensionException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Divide\r\n" + "2. Read from a file\r\n" + "3. Exit\r\n" + "Enter your choice :");
        int choice = scanner.nextInt();

        while(true) {
        if(choice == 1) {
            try{
                goToDivideMethod();
                System.out.println("result :" + result);
                } catch(InputMismatchException e){
                    System.out.println(" Exception " + e.getMessage() + " occurred. A number for expected, but wasn't provided");
                } catch(ArithmeticException e){
                    System.out.println(" Exception " + e.getMessage() + " occurred. Division by zero was attempted");
                } finally{
                scanner.close();
                }
        } else if(choice == 2) {
            try {
                readAFile();
            } catch(IOException e) {
                System.out.println(e);
            }
        } else if(choice == 3) {
            System.exit(0);
        }
    }
    }


    @SuppressWarnings("resource")
    public void readAFile() throws IOException, MissingExtensionException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path to the file: ");
        String path = scanner.nextLine();
        if (!path.contains(".") || !path.endsWith(".txt")) {
            throw new MissingExtensionException("File path is missing an extension");
        }
        try (FileReader fileReader = new FileReader(path);
             BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            System.out.println("Content of the file: " + line);
        }
        scanner.close();
    }
}
