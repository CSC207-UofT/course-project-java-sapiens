## Open questions our group is struggling with:
- How to get data from the Entities without breaking clean architecture and without implementing duplicate getter methods in the Use Cases that manage the Entities.
- Assigning functionality to Managers and Entities as per what best suits their functionality.

## What has worked well so far with our design:
- Our design provided good user feedback in the sense that the user can easily see and interact with the data they have created.
- Our design of seperating the text UI by how they will be implemented as activities in Android Studios would save time when we transition from our currerent text UI to an Android application.

## New Features that we will continue to develop after phase 2:
- Customers can choose their favoriate delivery man based on delivery man's rating and distance.
- Customers could edit their profile after they register.
- Display the google map to a customer, so the customer can know the current location of deliveryman.

## Summary of each group member's contributions in phase 2 and a link to their significant pull requests. 
- Kevin: Completed 4 Activities and fixed Clean Architecture violations from Phase 1. 

  Significant pull request: https://git.io/JMpZR, this pull request included the creation to completion of two Activities that allows the User to create and modify their shopping lists. Each Activity also uses a custom RecyclerView Adapter, an on click listener interface to invert the dependency, and a custom spinner that allows for items to be filtered based on the keywords that the user enters. 

- Patrick: Completed three activities and add JUnit tests. 

  The first major request (https://git.io/JMpZd) is to finish Customer Profile Activity that when a user clicks button of profile from Customer Activity, they can display user’s information from previous activity. 

  The second major request (https://git.io/JMpGZ) is to work on DeliveryMan Rating Activity that delivery man can rate their customer and the purpose of the rate is to sort the customer based on it. Therefore, Deliverymen can choose a customer's order next time based on the rating score and distance.

- Junsong Guo (Tyner): Completed GoogleMapGateway and its corresponding unit tests. Helped implementations relevant to GoogleMapGateway. Created DeliveryManActivity and OrderStatusDeliveryManActivity and their corresponding layout files. 

  Significant Pull Requests: \[[Link](https://github.com/CSC207-UofT/course-project-java-sapiens/pull/50)\] This pull request consists of two activities, one of them is the homepage of delivery man, the other one is where the delivery man changes the status of an accepted order.

  \[[Link](https://github.com/CSC207-UofT/course-project-java-sapiens/pull/41),[Link](https://github.com/CSC207-UofT/course-project-java-sapiens/pull/48)\] These two pull requests together form the complete GoogleMapGateway. This gateway is in charge of getting information from the Google Map API, and it also demonstrates my understanding of design patterns since it implements the façade and factory.
  
 - Nikhil Sreekumar: Assisted in Entity and Gateway creation. Implemented database interactions via FireBase API. Started the shift to Android with a functional SignInActivity and RegistrationActivity. Provided design and data transferring assistance in other activities in the project.
