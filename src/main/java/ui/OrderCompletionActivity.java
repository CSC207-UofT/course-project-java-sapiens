package ui;

import entities.Order;

public class OrderCompletionActivity implements Activity {
    private Order order;


    public void display(){
        order.setStatusCOMP();
        sio.sendOutput(order.getStatus());

        sio.sendOutput("The total Price is: " + order.getTotalPrice());

        sio.sendOutput("Would you like to rate your delivery man " + order.getDeliveryMan().getName() + "? [Y/N]");
        boolean rateDeliveryMan = sio.getInput().equalsIgnoreCase("Y");

        if (rateDeliveryMan){
            // Pass to RateDeliveryManActivity with the order containing the delivery man

//            RateDeliveryManActivity rateDeliveryMan = new RateDeliveryManActivity;
//            sio.intent(rateDeliveryManActivity, this.order);
        }
        else {
            // Pass to CustomerActivity with the costumer
            CustomerActivity customerActivity = new CustomerActivity();
            sio.intent(customerActivity, this.order.getCustomer());
        }
    }

    @Override
    public void getData(Object transferredData) {
        this.order = (Order) transferredData;
    }
}

