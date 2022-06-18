package com.company.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PageableUtil {
    public static Pageable getPageable(int page, int size, String sortBy){
        return PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, sortBy));
    }
    public static Pageable getPageable(int page, int size){
        return PageRequest.of(page, size, Sort.by(Sort.Direction.DESC));
    }
}
