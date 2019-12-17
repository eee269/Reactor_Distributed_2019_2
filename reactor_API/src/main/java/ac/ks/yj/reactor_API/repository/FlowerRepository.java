package ac.ks.yj.reactor_API.repository;

import ac.ks.yj.reactor_API.domain.Flower;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FlowerRepository extends ReactiveMongoRepository<Flower, String> {
}
