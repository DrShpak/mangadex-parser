package mangadexparser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mangadexparser.service.ISearchManga;
import mangadexparser.service.impl.SearchService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@NoArgsConstructor
@ToString
@Data
@AllArgsConstructor
public class ChapterInfoProxy {
    private Info[] data;
    private String result;
    private ISearchManga searchManga = new SearchService();

    @Component
    @Data
    @ToString
    public static class Info {
        private UUID id;
    }

    public ChapterInfo toChapterInfo() {
        return searchManga.getChapter(data[0].id.toString()).getBody();
    }
}
