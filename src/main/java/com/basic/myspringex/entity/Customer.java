package com.basic.myspringex.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@DynamicUpdate
public class Customer {
    @Id // @Entity 어노테이션에서 id값을 주지 않으면 오류
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private Long age;

    @Column(nullable = false)
    private LocalDateTime entryDate = LocalDateTime.now();
}
