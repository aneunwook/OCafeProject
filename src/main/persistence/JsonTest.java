package persistence;


import model.*;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

//JsonSerializationDemo.persistence.JsonTest
public class JsonTest {

    protected Order o1;
    protected Order o2;

    @BeforeEach
    protected void setup() {
        o1 = new Order();
        o1.setDate();
        o1.addItem(new Beverage("Espresso", 3, Beverage.NOT_CUSTOMIZABLE, Beverage.NOT_CUSTOMIZABLE));
        o1.addItem(new Dish("Thai Green Curry Seafood Linguine", 16));

        o2 = new Order();
        o2.setDate();
        o2.addItem(new Beverage("Chai Latte", 5, Beverage.REGULAR, Beverage.NOT_CUSTOMIZABLE));
        o2.addItem(new Dish("Banana Cream Pie", 5));
        Dish jcr = new Dish("Japanese Curry Rice", 12);
        AdditionalOptions pork = new AdditionalOptions("Fried Pork Cutlet", 4);
        jcr.addSideToOptions(pork);
        jcr.addSideToOptions(new AdditionalOptions("Assorted Tempura", 3));
        jcr.selectAddOn(pork);
        o2.addItem(jcr);
    }

    protected void checkOrder(Order order, Order parsedOrder) {
        assertEquals(order.getTotal(), parsedOrder.getTotal());
        assertEquals(order.size(), parsedOrder.size());
        assertEquals(order.getDayOfWeek(), parsedOrder.getDayOfWeek());
        assertEquals(order.getAmPm(), parsedOrder.getAmPm());
        int i = 0;
        for (MenuItem item : order.getItemList()) {
            String name = item.getName();
            int price = item.getPrice();
            assertEquals(name, parsedOrder.getItemList().get(i).getName());
            assertEquals(price, parsedOrder.getItemList().get(i).getPrice());
            i++;
        }
    }
}
