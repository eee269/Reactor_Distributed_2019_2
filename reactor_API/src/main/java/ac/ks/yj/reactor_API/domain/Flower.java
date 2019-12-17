package ac.ks.yj.reactor_API.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;


@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Flower {
    @Id
    private String id;
    private String name;

}
