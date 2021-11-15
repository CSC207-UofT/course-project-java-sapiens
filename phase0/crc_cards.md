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
### Class name: OrderSystem (Changed into a group of Activities in Phase 1)
Sub-classes:  
Responsibilities:  
- Takes input from UI and identifies which command the user is trying to execute then calls the corresponding use case.

Collaborators: All use cases

### Interface: Activity  
implementations: CustomerActivity, DeleveryManActivity, OrderCompletionActivity, OrderCreationActivity, OrderStatusActivity,
RatingActivity, RegistrationActivity, ShoppingListActivity, SignInActivity
Responsibilities:  
- Representing different Activities in Android.
- display(), and getData() methods to be implemented by child.

### Class name: SignInActivity  
interface implemented: Activity
Responsibilities:  
- Representing the page for logining in.
- check user accounts and password, navigating to different homepages for customer or diliveryman.
- navigating to registration page.

### Class name: RegistrationActivity  
interface implemented: Activity
Responsibilities:  
- Representing the page for registration.
- registration.
- navigating back to SignInActivity.

### Class name: CustomerActivity  
interface implemented: Activity
Responsibilities:  
- Representing homepage for customer's view.
- distinguishing commands from customer to navigate to different activities.

### Class name: DeleveryManActivity  
interface implemented: Activity
Responsibilities:  
- Representing homepage for delivery man's view.
- distinguishing commands from delivery man to navigate to different activities.

### Class name: ShoppingListActivity  
interface implemented: Activity
Responsibilities:  
- Representing the page for shopping list creation.
- using user commands to build shoppingList.
- navigating to IOrderCreationActivity

### Class name: OrderCreationActivity  
interface implemented: Activity
Responsibilities:  
- Representing the page for order creation.
- build order according to user's choice of delivery man.
- navigating back to customer's home page.

### Class name: OrderStatusActivity  
interface implemented: Activity
Responsibilities:  
- Representing the page for order status check.
- presenting order information to customer.
- navigating back to customer's home page.

### Class name: OrderCompletionActivity  
interface implemented: Activity
Responsibilities:  
- Representing the order completion page for customer.
- presenting order completion information to customer.
- end an order.
- navigating back to customer's home page or RatingActivity.


### Class name: RatingActivity  
interface implemented: Activity
Responsibilities:  
- Representing the rating page.
- allow customer to rate and leave comment to a deliveryman.
- navigating back to customer's home page.

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
- Load data from a local file, and create corresponding entities through their controllers.  
- Read and modify the info of entities through respective managers on a local file.  

Collaborators: LoginRegistrationController, ShoppingListController




