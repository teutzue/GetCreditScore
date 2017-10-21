package Entity;

import com.google.gson.Gson;


public class LoanRequest
{
    private String ssn;
    private double loanAmount;
    private String loanDuration;

    public LoanRequest(String ssn, double loanAmount, String loanDuration)
    {
        this.ssn = ssn;
        this.loanAmount = loanAmount;
        this.loanDuration = loanDuration;
    }

    public double getLoanAmount()
    {
        return loanAmount;
    }

    public String getSsn()
    {
        return ssn;
    }

    public String getLoanDuration()
    {
        return loanDuration;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Ssn: " + ssn + "; LoanAmount: " + loanAmount + "; Loan Durataion: " + loanDuration;
    }

    public String toJSON() {
        // TODO Auto-generated method stub
        Gson g = new Gson();
        return g.toJson(this);
    }

}
