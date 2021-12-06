### Design Document 

## About our app

Our app is a delivery service app named Sapiens Delivery that allows customers who use our app to create a delivery order from different stores, each with a list of items the customer wants to buy. Our app will then pair the customer with a deliveryman who delivery the customer's order. After the order has been delivered and completed, the customer will be prompted to pay the deliveryman, and then both the deliveryman and the customer can provide a rating for each other.  

For more details, please check our specification.md file: https://git.io/JMpmy. 

## Major changes we made in Phase 2:  
The most fundamental change we made in phase 2 was the transition from a command-line text UI to an Android application. Our controllers are changed to a number of android activities ([Link to the activities](https://github.com/CSC207-UofT/course-project-java-sapiens/tree/main/app/src/main/java/com/yde/sapiensdelivery/controllers)), each of them and their layout files corresponding to a specific page in our program.
Secondly, we completed the implementation of delivery man’s side and made the app usable both to customers and delivery men. In order to achieve the real-time interaction between two different device, all information that is relevant are fetched from or saved into the database.
Also, we used Google Map’s Direction and Location API ([Link to the gateway](https://github.com/CSC207-UofT/course-project-java-sapiens/blob/main/app/src/main/java/com/yde/sapiensdelivery/gateways/GoogleMapGateway.java)) to get real-time real-world data to calculate the distance and duration of the route required to complete an order and present the results to users. 
Lastly but not the least, we modified our program according to the phase 1 feedback, including adjusting code organization, increasing test coverage, increasing use of GitHub feature, adding design patterns... and most importantly, adhering to the Clean Architecture.
Please see the rest of the file to get detailed description.


## How our project follows Clean Architecture: 
Our project follows Clean Architecture layers with Entities for the different types of Users, and the data the Users can create and manipulate. We also have a separate UI (Android Studio layouts) for display and taking in user inputs, our Controllers gets that info from the UI and decides which Use Cases to call to interact with the Entities. Controllers also calls Gateways to get and set changes to our Fire Base database. Whenever our Controller, and Gateways want to manipulate, create, and get data from an Entity, we have used Our Use Cases to do so, so that they never directly interact with any of our Entity classes and are not dependent on the Entities.

## How our project follows SOLID design principles: 
Single Responsibility Principle: In phase 0, we had a huge controller OrderSystem that does all the things, which violated the single responsibility principle. In phase 1, we separated the OrderSystem and made it become a group of smaller activities so that each of them are responsible for only one part of the original controller. This made the process easier to code and clearer to understand.

Open-Closed Principle: Through out the process of developing our project, we separated our tasks. Thus, sometimes when we are trying to use someone else’s method, there might be some inconsistency between two dependent classes. (e.g. unexpected output/input of a use case class results in non-functioning controller method.) When we encounter this, we will try to choose to overload the method with expecting input/output rather than directly change what is already written.

Liskov Substitution Principle: One example of LSP in our project is in SignInActivity. In there, we used UserManager, which is the superclass of CustomerManager and DeliveryManManager, to represent both of them. And this does not result in any errors.

Interface Segregation Principle: In out project, we have an Activity interface that every different activities implements. We tried to only include the methods that all the activities have in common (display, and getData), so that none of them has to implement a method that it does not need.

Dependency Inversion Principle: In our program the most abstract classes that directly models real life things are not dependent on any other class, the less abstract usecases are dependent on the entities and the UI and controller depend on the usecases. Also, one implementation of the principle is in the GoogleMapGateway.class. In order to let the use case classes use the gateway without breaking the Clean Architecture, we created an interface [Locator](https://github.com/CSC207-UofT/course-project-java-sapiens/blob/main/app/src/main/java/com/yde/sapiensdelivery/use_cases/Locator.java) to inverse the dependency.

## A brief description of our packaging strategies:
This project structures the source code files into packages based on the clean architecture layers, with each layer having it's own package. Helper classes and design patterns that belong to a particular layer are also inside that layer's package but has their own packages that classify them, for example, Adapters of Controllers have their own package under the "controller" package. This results in packages by layer with low cohesion modularity, but with high coupling between packages. This strategy also leads to a package for each technical group of classes. One disadvantage of using Clean Architecture by layer is that editing a feature involves editing files across different folders. But since most of our features is very losely related, we didn't need to seperate our packages by feature. 

## Design Patters We Implemented
- We used a Factory to create the appropriate use case for the two type of users using our program. (UserManager.java)
- We used Command to recreate UI activities in Android and with all managers that need to use database interactions. (DBManager.java)
- We used Template in the registration of users into database as customers.
- The Model-Controller-View design was used to allot roles for each class in the program.
- A Factory method is added in the RouteInfoFinder class to build different URLs corresponding to different choice of mode of transportation.
- We made GoogleMapGateway a façade in adherence to the Single Responsibility Principle because the two methods in the gateway, FindRouteInfo and FindCurrentLocation, uses different approach to fetch data from different APIs. Thus, we decided to make GoogleMapGateway become a façade to lower the coupling.
- We have used 3 Adapters one for each of the RecyclerViews we used for our UI. These adapters are responsible for connecting our backend ArrayList data into data that could be displayed in the UI. Through this, they can also get the positions that the Users can click on the UI, and calls an interface that the Controllers is implemented on in order to use Controller methods to switch between activities or change backend data. One example of our Adapters can be found here: https://git.io/JMpsf. 

## Summary of each group member's contributions in phase 2 and a link to their significant pull requests. 
- Kevin: Completed 4 Activities and fixed Clean Architecture violations from Phase 1. Significant pull request: https://git.io/JMpZR, this pull request included the creation to completion of two Activities that allows the User to create and modify their shopping list. Each Activity also uses a custom RecyclerView Adapter. 
- 

