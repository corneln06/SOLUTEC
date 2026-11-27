package dk.via.solutec.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "steps")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Boolean isCompleted;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date startDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @ElementCollection
    @CollectionTable(name = "step_dates", joinColumns = @JoinColumn(name = "step_id"))
    @Column(name = "step_arriving_end_dates")
    private List<Date> arrivingAndEndDates = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "provider_name")
    private Provider provider;
}
