package indalosoftworks.spendtracker;

import android.app.Application;

/**
 * Extension of the Android Application class that allows for storing of session data and acts as
 * a single point of contact for Activities.
 */
public class SpendApp extends Application {
    // Insert some kind of datasource and database solution reference here
    EnvelopeHolder holder;

    public SpendApp()
    {
        super();
        holder = new EnvelopeHolder();
        // Test data to see how the list works
        Envelope env1 = new Envelope("Food", 35.00);
        Envelope env2 = new Envelope("Beer", 10.00);
        addEnvelope(env1);
        addEnvelope(env2);

    }

    /**
     * Adds and envelope to the EnvelopeHolder
     * @param envelope envelope to be added
     */
    public void addEnvelope(Envelope envelope)
    {
        holder.addEnvelope(envelope);

    }

    /**
     * Returns the enveloped holder held by the app
     * @return envelopeHolder
     */
    public EnvelopeHolder getHolder()
    {
        return this.holder;
    }

    /**
     * Returns the Envelope from the EnvelopHolder's ArrayList object.
     * @param index Index of the envelope
     * @return Envelope object
     * @throws IndexOutOfBoundsException
     */
    public Envelope getEnvelopeAt(int index) throws IndexOutOfBoundsException
    {
         return holder.getEnvelopeAt(index);
    }

    public void spend(int envelopeIndex, double amount)
    {
        holder.spendOnEnvelope(envelopeIndex, amount);
    }
}
