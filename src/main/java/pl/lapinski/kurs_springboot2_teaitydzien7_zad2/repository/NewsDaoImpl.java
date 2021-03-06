package pl.lapinski.kurs_springboot2_teaitydzien7_zad2.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.lapinski.kurs_springboot2_teaitydzien7_zad2.model.Article;
import pl.lapinski.kurs_springboot2_teaitydzien7_zad2.model.News;
import pl.lapinski.kurs_springboot2_teaitydzien7_zad2.service.NewsService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;


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
    public void saveNewsToDataBase() {
        for (Article article : generateNews()) {
            String sql = "INSERT INTO news VALUES (?,?,?)";
            jdbcTemplate.update(sql, article.getAuthor(),
                    article.getTitle(), article.getDescription());
        }
    }

    @Override
    public List<Article> generateNews() {
        List<Article> articles = newsService.getNewsRest().getArticles();
        return articles;
    }

    @Override
    public List<Article> findAll() {
        String sql = "SELECT * FROM news";
        List<Article> articles = new ArrayList<>();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        maps.stream().forEach(element -> articles.add(new Article(
                String.valueOf(element.get("author")),
                String.valueOf(element.get("title")),
                String.valueOf(element.get("description")),
                Long.parseLong(String.valueOf(element.get("article_id")))
        )));
        return articles;
    }

    @Override
    public void updateArticle(String author, String title, String description, long id) {
        String sql = "UPDATE news SET author=?, title=?, description=? WHERE article_id=?";
        jdbcTemplate.update(sql, author, title, description, id);
    }
}

