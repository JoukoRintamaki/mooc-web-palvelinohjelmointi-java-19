package links;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Link extends AbstractPersistable<Long> {

    @NotEmpty
    String title;
    @NotEmpty
    String description;
    @NotEmpty
    @URL
    String url;
}
