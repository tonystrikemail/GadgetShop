import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.util.ArrayList;

/**
 * ==============
 * This is a JavaFX GUI for Tony's Gadget Shop.
 * It uses a structured layout so the interface is more intuitive.
 *
 * Layout structure:
 * - Top: main title
 * - Centre: two side-by-side sections (Mobile and MP3) 
 * with inventory displayng all the gadgets in the shop currently 
 * - Bottom: shared buttons, exit button and main log area
 *
 * Messages:
 * - Information messages are identical in Terminal and logArea
 * - Error messages are identical in Terminal, logArea, and pop-up
 *
 * Final Mobile layout changes:
 * - Phone Number field added, with UK numbers validation method 
 * - Add Mobile button comes after Initial Credit
 * - Phone Index and Duration are used for Make a Call
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
 * static method does not belong to an object, but to its class
 *
 * --------------
 * @author Tony Strike
 * @version 3.10
 * ==============
 */

public class GadgetShop extends Application
{
    // ====================================   // DATA STORAGE   // =========================================================

    // This ArrayList stores all gadgets in the shop. Because Mobile and MP3 are subclasses of Gadget, both types can be stored in the same list.
    private final ArrayList<Gadget> gadgets = new ArrayList<Gadget>();

    // ==================================  // OUTPUT AREAS   // =========================================================

    // Main log area for general messages, actions, and errors.
    private final TextArea logArea = new TextArea();

    // Separate inventory area for Mobile gadgets only.
    private final TextArea mobileArea = new TextArea();

    // Separate inventory area for MP3 gadgets only.
    private final TextArea mp3Area = new TextArea();

    // ===============================  // CONSISTENT SIZES  // =========================================================

    // Fixed width for input fields so the forms stay aligned.
    private static final int FIELD_WIDTH = 180;

    // Fixed width for buttons so all buttons look consistent.
    private static final int BUTTON_WIDTH = 140;

    // ============================ // HELPER METHODS FOR FIELD PARSING   // =========================================================

    // This method takes a TextField, reads its text, .trim() removes extra spaces around it, and converts it into a double usig Double.parseDouble().
    private double parseDoubleField(TextField field) throws NumberFormatException
    {
        return Double.parseDouble(field.getText().trim());
    }

    // This method takes a TextField, reads its text, removes extra spaces around it, and converts it into an int.
    private int parseIntField(TextField field) throws NumberFormatException
    {
        return Integer.parseInt(field.getText().trim());
    }

    // ===================================   // MESSAGE METHODS   // =========================================================

    // This method shows a information message. The exact same text goes to: Terminal and logArea
    private void showInfoMessage(String message)
    {
        System.out.println(message);
        logArea.appendText(message + System.lineSeparator());
    }

    // This method shows an error message. The exact same text goes to: Terminal, logArea and pop-up window
    private void showErrorMessage(String message)
    {
        String finalMessage = "ERROR: " + message;

        System.out.println(finalMessage);
        logArea.appendText(finalMessage + System.lineSeparator());

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(finalMessage);
        alert.showAndWait();
    }
    // Creates a small italic styled note label.
    private Label createNoteLabel(String text)
    {
        Label note = new Label(text);
        note.setStyle("-fx-font-size: 11px; -fx-font-style: italic;");
        note.setWrapText(true);
        return note;
    }

    // ======================================  // FIELD STYLE METHODS  // =========================================================

    // This method removes any custom style from a field. We use it before validation so old red borders disappear.
    private void clearFieldStyle(TextField field)
    {
        field.setStyle("");
    }

    // This method adds a red border to a field. It helps the user see exactly which field is wrong.
    private void markFieldError(TextField field)
    {
        field.setStyle("-fx-border-color: red; -fx-border-width: 2;");
    }

    // ============================== // REUSABLE VALIDATION HELPERS  // =========================================================

    // Validates a required text field.
    private void validateRequiredText(TextField field, String fieldName, StringBuilder errors)
    {
        clearFieldStyle(field);

        if (field.getText().trim().isEmpty())
        {
            errors.append("- ").append(fieldName).append(" is required.").append("\n");
            markFieldError(field);
        }
    }

    // Validates a whole-number field.
    private void validateIntField(TextField field, String fieldName, StringBuilder errors)
    {
        clearFieldStyle(field);

        String text = field.getText().trim();

        if (text.isEmpty())
        {
            errors.append("- ").append(fieldName).append(" is required.").append("\n");
            markFieldError(field);
            return;
        }

        try
        {
            Integer.parseInt(text);
        }
        catch (NumberFormatException ex)
        {
            errors.append("- ").append(fieldName).append(" must be a valid whole number.").append("\n");
            markFieldError(field);
        }
    }

    // Validates a decimal-number field.
    private void validateDoubleField(TextField field, String fieldName, StringBuilder errors)
    {
        clearFieldStyle(field);

        String text = field.getText().trim();

        if (text.isEmpty())
        {
            errors.append("- ").append(fieldName).append(" is required.").append("\n");
            markFieldError(field);
            return;
        }

        try
        {
            Double.parseDouble(text);
        }
        catch (NumberFormatException ex)
        {
            errors.append("- ").append(fieldName).append(" must be a valid number.").append("\n");
            markFieldError(field);
        }
    }

    // ==========================   // FORM VALIDATION METHODS   // =========================================================

    // Validates the Add Mobile form.
    private String validateMobileAddForm(
    TextField modelField,
    TextField priceField,
    TextField weightField,
    TextField sizeField,
    TextField creditField)
    {
        StringBuilder errors = new StringBuilder();
        validateRequiredText(modelField, "Model", errors);
        validateDoubleField(priceField, "Price", errors);
        validateIntField(weightField, "Weight", errors);
        validateRequiredText(sizeField, "Size", errors);
        validateIntField(creditField, "Initial Credit", errors);
        return errors.toString();
    }

    // Validates the Add MP3 form.
    private String validateMP3AddForm(
    TextField modelField,
    TextField priceField,
    TextField weightField,
    TextField sizeField,
    TextField memoryField)
    {
        StringBuilder errors = new StringBuilder();
        validateRequiredText(modelField, "Model", errors);
        validateDoubleField(priceField, "Price", errors);
        validateIntField(weightField, "Weight", errors);
        validateRequiredText(sizeField, "Size", errors);
        validateIntField(memoryField, "Initial Memory", errors);
        return errors.toString();
    }

    // Validates the Make a Call form.
    private String validateMakeCallForm(
    TextField indexField,
    TextField durationField,
    TextField phoneNumberField)
    {
        StringBuilder errors = new StringBuilder();
        validateIntField(indexField, "Mobile Index", errors);
        validateIntField(durationField, "Duration", errors);
        validateRequiredText(phoneNumberField, "Phone Number", errors);
        validatePhoneNumber (phoneNumberField, errors);
        return errors.toString();
    }
    
    // ========================== // VALIDATE UK PHONE NUMBER  // ==========================

    private void validatePhoneNumber(TextField field, StringBuilder errors)
    {
        //clearFieldStyle(field);

        String number = field.getText().trim();

        // Must start with 07 and followed by 9 digits
        if (!number.matches("07\\d{9}"))
        {
            errors.append("- Phone Number must be a valid UK mobile (e.g. 07123456789).\n");
            markFieldError(field);
        }
    }

    // Validates the Download Music form.
    private String validateDownloadForm(
    TextField indexField,
    TextField downloadSizeField)
    {
        StringBuilder errors = new StringBuilder();
        validateIntField(indexField, "MP3 Index", errors);
        validateIntField(downloadSizeField, "Download Size", errors);
        return errors.toString();
    }

    // ======================// GUI HELPER METHODS // ======================

    // Creates a TextField with a consistent width.
    private TextField createField()
    {
        TextField field = new TextField();
        field.setPrefWidth(FIELD_WIDTH);
        return field;
    }

    // Creates a Button with a consistent width.
    private Button createButton(String text)
    {
        Button button = new Button(text);
        button.setPrefWidth(BUTTON_WIDTH);
        return button;
    }

    // Adds one label and one field into a GridPane row.
    private void addFieldRow(GridPane grid, int row, String labelText, TextField field)
    {
        Label label = new Label(labelText);
        grid.add(label, 0, row);
        grid.add(field, 1, row);
    }

    // Creates a styled section title.
    private Label createSectionTitle(String text)
    {
        Label title = new Label(text);
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        title.setUnderline(true);
        return title;
    }

    // Creates a styled inventory/log title.
    private Label createInventoryTitle(String text)
    {
        Label title = new Label(text);
        title.setStyle("-fx-font-size: 13px; -fx-font-weight: bold;");
        title.setUnderline(true);
        return title;
    }

    // Applies common formatting to a TextArea used for display.
    private void styleDisplayArea(TextArea area, double width, double height)
    {
        area.setEditable(false);
        area.setWrapText(true);
        area.setPrefWidth(width);
        area.setPrefHeight(height);
        area.setStyle("-fx-border-color: #888888;");
    }

    // ==============================  // INVENTORY UPDATE METHODS   //=========================================================

    // Refreshes the Mobile inventory area.
    private void updateMobileArea()
    {
        mobileArea.clear();

        boolean foundMobile = false;

        for (int i = 0; i < gadgets.size(); i++)
        {
            Gadget g = gadgets.get(i);

            if (g instanceof Mobile)
            {
                foundMobile = true;
                mobileArea.appendText("Index " + i + ": " + g + System.lineSeparator());
            }
        }

        if (!foundMobile)
        {
            mobileArea.appendText("No mobile gadgets in stock." + System.lineSeparator());
        }
    }

    // Refreshes the MP3 inventory area.
    private void updateMP3Area()
    {
        mp3Area.clear();
        boolean foundMP3 = false;

        for (int i = 0; i < gadgets.size(); i++)
        {
            Gadget g = gadgets.get(i);

            if (g instanceof MP3)
            {
                foundMP3 = true;
                mp3Area.appendText("Index " + i + ": " + g + System.lineSeparator());
            }
        }

        if (!foundMP3)
        {
            mp3Area.appendText("No MP3 gadgets in stock." + System.lineSeparator());
        }
    }

    // Refreshes both inventory areas.
    private void updateInventoryAreas()
    {
        updateMobileArea();
        updateMP3Area();
    }

    @Override
    public void start(Stage stage)
    {
        
        // ====================================// ROOT CONTAINER // =========================================================

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15));

        // ==================================   // MAIN TITLE  // =========================================================

        Label mainTitle = new Label("Tony's Gadget Shop");
        mainTitle.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        HBox titleBox = new HBox(mainTitle);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(0, 0, 15, 0));

        root.setTop(titleBox);

        // =================================== // MOBILE FIELDS  // =========================================================

        // Fields used to add a new Mobile gadget.
        TextField mobileModelField = createField();
        TextField mobilePriceField = createField();
        TextField mobileWeightField = createField();
        TextField mobileSizeField = createField();
        TextField mobileCreditField = createField();

        // Fields used to perform the Make a Call action.
        TextField mobileIndexField = createField();
        TextField phoneNumberField = createField();
        TextField durationField = createField();

        // ===================================== // MP3 FIELDS // =========================================================

        // Fields used to add a new MP3 gadget.
        TextField mp3ModelField = createField();
        TextField mp3PriceField = createField();
        TextField mp3WeightField = createField();
        TextField mp3SizeField = createField();
        TextField mp3MemoryField = createField();

        // Fields used to perform the Download Music action.
        TextField mp3IndexField = createField();
        TextField downloadSizeField = createField();

        // =================================== // MOBILE SECTION  // =========================================================

        Label mobileTitle = createSectionTitle("Mobile Gadget");

        GridPane mobileGrid = new GridPane();
        mobileGrid.setHgap(10);
        mobileGrid.setVgap(10);

        GridPane mobileCallGrid = new GridPane();
        mobileCallGrid.setHgap(10);
        mobileCallGrid.setVgap(10);

        // Main Mobile input fields
        addFieldRow(mobileGrid, 0, "Model:", mobileModelField);
        addFieldRow(mobileGrid, 1, "Price (£):", mobilePriceField);
        addFieldRow(mobileGrid, 2, "Weight (gr):", mobileWeightField);
        addFieldRow(mobileGrid, 3, "Size:", mobileSizeField);
        addFieldRow(mobileGrid, 4, "Initial Credit:", mobileCreditField);

        // Add Mobile button is placed after Initial Credit
        Button addMobileButton = createButton("Add Mobile");
        HBox addMobileButtonBox = new HBox(10, addMobileButton);

        // Note above make-a-call fields
        Label mobileNote = createNoteLabel(
                "To make a call, you need to fill the Mobile Index" + System.lineSeparator() + "Phone Number and Duration fields."
            );
        // Make a Call fields under Add Mobile
        addFieldRow(mobileCallGrid, 0, "Mobile Index:", mobileIndexField);
        addFieldRow(mobileCallGrid, 1, "Duration:", durationField);
        addFieldRow(mobileCallGrid, 2, "Phone Number:", phoneNumberField);

        // Make a Call button under the new fields
        Button makeCallButton = createButton("Make a Call");
        HBox makeCallButtonBox = new HBox(makeCallButton);

        Label mobileInventoryTitle = createInventoryTitle("MOBILE INVENTORY");
        HBox mobileInventoryTitleBox = new HBox(mobileInventoryTitle);
        mobileInventoryTitleBox.setAlignment(Pos.CENTER);

        styleDisplayArea(mobileArea, 350, 180);
        mobileArea.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(mobileArea, Priority.ALWAYS);

        VBox mobileGadgetSection = new VBox(
                10,
                mobileTitle,
                mobileGrid,
                addMobileButtonBox,
                mobileNote,
                mobileCallGrid,
                makeCallButtonBox
            );

        VBox mobileLogSection = new VBox(
                10,
                mobileInventoryTitleBox,
                mobileArea
            );
        HBox mobileSection = new HBox(20, mobileGadgetSection, mobileLogSection );

        mobileSection.setPadding(new Insets(15));
        mobileSection.setStyle(
            "-fx-border-color: #555555;" +
            "-fx-border-width: 1;" +
            "-fx-background-color: #f8f8f8;"
        );
        // Make section stretch
        HBox.setHgrow(mobileGadgetSection, Priority.ALWAYS);
        HBox.setHgrow(mobileLogSection, Priority.ALWAYS);
        HBox.setHgrow(mobileArea, Priority.ALWAYS);

        mobileGadgetSection.setMaxWidth(Double.MAX_VALUE);
        mobileLogSection.setMaxWidth(Double.MAX_VALUE);
        mobileSection.setMaxWidth(Double.MAX_VALUE);

        // ================================= // MP3 SECTION // =========================================================

        Label mp3Title = createSectionTitle("MP3 Gadget");

        GridPane mp3Grid = new GridPane();
        mp3Grid.setHgap(10);
        mp3Grid.setVgap(10);

        GridPane mp3DownloadGrid = new GridPane();
        mp3DownloadGrid.setHgap(10);
        mp3DownloadGrid.setVgap(10);

        // Main MP3 input fields
        addFieldRow(mp3Grid, 0, "Model:", mp3ModelField);
        addFieldRow(mp3Grid, 1, "Price (£):", mp3PriceField);
        addFieldRow(mp3Grid, 2, "Weight (gr):", mp3WeightField);
        addFieldRow(mp3Grid, 3, "Size:", mp3SizeField);
        addFieldRow(mp3Grid, 4, "Initial Memory:", mp3MemoryField);

        // Add MP3 button
        Button addMP3Button = createButton("Add MP3");
        HBox addMP3ButtonBox = new HBox(addMP3Button);

        // Note above download fields
        Label mp3Note = createNoteLabel(
                "To download music, you need to fill the MP3 Index" + System.lineSeparator() + "and Download Size fields."
            );

        // Download Music fields
        addFieldRow(mp3DownloadGrid, 0, "MP3 Index:", mp3IndexField);
        addFieldRow(mp3DownloadGrid, 1, "Download Size:", downloadSizeField);

        // Download Music button
        Button downloadMusicButton = createButton("Download Music");
        HBox downloadMusicButtonBox = new HBox(downloadMusicButton);

        Label mp3InventoryTitle = createInventoryTitle("MP3 INVENTORY");
        HBox mp3InventoryTitleBox = new HBox(mp3InventoryTitle);
        mp3InventoryTitleBox.setAlignment(Pos.CENTER);

        styleDisplayArea(mp3Area, 350, 180);
        mp3Area.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(mp3Area, Priority.ALWAYS);

        VBox mp3GadgetSection = new VBox(
                10,
                mp3Title,
                mp3Grid,
                addMP3ButtonBox,
                mp3Note,
                mp3DownloadGrid,
                downloadMusicButtonBox
            );

        VBox mp3LogSection = new VBox(
                10,
                mp3InventoryTitleBox,
                mp3Area
            );

        HBox mp3Section = new HBox(20, mp3GadgetSection, mp3LogSection);
        mp3Section.setPadding(new Insets(15));
        mp3Section.setStyle(
            "-fx-border-color: #555555;" +
            "-fx-border-width: 1;" +
            "-fx-background-color: #f8f8f8;"
        );

        // Make section stretch
        HBox.setHgrow(mp3GadgetSection, Priority.ALWAYS);
        HBox.setHgrow(mp3LogSection, Priority.ALWAYS);
        HBox.setHgrow(mp3Area, Priority.ALWAYS);

        mp3GadgetSection.setMaxWidth(Double.MAX_VALUE);
        mp3LogSection.setMaxWidth(Double.MAX_VALUE);
        mp3Section.setMaxWidth(Double.MAX_VALUE);
        
        // ===================================== // CENTRE AREA // =========================================================

        HBox centreBox = new HBox(20, mobileSection, mp3Section);
        HBox.setHgrow(mobileSection, Priority.ALWAYS);
        HBox.setHgrow(mp3Section, Priority.ALWAYS);

        mobileSection.setMaxWidth(Double.MAX_VALUE);
        mp3Section.setMaxWidth(Double.MAX_VALUE);

        root.setCenter(centreBox);

        // ==================================== // SHARED BUTTONS AND LOG AREA // =========================================================

        Button clearFieldsButton = createButton("Clear Fields");
        Button displayAllButton = createButton("Display All");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        //Create a Exit button
        Button exitButton = createButton("Exit");
        exitButton.setStyle("-fx-background-color: #cc4444; -fx-text-fill: white;");

        HBox commonButtonBox = new HBox(
                10, 
                clearFieldsButton,
                displayAllButton,
                spacer,
                exitButton
            );
        commonButtonBox.setAlignment(Pos.CENTER_LEFT);

        Label logTitle = createInventoryTitle("SYSTEM LOG");
        HBox logTitleBox = new HBox(logTitle);
        logTitleBox.setAlignment(Pos.CENTER);

        styleDisplayArea(logArea, 760, 170);

        VBox bottomSection = new VBox(10, commonButtonBox, logTitleBox, logArea);
        bottomSection.setPadding(new Insets(15, 0, 0, 0));

        root.setBottom(bottomSection);

        // ================================= // INITIAL INVENTORY DISPLAY  // =========================================================

        updateInventoryAreas();

        // ================================== // BUTTON ACTIONS  // =========================================================

        // ------------------------- // ADD MOBILE // -------------------------
        
        addMobileButton.setOnAction(e -> {

                    String validationErrors = validateMobileAddForm(
                            mobileModelField,
                            mobilePriceField,
                            mobileWeightField,
                            mobileSizeField,
                            mobileCreditField
                        );

                    if (!validationErrors.isEmpty())
                    {
                        showErrorMessage("Cannot add Mobile:\n" + validationErrors);
                        return;
                    }

                    String model = mobileModelField.getText().trim();
                    double price = parseDoubleField(mobilePriceField);
                    int weight = parseIntField(mobileWeightField);
                    String size = mobileSizeField.getText().trim();
                    int credit = parseIntField(mobileCreditField);

                    Mobile m = new Mobile(model, price, weight, size, credit);
                    gadgets.add(m);

                    updateInventoryAreas();

                    showInfoMessage("Added Gadget at index " + (gadgets.size() - 1) + ": " + m);
            });

        // ------------------------- // MAKE A CALL   // -------------------------
        
        makeCallButton.setOnAction(e -> {

                    String validationErrors = validateMakeCallForm(
                            mobileIndexField,
                            durationField,
                            phoneNumberField
                        );

                    if (!validationErrors.isEmpty())
                    {
                        showErrorMessage("Cannot make a call:\n" + validationErrors);
                        return;
                    }

                    int index = parseIntField(mobileIndexField);
                    int duration = parseIntField(durationField);
                    String phoneNumber = phoneNumberField.getText().trim();

                    clearFieldStyle(mobileIndexField);
                    clearFieldStyle(durationField);

                    // Check whether the index exists
                    if (index < 0 || index >= gadgets.size())
                    {
                        markFieldError(mobileIndexField);
                        showErrorMessage("Cannot make a call:\n- Mobile Index does not exist in the inventory.");
                        return;
                    }

                    // Check whether the selected gadget is actually a Mobile
                    if (!(gadgets.get(index) instanceof Mobile))
                    {
                        markFieldError(mobileIndexField);
                        showErrorMessage("Cannot make a call:\n- The selected index is not a Mobile gadget.");
                        return;
                    }

                    Mobile selectedMobile = (Mobile) gadgets.get(index);

                    // Check duration is positive
                    if (duration <= 0)
                    {
                        markFieldError(durationField);
                        showErrorMessage("Cannot make a call:\n- Duration must be greater than zero.");
                        return;
                    }

                    // Check enough credit exists
                    if (duration > selectedMobile.getCallingCredit())
                    {
                        markFieldError(durationField);
                        showErrorMessage("Cannot make a call:\n- Duration is greater than the available calling credit.");
                        return;
                    }

                    // Make the call
                    boolean success = selectedMobile.makeCall(duration);

                    if (!success)
                    {
                        showErrorMessage("Cannot make a call:\n- The call could not be completed.");
                        return;
                    }

                    updateInventoryAreas();

                    showInfoMessage(
                        "Calling " + phoneNumber +
                        " from Mobile at index " + index +
                        " for " + duration +
                        " minutes. Remaining credit: " + selectedMobile.getCallingCredit()
                    );
            });

        // -------------------------   // ADD MP3  // -------------------------
        
        addMP3Button.setOnAction(e -> {

                    String validationErrors = validateMP3AddForm(
                            mp3ModelField,
                            mp3PriceField,
                            mp3WeightField,
                            mp3SizeField,
                            mp3MemoryField
                        );

                    if (!validationErrors.isEmpty())
                    {
                        showErrorMessage("Cannot add MP3:\n" + validationErrors);
                        return;
                    }

                    String model = mp3ModelField.getText().trim();
                    double price = parseDoubleField(mp3PriceField);
                    int weight = parseIntField(mp3WeightField);
                    String size = mp3SizeField.getText().trim();
                    int memory = parseIntField(mp3MemoryField);

                    MP3 p = new MP3(model, price, weight, size, memory);
                    gadgets.add(p);

                    updateInventoryAreas();

                    showInfoMessage("Added Gadget at index " + (gadgets.size() - 1) + ": " + p);
            });

        // -------------------------// DOWNLOAD MUSIC // -------------------------
        
        downloadMusicButton.setOnAction(e -> {

                    String validationErrors = validateDownloadForm(
                            mp3IndexField,
                            downloadSizeField
                        );

                    if (!validationErrors.isEmpty())
                    {
                        showErrorMessage("Cannot download music:\n" + validationErrors);
                        return;
                    }

                    int index = parseIntField(mp3IndexField);
                    int downloadSize = parseIntField(downloadSizeField);

                    clearFieldStyle(mp3IndexField);
                    clearFieldStyle(downloadSizeField);

                    // Check whether the index exists
                    if (index < 0 || index >= gadgets.size())
                    {
                        markFieldError(mp3IndexField);
                        showErrorMessage("Cannot download music:\n- MP3 Index does not exist in the inventory.");
                        return;
                    }

                    // Check whether the selected gadget is really an MP3
                    if (!(gadgets.get(index) instanceof MP3))
                    {
                        markFieldError(mp3IndexField);
                        showErrorMessage("Cannot download music:\n- The selected index is not an MP3 gadget.");
                        return;
                    }

                    MP3 selectedMP3 = (MP3) gadgets.get(index);

                    // Check download size is positive
                    if (downloadSize <= 0)
                    {
                        markFieldError(downloadSizeField);
                        showErrorMessage("Cannot download music:\n- Download Size must be greater than zero.");
                        return;
                    }

                    // Check enough memory exists
                    if (downloadSize > selectedMP3.getAvailableMemory())
                    {
                        markFieldError(downloadSizeField);
                        showErrorMessage("Cannot download music:\n- Download Size is greater than the available memory.");
                        return;
                    }

                    boolean success = selectedMP3.downloadMusic(downloadSize);

                    if (!success)
                    {
                        showErrorMessage("Cannot download music:\n- The download could not be completed.");
                        return;
                    }

                    updateInventoryAreas();

                    showInfoMessage(
                        "Music downloaded to MP3 at index " + index +
                        " using " + downloadSize +
                        " memory units. Remaining memory: " + selectedMP3.getAvailableMemory()
                    );
            });

        // ------------------------- // CLEAR FIELDS  // -------------------------
        
        clearFieldsButton.setOnAction(e -> {

                    // Clear Mobile fields
                    mobileModelField.clear();
                    mobilePriceField.clear();
                    mobileWeightField.clear();
                    mobileSizeField.clear();
                    mobileCreditField.clear();
                    mobileIndexField.clear();
                    durationField.clear();
                    phoneNumberField.clear();

                    // Clear MP3 fields
                    mp3ModelField.clear();
                    mp3PriceField.clear();
                    mp3WeightField.clear();
                    mp3SizeField.clear();
                    mp3MemoryField.clear();
                    mp3IndexField.clear();
                    downloadSizeField.clear();

                    // Clear field styles mobile
                    clearFieldStyle(mobileModelField);
                    clearFieldStyle(mobilePriceField);
                    clearFieldStyle(mobileWeightField);
                    clearFieldStyle(mobileSizeField);
                    clearFieldStyle(mobileCreditField);
                    clearFieldStyle(mobileIndexField);
                    clearFieldStyle(durationField);
                    clearFieldStyle(phoneNumberField);

                    // Clear field styles mp3
                    clearFieldStyle(mp3ModelField);
                    clearFieldStyle(mp3PriceField);
                    clearFieldStyle(mp3WeightField);
                    clearFieldStyle(mp3SizeField);
                    clearFieldStyle(mp3MemoryField);
                    clearFieldStyle(mp3IndexField);
                    clearFieldStyle(downloadSizeField);

                    logArea.clear();

                    showInfoMessage("All input fields have been cleared.");
            });

        // -------------------------  // DISPLAY ALL  // -------------------------
        
        displayAllButton.setOnAction(e -> {

                    if (gadgets.isEmpty())
                    {
                        showInfoMessage("There are no gadgets in the shop.");
                        return;
                    }

                    showInfoMessage("Displaying all gadgets:");

                    for (int i = 0; i < gadgets.size(); i++)
                    {
                        Gadget g = gadgets.get(i);
                        showInfoMessage("Index " + i + ": " + g);
                    }
            });
        
            // ------------------------- // Exit the application   // -------------------------
        
            exitButton.setOnAction(e -> {
                    System.exit(0);
            });
        // ===============================  // STAGE / SCENE  // =========================================================

        Scene scene = new Scene(root, 1460, 760);
        stage.setScene(scene);
        stage.setTitle("Tony's Gadget Shop");
        stage.show();
    }

    // Main method launches the JavaFX application.
    public static void main(String[] args)
    {
        launch();
    }
}
