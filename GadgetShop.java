/**
 * ==============
 * This is a JavaFX GUI for pane layout, text fields, buttons, information area
 * and error messages
 * --------------  
 * @author Tony Strike
 * @version 1.00
 * ==============
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

public class GadgetShop extends Application
{
    // array list containing all the gadgets in our shop
    private final ArrayList<Gadget> gadgets = new ArrayList<>();

    // log area displang output messages
    private final TextArea logArea = new TextArea();
    // set fixed width of 200px for all fields
    private static final int FIELD_WIDTH = 200;
    // set fixed 
    // create a method for creating labels with fields and add them to the pane
    private TextField createFieldWithLabel(String text, int labelX, int labelY, int fieldX, Pane root){
        //label object
        Label label = new Label(text);
        label.setLayoutX(labelX);
        label.setLayoutY(labelY);
        //field oject
        TextField field = new TextField();
        field.setLayoutX(fieldX);
        field.setLayoutY(labelY);
        field.setPrefWidth(FIELD_WIDTH);//same width to all fields
        //add the elements to the pane
        root.getChildren().addAll(label,field);
        return field;// returns the field so that we can read data
    }

    @Override
    public void start(Stage stage){

        Pane root = new Pane();
        // variables for the coordinates of the fields and labels for quick alligment
        int labelX = 20;
        int fieldX = 100;
        int startY = 20;
        int offset = 35;

        // Lables-------------
        TextField modelField = createFieldWithLabel("Model: ",labelX, startY,fieldX, root);
        TextField priceField = createFieldWithLabel("Price: ",labelX, startY + offset, fieldX,root);
        TextField weightField = createFieldWithLabel("Weight: ",labelX, startY + offset*2, fieldX,root);
        TextField sizeField = createFieldWithLabel("Size: ",labelX, startY + offset*3, fieldX,root);

        root.getChildren().addAll(
                   logArea);

        logArea.setLayoutX(20);
        logArea.setLayoutY(360);
        logArea.setPrefWidth(500);
        logArea.setPrefHeight(230);

        // creates the window container for all the fields, buttons and info

        stage.setScene(new Scene(root,600,800));
        stage.setTitle("Tony's Gadget Shop");
        stage.show();
    }

    // start JavaFX application
    public static void main(String[] args){
        launch();
    }
}