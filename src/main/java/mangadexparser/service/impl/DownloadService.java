package mangadexparser.service.impl;

import lombok.val;
import mangadexparser.service.Downlodable;
import mangadexparser.service.MangadexParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.stream.Collectors;

@Service
public class DownloadService {
    private final Downlodable downloader;
    private final MangadexParserService mangadexParserService;
//    private RestTemplate restTemplate;

    @Autowired
    public DownloadService(Downlodable downloader, RestTemplate restTemplate, MangadexParserService mangadexParserService) {
        this.downloader = downloader;
//        this.restTemplate = restTemplate;
        this.mangadexParserService = mangadexParserService;
    }

    //todo download all manga or one chapter??
    public void downloadChapter(String URL, String path, int buffSize) {
        System.out.println("in downloadChapter");
        val chapterId = "b874e105-2fc1-439b-994e-60bf4b24416d";
        val chapterPagesInfo = mangadexParserService.getChapterPagesInfo(chapterId).getBody();
        URL += chapterPagesInfo.getChapter().getHash() + "/";
        Queue<String> pagesNames = Arrays.stream(chapterPagesInfo.getChapter().getData()).collect(Collectors.toCollection(ArrayDeque::new));
        val pageNumber = pagesNames.size();
        String pageName;
        while (!pagesNames.isEmpty()) {
            System.out.println("page name " + pagesNames.peek());
            pageName = pagesNames.poll();
            downloader.download(URL + pageName, path, buffSize, pageNumber - pagesNames.size());
        }
    }
}
