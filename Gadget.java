/**
 * ==============
 * This class represents a general Gadget.
 * It stores the basic information that every gadget should have
 * such as model, price, weight and size.
 *
 * This class is used as a base (superclass) for other gadget types
 * like Mobile and MP3.
 *
 * The data is set through the constructor and accessed using getters.
 * The toString() method is used when displaying the object in the GUI.
 * 
 * extends - means this class inherits from another class (Gadget)
 * super - refers to the parent class (Gadget)
 * @Override - replaces a method from the parent class (Gadget class)
 * Class - blueprint for objects
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

public class Gadget {

    // =========================
    // FIELDS (ATTRIBUTES)
    // =========================
    private final String model;      // "model" is text
    private final double price;      // "price" is decimal
    private final int weight;        // "weight" is whole number in grams
    private final String size;       // "size" is text

    // =========================
    // CONSTRUCTOR
    // =========================
    public Gadget(String model, double price, int weight, String size) {
        // Assign the constructor parameters into the fields (initialisation)
        this.model = model;
        this.price = price;
        this.weight = weight;
        this.size = size;
    }

    // =========================
    // ACCESSOR METHODS (GETTERS)
    // =========================
    public String getModel() {
        return model; // Return the model text
    }

    public double getPrice() {
        return price; // Return the price (double)
    }

    public int getWeight() {
        return weight; // Return the weight in grams (int)
    }

    public String getSize() {
        return size; // Return the size text
    }

    // =========================
    // toString() - used for logging in TextArea
    // =========================
    @Override
    public String toString() {
        // Build a readable one-line summary for logs
        return "{model: '" + getModel()
        + "', price: £" + getPrice()
        + ", weight: " + getWeight() + " gr."
        + ", size: '" + getSize() + " mm";
    }

    // =========================
    // display() - prints details
    // =========================
    public void display() {
        System.out.println("Gadget");
        System.out.println("model=" + model);
        System.out.println("price=" + price);
        System.out.println("weight=" + weight);
        System.out.println("size=" + size);
    }
}
