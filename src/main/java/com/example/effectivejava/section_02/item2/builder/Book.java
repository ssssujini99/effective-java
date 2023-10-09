package com.example.effectivejava.section_02.item2.builder;

/**
 * 코드 2-3: 빌더 패턴 - 점층적 생성자 패턴과 자바빈즈 패턴의 장점만을 취함
 */
public class Book {

    private final long id;
    private final String title;

    /**
     * Book 클래스 내부의 static class
     */
    public static class Builder {
        private long id;
        private String title;

        public Builder id (long id) {
            this.id = id;
            return this;
        }

        public Builder title (String title) {
            this.title = title;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }

    private Book(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
