import java.util.*;

// Base Class (Inheritance)
class Vehicle {
    String name;
    String type;
    Double rentPerDay; // Wrapper class

    Vehicle(String name, String type, Double rentPerDay) {
        this.name = name;
        this.type = type;
        this.rentPerDay = rentPerDay;
    }

    void displayVehicle() {
        System.out.println(name + " | " + type + " | Rent: " + rentPerDay);
    }
}

// Derived Class
class RentalVehicle extends Vehicle {
    Integer vehicleId; // Wrapper class
    boolean isAvailable;
    HashSet<String> features; // Collection

    RentalVehicle(Integer id, String name, String type, Double rent) {
        super(name, type, rent); // Inheritance
        this.vehicleId = id;
        this.isAvailable = true;
        this.features = new HashSet<>();
    }

    void addFeature(String f) {
        features.add(f); // avoids duplicates
    }

    void displayDetails() {
        System.out.println("ID: " + vehicleId);
        displayVehicle();

        // Operator usage
        if (isAvailable == true) {
            System.out.println("Status: Available");
        } else {
            System.out.println("Status: Rented");
        }

        System.out.println("Features: " + features);
    }
}

public class Main {

    static ArrayList<RentalVehicle> vehicles = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Default vehicles
        RentalVehicle v1 = new RentalVehicle(1, "Honda City", "Car", 2000.0);
        v1.addFeature("AC");
        v1.addFeature("GPS");

        RentalVehicle v2 = new RentalVehicle(2, "Activa", "Bike", 500.0);
        v2.addFeature("Helmet");

        vehicles.add(v1);
        vehicles.add(v2);

        int choice;

        do {
            System.out.println("\n--- VEHICLE RENTAL SYSTEM ---");
            System.out.println("1. View Vehicles");
            System.out.println("2. Rent Vehicle");
            System.out.println("3. Return Vehicle");
            System.out.println("4. Add Vehicle (Admin)");
            System.out.println("5. Exit");

           try {
         choice = sc.nextInt();
        } catch (InputMismatchException e) {
         System.out.println("Invalid input! Please enter number only.");
         sc.nextLine(); // clear buffer
         choice = 0;
        }

            switch (choice) {
                case 1:
                    viewVehicles();
                    break;

                case 2:
                    rentVehicle();
                    break;

                case 3:
                    returnVehicle();
                    break;

                case 4:
                    addVehicle();
                    break;
            }

        } while (choice != 5);
    }

    static void viewVehicles() {
        for (RentalVehicle v : vehicles) {
            System.out.println("\n------------------");
            v.displayDetails();
        }
    }

        static void rentVehicle() {
            try {
              System.out.print("Enter Vehicle ID: ");
              int id = sc.nextInt();

              for (RentalVehicle v : vehicles) {
              if (v.vehicleId.equals(id)) {
                  if (v.isAvailable) {
                        v.isAvailable = false;
                            System.out.println("Vehicle Rented Successfully!");
                        } else {
                            System.out.println("Already Rented!");
                    }
                    return;
                }
        }

        System.out.println("Vehicle not found!");

    } catch (InputMismatchException e) {
        System.out.println("Invalid input! Enter numeric ID.");
        sc.nextLine();
    }
}

    static void returnVehicle() {
        System.out.print("Enter Vehicle ID: ");
        int id = sc.nextInt();

        for (RentalVehicle v : vehicles) {
            if (v.vehicleId.equals(id)) {
                v.isAvailable = true;
                System.out.println("Vehicle Returned!");
                return;
            }
        }

        System.out.println("Vehicle not found!");
    }

    static void addVehicle() {
        System.out.print("Enter ID: ");
        Integer id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Type: ");
        String type = sc.nextLine();

        System.out.print("Enter Rent per Day: ");
        Double rent = sc.nextDouble();

        sc.nextLine();

        RentalVehicle v = new RentalVehicle(id, name, type, rent);

        System.out.print("Enter Feature: ");
        String feature = sc.nextLine();
        v.addFeature(feature);

        vehicles.add(v); // ArrayList

        System.out.println("Vehicle Added!");
    }
}