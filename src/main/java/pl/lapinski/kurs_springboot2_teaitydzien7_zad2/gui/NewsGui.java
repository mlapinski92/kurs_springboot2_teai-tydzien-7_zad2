package pl.lapinski.kurs_springboot2_teaitydzien7_zad2.gui;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import pl.lapinski.kurs_springboot2_teaitydzien7_zad2.model.Article;
import pl.lapinski.kurs_springboot2_teaitydzien7_zad2.model.News;
import pl.lapinski.kurs_springboot2_teaitydzien7_zad2.repository.NewsDao;

import java.util.stream.Stream;

@Route("news-database")
public class NewsGui extends VerticalLayout {

    private NewsDao newsDao;

    public NewsGui(NewsDao newsDao) {
        this.newsDao = newsDao;

        Grid<Article> newsGrid = new Grid<Article>(Article.class);

        Accordion accordion = new Accordion();
        HorizontalLayout horizontalLayoutForNews = new HorizontalLayout();

        Button generateNewsDatabase = new Button("Generate Database");
        generateNewsDatabase.addClickListener(buttonClickEvent -> {
            newsDao.generateAndSaveNews();
            newsGrid.setItems((Stream<Article>) newsDao.findAll());
        });
        horizontalLayoutForNews.add(newsGrid, generateNewsDatabase);

        accordion.add("News Database", horizontalLayoutForNews);

        HorizontalLayout horizontalLayout = new HorizontalLayout();

        accordion.add("News Database", horizontalLayout);

        add(newsGrid, accordion);


    }


}
