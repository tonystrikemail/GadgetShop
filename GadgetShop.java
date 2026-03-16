/**
 * ==============
 * This is a JavaFX GUI for pane layout, text fields, buttons, information area
 * and error messages
 * --------------  
 * @author Tony Strike
 * @version 1.01
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
    // set fixed width of 150px for all BUTTONS
    private static final int BUTTON_WIDTH = 150;
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
    //method to create buttons and add them to the pane
    private Button createButton(String text, int x, int y, Pane root){
        Button button = new Button(text);
        button.setLayoutX(x); //horisontal position
        button.setLayoutY(y); //vertical position
        button.setPrefWidth(BUTTON_WIDTH); //same width for all buttons
        root.getChildren().add(button); //add button to the pane
        return button;
    }

    @Override
    public void start(Stage stage){

        Pane root = new Pane();
        // variables for the coordinates of the fields and labels for quick alligment
        int labelX = 20;
        int fieldX = 150;
        int startY = 20;
        int offset = 35;

        // Lables -------------
        //TextField modelField = createFieldWithLabel("Model: ",labelX, startY,fieldX, root);
        //TextField priceField = createFieldWithLabel("Price: ",labelX, startY + offset, fieldX,root);
        //TextField weightField = createFieldWithLabel("Weight: ",labelX, startY + offset*2, fieldX,root);
        //TextField sizeField = createFieldWithLabel("Size: ",labelX, startY + offset*3, fieldX,root);

        //Using list and for loop to generate text fields and labels
        String[] labels = {
                "Model:",
                "Price (£):",
                "Weight (gr):",
                "Size (eg 2x4x5):",
                "Initial Credit (£):",
                "Initial Memory (MB):",
                "Phone No: ",
                "Duration:",
                "Download:",
                "Gdget Index No:",
            };
        //create list to store the labels/fields
        TextField[] fields = new TextField[labels.length];
        for (int i = 0; i < labels.length; i++){
            fields[i] = createFieldWithLabel(labels[i], labelX, startY + offset * i, fieldX, root);
        }
        TextField modelField = fields[0];
        TextField priceField = fields[1];
        TextField weightField = fields[2];
        TextField sizeField = fields[3];
        TextField creditField = fields[4];
        TextField memoryField = fields[5];
        TextField phoneNoField = fields[6];
        TextField durationField = fields[7];
        TextField downloadField = fields[8];
        TextField indexField = fields[9];
        
        // End of Labels code ---------------
        // Buttons --------------
        int buttonX = 370;    
        int buttStartY = 20;
        int buttOffset = 35;
        Button mobileButton = createButton("Add Mobile",buttonX,buttStartY + buttOffset * 4,root);
        Button mp3Button = createButton("Add MP3",buttonX,buttStartY + buttOffset * 5,root);
        Button callButton = createButton("Make a Call",buttonX,buttStartY + buttOffset * 6,root);
        Button downloadButton = createButton("Download Music",buttonX,buttStartY + buttOffset * 8,root);
        Button clearButton = createButton("Clear Fields",buttonX-350,buttStartY + buttOffset * 11,root);
        Button displayButton = createButton("Display All",buttonX,buttStartY + buttOffset * 11,root);
        // End of Buttons code --------------

        // set the log area
        root.getChildren().add(logArea);
        logArea.setLayoutX(20);
        logArea.setLayoutY(500);
        logArea.setPrefWidth(520);
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