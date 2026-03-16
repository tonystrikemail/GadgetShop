/**
 * ==============
 * This is a superclass contains the common atributes of Mobile class 
 * and MP3 class, getters 
 * --------------
 * @author Tony Strike
 * @version 1.00
 * ==============
 */

public class Gadget {
    // The common atributes:
    private final String model; //create model variable
    private final double price; //create price variable
    private final int weight; //create weight variable
    private final String size; //create size variable

    // The constructor:
    public Gadget(String model, double price, int weight, String size){

        // Initialise constructor fields to the attributes    
        this.model= model;
        this.price= price;
        this.weight= weight;
        this.size= size; 
    }

    //Accessor methods:

    public String getModel() {
        return model; //Returns the model as String
    }

    public double getPrice() {
        return price; //Returns the price as double
    }

    public int getWeight() {
        return weight; //returns the weight as int in grams
    }

    public String getSize() {
        return size; // Returns the size as String
    }

    @Override
    // send information in to the GUI as string
    public String toString() {
        return "Gadget{model='" 
        + model + "', price=" 
        + price + ", weight=" 
        + weight + ", size='" 
        + size +"'}";

    }
    // method to display information in therminal window
    public void display(){ 
        System.out.println("Tony's Gadget Shop");
        System.out.println("model=" + model);
        System.out.println("price=" + price);
        System.out.println("weight=" + weight);
        System.out.println("size=" + size);
    }

}