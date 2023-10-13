package ie.jak.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stylist {
    private int stylistId;
    private String stylistName;
    private float stylistPhoneNumber;
    private int stylistSalary;
    private int salonId;
}
