package mangadexparser.runner;

import mangadexparser.service.impl.DownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(ConsoleRunner.class);

    @Value("${url_to_get_page}")
    private String URL_TO_GET_PAGE;
    @Value("${path_to_file}")
    private String PATH_TO_FILE;

    @Autowired
    private DownloadService downloadService;

    @Override
    public void run(String... args) {
        StringBuilder title = new StringBuilder();
        StringBuilder chaptersString = new StringBuilder();

        int delimiterPos = 0;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("!")) {
                delimiterPos = i;
                break;
            } else {
                title.append(args[i]).append(" ");
            }
        }

        for (int i = delimiterPos + 1; i < args.length; i++) {
            chaptersString.append(args[i]).append(" ");
        }

        System.out.println(title + "!");
        System.out.println(chaptersString);
        downloadService.download(URL_TO_GET_PAGE, PATH_TO_FILE, 1,
                title.deleteCharAt(title.length() - 1).toString(),
                chaptersString.deleteCharAt(chaptersString.length() - 1).toString().split(" "));

//        downloadService.download(URL_TO_GET_PAGE, PATH_TO_FILE, 1, "tokyo ghoul", new String[]{"12"});
    }
}