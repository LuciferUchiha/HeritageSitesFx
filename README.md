# OOP2 Programming Project 2ICeng/2ICbb FS21 

## Edited By
George Rowlands

## Handing in
- Sunday, 07.06.21, 18:00
- Handing-in happens through a "Push" to the Master-Branch of your GitHub repository.

## Exercise: HeritageSites FX
Implement your own application on the basis of JavaFX according to the description on the AD.

That description is the most important one, here are the most important points as reminder:

- For the implementation use the structure of the "Application-Template"
- Usage of SceneBuilder and FXML are prohibited
- For a 4.0 you need to implement at least the following features
    - Loading of the data
    - Saving of Changes
    - Visualize all HeritageSites in a table/list
    - Editor-View
        - Editor-View always works on the selected item from the table
        - Changes lead to an *immediate* update of the table and the header
        - Changes of *hasVisited* leads to an immediate update of the Footer elements
            - Number of Countries Visited
            - Number of Places visited
            - List of Countries visited (using abbreviations)
        - Footer
            - Number of Countries Visited
            - Number of Places visited
            - List of Countries visited  
            - simple styling with CSS
    - Layout with SplitPane
    - reasonable Resizing-Management
    - Creation of a new Heritage Site
    - Deletion of a Heritage Site
    - Application-Structure
        - two Layers for Presentation-Model and View
    
## Notes
- The grading criteria are available on the AD in a PDF file
    - The defined criteria in there are authoritative
    - non compiling code will be graded with 1.0
    - projects that are not worked on independently lead to a significant mark deduction
- In the project you find the data file
    - `resources/data/heritage_sites.csv` contain the Heritage Sites to display
- It is expressly permitted and desired that you get help if necessary.
    - The "Programmierzentrum" is open and questions are gladly answered either through the integrated Issue Tracker or by mail.
    
## In case of problems with IntelliJ-Setup
Sometimes it happens that the setup of the project in IntelliJ fails or "suddenly" no longer works.
In this case you DON'T need to accept the invitation link for a second time, or re-create the project through "Checkout from Version Control" or "git clone ...".

Instead, the easiest is to re-do the IntelliJ-Setup. For this you can use the "build.gradle" which contains a complete, IDE independent project description.

Do the following steps:
- Close the open project (File -> Close Project)
- Select "OPEN"
- In the window, navigate to your project
- Select the File "build.gradle"
- In the next window select "Open as Project".
- In the next window check if Java 11 is selected
- Mark "File already exists" with YES
- ATTENTION: Click "Delete existing Project and Import"
- Wait, wait, and wait some more

If everything went well the folder "src/main/java" should be blue, and "src/test/java" should be green.

## Implemented features
- Save, read, create, delete
- Direct updates (visited is buggy but unsure why?)
- Footer with visited count etc. but buggy due to checkbox?  
- Search (The javaFx way)
- Complete styling (dark theme)
- Inline edits in table
- Custom table cell showing flags of countries (not sure why it isn't displaying all flags if multiple countries)
- Multi-Language(German and English) as agreed with props
- Popup for deleting, saving and changing language