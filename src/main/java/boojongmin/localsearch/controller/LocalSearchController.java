package boojongmin.localsearch.controller;

import boojongmin.localsearch.model.DocumentModel;
import boojongmin.localsearch.model.KeywordCountModel;
import boojongmin.localsearch.model.LocationSearchModel;
import boojongmin.localsearch.service.LocalSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/localsearch")
public class LocalSearchController {
    private LocalSearchService service;

    public LocalSearchController(LocalSearchService service) {
        this.service = service;
    }


    // ex: http://localhost:8080/api/localsearch/keyword/1/page/1
    @GetMapping("/keyword/{keyword}/page/{page}")
    public LocationSearchModel locationSearch(@PathVariable("keyword") String keyword,
                                              @PathVariable("page") int page) {
        return service.locationSearch(keyword, page);
    }

    @GetMapping("/keyword/rank")
    public List<KeywordCountModel> keywordRank() {
        return service.getKeywordCount();
    }
}
