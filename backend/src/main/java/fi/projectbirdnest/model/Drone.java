package fi.projectbirdnest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Drone {

    @Id
    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "position_y")
    private BigDecimal positionY;

    @Column(name = "position_x")
    private BigDecimal positionX;

}
