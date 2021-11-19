# Scenario Walkthrough

Upon running the main function, the user(customer) is asked to enter their login details, i.e., their username and
password. For Phase 0, we have hard coded the sample username and password which can be found under the documentation 
of the Main.java file. The user can enter command based on what they want to achieve. They can press help if unsure of 
the commands. 'help' then gives a list of commands available for the user including place order which is our main 
functionality of the app. In this scenario, we will be going through the flow of the code if the user chooses the 
function 'place order.'

The main method uses the SystemInOut under the ui folder to accept the user input which then hands it to the OrderSystem
controller which gives meaning to these inputs from the user and decides what is to be called and to which use case
the control goes. It also takes in the order. Upon typing the command 'place order', the user is asked to enter a location from which they want 
to get their commodity. Say in this scenario we choose 'Walmart'. The user is then asked to add the commodities and
their corresponding price that they'd like to order from Walmart. Suppose we had 3 items we wanted to buy - 
'Banana', 'Playstation', and 'Life', the function addComs, upon entering each of the item,calls and passes the flow to the
ShoppingListManager which is a use case. It is responsible for creating ShoppingLists, linking them to the customer, 
and adding commodities to the shopping list. Each item ordered is made into a Commodity object which is an entity. It 
stores the information of the item, i.e., its name and price. The ShoppingListManager calls the entity ShoppingList 
which has the feature to add and remove commodities from the shopping list, store a hashmap of the store to the list
of commodities, and store the total price.

Upon entering finish, the user has an option to add another store with commodities to buy which has a similar workflow,
and it will not be a part of this scenario. The user is allowed to pick a delivery man to complete the service. These 
delivery men are also hard coded into the system for the purpose of Phase 0. Upon choosing a name, the order is complete!
The user is presented with the command options again where they may opt to quit.
