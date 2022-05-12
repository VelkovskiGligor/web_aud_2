package mk.ukim.finki.repository.jpa;

import mk.ukim.finki.models.ShoppingCart;
import mk.ukim.finki.models.User;
import mk.ukim.finki.models.enumeration.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
   Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);
}
