public class Milk extends CoffeeDecorator{

    private double cost = 1.00;

    Milk(Coffee specialCoffee){
        super(specialCoffee);
    }

    public double makeCoffee() {
        return specialCoffee.makeCoffee()+ addMilk();
    }

    private double addMilk() {

        System.out.println(" + milk: $1.00");

        return cost;
    }
}
