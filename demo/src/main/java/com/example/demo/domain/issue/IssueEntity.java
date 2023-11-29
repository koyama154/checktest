package com.example.demo.domain.issue;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "issues" , schema = "public")
public class IssueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String summary;
    private String description;

    public IssueEntity(String summary, String description) {
        this.summary = summary;
        this.description = description;
    }
}
