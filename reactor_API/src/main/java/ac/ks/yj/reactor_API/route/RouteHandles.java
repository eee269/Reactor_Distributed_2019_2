package ac.ks.yj.reactor_API.route;

import ac.ks.yj.reactor_API.domain.Flower;
import ac.ks.yj.reactor_API.domain.FlowerEvents;
import ac.ks.yj.reactor_API.service.FluxFlowerService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class RouteHandles{

    private final FluxFlowerService fluxFlowerService;

    public RouteHandles(FluxFlowerService fluxFlowerService) {
        this.fluxFlowerService = fluxFlowerService;
    }

    public Mono<ServerResponse> allFlowers(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .body(fluxFlowerService.all(), Flower.class)
                .doOnError(throwable -> new IllegalStateException("Oh,,"));
    }

    public Mono<ServerResponse> flowerById(ServerRequest serverRequest) {
        String flowerId = serverRequest.pathVariable("flowerId");
        return ServerResponse.ok()
                .body(fluxFlowerService.byId(flowerId), Flower.class)
                .doOnError(throwable -> new IllegalStateException("try again"));
    }
    public Mono<ServerResponse> events(ServerRequest serverRequest) {
        String flowerId = serverRequest.pathVariable("flowerId");
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(fluxFlowerService.streams(flowerId), FlowerEvents.class)
                .doOnError(throwable -> new IllegalStateException("..!"));
    }
}
