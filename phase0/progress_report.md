# Progress Report

## Summary of specification: 
Running the project that allows the user to interact with the login system firstly. While running, the order system prints a list of prompts where customers can repeatedly command that creating a shopping list order, choosing one of the available delivery men, and view or cancel the order at the end. In terms of delivery men, they wait for an order, then view the order information and use a message application to interact with the customer about the estimated time and fees. After the delivery man has finished the order, the customer writes service feedback.

## Summary of CRC model:
We have entities for the users of our program, the shopping list and order that customers can make, and commodities they want to have delivered. The information about each entity is stored in its object’s private variables. We also have use cases to create, set, and manipulate the entities. To interact with the user we have a text-based user interface for now, which takes in inputs and calls the controller to use the use cases and returns different outputs. To store and read data we have a gateway that stores the information in the use cases.

## Summary of scenario walk-through:
We consider the scenario in which the user(Customer) logs into their account, chooses a store for the items they want to buy, gives information about the items, i.e., their name and price, and selects the delivery person they want to complete this service and transaction. It goes over the control flow by the crc cards from the ui to controllers, use cases, and entities, thus completing one run-through of the application.

## Summary of skeleton program:
The transfer of control works its way from the Main.class, the entry of the program, which then creates a SystemInOut.class for i/o and a OrderSystem.class which serves as a controller, taking in the input and calling the classes in controllers.use_cases as required.

The controllers.use_cases classes are managers. These classes serve as factories for core entities such as Users ( DeliveryMan and Customers as per required ), Outlets, Commodities and ShoppingLists when an Order is being created. Since our application is intended to run on Android and serve live users, we have to rely on a database. All Managers thus implement DBManager.class that provides methods to store a key (Unique identification) and value (required object) onto the database, and retrieve as required.

The Database link is currently not implemented but will be connected via the class in gateways package, DataGateway.

Now, The Managers hand the transfer of control (via database) to the entities, which can be edited and read as required by the managers. Thus, the controllers do not directly interact with the entities and the application has a well-defined layered architecture.

## Questions our group has:

* We plan on transforming our app into an Android application. In which phase should we start going about it.
* Will Firebase as a NoSQL database be a good idea for us to implement? With some SQL knowledge, would Firebase be easy to adapt to in time?
* Are there any design patterns (besides factory) that can be implemented based on the current phase 0 idea of how classes and their relations look?

## Blocks we faced or may face:
* Understanding how controllers are used and supposed to be implemented.
* Assigning functionality to Managers and Entities as per what best suits their functionality.

## Each member’s contributions:
- Kevin: ShoppingListManager class and JUnit tests
- Patrick: UserManager class
- Nikhil: DBManager.class, OrderManager.class, Order.class
- Sidharth Sachdev: User, Customer, DeliveryMan, Scenario Walkthrough, assisted with the ui.
- Tyner: Main.java, SystemInOut class, OrderSystem class.
- Ansh: ShoppingList class
