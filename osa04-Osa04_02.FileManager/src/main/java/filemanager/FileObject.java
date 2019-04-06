package filemanager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FileObject extends AbstractPersistable<Long> {
    private String name;
    private String contentType;
    private Long contentLength;
    @Lob
    private byte[] content;
}
