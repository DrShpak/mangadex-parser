package mangadexparser.service.impl;

import mangadexparser.model.ChapterInfo;
import mangadexparser.model.ChapterPagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import mangadexparser.service.MangadexParserService;

import java.util.UUID;

@Service
public class MangadexParserServiceImpl implements MangadexParserService {
    private static final String MANGADEX_CHAPTER_INFO_URL = "https://api.mangadex.org/chapter/{chapterId}";
    private static final String MANGADEX_CHAPTER_PAGES_INFO_URL = "https://api.mangadex.org/at-home/server/{chapterId}";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void parseChapter(UUID chapterId) {
        var chapterInfoResponseEntity = restTemplate.
                getForEntity(MANGADEX_CHAPTER_INFO_URL, ChapterInfo.class, chapterId);
        var chapterPages = chapterInfoResponseEntity.getBody();
        var chapterPagesInfoResponseEntity = restTemplate.
                getForEntity(MANGADEX_CHAPTER_PAGES_INFO_URL, ChapterPagesInfo.class, chapterId);
        var chapterPagesInfo = chapterPagesInfoResponseEntity.getBody();
        System.out.println(chapterPages);
        System.out.println(chapterPagesInfo);
    }

    @Override
    public ResponseEntity<ChapterPagesInfo> getChapterPagesInfo(String chapterId) {
        return restTemplate.
                getForEntity(MANGADEX_CHAPTER_PAGES_INFO_URL, ChapterPagesInfo.class, chapterId);
    }
}
