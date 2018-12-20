package boojongmin.localsearch.service;

import boojongmin.localsearch.config.ComponentConfig;
import boojongmin.localsearch.model.LocationSearchModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;

@RunWith(SpringRunner.class)
@Import(ComponentConfig.class)
@TestPropertySource(locations = "classpath:application.properties")
@Slf4j
public class LocalSearchServiceTest {
    @Autowired
    RestTemplate restTemplate;
    @Value("${location-search.key}")
    String apiKey;

    //    @Ignore
    @Test
    public void search() throws UnsupportedEncodingException {
        String url = "https://dapi.kakao.com/v2/local/search/keyword.json";
//        String url = "https://dapi.kakao.com/v2/local/search/keyword.json?y=37.514322572335935&x=127.06283102249932&radius=20000";
        url += "?query=카카오";
//        url += "&query=카카오";
        url += "&page=45";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + apiKey);
        HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<LocationSearchModel> exchange = restTemplate.exchange(url, HttpMethod.GET, httpEntity, LocationSearchModel.class);
        LocationSearchModel body = exchange.getBody();
        System.out.println(body);
    }
}