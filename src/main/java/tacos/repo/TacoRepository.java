package tacos.repo;

import org.springframework.data.repository.CrudRepository;
import tacos.model.Taco;

import java.util.Optional;

public interface TacoRepository extends CrudRepository<Taco, String> {
    Optional<Taco> findById(Long id);
}
