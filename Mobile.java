/**
 * ==============
 * This class is a subclass of Gadget.
 * It represents a Mobile gadget and adds one extra attribute: calling credit (minutes remaining).
 * The action methods update the object state,
 * and the GUI is  displaying messages to the user.
 * 
 * extends - means this class inherits from another class (Gadget)
 * super - refers to the parent class (Gadget)
 * @Override - replaces a method from the parent class (Gadget class)
 *  Class - blueprint for objects
 * Object - instance of a class
 * Field - variable inside a class
 * Constructor - runs when object is created
 * Method - block of code that performs an action
 * Parameter - value passed into a method to provide additional information
 * Return type - what a method gives back
 * private - access modifier only used inside this class
 * public - accssess modifier accessible from other classes

 * --------------
 * @author Tony Strike
 * @version 3.00
 * ==============
 */

public class Mobile extends Gadget
{
    
    // ========================= // FIELD (ATTRIBUTE) // =========================

    // This stores the number of calling minutes currently available.
    private int callingCredit;

    // ========================= //= CONSTRUCTOR =// =========================

    // This constructor creates a Mobile object. It receives the common Gadget values and the Mobile-specific credit value.
    public Mobile(String model, double price, int weight, String size, int callingCredit)
    {
        // Call the parent constructor to initialise the shared Gadget fields.
        super(model, price, weight, size);

        // Store the Mobile-specific value.
        this.callingCredit = callingCredit;
    }

    // ========================= // ACCESSOR (GETTER) // =========================

    // This method returns the current calling credit.
    public int getCallingCredit()
    {
        return callingCredit;
    }

    // =========================  // ADD CALLING CREDIT // =========================

    // This method adds extra minutes to the Mobile. It returns true if the operation succeeds. It returns false if the value is invalid.
    public boolean addCallingCredit(int minutesToAdd)
    {
        // Credit to add must be a positive value.
        if (minutesToAdd <= 0)
        {
            return false;
        }

        // Increase the stored calling credit.
        callingCredit += minutesToAdd;
        return true;
    }

    // ========================= // MAKE A CALL  // =========================

    // This method reduces the calling credit when a call is made.

    // It returns true if the call is valid and the credit is reduced. It returns false if: duration is not positive, there is not enough credit
    public boolean makeCall(int durationMinutes)
    {
        // The duration must be greater than zero.
        if (durationMinutes <= 0)
        {
            return false;
        }

        // There must be enough credit available.
        if (callingCredit < durationMinutes)
        {
            return false;
        }

        // Reduce the calling credit.
        callingCredit -= durationMinutes;

        // Report success to the caller.
        return true;
    }

    // ========================= // toString() // =========================

    @Override
    public String toString()
    {
        // Return a short one-line summary of the Mobile object.
        return "Type: Mobile " + super.toString()+ "', credit: " + callingCredit + " min.}";
    }

    // =========================// display() // =========================

    // This method can be useful for direct testing in the Terminal. It prints details to the Terminal.
    @Override
    public void display()
    {
        System.out.println("Mobile");
        super.display();
        System.out.println("Calling Credit= " + callingCredit);
    }
}
