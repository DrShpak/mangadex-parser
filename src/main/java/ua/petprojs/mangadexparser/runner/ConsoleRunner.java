package ua.petprojs.mangadexparser.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ua.petprojs.mangadexparser.service.MangadexParserService;

import java.util.Scanner;
import java.util.UUID;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(ConsoleRunner.class);

    @Autowired
    private MangadexParserService mangadexParserService;

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            var input = scanner.next();
            try {
                var chapterId = UUID.fromString(input);
                mangadexParserService.parseChapter(chapterId);
            } catch (RuntimeException ex) {
                LOG.error(String.format("An error occurred while parsed %s", input), ex);
            }
        }
    }
}
