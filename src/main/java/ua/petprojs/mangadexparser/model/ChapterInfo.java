package ua.petprojs.mangadexparser.model;

import lombok.*;

import javax.persistence.Entity;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChapterInfo {
    private String result;
    private String response;
    private ChapterData data;

    @Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class ChapterData {
        private UUID id;
        private String type;
        private ChapterDataAttributes attributes;

        @Entity
        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        @ToString
        public static class ChapterDataAttributes {
            private int volume;
            private int chapter;
            private String title;
            private String translatedLanguage;
            private String externalUrl;
            private ZonedDateTime publishAt;
            private ZonedDateTime readableAt;
            private ZonedDateTime createdAt;
            private ZonedDateTime updatedAt;
            private int pages;
            private int version;
        }
    }
}
