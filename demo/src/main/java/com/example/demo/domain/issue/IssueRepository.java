package com.example.demo.domain.issue;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IssueRepository extends JpaRepository<IssueEntity, Long> {
}
