
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
    // assessor method
    public int getCallingCredit(){
        return callingCredit;
    }

}