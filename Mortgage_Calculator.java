import java.text.NumberFormat;
import java.util.Scanner;

public class Mortgage_Calculator {
    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;
    public static void main(String[] args) {
        int principal = (int)readNumber("Principal: ", 1_000, 1_000_000);
        float annualIntRate = (float)readNumber("Annual Interest Rate: ", 1, 30);
        byte years = (byte)readNumber("Perdiod (Years): ", 1, 30);
        
        printMortgage(principal, annualIntRate, years);
        printPaymentSchedule(principal, annualIntRate, years);
    }

    private static void printMortgage(int principal, float annualIntRate, byte years) {
        double mortgage = calulateMortgage(principal, annualIntRate, years);
        String formatedMortgage = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + formatedMortgage);
    }

    private static void printPaymentSchedule(int principal, float annualIntRate, byte years) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for (short month = 1; month <= years * MONTHS_IN_YEAR; month++){
            double balance = calculateBalance(principal, annualIntRate, years, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    public static double readNumber(String prompt, double min, double max){
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextFloat();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter a number between" + min + "and" + max);
            }
        return value;
    }

    public static double calculateBalance(
            int principal, 
            float annualIntRate, 
            byte years,
            short numberOfPaymentsMade
    ){
        float monthlyIntRate = annualIntRate / PERCENT / MONTHS_IN_YEAR; // monthly int rate as a decimal
        short numberOfPayments = (short)(years * MONTHS_IN_YEAR);
    
        double balance = principal
            *(Math.pow(1 + monthlyIntRate, numberOfPayments) - Math.pow(1 + monthlyIntRate, numberOfPaymentsMade))
            / (Math.pow(1 + monthlyIntRate, numberOfPayments) - 1);

        return balance;
    }

    public static double calulateMortgage(
            int principal, 
            float annualIntRate, 
            byte years) {

        float monthlyIntRate = annualIntRate / PERCENT / MONTHS_IN_YEAR; // monthly int rate as a decimal
        short numberOfPayments = (short)(years * MONTHS_IN_YEAR); // number of payments
            
        double Numerator = monthlyIntRate * (Math.pow(((float)1 + monthlyIntRate), numberOfPayments)); // calculating mortgage
        double Denominator = Math.pow(((float)1 + monthlyIntRate), numberOfPayments) - (float)1;
        double mortgage = principal * (Numerator / Denominator);

        return mortgage;
        }
}
