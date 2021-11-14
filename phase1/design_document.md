### A description of any major design decisions your group has made (along with brief explanations of why you made them).
We decided to continue with a text based UI for phase 1 since most of our group members are still learning Android Studio. But we implemented our text UI completely differently from phase 0. We modeled our text UI based on how we plan to implement the UI in Android Studio, with different activities classes in the UI layer of our program. This way when we work in Android, we re-use most of the code we used in phase 1 for the text UI. 

### A brief description of how your project adheres to Clean Architecture (if you notice a violation and aren't sure how to fix it, talk about that too!)
Our project follows Clean Architecture layers with Entities for the different types of Users, and the data the Users can create and manipulate. We also have usecases that uses dependency inversion to call the controller interface to save data remotely. We also have a seperate UI for display and taking 


### A brief description of how your project is consistent with the SOLID design principles (if you notice a violation and aren't sure how to fix it, talk about that too!)
Single Responsibility Principle: In phase 0, we had a huge controller OrderSystem that does all the things, which violated the single responsibility principle. In phase 1, we separated the OrderSystem and made it become a group of smaller activities so that each of them are responsible for only one part of the original controller. This made the process easier to code and clearer to understand.

Open-Closed Principle: Through out the process of developing our project, we separated our tasks. Thus, sometimes when we are trying to use someone else’s method, there might be some inconsistency between two dependent classes. (e.g. unexpected output/input of a use case class results in non-functioning controller method.) When we encounter this, we will try to choose to overload the method with expecting input/output rather than directly change what is already written.

Liskov Substitution Principle: One example of LSP in our project is in SignInActivity. In there, we used UserManager, which is the superclass of CustomerManager and DeliveryManManager, to represent both of them. And this does not result in any errors.

Interface Segregation Principle: In out project, we have an Activity interface that every different activities implements. We tried to only include the methods that all the activities have in common (display, and getData), so that none of them has to implement a method that it does not need.

### A brief description of which packaging strategies you considered, which you decided to use, and why. (see slide 7 from the packages slides)
This project structures the source code files into packages based on architecture by layer being showed: [img.png](img.png).
Each fold contains files that usually aren't closely related to each other. This results in packages by layer with low cohesion modularity, but with high coupling between packages.
This strategy also leads to a package for each technical group of classes. One disadvantage of using Clean Architecture by layer is that editing a feature involves editing files across different folders.
### A summary of any design patterns your group has implemented (or plans to implement).

