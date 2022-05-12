package mk.ukim.finki.repository.impl;

import mk.ukim.finki.bootstrap.DataHolder;
import mk.ukim.finki.models.Category;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository //Kazuvame deka ovaa klasa e repository, Springboot avtomacki prave instanca od ovaa klasa
public class inMemoryCategoryRepository {

    public List<Category> findAll() {
        return DataHolder.categoryList; // Ova mozeme da go pristapime bez instanca bidejki e staticna promenliva vo DataHolderot
    }

    public Category save(Category c) {
        if (c == null || c.getName() == null || c.getName().isEmpty()) {
            return null;
        }
        DataHolder.categoryList.removeIf(r-> r.getName().equals(c.getName()));
        DataHolder.categoryList.add(c);
        return c;
    }

    public Optional<Category> findByName(String s){
        return DataHolder.categoryList.stream().filter(r-> r.getName().equals(s)).findFirst();
    }
    public Optional<Category> findById(Long id){
        return DataHolder.categoryList.stream().filter(r-> r.getId().equals(id)).findFirst();
    }


    public List<Category> search(String text){
        return DataHolder.categoryList.stream().filter(r-> r.getName().contains(text)
                || r.getDescription().contains(text)).collect(Collectors.toList());
    }
    public void delete(String name){
        if (name == null) return;
        DataHolder.categoryList.removeIf(r-> r.getName().equals(name));
    }

}
