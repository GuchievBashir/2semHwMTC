package mipt.guchievmb.hw1.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class University {

  @Size(min = 10, max = 100)
  private int id;

  @NotEmpty
  private String name;

}
