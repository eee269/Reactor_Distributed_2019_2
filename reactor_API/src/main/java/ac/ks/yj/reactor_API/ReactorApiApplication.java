package ac.ks.yj.reactor_API;

import ac.ks.yj.reactor_API.route.RouteHandles;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ReactorApiApplication {

	@Bean
	RouterFunction<?> routes(RouteHandles routeHandles) {
		return RouterFunctions.route(
				RequestPredicates.GET("/flowers"), routeHandles::allFlowers)
				.andRoute(RequestPredicates.GET("/flowers/{flowerId}"), routeHandles::flowerById)
				.andRoute(RequestPredicates.GET("/flowers/{flowerId}/events"), routeHandles::events);
	}


	public static void main(String[] args) {
		SpringApplication.run(ReactorApiApplication.class, args);
	}

}

