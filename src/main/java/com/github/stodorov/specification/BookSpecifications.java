package com.github.stodorov.specification;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public final class BookSpecifications {

    private static final int PAGE_SIZE = 2;

    private BookSpecifications() {
    }

    public static PageRequest paginate(Integer page, Sort sort) {
        return PageRequest.of(page, PAGE_SIZE, sort);
    }

}