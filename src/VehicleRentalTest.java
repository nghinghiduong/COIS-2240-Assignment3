import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import  java.lang.reflect.Constructor; 
import  java.lang.reflect.Modifier ; 


public class VehicleRentalTest {
	private RentalSystem rentalSystem;
	
	@BeforeEach 
	public void setUp() {
		rentalSystem = RentalSystem.getInstance();
	}

    @Test
    public void testLicensePlate() {

        Vehicle v = new Car("Toyota", "Corolla", 2020, 5);

        // valid
        assertDoesNotThrow(() -> v.setLicensePlate("AAA100"));
        assertEquals("AAA100", v.getLicensePlate());

        assertDoesNotThrow(() -> v.setLicensePlate("ABC567"));
        assertEquals("ABC567", v.getLicensePlate());

        assertDoesNotThrow(() -> v.setLicensePlate("ZZZ999"));
        assertEquals("ZZZ999", v.getLicensePlate());

        // invalid 
        
        // empty string
        assertThrows(IllegalArgumentException.class, () -> {
            v.setLicensePlate("");
        });

        // null
        assertThrows(IllegalArgumentException.class, () -> {
            v.setLicensePlate(null);
        });

        // too many digits
        assertThrows(IllegalArgumentException.class, () -> {
            v.setLicensePlate("AAA1000");
        });

        // less digits
        assertThrows(IllegalArgumentException.class, () -> {
            v.setLicensePlate("ZZZ99");
        });
    }
    
    @Test
    public void testRentAndReturnVehicle() {
    	//success rent
    	Vehicle v = new Car("Toyota", "Corolla", 2020, 5);
    	Customer c = new Customer(001, "Jane Smith");
    	assertEquals(Vehicle.VehicleStatus.Available, v.getStatus());
    	boolean result = rentalSystem.rentVehicle(v, c,  LocalDate.now(), 50.0);
    	assertTrue(result);
    	assertEquals(Vehicle.VehicleStatus.Rented, v.getStatus());
    	
    	//fail
    	result = rentalSystem.rentVehicle(v,c, LocalDate.now(), 50.0);
    	assertFalse(result);
    	
    	//success return
    	result = rentalSystem.returnVehicle(v, c, LocalDate.now(), 10.0);
    	assertTrue(result);
    	assertEquals(Vehicle.VehicleStatus.Available, v.getStatus());
    }


    @Test
    public void testSingletonRentalSystem() { 
        try { 
            // constructor of RentalSystem 
            Constructor<RentalSystem> constructor = RentalSystem.class.getDeclaredConstructor(); 

            // modifiers of theconstructor 
            int modifiers = constructor.getModifiers() ; 
            assertEquals(Modifier.PRIVATE, modifiers) ;

            // get instance and assert if its not null 
            RentalSystem instance = RentalSystem.getInstance() ; 
            assertNotNull(instance); 

        } catch (Exception e) { 
            fail("Exception: " + e.getMessage()) ; 
        }

    }

}


