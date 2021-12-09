# Design Document 

## About Our Project

Our project is a delivery service App named Sapiens Delivery that allows customers who use our app to create a delivery order from different stores, each with a list of items the customer wants to buy. Our app will then pair the customer with a deliveryman who delivers the customer's order. After the order has been delivered and completed, the customer will be prompted to pay the delivery man, and then both the deliveryman and the customer can provide a rating for each other.   

For more details, please check our [specification.md file](https://git.io/JMpmy).

We have provided a short demonstration video as instruction to use our app! Please check it out [here](https://utoronto.zoom.us/rec/share/_3t-9gJjbJzSl3ZZHUhCMHagpzYcEBNPlmzn_VYoOJUoICk5ZBl0vNW3OZTU-I0.UY8aXI2FZbXSwUHL) (Password:=Z88a6.T=3)

Note about using our program: if you click sign in as deliveryman and the app crashes, that's due to your last know location not being recognized by the emulator you're using. Please manually open the Google Maps app on your emulator to detect your location again. 

## Major Changes We Made in Phase 2:  
The most fundamental change we made in phase 2 was the transition from a command-line text UI to an Android application. Our controllers are changed to a number of android activities ([Link to the activities](https://github.com/CSC207-UofT/course-project-java-sapiens/tree/main/app/src/main/java/com/yde/sapiensdelivery/controllers)), each of them and their layout files corresponding to a specific page in our program.

Secondly, we completed the implementation of delivery man’s side and made the app usable both to customers and delivery men. In order to achieve the real-time interaction between two different devices, all information that is relevant is fetched from or saved into the database.

Also, we used Google Map’s Direction and Location API ([Link to the gateway](https://github.com/CSC207-UofT/course-project-java-sapiens/blob/main/app/src/main/java/com/yde/sapiensdelivery/gateways/GoogleMapGateway.java)) to get real-time real-world data to calculate the distance and duration of the route required to complete an order and present the results to users. 

Last but not the least, we modified our program according to the phase 1 feedback, including adjusting code organization, increasing test coverage, increasing use of GitHub feature, adding design patterns... and most importantly, adhering to the Clean Architecture.
Please see the rest of the file to get a detailed description.


## How Our Project Follows Clean Architecture: 
Our project follows Clean Architecture layers with Entities for the different types of Users, and the data the Users can create and manipulate. We also have a separate UI (Android Studio layouts) for display and taking in user inputs, our Controllers get that info from the UI and decide which Use Cases to call to interact with the Entities. Controllers also call Gateways to get and set changes to our FireBase database. Whenever our Controller and Gateways want to manipulate, create, and get data from an Entity, we have used Our Use Cases to do so, so that they never directly interact with any of our Entity classes and are not dependent on the Entities.

- Entities:

![image](https://media.discordapp.net/attachments/888599563257122851/917544110611632168/entities.png)

- Use Cases:

![image](https://media.discordapp.net/attachments/888599563257122851/917544110179614720/use_cases.png)

- Controllers:

![image](https://media.discordapp.net/attachments/888599563257122851/917544110880096286/Controllers.png)

- Adapters:

![image](https://media.discordapp.net/attachments/888599563257122851/917544111186276442/adapters.png)

- Gateways:

![image](https://media.discordapp.net/attachments/888599563257122851/917544110389350450/Gateway.png)

## How Our Project Follows SOLID Design Principles: 
- Single Responsibility Principle: In phase 0, we had a huge controller OrderSystem that does all the things, which violated the single responsibility principle. In phase 1, we separated the OrderSystem and made it become a group of smaller activities so that each of them is responsible for only one part of the original controller. This made the process easier to code and clearer to understand.

- Open-Closed Principle: Throughout the process of developing our project, we separated our tasks. Thus, sometimes when we are trying to use someone else’s method, there might be some inconsistency between two dependent classes. (e.g. unexpected output/input of a use case class results in non-functioning controller method.) When we encounter this, we will try to choose to overload the method with expecting input/output rather than directly change what is already written.

- Liskov Substitution Principle: One example of LSP in our project is in SignInActivity. In there, we used UserManager, which is the superclass of CustomerManager and DeliveryManManager, to represent both of them. And this does not result in any errors.

- Interface Segregation Principle: In our project, we have an Activity interface that every different activity implement. We tried to only include the methods that all the activities have in common (display, and getData) so that none of them has to implement a method that it does not need.

- Dependency Inversion Principle: In our program, the most abstract classes that directly model real-life things are not dependent on any other class, the less abstract use cases are dependent on the entities and the UI and controller depend on the use cases. Also, one implementation of the principle is in the GoogleMapGateway.class. In order to let the use case classes use the gateway without breaking the Clean Architecture, we created an interface [Locator](https://github.com/CSC207-UofT/course-project-java-sapiens/blob/main/app/src/main/java/com/yde/sapiensdelivery/use_cases/Locator.java) to inverse the dependency.

## A Brief Description Of Our Packaging Strategies:
This project structures the source code files into packages based on the clean architecture layers, with each layer having its own package. Helper classes and design patterns that belong to a particular layer are also inside that layer's package but have their own packages that classify them, for example, Adapters of Controllers have their own package under the "controller" package. This results in packages by layer with low cohesion modularity, but with high coupling between packages. This strategy also leads to a package for each technical group of classes. One disadvantage of using Clean Architecture by layer is that editing a feature involves editing files across different folders. But since most of our features are very closely related, we didn't need to separate our packages by feature. 

## Design Patterns We Implemented
- In phase1, We used a Factory method in (UserManager.java) to create the appropriate use case for the two types of users using our program. 
- In phase1, We used the Command design pattern in (DBManager.java) to recreate UI activities in Android and with all managers that need to use database interactions. We have created an interface DBManager which is acting as a command. We have created DBController class which acts as a request. We have concrete command classes setData and getData implementing the DBManager interface which will do actual command processing. 
- In phase1, We used a Template in the registration of users into the database as customers. We break down the algorithm into a series of steps, turn those steps into methods, and put a series of calls to these methods inside a single template method.
- In phase1, we implemented a Model-Controller-View design was used to allot roles for each class in the program. First, we grouped UI classes as a view package. Similarly, all entities class group into a Model package, and the Controller package responds to the user input and performs interactions, and is used by UI. This strategy can lead to a package for each layer group of classes with high coupling between packages. One inconvenient thing is that editing a feature involves editing files across different folders. However, most of our features are not very closely related, we didn't need to separate our packages by feature.
- In phase2, a Factory method is added in the RouteInfoFinder class to build different URLs corresponding to a different choice of mode of transportation. [Link to Factory](https://github.com/CSC207-UofT/course-project-java-sapiens/blob/main/app/src/main/java/com/yde/sapiensdelivery/gateways/RouteInfoFinder.java) - line 69
- In phase2, We made GoogleMapGateway a façade in adherence to the Single Responsibility Principle because the two methods in the gateway, FindRouteInfo, and FindCurrentLocation, use different approaches to fetch data from different APIs. Thus, we decided to make GoogleMapGateway become a façade to lower the coupling. [Link to Locator](https://github.com/CSC207-UofT/course-project-java-sapiens/blob/main/app/src/main/java/com/yde/sapiensdelivery/use_cases/Locator.java)
- In phase2, We have used 3 Adapters one for each of the RecyclerViews we used for our UI. These adapters are responsible for connecting our backend ArrayList data into data that could be displayed in the UI. Through this, they can also get the positions that the Users can click on the UI and call an interface that the Controllers is implemented on in order to use Controller methods to switch between activities or change backend data. One example of our Adapters can be found here: https://git.io/JMpsf. 

## Our Use of GitHub Features: 
We made our own branches whenever we implemented a new feature, and the branches are merged through Pull Requests after they had been completed. We also marked errors in our code following the feedbacks given in Phase 1 through using GitHub Issues, and assigned these issues in GitHub. The Project feature has been used to keep track of the progress of a list of features we needed to implement. 

## Our Test Coverage:
We covered all the key methods in all of our backend Use Cases and Entity classes, since they are essential to our program and we want to ensure their correctness. The only methods we haven't covered are some getter and setter methods that simply set or return the value of instance attributes stored in the Use Case or Entity object. 

## Progress Report:
For more details on our progress report and individual contributions, please check this seperate [markdown file](https://github.com/CSC207-UofT/course-project-java-sapiens/blob/main/phase2/progress_report.md).

## How to Use our Application:
Once Sapiens Delivery is launched, the user has the option to either sign in or register as a new user (as either a delivery person or a customer in both cases). (You must sign in again after you have registered as a new user).

- Customer Perspective:

Once logged in, the customer can do one of three things: view their profile, place an order, or view their order status. If they have not placed an order yet, an appropriate message is displayed when they try to choose the third option. When placing an order, the customer can choose various commodities from different outlets (limited to 2 outlets and 2 commodities per outlet as of now) and add them to their order. Once they finish creating an order, they are requested to wait as a delivery person takes their order. The customer is then given the delivery person's phone number and requested to wait as the delivery person completes the delivery. Once the delivery person has completed the delivery and notified the customer, the customer gets a summary of the order as well as a total price to pay. This price is the sum of total cost of commodities bought plus delivery fees charged as distance per hour (Total distance covered calculated through google map API). The customer can then select proceed to rate the delivery person. 

A Customer can only have ONE active order at a time. The application makes adequate checks and provides messages to ensure this stays true.

- Delivery Person Perspective:

Once logged in, the delivery person can do one of three things: view their profile, take an order, or view the order status. If they have not taken an order yet, an appropriate message is displayed when they try to choose the third option. When choosing which order to take, the delivery person can view the order details and who has placed the order. Once they choose an order, they can notify the customer that they are out for delivery. Once the delivery is completed, they can once again notify the customer. Once the customer verifies completion of the order on their end, the delivery person can then rate the customer. 

A DeliveryMan can be in charge of ONE active order at a time. The application makes adequate checks and provides messages to ensure this stays true.

Note: if you are running on an AVD, we strongly suggest you, befre logging into our app, to open the "Map" app on the device and locate the phone for once. If not, there is a possibility that the app will crash when logging in as a delivery man. This is because the app need the "last know location" to login, if the AVD have never had a location before, it will cause a problem in the gateway. We didn't fix this sepcific error because we think that on a real android phone, it is extremely rare that it has never had a location.

For more detail, please check out the [demonstration video ](https://utoronto.zoom.us/rec/share/_3t-9gJjbJzSl3ZZHUhCMHagpzYcEBNPlmzn_VYoOJUoICk5ZBl0vNW3OZTU-I0.UY8aXI2FZbXSwUHL) (Password:=Z88a6.T=3)

## Future Improvements...
Although we successfully completed the complete cycle of the two users and made our program functional, there are definitely a lot of possible improvements. 
Firstly, in terms of functionality, there are various new features we can add. For example, although we used Google Map API as an information source, the only implementation now is really just about distance calculation, which we feel shame of because there are so many other data that can be utilized. With these data, we can make a route guide for the delivery man, or even an embedded interactive map! Another potential optimization is the behavior of the android “back” button, sometimes clicking on the button will result in confusing consequences especially in the process of order placement.

Secondly, as for debugging, there are indeed a lot of flaws that need to be handled. In our current program, some unexpected behavior from the user might cause a crash, such as clicking a button at a very fast speed, placing an empty order, etc. All these issues definitely need to be solved in order to present a more user-friendly application.

Lastly, there are also many improvements possible in the aspect of accessibility and the Principle of Universal Design. Such as adding more options of language and color themes to increase the program’s flexibility, making the program less tolerable to address errors, etc.

In a nutshell, despite our hard work throughout this entire semester, there are indeed a lot of improvements possible. And it is only through this project we can see the difficulty of software design. However, we are not frustrated but excited for incoming challenges, and looking forward to the world of computer science!
