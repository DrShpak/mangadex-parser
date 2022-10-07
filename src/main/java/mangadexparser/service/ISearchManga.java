package mangadexparser.service;

import mangadexparser.model.ChapterInfo;
import mangadexparser.model.ChapterPagesInfo;
import mangadexparser.model.Manga;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface ISearchManga {
    void parseChapter(UUID chapterId);

    ResponseEntity<ChapterPagesInfo> getChapterPagesInfo(String chapterId);
    ResponseEntity<ChapterInfo> getChapter(String chapterId);

    Manga getManga(String title);

    ChapterInfo getChapter(String mangaId, String chapterNumber);
}
