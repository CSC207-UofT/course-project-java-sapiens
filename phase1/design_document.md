### A description of any major design decisions your group has made (along with brief explanations of why you made them).
We decided to continue with a text based UI for phase 1 since most of our group members are still learning Android Studio. But we implemented our text UI completely differently from phase 0. We modeled our text UI based on how we plan to implement the UI in Android Studio, with different activities classes in the UI layer of our program. This way when we work in Android, we re-use most of the code we used in phase 1 for the text UI. 

### A brief description of how your project adheres to Clean Architecture (if you notice a violation and aren't sure how to fix it, talk about that too!)
Our project follows Clean Architecture layers with Entities for the different types of Users, and the data the Users can create and manipulate. We also have usecases that uses dependency inversion to call the controller interface to save data remotely. We also have a seperate UI for display and taking 


### A brief description of how your project is consistent with the SOLID design principles (if you notice a violation and aren't sure how to fix it, talk about that too!)

### A brief description of which packaging strategies you considered, which you decided to use, and why. (see slide 7 from the packages slides)
This project structures the source code files into packages based on architecture by layer being showed: [img.png](img.png). 
Each fold contains files that usually aren't closely related to each other. This results in packages by layer with low cohesion modularity, but with high coupling between packages.
This strategy also leads to a package for each technical group of classes. One disadvantage of using Clean Architecture by layer is that editing a feature involves editing files across different folders.
### A summary of any design patterns your group has implemented (or plans to implement).

