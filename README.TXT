------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all they need to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE: Gadget Shop
PURPOSE OF PROJECT: University project to learn basic knowledge of the Java programming language.
VERSION or DATE: 1.00
HOW TO START THIS PROJECT: Carefully
AUTHORS: Tony Strike
USER INSTRUCTIONS:

Program 

The program should consist of the following classes (with no additional attributes or methods).


1.	The Gadget class has four attributes, which correspond to the model, the price (in pounds), the weight (in grams), the size (e.g. “71mm x 137mm x 9mm”) respectively. The price is a decimal number, the weight is an integer, and the model and the size are strings of text. Each attribute is initialised in the constructor, by being assigned the value of one of the constructor's four parameters, and each attribute has a corresponding accessor method. A display method will output (suitably annotated) the model, the price, the weight and the size

2.	The Mobile class is a subclass of the Gadget class and has just one attribute, which corresponds to the (whole) number of minutes of calling credit remaining. The attribute is initialised in the constructor by being assigned the value of one of the constructor's five parameters and it has a corresponding accessor method. The other parameters of the constructor represent the model the price, the weight and the size of the mobile phone and these four values are passed to the constructor of the Gadget class.The Mobile class has a method so that the user of the mobile can add (via a parameter) calling credit to the amount that they currently have. If the amount of credit provided by the user is greater than zero then this amount is added to the amount of credit stored in the mobile. Otherwise, a message is displayed prompting the user to enter a positive amount. There is also a method to represent the user making a phone call. The user needs to provide the phone number and the duration of the call in minutes. If there is enough credit then a message giving the phone number and duration is displayed and the remaining calling credit is reduced by the number of minutes that the call lasted. Otherwise, a message informing the user that there is insufficient credit to make the call is displayed. A method to display the details of the mobile is required. It must have the same signature as the display method in the Gadget class. It will call the method in the Gadget class to display the model, the price, the weight and the size. The number of minutes of calling credit remaining is then output suitably annotated.

3.	The MP3 class is also a subclass of the Gadget class and has just one attribute, which corresponds to the available memory. The attribute is initialised in the constructor by being assigned the value of one of the constructor's five parameters and it has a corresponding accessor method. The other parameters of the constructor represent the model, the price, the weight and the size of the MP player and these four values are passed to the constructor of the Gadget class.The MP3 class also has a method for downloading music which takes a parameter representing the amount of memory that the music will take up and, if there is sufficient available memory on the MP3 player, decreases the available memory accordingly, otherwise an appropriate error message is printed. There is also a method for deleting music which takes a parameter representing the amount of memory that the music took up and increases the available memory of the MP3 player accordingly.A method to display the details of the MP3 player is required. It must have the same signature as the display method in the Gadget class. It will call the method in the Gadget class to display the model, the price, the weight and the size. The available memory is then output suitably annotated.


4.	The GadgetShop class should store an ArrayList (not an array) of type Gadget to hold the gadgets. An example GUI is shown below: Your GUI (GadgetShop class) should contain the same components, but you are free to use a different layout if you feel that it improves the aesthetics, ease of use etc. 

Text Field Input Methods 

For each text field, write a method to read its contents and return the value. For the model, size and phone number, the value should simply be returned as a string but, for each of the other text fields, the input value should be converted into its correct numerical data type and then that value should be returned. 

There should be text fields for entering:

•	The model
•	The price
•	The weight
•	The size
•	The initial credit (when adding a mobile phone)
•	The initial available memory (when adding an MP3 player)
•	The phone number (when making a mobile phone call)
•	The duration (when making a mobile phone call)
•	The download size (when downloading music to the MP3 player)
•	The display number, which is the same as the index of the gadget in the array list


The input method for the display number is different from the input methods for the other text fields. The method first initialises the display number to -1. If the input from the user via the GUI is valid, the display number will be changed to a value that corresponds to a gadget in the array list. The input from the display number text field must therefore be tested using a try/catch statement to ensure that the gadget number entered is an integer and, if it is, that it is in the correct range. If the value entered is an integer but is not in the correct range, a suitable error message is displayed using a message dialog box. If the display number entered is not an integer then an alternative error message is displayed using a message dialog box. The method will therefore either return –1 or a value that corresponds to a gadget in the array list, depending on whether or not there was an error in the input. Any method that gets the display number should check its value and only use it if its value is not equal to –1. 

Buttons 

The GUI should have the following buttons: 

1)	Add Mobile

The model, price, weight, size and credit are input via the GUI. When this button is pressed, the methods to get the model, price, weight, size and credit are called, and these values are used to create a new object of type Mobile, which is added to the ArrayList of gadgets. 

2)	Add MP3

The model, price, weight, size and memory are input via the GUI. When this button is pressed, the methods to get the model, price, weight, size and memory are called, and these values are used to create a new object of type MP3, which is added to the ArrayList of gadgets.

3)	Clear


When this button is pressed, the text is cleared from each of the ten text fields. 

4)	Display All

When this button is pressed, the display number is printed and the method to display the details of the gadget is called, for each gadget in the array list. 

5)	Make A Call

The display number, phone number and duration are input via the GUI. When this button is pressed, the method to get the display number is called and its value is checked. If it is not equal to –1:

•	The display number is used to get the gadget from the ArrayList and cast it to Mobile.
•	The method to make a call in the Mobile class is called with the phone number and duration entered.

6)	Download Music

The display number and download size are input via the GUI. When this button is pressed, the method to get the display number is called and its value is checked. If it is not equal to –1:


•	The display number is used to get the gadget from the ArrayList and cast it to MP3.
•	The method to download music in the MP3 class is called with the download size entered.
