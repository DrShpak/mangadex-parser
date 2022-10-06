package mangadexparser.runner;

import mangadexparser.service.impl.DownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import mangadexparser.service.MangadexParserService;

import java.util.Scanner;
import java.util.UUID;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(ConsoleRunner.class);

    @Autowired
    private MangadexParserService mangadexParserService;

    @Value("${url_to_get_page}")
    private String URL_TO_GET_PAGE;
    @Value("${path_to_file}")
    private String PATH_TO_FILE;

    @Autowired
    private DownloadService downloadService;

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) throws Exception {
        while (true) {
//            var input = scanner.next();
            try {
//                var chapterId = UUID.fromString(input);
//                mangadexParserService.parseChapter(chapterId);
                downloadService.downloadChapter(URL_TO_GET_PAGE, PATH_TO_FILE, 1);
            } catch (RuntimeException ex) {
//                LOG.error(String.format("An error occurred while parsed %s", input), ex);
            }
        }
    }
}
