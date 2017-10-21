package Service;

import Entity.CreditScore;
import Entity.LoanRequest;

public class Main {

    public static void main(String[] args) {
        //Send the loan request

        LoanRequest loan_request = new LoanRequest("123456-6543", 1234567.00, "6");

        //This credit score call has to be in the GetCreditScore module when you send the bessage to GetBanks :)
        CreditScore cs = new CreditScore("123456-6543");
        System.out.println("Received credit score: " + cs.getScore());
    }

}
