package mangadexparser.service.impl;

import lombok.val;
import mangadexparser.model.ChapterInfo;
import mangadexparser.model.Manga;
import mangadexparser.service.Downlodable;
import mangadexparser.service.ISearchManga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zeroturnaround.zip.ZipUtil;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;

@Service
public class DownloadService {
    private final Downlodable downloader;
    private final ISearchManga searchManga;

    @Autowired
    public DownloadService(Downlodable downloader, ISearchManga searchManga) {
        this.downloader = downloader;
        this.searchManga = searchManga;
    }

    public void download(String URL, String path, int buffSize, String title, String[] chapters) {
        for (String chapter : chapters) {
            String destDirPath = downloadChapter(URL, path, buffSize, title, chapter);
            compressToCbz(destDirPath);
        }
    }

    //download one chapter
    public String downloadChapter(String URL, String path, int buffSize, String title, String chapterNumber) {
        System.out.println("in downloadChapter");
        Manga manga = searchManga.getManga(title);
        ChapterInfo chapterInfo = searchManga.getChapter(manga.getId().toString(), chapterNumber);
        val chapterId = chapterInfo.getData().getId().toString();
        val chapterPagesInfo = searchManga.getChapterPagesInfo(chapterId).getBody();
        URL += Objects.requireNonNull(chapterPagesInfo).getChapter().getHash() + "/";
        Queue<String> pagesNames = Arrays.stream(chapterPagesInfo.getChapter().getData()).collect(Collectors.toCollection(ArrayDeque::new));
        val pageNumber = pagesNames.size();
        String pageName;
        String destPath = path + "chapter_" + chapterNumber + "/";
        while (!pagesNames.isEmpty()) {
            System.out.println("page name " + pagesNames.peek());
            pageName = pagesNames.poll();
            downloader.download(URL + pageName, destPath , buffSize, pageNumber - pagesNames.size());
        }
        return destPath;
    }

    private void compressToCbz(String path) {
        var chapterDir = new File(path);
        ZipUtil.unexplode(chapterDir);
        if (chapterDir.renameTo(new File(chapterDir.getAbsolutePath() + ".cbz"))) {
            System.out.println("renamed");
        } else {
            System.out.println("not renamed");
        }
//        chapterDir.renameTo(new File(path + ".cbz"));

    }

}
