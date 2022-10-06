package mangadexparser.service;

import mangadexparser.model.ChapterPagesInfo;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface MangadexParserService {
    void parseChapter(UUID chapterId);

    ResponseEntity<ChapterPagesInfo> getChapterPagesInfo(String chapterId);
}
