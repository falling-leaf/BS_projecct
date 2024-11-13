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
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 512, nullable = false, unique = true)
    String item_name;
    @Column(nullable = false)
    Double price;
    @Column(length = 512)
    String shop_name;
    @Column(nullable = false, unique = true)
    LocalDateTime item_time;
    @Column(length = 128, nullable = false)
    String platform;
    @Column(length = 512)
    String image;
    @Column(length = 512)
    String item_id;
}

