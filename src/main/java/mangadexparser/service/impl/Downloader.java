package mangadexparser.service.impl;

import mangadexparser.service.Downlodable;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;

@Component
public class Downloader implements Downlodable {
    @Override
    public void download(String URL, String path, int buffSize, int pageNumber) {
        System.out.println("URL " + URL);
        try {
            String page_name = pageNumber + "_page.jpg";
            File file = new File(path + page_name);

            if (file.createNewFile()) {
                System.out.println("created");
            } else {
                System.out.println("not created");
            }

            try (
                    InputStream in = new BufferedInputStream(new URL(URL).openStream());
                    OutputStream writer = new FileOutputStream(file)
            ) {
                byte[] buffer = new byte[buffSize];
                int c = in.read(buffer);
                while (c > 0) {
                    writer.write(buffer, 0, c);
                    c = in.read(buffer);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
