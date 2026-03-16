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
    private final TextArea logArea = new TextArea();

    @Override
    public void start(Stage stage){
        
        // create the window container for all the fields, buttons and info
        Pane root = new Pane();
        stage.setScene(new Scene(root,600,800));
        stage.setTitle("Tony's Gadget Shop");
        stage.show();
    }

    
    // start JavaFX application
    public static void main(String[] args){
        launch();
    }
}