package fi.projectbirdnest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;


@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@Getter
public class Pilot {
    @Id
    @Column(name = "pilot_id")
    private String pilotId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private final String phoneNumber;

    @Column(name = "email")
    private final String email;
}
