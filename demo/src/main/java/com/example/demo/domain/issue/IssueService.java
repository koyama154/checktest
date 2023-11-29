package com.example.demo.domain.issue;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;

    public List<IssueEntity> findAll() {
        return issueRepository.findAll();
    }

    @Transactional
    public void create(String summary, String description) {
        IssueEntity newIssue = new IssueEntity(summary, description);
        issueRepository.save(newIssue);
    }

    public Optional<IssueEntity> findById(long issueId) {
        return issueRepository.findById(issueId);
    }

    // 課題の削除
    @Transactional
    public void delete(Long id) {
        Optional<IssueEntity> issueOptional = findById(id);
        if (issueOptional.isPresent()) {
            issueRepository.delete(issueOptional.get());
        } else {
            throw new EntityNotFoundException("Issue with ID " + id + " not found.");
        }
    }
}
