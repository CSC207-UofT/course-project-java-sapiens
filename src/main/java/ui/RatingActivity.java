package ui;

import entities.User;

import java.lang.reflect.Array;

public class RatingActivity implements Activity{
    User user;
    User userToRate;
    String userToRateType;

    @Override
    public void display() {
        String userToRateType = (this.userToRateType.equals("c")) ? "customer" : "delivery man";
        System.out.println("Please rate your experience with the " + userToRateType + ":");
        float ratedValue = Float.parseFloat(sio.getInput());
        float rating = userToRate.getRating();
        int noOfRatings = userToRate.getNoOfRatings();
        userToRate.setRating((rating * noOfRatings + ratedValue) / ++noOfRatings);
        userToRate.setNoOfRatings(noOfRatings);

        sio.sendOutput("Would you like to leave a review? [Y/N]");
        boolean toCommentOrNot = sio.getInput().equalsIgnoreCase("Y");
        if (toCommentOrNot){
            System.out.println("Please leave a comment:");
            String comment = sio.getInput();
            userToRate.getComments().add(comment);
        }

        if (this.userToRateType.equals("d")){
            CustomerActivity customerActivity = new CustomerActivity();
            sio.intent(customerActivity, new Object[]{this.user, null});
        }
        else {
            DeliveryManActivity deliveryManActivity = new DeliveryManActivity();
            sio.intent(deliveryManActivity, new Object[]{this.user, null});
        }
    }

    @Override
    public void getData(Object transferredData) {
        this.user = (User) Array.get(transferredData, 0);
        this.userToRate = (User) Array.get(transferredData, 1);
        this.userToRateType = (String) Array.get(transferredData, 2);
    }
}
