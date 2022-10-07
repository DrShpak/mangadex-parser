package mangadexparser.service.impl;

import mangadexparser.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import mangadexparser.service.ISearchManga;

import java.util.UUID;

@Service
public class SearchService implements ISearchManga {
    private static final String MANGADEX_CHAPTER_INFO_URL = "https://api.mangadex.org/chapter/{chapterId}";
    private static final String MANGADEX_CHAPTER_PAGES_INFO_URL = "https://api.mangadex.org/at-home/server/{chapterId}";
    @Value("${url_search_manga_by_title}")
    private String URL_TO_SEARCH_MANGA_BY_TITLE;
    @Value("${url_search_chapter_by_manga_id}")
    private String URL_TO_SEARCH_CHAPTER_BY_MANGA_ID;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void parseChapter(UUID chapterId2) {
        var chapterInfoResponseEntity = restTemplate.
                getForEntity(MANGADEX_CHAPTER_INFO_URL, ChapterInfo.class, chapterId2);
        var chapterPages = chapterInfoResponseEntity.getBody();
        var chapterPagesInfoResponseEntity = restTemplate.
                getForEntity(MANGADEX_CHAPTER_PAGES_INFO_URL, ChapterPagesInfo.class, chapterId2);
        var chapterPagesInfo = chapterPagesInfoResponseEntity.getBody();
        System.out.println(chapterPages);
        System.out.println(chapterPagesInfo);
    }

    @Override
    public ResponseEntity<ChapterPagesInfo> getChapterPagesInfo(String chapterId) {
        return restTemplate.
                getForEntity(MANGADEX_CHAPTER_PAGES_INFO_URL, ChapterPagesInfo.class, chapterId);
    }

    @Override
    public ResponseEntity<ChapterInfo> getChapter(String chapterId) {
        return new RestTemplate().
                getForEntity(MANGADEX_CHAPTER_INFO_URL, ChapterInfo.class, chapterId);
    }


    public Manga getManga(String title) {
        return restTemplate.
                getForEntity(URL_TO_SEARCH_MANGA_BY_TITLE, MangaProxy.class, title).getBody().toManga();
    }

    public ChapterInfo getChapter(String mangaId, String chapterNumber) {
        return restTemplate.
                getForEntity(URL_TO_SEARCH_CHAPTER_BY_MANGA_ID, ChapterInfoProxy.class, mangaId, chapterNumber).
                getBody().
                toChapterInfo();
    }
}
