### Design Document 

## About our app

Our app is a delivery service app named Sapiens Delivery that allows customers who use our app to create a delivery order from different stores, each with a list of items the customer wants to buy. Our app will then pair the customer with a deliveryman who delivery the customer's order. After the order has been delivered and completed, the customer will be prompted to pay the deliveryman, and then both the deliveryman and the customer can provide a rating for each other.  

For more details, please check our specification.md file (git.io/JMpmy). 

## Major changes we made in Phase 2:  
We decided to continue with a text-based UI for phase 1 since most of our group members are still learning Android Studio. But we implemented our text UI completely differently from phase 0. We modelled our text UI based on how we plan to implement the UI in Android Studio, with different activities classes in the UI layer of our program and with the controlls being passed around from one activity to another. This way when we work in Android, we re-use most of the code we used in phase 1 for the text UI.

## How our project follows Clean Architecture: 
Our project follows Clean Architecture layers with Entities for the different types of Users, and the data the Users can create and manipulate. We also have a separate UI (Android Studio layouts) for display and taking in user inputs, our Controllers gets that info from the UI and decides which Use Cases to call to interact with the Entities. Controllers also calls Gateways to get and set changes to our Fire Base database. Whenever our Controller, and Gateways want to manipulate, create, and get data from an Entity, we have used Our Use Cases to do so, so that they never directly interact with any of our Entity classes and are not dependent on the Entities.

## How our project follows SOLID design principles: 
Single Responsibility Principle: In phase 0, we had a huge controller OrderSystem that does all the things, which violated the single responsibility principle. In phase 1, we separated the OrderSystem and made it become a group of smaller activities so that each of them are responsible for only one part of the original controller. This made the process easier to code and clearer to understand.

Open-Closed Principle: Through out the process of developing our project, we separated our tasks. Thus, sometimes when we are trying to use someone else’s method, there might be some inconsistency between two dependent classes. (e.g. unexpected output/input of a use case class results in non-functioning controller method.) When we encounter this, we will try to choose to overload the method with expecting input/output rather than directly change what is already written.

Liskov Substitution Principle: One example of LSP in our project is in SignInActivity. In there, we used UserManager, which is the superclass of CustomerManager and DeliveryManManager, to represent both of them. And this does not result in any errors.

Interface Segregation Principle: In out project, we have an Activity interface that every different activities implements. We tried to only include the methods that all the activities have in common (display, and getData), so that none of them has to implement a method that it does not need.

Dependency Inversion Principle: In our program the most abstract classes that directly models real life things are not dependent on any other class, the less abstract usecases are dependent on the entities and the UI and controller depend on the usecases.

## A brief description of which packaging strategies you considered, which you decided to use, and why. (see slide 7 from the packages slides)
This project structures the source code files into packages based on the clean architecture layers of Model Controller View. Where View = ui package, Model = entities package, and Controller= anything that interacts directly with the entities and is used by the UI. This results in packages by layer with low cohesion modularity, but with high coupling between packages. This strategy also leads to a package for each technical group of classes. One disadvantage of using Clean Architecture by layer is that editing a feature involves editing files across different folders. But since most of our features is very losely related, we didn't need to seperate our packages by feature.

## Design Patters We Implemented

- We used a Factory to create the appropriate use case for the two type of users using our program. (UserManager.java)
- We used Command to recreate UI activities in Android and with all managers that need to use database interactions. (DBManager.java)
- We used Template in the registration of users into database as customers.
- The Model-Controller-View design was used to allot roles for each class in the program.
- A Factory method is added in the RouteInfoFinder class to build different URLs corresponding to different choice of mode of transportation.
- We made GoogleMapGateway a façade in adherence to the Single Responsibility Principle because the two methods in the gateway, FindRouteInfo and FindCurrentLocation, uses different approach to fetch data from different APIs. Thus, we decided to make GoogleMapGateway become a façade to lower the coupling.
- We have used 3 Adapters one for each of the RecyclerViews we used for our UI. These adapters are responsible for connecting our backend ArrayList data into data that could be displayed in the UI. Through this, they can also get the positions that the Users can click on the UI, and calls an interface that the Controllers is implemented on in order to use Controller methods to switch between activities or change backend data. One example of our Adapters can be found here: https://git.io/JMpsf. 
