package mangadexparser.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@ToString
public class MangaProxy {
    private MangaInfo[] data;
    private String result;

    @Entity
    @Data
    @NoArgsConstructor
    @ToString
    public static class MangaInfo {
        private UUID id;
    }

    public Manga toManga() {
        return new Manga(data[0].id);
    }
}
