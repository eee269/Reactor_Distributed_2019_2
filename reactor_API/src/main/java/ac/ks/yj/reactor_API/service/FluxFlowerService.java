package ac.ks.yj.reactor_API.service;


import ac.ks.yj.reactor_API.domain.Flower;
import ac.ks.yj.reactor_API.domain.FlowerEvents;
import ac.ks.yj.reactor_API.repository.FlowerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

@Service
public class FluxFlowerService {

    private final FlowerRepository flowerRepository;

    public FluxFlowerService(FlowerRepository flowerRepository) {
        this.flowerRepository = flowerRepository;
    }


    public Flux<Flower> all() { return flowerRepository.findAll(); }

    public Mono<Flower> byId(String flowerId) { return flowerRepository.findById(flowerId); }

    public Flux<FlowerEvents> streams(String flowerId) {
        return byId(flowerId).flatMapMany(flower -> {
            Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
            Flux<FlowerEvents> events = Flux.fromStream(
                    Stream.generate(() -> new FlowerEvents(flower, new Date()))
            );
            return Flux.zip(interval, events).map(Tuple2::getT2);
        });
    }
}
