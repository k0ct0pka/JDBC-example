package dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import java.io.File;
import java.sql.Blob;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
public class Car {
    Integer id;
    String mark;
    String model;
    Integer year;
    Double price;
    Blob image;
    User user;
}
