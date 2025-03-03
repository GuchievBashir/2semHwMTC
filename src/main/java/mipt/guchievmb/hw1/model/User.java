package mipt.guchievmb.hw1.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class User {

  @Size(min = 10, max = 100)
  private String id;

  @NotEmpty
  private String name;

  @Min(10)
  @Max(100)
  private int age;

}
