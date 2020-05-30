package pl.lapinski.kurs_springboot2_teaitydzien7_zad2.repository;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pl.lapinski.kurs_springboot2_teaitydzien7_zad2.model.Article;
import pl.lapinski.kurs_springboot2_teaitydzien7_zad2.model.News;

import java.util.List;

@Service
@Repository
public interface NewsDao {

    void generateAndSaveNews();
    List<Article> findAll();
    void saveNews();
    void findNewsById();
    void findNewsByYear();

}
