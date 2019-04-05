package gifbin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FileObject extends AbstractPersistable<Long> {
    @Lob
    private byte[] content;
}