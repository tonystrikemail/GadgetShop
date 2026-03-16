/**
 * ==============
 * This is a subclass of the superclass Gadget contains additional specific 
 *   characteristics for the Mobie gadgets 
 * --------------  
 * @author Tony Strike
 * @version 1.00
 * ==============
 */
public class MP3 extends Gadget
{
    // the attribute of MP3 gadgets
    private int availableMemory;

    // constructor
    public MP3(String model, double price, int weight, String size, int callingCredit){
        //takes the attributes from super class to initialise
        super(model, price, weight, size);

        //initializing its own attributes
        this.availableMemory = availableMemory;
    }
    // accessor method
    public int getAvailableMemory(){
        return availableMemory;
    }

    @Override
    // send information in to the GUI as string
    public String toString() {
        return "MP3{model= '" 
        + getModel() + "', availableMemory=" 
        + availableMemory + "MB}";
    }
    // get the information from Gadget superclass display method
    @Override
    public void display(){
        System.out.println("MP3");
        super.display();
        System.out.println("availableMemory=" +availableMemory);
    }

    // -----------------
    // Actions methods
    // -----------------
    // method to delete muisic and therefore freed memory

    public void deleteMusic(int memoryGained){
        //check for a positive value
        if (memoryGained <= 0){    
            System.out.println("Please enter a positive value (memory in MB).");
            return;        
        }
        // increase the available memory 
        availableMemory = availableMemory + memoryGained;
        System.out.println ("You deleted " + memoryGained + " MB, and now you have " + availableMemory + " MB.");
    }
    // method to download music
    public void downloadMusic(int trackMemory){
        // check for positive value
        if (trackMemory <=10){
            System.out.println("Songs are minimum 10MB, please enter value larger than 10.");
            return;// if the value is not positive ends the method
        }
        // check if there is enough available memory
        if (availableMemory >= trackMemory){
            availableMemory = availableMemory - trackMemory;
            System.out.println("Downloded songs used " + trackMemory + " MB, and now you have " + availableMemory + " MB.");            
        }
        else {
            System.out.println("Not enough memory to download music, please delete some tracks to free space.");
        }
    }

}