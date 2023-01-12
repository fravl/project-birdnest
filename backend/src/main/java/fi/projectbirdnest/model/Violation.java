package fi.projectbirdnest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Violation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pilot_id", referencedColumnName = "pilot_id")
    private Pilot pilot;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "drone_serial_number", referencedColumnName = "serial_number")
    private Drone drone;

    @Column(name = "last_seen")
    private Instant lastSeen;

    @Column(name = "closest_distance_to_nest")
    private double closestDistanceToNest;
}
