/**
 * ==============
 * This is a subclass of the superclass Gadget contains additional specific 
 *   characteristics for the Mobie gadgets 
 * --------------  
 * @author Tony Strike
 * @version 1.00
 * ==============
 */
public class Mobile extends Gadget 
{
    // the attribute of Mobile gadgets
    private int callingCredit;

    // constructor
    public Mobile(String model, double price, int weight, String size, int callingCredit){
        //takes the attributes from super class to initialise
        super(model, price, weight, size);

        //initializing its own attributes
        this.callingCredit = callingCredit;
    }
    // accessor method
    public int getCallingCredit(){
        return callingCredit;
    }

    @Override
    // send information in to the GUI as string
    public String toString() {
        return "Mobile{model= '" 
        + getModel() + "', credit=" 
        + callingCredit + "mins}";
    }
    // get the information from Gadget superclass display method
    @Override
    public void display(){
        System.out.println("Mobile");
        super.display();
        System.out.println("callingCredit=" +callingCredit);
    }

    // -----------------
    // Actions methods
    // -----------------
    // method to add calling credit

    public void addCallingCredit(int addMinutes){
        //check for a positive value and if true add calling credit
        if (addMinutes > 0){
            callingCredit = callingCredit + addMinutes;
            System.out.println("Added additional credit: " + addMinutes + " minutes.");
        }
        else{
            System.out.println("Credit is in minutes, please enter positive value.");
        }
    }
    // method to make call and reduce the available credit
    public void makeCall(String phoneNumber, int durationTime){
        // check for positive value
        if (durationTime <=0){
            System.out.println("Call duration is in minutes, please enter positive value.");
            return;// if the value is not positive ends the method
        }
        // check if there is enough credit
        if (callingCredit >= durationTime){
            System.out.println("Calling " + phoneNumber + " for " + durationTime + " minutes.");
            callingCredit = callingCredit - durationTime; // reduce the cerdit
        }
        else {
            System.out.println("Not enough credit to make this call, please add more minutes.");
        }
    }

}