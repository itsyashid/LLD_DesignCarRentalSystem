
public class CarRentalApp {
    public static void main(String[] args) {

        Car car1=new Car("C001","Honda","Model1",60.0,true);
        Car car2=new Car("C002","Toyota","Model2",160.0,true);
        Car car3=new Car("C003","Mahindra","Model3",200.0,true);

        CarRentalSystem rentalSystem=new CarRentalSystem();
        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);

        rentalSystem.menu();
    }
}