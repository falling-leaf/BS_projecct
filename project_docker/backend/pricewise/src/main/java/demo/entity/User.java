package demo.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 128, nullable = false, unique = true)
    String account;
    @Column(length = 128, nullable = false)
    String password;
    @Column(length = 128, nullable = false, unique = true)
    String email;
}
