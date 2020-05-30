package pl.lapinski.kurs_springboot2_teaitydzien7_zad2.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.lapinski.kurs_springboot2_teaitydzien7_zad2.model.Article;
import pl.lapinski.kurs_springboot2_teaitydzien7_zad2.model.News;
import pl.lapinski.kurs_springboot2_teaitydzien7_zad2.service.NewsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Repository
public class NewsDaoImpl implements NewsDao {

    private JdbcTemplate jdbcTemplate;
    private NewsService newsService;

    @Autowired
    public NewsDaoImpl(JdbcTemplate jdbcTemplate, NewsService newsService) {
        this.jdbcTemplate = jdbcTemplate;
        this.newsService = newsService;
    }

    @Override
    public void generateAndSaveNews() {
        List<Article> articles = newsService.getNewsRest().getArticles();
        for (Article article : articles) {
            String sql = "INSERT INTO news VALUES (?,?,?,?)";
            jdbcTemplate.update(sql, article.getArticleId(),
                    article.getAuthor(), article.getTitle(), article.getDescription());
        }
    }

    @Override
    public List<Article> findAll() {
        String sql = "SELECT * FROM news";
        List<Article> articles = new ArrayList<>();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        maps.stream().forEach(element -> articles.add(new Article(
                Long.parseLong(String.valueOf(element.get("news_id"))),
                String.valueOf(element.get("author")),
                String.valueOf(element.get("title")),
                String.valueOf(element.get("description"))
        )));
        return articles;
    }

    @Override
    public void saveNews() {

    }

    @Override
    public void findNewsById() {

    }

    @Override
    public void findNewsByYear() {

    }
}
