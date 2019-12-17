package ac.ks.yj.reactor_API.config;

import ac.ks.yj.reactor_API.domain.Flower;
import ac.ks.yj.reactor_API.repository.FlowerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Component
public class PreData implements CommandLineRunner {

    private final FlowerRepository flowerRepository;

    public PreData(FlowerRepository flowerRepository) {
        this.flowerRepository = flowerRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("run..");
        flowerRepository.deleteAll()
                .thenMany(
                        Flux.just("튤립", "장미", "수국")
                            .map(name -> new Flower(UUID.randomUUID().toString(), name))
                            .flatMap(flowerRepository::save))
                .subscribe(System.out::println);
    }
}
