package ui;

import entities.Order;

import java.lang.reflect.Array;

public class OrderCompletionActivity implements Activity {
    private Order order;

    public void display(){
        order.setStatusCOMP();
        sio.sendOutput(order.getStatus());

        sio.sendOutput("The total Price is: " + order.getTotalPrice());

        sio.sendOutput("Would you like to rate your delivery man " + order.getDeliveryMan().getName() + "? [Y/N]");
        boolean toRateOrNot = sio.getInput().equalsIgnoreCase("Y");

        if (toRateOrNot){
            // Pass to RatingActivity with the order containing the delivery man

            RatingActivity rateDeliveryMan = new RatingActivity();
            sio.intent(rateDeliveryMan, new Object[]{this.order.getCustomer(), this.order.getDeliveryMan(), "d"});
        }
        else {
            // Pass to CustomerActivity with the customer
            CustomerActivity customerActivity = new CustomerActivity();
            sio.intent(customerActivity, new Object[]{this.order.getCustomer(), null});
        }
    }

    @Override
    public void getData(Object transferredData) {
        this.order = (Order) Array.get(transferredData, 1);
    }
}

