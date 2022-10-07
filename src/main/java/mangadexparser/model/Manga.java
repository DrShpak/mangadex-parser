package mangadexparser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Manga {
    private UUID id;
}
