package mipt.guchievmb.hw1.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "universities")
public class University {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;
}