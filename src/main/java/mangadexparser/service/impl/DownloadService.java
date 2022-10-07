package mangadexparser.service.impl;

import lombok.val;
import mangadexparser.model.ChapterInfo;
import mangadexparser.model.Manga;
import mangadexparser.service.Downlodable;
import mangadexparser.service.ISearchManga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.stream.Collectors;

@Service
public class DownloadService {
    private final Downlodable downloader;
    private final ISearchManga searchManga;
//    private RestTemplate restTemplate;

    @Autowired
    public DownloadService(Downlodable downloader, ISearchManga searchManga) {
        this.downloader = downloader;
        this.searchManga = searchManga;
    }

    //download one chapter
    public void downloadChapter(String URL, String path, int buffSize, String title, String chapterNumber) {
        System.out.println("in downloadChapter");
        Manga manga = searchManga.getManga(title);
        ChapterInfo chapterInfo = searchManga.getChapter(manga.getId().toString(), chapterNumber);
        val chapterId = chapterInfo.getData().getId().toString();
        val chapterPagesInfo = searchManga.getChapterPagesInfo(chapterId).getBody();
        URL += chapterPagesInfo.getChapter().getHash() + "/";
        Queue<String> pagesNames = Arrays.stream(chapterPagesInfo.getChapter().getData()).collect(Collectors.toCollection(ArrayDeque::new));
        val pageNumber = pagesNames.size();
        String pageName;
        while (!pagesNames.isEmpty()) {
            System.out.println("page name " + pagesNames.peek());
            pageName = pagesNames.poll();
            downloader.download(URL + pageName, path + "chapter_" + chapterNumber + "/" , buffSize, pageNumber - pagesNames.size());
        }
    }
}
