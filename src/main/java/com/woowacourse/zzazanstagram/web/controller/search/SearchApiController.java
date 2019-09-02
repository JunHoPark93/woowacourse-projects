package com.woowacourse.zzazanstagram.web.controller.search;

import com.woowacourse.zzazanstagram.model.search.dto.SearchResponse;
import com.woowacourse.zzazanstagram.model.search.service.SearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/search")
@RestController
public class SearchApiController {
    private final SearchService searchService;

    public SearchApiController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/{keyword}")
    public ResponseEntity<SearchResponse> searchByKeyword(@PathVariable String keyword, @RequestParam int maxSizeOfNickName
            , @RequestParam int maxSizeOfHashtag) {
        SearchResponse searchResponse = searchService.searchByKeyword(keyword, maxSizeOfNickName, maxSizeOfHashtag);
        return new ResponseEntity<>(searchResponse, HttpStatus.OK);
    }
}
