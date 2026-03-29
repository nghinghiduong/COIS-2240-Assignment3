
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;

// 1. Extend the Application class
public class RentalSystemGUI extends Application {

    
    private RentalSystem rentalSystem = RentalSystem.getInstance();

    
    @Override
    public void start(Stage primaryStage) {
    	//List show vehicles
    	ListView<Vehicle> vehicleList = new ListView<>();
    	vehicleList.setPrefHeight(150);
       
        VBox pane = new VBox(10);
        //license plate input box
        TextField licensePlate = new TextField();
        licensePlate.setPromptText("License Plate:");
        //make input box
        TextField make = new TextField();
        make.setPromptText("Car make:");
        //model input box
        TextField model = new TextField();
        model.setPromptText("Car Model");
        //year input box
        TextField year = new TextField();
        year.setPromptText("Car year:");
        //seat input box
        TextField seat = new TextField();
        seat.setPromptText("Number of Seat:");
        
        Button addButton = new Button("Add Vehicle");
        
        addButton.setOnAction(e -> {
        	try {
        	String plate = licensePlate.getText();
        	String carMake = make.getText();
        	String carModel = model.getText();
        	int carYear = Integer.parseInt(year.getText());
        	int carSeat = Integer.parseInt(seat.getText());
        	
        		Vehicle v = new Car(carMake, carModel, carYear, carSeat);
        		v.setLicensePlate(plate);
        		rentalSystem.addVehicle(v);
        		vehicleList.getItems().add(v);
        		
        		System.out.println("Vehicle Added: " + plate);
        		
        		licensePlate.clear();
        		make.clear();
        		model.clear();
        		year.clear();
        		seat.clear();
        	} catch (NumberFormatException ex) {
        		System.out.println("Error: Year and seats must be numbers!");
        	} catch (IllegalArgumentException ex) {
        		System.out.println("Error:" +ex.getMessage());
        	}
        	});
        
        pane.getChildren().addAll(licensePlate,make, model, year, seat, addButton, vehicleList);
        Scene scene = new Scene(pane, 400, 600);
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }

    // 4. Main method to launch the GUI
    public static void main(String[] args) {
        launch(args);
    }
} 