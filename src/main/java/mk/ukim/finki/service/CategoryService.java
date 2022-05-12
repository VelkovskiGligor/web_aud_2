package mk.ukim.finki.service;

import mk.ukim.finki.models.Category;

import java.util.List;

public interface CategoryService {
    Category create(String name, String description);

    void delete(String name);

    void update(String name, String description);

    List<Category> listCategories();

    List<Category> searchCategories(String searchText);
}
