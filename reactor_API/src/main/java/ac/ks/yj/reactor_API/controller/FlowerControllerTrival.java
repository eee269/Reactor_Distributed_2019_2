package ac.ks.yj.reactor_API.controller;


import ac.ks.yj.reactor_API.domain.Flower;
import ac.ks.yj.reactor_API.domain.FlowerEvents;
import ac.ks.yj.reactor_API.service.FluxFlowerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/flower-trivial")
public class FlowerControllerTrival {

    private final FluxFlowerService fluxFlowerService;

    public FlowerControllerTrival(FluxFlowerService fluxFlowerService) {
        this.fluxFlowerService = fluxFlowerService;
    }

    @GetMapping("/flowers")
    public Flux<Flower> all() { return fluxFlowerService.all(); }

    @GetMapping("/flowers/{flowerId}")
    public Mono<Flower> byId(@PathVariable String flowerId) { return fluxFlowerService.byId(flowerId); }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/flowers/{flowerId}/events")
    public Flux<FlowerEvents> eventsOsStreams(@PathVariable String flowerId) { return fluxFlowerService.streams(flowerId); }
}
