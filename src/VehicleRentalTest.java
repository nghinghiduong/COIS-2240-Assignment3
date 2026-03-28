import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}


