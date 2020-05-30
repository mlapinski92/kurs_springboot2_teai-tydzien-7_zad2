package pl.lapinski.kurs_springboot2_teaitydzien7_zad2.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.lapinski.kurs_springboot2_teaitydzien7_zad2.model.News;

@Service
public class NewsService {

    public News getNewsRest() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://newsapi.org/v2/top-headlines?country=us&apiKey=34ab2de9c7a44f5a88ad7745fb095410";
        News newsObject = restTemplate.getForObject(url, News.class);
        return newsObject;
    }



}
