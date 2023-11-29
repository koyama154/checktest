package com.example.demo.web.issue;

import com.example.demo.domain.issue.IssueEntity;
import com.example.demo.domain.issue.IssueService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/issues")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    // 一覧画面の表示
    @GetMapping("")
    public String showList(Model model) {
        model.addAttribute("issueList", issueService.findAll());
        return "issues/list";
    }

    // 課題作成画面の表示
    @GetMapping("/creationForm")
    public String showCreationFrom(@ModelAttribute IssueForm form) {
        return "issues/creationForm";
    }

    @PostMapping("")
    public String create(@Validated IssueForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showCreationFrom(form);
        }
        issueService.create(form.getSummary(), form.getDescription());
        return "redirect:/issues";
    }

    // 課題詳細画面の表示
    @GetMapping("/{issueId}")
    public String showDetail(@PathVariable("issueId") long issueId, Model model) {
        Optional<IssueEntity> issueOptional = issueService.findById(issueId);

        if (issueOptional.isPresent()) {
            model.addAttribute("issue", issueOptional.get());
        } else {
            // issueが見つからない場合の処理（例: 404エラーページにリダイレクトする）
            return "redirect:/issues";
        }
        return "issues/detail";
    }

    // 課題の削除
    @PostMapping("/delete/{id}")
    public String deleteIssue(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            issueService.delete(id);
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/issues";
        }
        return "redirect:/issues";
    }
}
