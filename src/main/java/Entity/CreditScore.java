package Entity;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.bank.credit.web.service.CreditScoreService_PortType;
import org.bank.credit.web.service.CreditScoreService_ServiceLocator;

public class CreditScore {
    private String ssn;
    private int score;

    public CreditScore(String ssn) {
        this.ssn = ssn;
        updateScore();
    }


    /**
     * Use this to get random credit score.
     * Keep in mind that this method will override old credit score.
     *
     * @return new credit score
     */
    public int updateScore() {
        CreditScoreService_ServiceLocator csl = new CreditScoreService_ServiceLocator();
        try {

            CreditScoreService_PortType locator = csl.getCreditScoreServicePort();
            score = locator.creditScore(ssn);

        } catch (ServiceException | RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return score;
    }

    public int getScore() {
        return score;
    }

    public String getSsn() {
        return ssn;
    }

}
