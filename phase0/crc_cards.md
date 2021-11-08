# Entities:

### Class name: User (Sidharth)
Sub-classes: Customer, DeliveryMan  
Responsibilities:
- Store info (name, location, phone number, rating, comment, username, password)  

### Class name: Customer (Sidharth)
Parent class: User  
Responsibilities:  
- Store additional info (past orders)  

### Class name: DeliveryMan (Sidharth)  
Parent class: User  
Responsibilities:  
- Store additional info (legal identification, transportation used to deliver, and how their service is charged, deposited money(in case they run away))

### Class name: ShoppingList (Ansh)
Responsibilities:  
- Store total price  
- Store a Hashmap of store/location/outlet to a List of Commodities  
- Add/remove the Commodity price to the total price each time a Commodity is added/removed 

Collaborators: Customer, DeliveryMan

### Class name:  Messenger
Responsibilities:  
- Store chat history  
- Store the Customer and DeliveryMan in this chat  

Collaborators: Customer, DeliveryMan  

### Class name: Order (Nikhil)
Responsibilities:  
- Map Customer, DeliveryMan, and ShoppingList for this order.
- Store transaction detail (time to complete, money earned for our app, order status, payment method)
- Calculate the total price by adding the delivery fee to the total price of the ShoppingList

Collaborators: shopping list, customer, DeliveryMan  

### Class name: Commodity
Responsibilities:
- Stores characteristics of the commodity. (name, price)


# Use Cases:
### Class name: DBManager (interface)  
Sub-classes: UserManager, OrderManager, MessengerManager  
Responsibilities: 
- save and get methods used for DB transactions (implemented by children)   

Collaborators:  

### Class name: UserManager (Patrick)  
Sub-classes:  
Responsibilities:  
- Store a Hashmap of userID to User 
- Create Users 
- get user info 
- Check password and username of a certain user match  

Collaborators: Customer, DeliveryMan  

### Class name: OrderManager (Nikhil)
Parent-classes: DBManager
Responsibilities:  
- Create Order
- Get/set Order info  

Collaborators: Order  

### Class name: ShoppingListManager (Kevin)
Parent-classes: DBManager  
Responsibilities:  
- Store a Hashmap of userID to ShoppingList 
- Create ShoppingLists 
- Link ShoppingLists to a Customer 
- Create Commodities and add to ShoppingLists

Collaborators: ShoppingList, Commodity  

### Class name: MessengerManager
Parent-classes: DBManager  
Responsibilities:  
- Create a Messenger  
- Link a User to a Messenger  

Collaborators: Customer, DeliveryMan  

# Controller:  
### Class name: OrderSystem  
Sub-classes:  
Responsibilities:  
- Takes input from UI and identifies which command the user is trying to execute then calls the corresponding use case.

Collaborators: All use cases

# Text User Interface:
### Class name: SystemInOut (Tyner)
Sub-classes:  
Responsibilities:  
- Take inputs from the console and send outputs to the console

Collaborators: UserManager

# Gateway:
### Class name: DataGateway. 
Sub-classes:  
Responsibilities:  
- Load data from a local file, and create corresponding entities through their ui.controllers.  
- Read and modify the info of entities through respective managers on a local file.  

Collaborators: LoginRegistrationController, ShoppingListController




