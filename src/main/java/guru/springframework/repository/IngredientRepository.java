package guru.springframework.repository;

import guru.springframework.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    Optional<Ingredient> findByDescription(String description);
}
