# Specification (Phase 1)

- Running the project starts an Android application that the user can interact with and prompts the user to choose whether they are a customer or a delivery person. 
- - Registration: users will need to register for an account and sign in before using the project. 
    - If the users choose to create a customer account, they need to provide: name, phone number, default location.
    - If the users choose to create a delivery person account, they need to provide: name, phone number, legal identification, transportation used to deliver, and how their service is charged.


## After signed in:
- ### For customers accounts:
    - For each outlet that the customer needs items from, the customer can input a list of items.
    - Post a request with the list of items and stores, then select a delivery person based on their rating from all the delivery men who accepted the request which will mark the end of the order placement.
    - **(New)** Has the option to choose between adding the shopping list of a general store (doesn't need a location, the delivery man can find the closest store near them) or an outlet. 
    - **(New)** Can remove or add the quantities of Commodities after they are added to a ShoppingList.
    - **(New)** As the order progresses, the customer will be able to keep a track of the status of their order. 
    - For the ongoing orders, if multiple they will be sorted based on their status which are: The delivery person is on the way to get your order, they have received your order and are out for delivery, or order complete.
    - Can view a history of all the orders they've ever placed. These orders will be sorted based on their timestamp. 
    - After the delivery person delivers the order, the customer can write a comment and give a satisfaction score to this delivery person. 


- ### For delivery person accounts:
    - Browse through a list of requests and picks one to accept
    - After accepting an order, the project generates the best route (google map package) from the delivery person’s location to the destination, while passing through all the stores in the order.
    - In the process of delivery, the delivery person and the customer will be able to contact each other via the contact information as provided on the order under its details. 
    - The system sends the order information to an available delivery person and estimates the delivery fee based on the distance and time of travelling.
    - The delivery person is responsible for updating the status of the order between on the way, out for delivery, and delivered - Order complete.
    - After the transaction is done, the delivery person is able to rate and comment on the customer.

## For the outlets who want to partner:
- No UI, they are directly added into the database with location, commodities and corresponding price, proof of verification (maybe)
- Their catalogue is stored in a database (JDBC, or csv locally)
  Reference:


## Features:
- Users can choose to be a delivery person or a customer when the project starts based on suggestions made by the app using ratings and proximity.
- A best route calculator/generator.
- Customers can rate the delivery person they ordered.
- Provides a platform for delivering products.
- Calculates cost of delivery (and in the case of buying things, total cost).
- Outlets for customers to buy directly from the store.
- Has the order tracker capability.
