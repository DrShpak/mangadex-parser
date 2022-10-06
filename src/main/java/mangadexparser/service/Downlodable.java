package mangadexparser.service;

public interface Downlodable {

    /**
     *
     * @param URL like https://uploads.mangadex.org/data/chapter-hash/page-name
     * @param path path to file
     * @param buffSize buffer size
     * @param pageNumber count of pages
     */
    void download(String URL, String path, int buffSize, int pageNumber);
}
