package registration;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Registration extends AbstractPersistable<Long> {

    @NotEmpty
    @Size(min = 4, max = 50)
    private String name;
    @Size(min = 4, max = 50)
    @NotEmpty
    private String address;
    @Email
    @NotEmpty
    private String email;
}
