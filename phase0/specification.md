# Specification

- Running the project starts an Android application that the user can interact with and prompts the user to choose whether they are a customer or a delivery man. 
- Registration: users will need to register for an account and sign in before using the project. 
	- If the users choose to create a customer account, they need to provide: name, phone number, default location. (A list of hashmaps)
	- If the users choose to create a delivery man account, they need to provide: name, phone number, legal identification, transportation used to deliver, and how their service is charged.

## After signed in:
- ### For customers accounts:
    - For each store that the customer needs items from, the customer can input a list of items with words, descriptions, and images
    - Post a request with the list of items and stores, then select a delivery man based on their rating from all the delivery men who accepted the request. 
    - After the delivery man delivers the order, the customer can write a comment  and give a satisfaction score to this delivery man.

- ### For delivery man accounts:
    - Browse through a list of requests and picks one to accept
    - After accepting an order, the project generates the best route (google map package) from the delivery man’s location to the destination, while passing through all the stores in the order.
    - In the process of delivery, the delivery man and the customer will be able to contact each other. (Messaging service)
    - After acquiring items in each location, the delivery man is responsible for confirming with the customer. (the system sends the order information to an available delivery man and estimates the delivery fee based on the distance and time of travelling)
    - Gets a transaction UID common to customer
    - After the transaction is done, the delivery man is able to rate and comment on the customer.


## For the outlets who want to parter:
- No UI, they are directly added into the database with location, commodities and corresponding price, proof of verification (maybe)
- Their catalogue is stored in a database (JDBC, or csv locally)
Reference:


## Features: 
- Users can choose to be a delivery man or a customer when the project starts based on suggestions made by the app using ratings and proximity.
- A best route calculator/generator.
- Customers can rate the delivery man they ordered.
- Provides a platform for delivering products.
- Calculates cost of delivery (and in the case of buying things, total cost).
- Outlets for customers to buy directly from the store.
- Contact(messaging) service between client and driver.
