public class flavouredSyrup extends CoffeeDecorator{

    private double cost = 0.45;

    flavouredSyrup(Coffee specialCoffee){
        super(specialCoffee);
    }

    public double makeCoffee() {
        return specialCoffee.makeCoffee()+ addflavouredSyrup();
    }

    private double addflavouredSyrup() {

        System.out.println(" + flavoured syrup: $0.45");

        return cost;
    }
}