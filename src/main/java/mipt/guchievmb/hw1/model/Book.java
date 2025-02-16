package mipt.guchievmb.hw1.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Book {

  @Size(min = 10, max = 100)
  int id;

  @NotEmpty
  String title;

  @NotEmpty
  String author;

}
