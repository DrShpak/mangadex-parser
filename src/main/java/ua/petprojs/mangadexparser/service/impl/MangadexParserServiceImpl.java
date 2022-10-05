package ua.petprojs.mangadexparser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.petprojs.mangadexparser.model.ChapterInfo;
import ua.petprojs.mangadexparser.model.ChapterPagesInfo;
import ua.petprojs.mangadexparser.service.MangadexParserService;

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
}
