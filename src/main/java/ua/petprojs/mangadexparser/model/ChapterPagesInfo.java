package ua.petprojs.mangadexparser.model;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChapterPagesInfo {
    private String result;
    private String baseUrl;
    private Chapter chapter;

    @Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Chapter {
        private String hash;
        private String[] data;
    }
}
