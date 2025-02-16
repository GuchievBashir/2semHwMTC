package mipt.guchievmb.hw1.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Book {

  @Size(min = 10, max = 100)
  private String id;

  @NotEmpty
  private String title;

  @NotEmpty
  private String author;

}
