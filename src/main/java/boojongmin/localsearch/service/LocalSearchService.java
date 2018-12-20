package boojongmin.localsearch.service;

import boojongmin.localsearch.exception.ApiServerException;
import boojongmin.localsearch.model.KeywordCountModel;
import boojongmin.localsearch.model.LocationSearchModel;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class LocalSearchService {
    private RestTemplate restTemplate;
    private BoundZSetOperations<String, String> zSetOpertations;
    private String apiKey;
    final String URL_PREFIX = "https://dapi.kakao.com";

    public LocalSearchService(RestTemplate restTemplate,
                              BoundZSetOperations zSetOpertations,
                              @Value("${location-search.key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.zSetOpertations = zSetOpertations;
        this.apiKey = apiKey;
    }

    public LocationSearchModel locationSearch(String keywoard, int page) {
        increaseScoreToSearchedKeyword(keywoard);
        return search(keywoard, page);
    }

    @HystrixCommand(fallbackMethod = "searchErrorFallback")
    public LocationSearchModel search(String keywoard, int page) {
        String url = URL_PREFIX + "/v2/local/search/keyword.json";
        url += "?query=" + keywoard;
        url += "&page=" + page;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + apiKey);
        HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<LocationSearchModel> exchange = restTemplate.exchange(url, HttpMethod.GET, httpEntity, LocationSearchModel.class);
        return exchange.getBody();
    }

    private void increaseScoreToSearchedKeyword(String keyword) {
        zSetOpertations.incrementScore(keyword, 1);
    }

    @HystrixCommand
    public LocationSearchModel searchErrorFallback(String keyword, int page) {
        throw new ApiServerException("location search api has error(keyword:" + keyword + ", page" + page + "): " + URL_PREFIX);
    }

    public List<KeywordCountModel> getKeywordCount() {
        return zSetOpertations.reverseRangeWithScores(0, 9).stream()
                .map(x -> KeywordCountModel.builder().keyword(x.getValue()).count(x.getScore().longValue()).build())
                .collect(toList());
    }
}
