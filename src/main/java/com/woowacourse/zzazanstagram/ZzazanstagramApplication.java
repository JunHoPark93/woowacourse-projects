package com.woowacourse.zzazanstagram;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ZzazanstagramApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ZzazanstagramApplication.class)
                .run(args);
    }
}
