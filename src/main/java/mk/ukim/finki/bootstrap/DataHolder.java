package mk.ukim.finki.bootstrap;

import lombok.Getter;
import mk.ukim.finki.models.*;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Getter
@Component //   ova kazuva deka ovaa klasa treba da se skenira i samio Springbut prave instanca od ovaj klasa
public class DataHolder {
    public static List<Category> categoryList = new ArrayList<Category>();
    public static  List<User> usersList=new ArrayList<User>();
    public static  List<Manufacturer> manufacturers=new ArrayList<Manufacturer>();
    public static  List<Product> products=new ArrayList<>();
    public static  List<ShoppingCart> shoppingCarts=new ArrayList<>();
/*
    @PostConstruct // So ovaa notacija kazuvame deka pri kriiranjeto na instanca od klasata ovoj metod sam se povikuva
    public void init() {
        this.categoryList.add(new Category("Movie", "Movie category"));
        this.categoryList.add(new Category("Books", "Books category"));
        this.categoryList.add(new Category("Software", "Software category"));

        this.usersList.add(new User("gligorije.velkovski","wep","Gligorije","Velkovski"));
        this.usersList.add(new User("kostadin.mishev","wep","Kostadin","Mishev"));

         Manufacturer manufacturer=new Manufacturer("Nike","NY NY");
        this.manufacturers.add(manufacturer);

        Category category =new Category("Sport", "Sport category");
        categoryList.add(category);

        products.add(new Product("Ball 1",256.8,7,category,manufacturer));
        products.add(new Product("Ball 2",356.8,7,category,manufacturer));
        products.add(new Product("Ball3 ",456.8,7,category,manufacturer));

    }

 */
}


