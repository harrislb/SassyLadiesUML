# SassyLadiesUML

Who did what:

We drew out the UML diagram on paper together first. Addison then implemented the UML diagram for the design on UMLet. We pair programmed the milestone. Lexi was the driver for the first half for structuring the basics by coding SassyField, SassyMethod, and SassyClass, as well as the visitors and the DesignParser. Once these were set up, Addison became the driver for the UMLDrawer class and the testing. 


Description of Design: 
ClassDeclarationVisitor is the visitor class for visiting classes when they are declared. It sets the class name and superclass if applicable, as well as any interfaces it implements. 
ClassFieldVisitor is the visitor class for visiting fields. It sets the field’s name, privacy, and type, and adds it to the SassyClass’s field ArrayList (the SassyClass that was passed into the ClassFieldVisitor constructor).
ClassMethodVisitor is the visitor class for visiting methods. It sets the method name, privacy level, return type, and argument types. 

DesignParser is the main driver class where all visitors are created, adds a new class to the parser, and tells the UML drawer to draw the diagram.

SassyClass is the concrete implementation of a class. Six fields are recorded in this class, className, list of class methods, list of class it implements, classExtends, its fields and a boolean value recording whether the class is an interface or not. Getters and setters for these fields are also included.

SassyField is the concrete class to represent fields. Three fields are recorded in this class, fieldName, fieldType, and fieldAccess. Getter and setters for these fields are also included.
SassyMethod is the concrete class to record methods. Four fields are recorded in this class, methodName, methodReturnType, methodAccess and argTypes. Getter and setters for these fields are also included.

UMLDrawer parses the indicated classes and prints out the GraphViz code to the console. If this text is copied and then run with the executable (see instructions below), it will display the UML in a specified file. UMLDrawer contains addMethods, addFields, addClass, addExtensionArrows, and addInterfaceArrows methods to handle the different cases for parsing classes. 

To use the code:

Link the source files for the project you wish to run the code on if they are outside of the package problem.asm (For example: Lab 1-3). Then in the DesignParser class, edit the classes to contain a list of the classes you want to display the UML diagram for. Then run the DesignParser class. It will output text to the console. Copy this text into the text.dot file and save it. Then in the command line, run the dot executable with -Tpng text.dot -o output.png as parameters. Then open the output.png file to view the UML diagram.

