package indalosoftworks.spendtracker;

/**
 * This class represents an envelope, and is used in processing and the list adapter for display
 */
public class Envelope {
    //these function for display.
    private String budgetAmount;
    private String remainingAmount;
    private String spentAmount;


    //Actual instance variables for processing & logic.
    private double budgetNumber;
    private double remainingNumber;
    private String envelopeName;


    //Constructor
    public Envelope(String envName, double budgetNumber)
    {
        super();
        this.envelopeName = envName;
        this.setBudget(budgetNumber);
        this.remainingNumber = budgetNumber;

    }

    public String getBudgetAmount() {
        return budgetAmount;
    }

    public String getRemainingAmount() {
        return remainingAmount;
    }

    public String getSpentAmount() {
        return spentAmount;
    }

    public double getRemainingNumber() {
        return remainingNumber;
    }

    public double getBudgetNumber() {
        return budgetNumber;
    }

    public double getSpentNumber() {
        double spentNumber = budgetNumber - remainingNumber;
        return spentNumber;
    }

    public String getEnvelopeName() {
        return envelopeName;
    }


    public void setEnvelopeName(String envelopeName) {
        this.envelopeName = envelopeName;
    }

    /**
     * This method sets the budgetNumber and budgetAmount
     */
    public void setBudget(double budget)
    {
        this.budgetNumber = budget;
        this.budgetAmount = String.valueOf(budget);
    }

    /**
     * This method subtracts the spent amount from the budget and stores it as the
     * remaining number
     * @param amount double amount that is spent
     */
    public void budgetSpend(double amount)
    {
        if(remainingNumber == budgetNumber)
        {
            remainingNumber = budgetNumber - amount;
            remainingAmount = String.valueOf(remainingNumber);
        }
        else {
            this.remainingNumber -= amount;
            remainingAmount = String.valueOf(remainingNumber);
        }

    }

}
