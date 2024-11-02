package dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.*;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
public class User {
    Integer id;
    String name;
    Credentials credentials;
    String image;
    List<Car> cars;
}
