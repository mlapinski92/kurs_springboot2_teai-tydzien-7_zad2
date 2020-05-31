package pl.lapinski.kurs_springboot2_teaitydzien7_zad2.gui;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
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
        newsGrid.setColumns("author", "description", "title", "articleId");
        newsGrid.recalculateColumnWidths();

        Button generateNewsDatabase = new Button("Generate News");
        generateNewsDatabase.addClickListener(buttonClickEvent -> {
            newsDao.generateAndSaveNews();
        });

        Button refreshDataBase = new Button("Refresh");
        refreshDataBase.addClickListener(buttonClickEvent -> {
            newsGrid.setItems(newsDao.findAll());
        });

        Accordion accordion = new Accordion();
        HorizontalLayout horizontalLayoutForModyfication = new HorizontalLayout();

        TextField authorTextField = new TextField("Author");
        TextField descriptionTextField = new TextField("Description");
        TextField titleTextField = new TextField("Title");


        accordion.add("Edit news", horizontalLayoutForModyfication);

        add(newsGrid, generateNewsDatabase, refreshDataBase, accordion);

    }


}
