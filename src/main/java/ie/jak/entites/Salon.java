package ie.jak.entites;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Salon {
    private int salonId;
    private String salonName;
    private String salonAddress;
    private float salonPhoneNumber;
    private int salonDaysOpen;
}
