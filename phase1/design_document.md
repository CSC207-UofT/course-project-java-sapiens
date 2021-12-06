## A description of any major design decisions your group has made (along with brief explanations of why you made them).
We decided to continue with a text-based UI for phase 1 since most of our group members are still learning Android Studio. But we implemented our text UI completely differently from phase 0. We modelled our text UI based on how we plan to implement the UI in Android Studio, with different activities classes in the UI layer of our program and with the controlls being passed around from one activity to another. This way when we work in Android, we re-use most of the code we used in phase 1 for the text UI. 

## A brief description of how your project adheres to Clean Architecture (if you notice a violation and aren't sure how to fix it, talk about that too!)
Our project follows Clean Architecture layers with Entities for the different types of Users, and the data the Users can create and manipulate. We also have usecases that uses dependency inversion to call the controller interface to save data remotely. We also have a seperate UI for display and taking in user inputs. We violated the clean architecture only a little since our UI is using the methods of Entities on rare occassions. We are not sure weither having duplicate methods inside the use cases is necessary since a lot of the methods the UI uses from the Entities is implemented in the Entities. So if we want follow clean architecture then we have to have a lot of duplicate methods which we're not sure is the best solution. 

## A brief description of how your project is consistent with the SOLID design principles (if you notice a violation and aren't sure how to fix it, talk about that too!)
Single Responsibility Principle: In phase 0, we had a huge controller OrderSystem that does all the things, which violated the single responsibility principle. In phase 1, we separated the OrderSystem and made it become a group of smaller activities so that each of them are responsible for only one part of the original controller. This made the process easier to code and clearer to understand.

Open-Closed Principle: Through out the process of developing our project, we separated our tasks. Thus, sometimes when we are trying to use someone else’s method, there might be some inconsistency between two dependent classes. (e.g. unexpected output/input of a use case class results in non-functioning controller method.) When we encounter this, we will try to choose to overload the method with expecting input/output rather than directly change what is already written.

Liskov Substitution Principle: One example of LSP in our project is in SignInActivity. In there, we used UserManager, which is the superclass of CustomerManager and DeliveryManManager, to represent both of them. And this does not result in any errors.

Interface Segregation Principle: In out project, we have an Activity interface that every different activities implements. We tried to only include the methods that all the activities have in common (display, and getData), so that none of them has to implement a method that it does not need.

Dependency Inversion Principle: In our program the most abstract classes that directly models real life things are not dependent on any other class, the less abstract usecases are dependent on the entities and the UI and controller depend on the usecases.

## A brief description of which packaging strategies you considered, which you decided to use, and why. (see slide 7 from the packages slides)
This project structures the source code files into packages based on the clean architecture layers of Model Controller View. Where View = ui package, Model = entities package, and Controller= anything that interacts directly with the entities and is used by the UI. This results in packages by layer with low cohesion modularity, but with high coupling between packages. This strategy also leads to a package for each technical group of classes. One disadvantage of using Clean Architecture by layer is that editing a feature involves editing files across different folders. But since most of our features is very losely related, we didn't need to seperate our packages by feature. 

## A summary of any design patterns your group has implemented (or plans to implement).

### Current Design Patters

- We used a Factory to create the appropriate use case for the two type of users using our program. (UserManager.java)
- We used Command to recreate UI activities in Android and with all managers that need to use database interactions. (DBManager.java)
- We used Template in the registration of users into database as customers.
- The Model-Controller-View design was used to allot roles for each class in the program.

### Future additions
- We used an Adaptor to wrap additional info to the Commodities such as a note to the delivery on how the commodity should be handled. 
- We plan to use a Builder to create different types of ShoppingLists such as for stores and for outlets. 
- we plan to implement a Memento to store the state of a ShoppingList so the customer can undo changes they make on a ShoppingList. 
