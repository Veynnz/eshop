package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.service.itemServicePost;

public abstract class AbstractControllerPost<T> {

    protected final itemServicePost<T> itemService;
    protected final String redirectList;

    public AbstractControllerPost(
            itemServicePost<T> itemService,
            String redirectList) {
        this.itemService = itemService;
        this.redirectList = redirectList;
    }

    protected String createPost(T item) {
        itemService.create(item);
        return redirectList;
    }

    protected String editPost(String id, T item) {
        itemService.edit(id, item);
        return redirectList;
    }

    protected String delete(String id) {
        itemService.delete(id);
        return redirectList;
    }
}