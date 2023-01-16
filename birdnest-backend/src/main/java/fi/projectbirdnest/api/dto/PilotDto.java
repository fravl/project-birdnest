package fi.projectbirdnest.api.dto;

import fi.projectbirdnest.model.Pilot;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PilotDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    public PilotDto(Pilot pilot){
        if(pilot != null) {
            this.firstName = pilot.getFirstName();
            this.lastName = pilot.getLastName();
            this.phoneNumber = pilot.getPhoneNumber();
            this.email = pilot.getEmail();
        } else {
            this.firstName = "Unknown";
            this.lastName = "Pilot";
            this.phoneNumber = "-";
            this.email = "-";
        }
    }
}
