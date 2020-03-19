package com.kakao.api.domain;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;
}
