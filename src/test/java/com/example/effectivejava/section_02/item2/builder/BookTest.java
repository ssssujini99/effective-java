package com.example.effectivejava.section_02.item2.builder;

import com.example.effectivejava.section_02.item2.builder.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void test() {
        // given
        long id = 1L;
        String title = "title";

        // when
        Book book = new Book.Builder()
                .id(id)
                .title(title)
                .build();

        // then
        assertAll(
                () -> assertThat(book.getId()).isEqualTo(id),
                () -> assertThat(book.getTitle()).isEqualTo(title)
        );
    }
}