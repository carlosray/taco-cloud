package tacos.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import tacos.model.Taco;

import java.util.Optional;

public interface TacoRepository extends PagingAndSortingRepository<Taco, String> {
    Optional<Taco> findById(Long id);
}
