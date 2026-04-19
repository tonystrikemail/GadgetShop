/**
 * ==============
 * This class is a subclass of Gadget.
 * It represents an MP3 gadget and adds one extra attribute: available memory.
 * The action methods update the object state , and the GUI is displaying messages to the user.
 * 
 * extends - means this class inherits from another class (Gadget)
 * super - refers to the parent class (Gadget)
 * @Override - replaces a method from the parent class (Gadget class)
 * Class - blueprint for objects
 * Object - instance of a class
 * Field - variable inside a class
 * Constructor - runs when object is created
 * Method - block of code that performs an action
 * Parameter - value passed into a method
 * Return type - what a method gives back
 * private - only used inside this class
 * public - accessible from other classes
 * --------------
 * @author Tony Strike
 * @version 3.00
 * ==============
 */

public class MP3 extends Gadget
{
    // ========================= // FIELD (ATTRIBUTE) // =========================

    // this field stores the currently available memory.
    private int availableMemory;

    // ========================= //= CONSTRUCTOR =// =========================

    // This constructor creates an MP3 object. It receives the common Gadget values and the MP3-specific memory value.
    public MP3(String model, double price, int weight, String size, int availableMemory)
    {
        // Call the parent constructor to initialise shared Gadget fields.
        super(model, price, weight, size);

        // Store the MP3-specific value.
        this.availableMemory = availableMemory;
    }

    // ========================= // ACCESSOR // =========================

    // This method returns the current available memory.
    public int getAvailableMemory()
    {
        return availableMemory;
    }

    // ========================= // DOWNLOAD MUSIC // =========================

    // This method reduces available memory when music is downloaded. It returns true if the operation succeeds. It returns false if: memoryNeeded is not positive, there is not enough available memory

    public boolean downloadMusic(int memoryNeeded)
    {
        // The download size must be greater than zero.
        if (memoryNeeded <= 0)
        {
            return false;
        }

        // There must be enough available memory.
        if (availableMemory < memoryNeeded)
        {
            return false;
        }

        // Reduce available memory.
        availableMemory -= memoryNeeded;

        return true;
    }

    // ========================= //  DELETE MUSIC // =========================

    // This method increases available memory when music is deleted. It returns true if the operation succeeds. It returns false if the input value is invalid.
    // This method is not used in GadgetShop. It's been left for additional feature, no needed for now.

    public boolean deleteMusic(int memoryFreed)
    {
        // The amount freed must be positive.
        if (memoryFreed <= 0)
        {
            return false;
        }

        // Increase available memory.
        availableMemory += memoryFreed;

        return true;
    }

    // ========================= // toString() // =========================

    @Override
    public String toString()
    {
        return "Type: MP3 " + super.toString()+ "', available memory: " + availableMemory + " MB}";
    }

    // ========================= // display()  // =========================

    // This method is for direct Terminal testing.
    @Override
    public void display()
    {
        System.out.println("MP3");
        super.display();
        System.out.println("Available Memory= " + availableMemory);
    }
}
