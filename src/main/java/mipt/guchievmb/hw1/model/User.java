package mipt.guchievmb.hw1.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class User {

  int id;

  @NotEmpty
  String name;

  @Min(10)
  @Max(100)
  int age;

}
