import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarRentalSystem {
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRentalSystem(List<Car> cars, List<Customer> customer, List<Rental> rentals) {
        this.cars = cars;
        this.customers = customer;
        this.rentals = rentals;
    }
    public CarRentalSystem(){
        cars=new ArrayList<>();
        customers=new ArrayList<>();
        rentals=new ArrayList<>();
    }
    public void addCar(Car car){
        cars.add(car);
    }
    public void addCustomer(Customer customer){
        customers.add(customer);
    }
    public void rentCar(Car car,Customer customer,int days){
        if(car.isAvailable()){
            car.rent();
            rentals.add(new Rental(car,customer,days));
        }
        else {
            System.out.println("Car is not available for rent");
        }
    }
    public void returnCar(Car car){

        Rental rentedCarToRemove=null;
        for(Rental rental:rentals){
            if(rental.getCar()==car){
                rentedCarToRemove=rental;
                rentals.remove(rentedCarToRemove);
                System.out.println(car.getCarId()+" returned successfully");
                car.returnCar(); // making the car as isAvailable = true;
                break;
            }
        }
        if(rentedCarToRemove==null){
            System.out.println("Car was not rented");
        }
    }
    public void menu(){
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("======= Welcome to Car Rental System App =======");
            System.out.println("Please choose any of the below options to continue...\n");
            System.out.println("1. Rent a car");
            System.out.println("2. Return a car");
            System.out.println("3. Exit");

            int choice=sc.nextInt();
            sc.nextLine();

            if(choice ==1){
                System.out.print("Enter your name: ");
                String customerName=sc.nextLine();

                System.out.println("\n Available Cars");
                for(Car car:cars){
                    if(car.isAvailable()){
                        System.out.println(car.getCarId()+"-"+car.getBrand()+"-"+car.getModel());
                    }
                }
                System.out.println("Enter the CarId you want to rent");
                String carId=sc.nextLine();

                System.out.println("Enter the number of days you want to rent");
                int days=sc.nextInt();
                sc.nextLine();

                Customer newCustomer=new Customer(customerName,"CUS"+(customers.size()+1));
                customers.add(newCustomer);

                Car selectedCar=null;
                for(Car car:cars){
                    if(car.getCarId().equals(carId) && car.isAvailable()){
                        selectedCar=car;
                        break;
                    }
                }
                if(selectedCar!=null){
                    double totalPrice=selectedCar.calculatePrice(days);
                    System.out.println("\n == Rental Information ==\n");
                    System.out.println("Customer Id : "+newCustomer.getCustomerId());
                    System.out.println("Customer Name : "+newCustomer.getName());
                    System.out.println("Rented Car Details : "+selectedCar.getBrand()+" "+selectedCar.getModel());
                    System.out.println("Rental Days : "+days);
                    System.out.println("Total Price : "+totalPrice);

                    System.out.println("Confirm Rental (Y/N): ");
                    String confirm=sc.nextLine();

                    if(confirm.equalsIgnoreCase("Y")){
                        rentCar(selectedCar,newCustomer,days);
                        System.out.println("Car rented Successfully\n");
                    }
                    else{
                        System.out.println("Rental Cancelled\n");
                    }
                }
                else{
                    System.out.println("Invalid car selection or car not available for rent.\n");
                }
            }else if(choice ==2){
                System.out.println("Return a car");
                System.out.println("Enter the car Id you want to return");
                String carId=sc.nextLine();

                Car carToReturn=null;
                for(Car car:cars){
                    if(car.getCarId().equals(carId) && !car.isAvailable()){
                        carToReturn=car;
                        break;
                    }
                }
                if(carToReturn!=null){
                    Customer customer=null;
                    for(Rental rental:rentals){
                        if(rental.getCar()==carToReturn){
                            customer=rental.getCustomer();
                            break;
                        }
                    }
                    if(customer!=null){
                        returnCar(carToReturn);
                    }

                }
                else{
                    System.out.println("Car was not rented\n\n");
                }
            }
            else if(choice ==3){
                break;
            }
            else{
                System.out.println("Invalid choice");
            }
        }
        System.out.println("Thank you for using Car Rental System");
    }
}
