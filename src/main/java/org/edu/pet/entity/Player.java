package org.edu.pet.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "players")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Player {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 48)
    private String name;
}