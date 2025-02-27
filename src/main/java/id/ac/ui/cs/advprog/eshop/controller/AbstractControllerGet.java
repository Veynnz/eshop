package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.service.itemServiceGet;
import org.springframework.ui.Model;
import java.util.List;

public abstract class AbstractControllerGet<T> {
    protected final itemServiceGet<T> itemService;
    protected final String createView;
    protected final String listView;
    protected final String editView;
    protected final String itemAttribute;
    protected final String itemsAttribute;

    public AbstractControllerGet(
            itemServiceGet<T> itemService,
            String createView,
            String listView,
            String editView,
            String itemAttribute,
            String itemsAttribute) {
        this.itemService = itemService;
        this.createView = createView;
        this.listView = listView;
        this.editView = editView;
        this.itemAttribute = itemAttribute;
        this.itemsAttribute = itemsAttribute;
    }

    public String createPage(Model model) {
        model.addAttribute(itemAttribute, createNewInstance());
        return createView;
    }

    public String listPage(Model model) {
        List<T> allItems = itemService.findAll();
        model.addAttribute(itemsAttribute, allItems);
        return listView;
    }

    public String editPage(String id, Model model) {
        T item = itemService.findById(id);
        if (item == null) {
            return listView;
        }
        model.addAttribute(itemAttribute, item);
        return editView;
    }

    protected abstract T createNewInstance();
}
