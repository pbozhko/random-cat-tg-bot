package by.bozhko.tg.bot.management.web;

import by.bozhko.tg.bot.dao.ImageDao;
import by.bozhko.tg.bot.dao.model.Image;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;

@Route("/")
public class MainView extends AppLayout {

    private final Grid<Image> grid = new Grid<>(Image.class);

    private final ImageDao imageDao;

    public MainView(ImageDao imageDao) {

        this.imageDao = imageDao;

        setPrimarySection(Section.NAVBAR);

        Tabs tabs = new Tabs(new Tab("Фото"));
        addToNavbar(tabs);

        grid.setHeightByRows(true);
        
        setContent(grid);

        loadImages();
    }

    private void loadImages() {

        grid.setItems(imageDao.getAll());
        grid.removeAllColumns();
        grid.addColumn(Image::getId).setHeader("ID").setSortable(true);
        grid.addColumn(Image::getTitle).setHeader("Title").setSortable(true);
        grid.addColumn(Image::getMimeType).setHeader("Mime-Type");
        grid.addColumn(Image::getWidth).setHeader("Width").setSortable(true);
        grid.addColumn(Image::getHeight).setHeader("Height").setSortable(true);
        grid.addComponentColumn(this::buildImage).setHeader("Content");
    }

    private com.vaadin.flow.component.html.Image buildImage(Image image) {

        StreamResource resource = new StreamResource(
            image.getTitle(),
            () -> new ByteArrayInputStream(image.getContent())
        );

        com.vaadin.flow.component.html.Image img = new com.vaadin.flow.component.html.Image(resource, image.getTitle());

        img.setMaxWidth(100, Unit.PIXELS);
        img.setMaxHeight(100, Unit.PIXELS);

        return img;
    }
}
