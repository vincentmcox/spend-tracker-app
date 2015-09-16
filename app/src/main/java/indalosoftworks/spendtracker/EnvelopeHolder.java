package indalosoftworks.spendtracker;

import java.security.AllPermission;
import java.util.ArrayList;

/**
 * Class that holds the references to envelopes in the system
 */
public class EnvelopeHolder {

    private ArrayList<Envelope> envelopes;

    public void spendOnEnvelope(int index, double amount)
    {
        envelopes.get(index).budgetSpend(amount);
    }

    public EnvelopeHolder()
    {
        envelopes = new ArrayList<>();
    }

    public ArrayList<Envelope> getEnvelopes() {
        return envelopes;
    }

    public void addEnvelope(Envelope newEnvelope)
    {
        envelopes.add(newEnvelope);
    }

    public Envelope getEnvelopeAt(int index) throws IndexOutOfBoundsException
    {
         return envelopes.get(index);
    }


}
