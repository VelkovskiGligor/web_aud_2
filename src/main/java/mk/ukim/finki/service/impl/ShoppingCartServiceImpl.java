package mk.ukim.finki.service.impl;

import mk.ukim.finki.models.Product;
import mk.ukim.finki.models.ShoppingCart;
import mk.ukim.finki.models.User;
import mk.ukim.finki.models.enumeration.ShoppingCartStatus;
import mk.ukim.finki.models.exeptions.ProductAlreadyInShoppingCartException;
import mk.ukim.finki.models.exeptions.ProductNotFoundException;
import mk.ukim.finki.models.exeptions.ShoppingCartNotFoundException;
import mk.ukim.finki.models.exeptions.UserNotFoundException;
import mk.ukim.finki.repository.impl.InMemoryUserRepository;
import mk.ukim.finki.repository.impl.inMemoryShoppingCartRepository;
import mk.ukim.finki.repository.jpa.ShoppingCartRepository;
import mk.ukim.finki.repository.jpa.UserRepository;
import mk.ukim.finki.service.ProductService;
import mk.ukim.finki.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ProductService productService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        if (!this.shoppingCartRepository.findById(cartId).isPresent())
            throw new ShoppingCartNotFoundException(cartId);
        return shoppingCartRepository.findById(cartId).get().getProducts();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        return  this.shoppingCartRepository
                .findByUserAndStatus(user,ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                            ShoppingCart cart = new ShoppingCart(user);
                            return this.shoppingCartRepository.save(cart);
                        }
                );
    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Product product = this.productService.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        if (shoppingCart.getProducts()
                .stream()
                .filter(r -> r.getId().equals(productId))
                .collect(Collectors.toList()).size() > 0) {
            throw new ProductAlreadyInShoppingCartException(productId, username);
        }
        shoppingCart.getProducts().add(product);
        return shoppingCartRepository.save(shoppingCart);
    }
}
