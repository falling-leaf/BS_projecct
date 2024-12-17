package demo.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 128, nullable = false)
    String account;
    @Column(length = 512, nullable = false)
    String item_name;
    @Column(nullable = false)
    Double price;
    @Column(nullable = false)
    LocalDateTime item_time;
    @Column(length = 128, nullable = false)
    String platform;
}
