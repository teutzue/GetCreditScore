package Service;

import Entity.CreditScore;
import Entity.LoanRequest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

public class GetCreditScoreService {

    //1. make the loan request object
    //2. get the credit score from the bureau
    //3. create the Json object with the loan request in it and enrich it with the credit score when you send it

    private final static String QUEUE_NAME = "credit_score_queue";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //JSONify the loan request and then add the credit score to it

        LoanRequest loan_request = new LoanRequest("123456-6543", 1234567.00, "6");
        String loan_request_Json = loan_request.toJSON();
        System.out.println("Loan request in JSON format before it was enriched: " + loan_request_Json);

        JsonParser parser = new JsonParser();
        JsonObject loan_request_complete = parser.parse(loan_request_Json).getAsJsonObject();

        CreditScore cs = new CreditScore(loan_request_complete.get("ssn").getAsString());
        loan_request_complete.addProperty("creditScore", cs.getScore());

        System.out.println("Loan request in JSON format after enrichment: " + loan_request_complete);

        //convert the object to string because this is the only way you can send a byte array
        channel.basicPublish("", QUEUE_NAME, null, loan_request_complete.toString().getBytes("UTF-8"));
        System.out.println(" [x] Sent from Get Credit Score '" + loan_request_complete + "'");

        channel.close();
        connection.close();

    }
}
