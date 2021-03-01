package com.example.demo.entity;

import static javax.persistence.EnumType.STRING;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "alert")
public class Alert extends AbstractPersistable<Long> {

    @Column(name = "title")
    private String title;

    @Enumerated(STRING)
    @Column(name = "target")
    private AlertTarget target;

    public Alert(Long id) {
        setId(id);
    }

    public enum AlertTarget {
        USER, SERVICE
    }
}
