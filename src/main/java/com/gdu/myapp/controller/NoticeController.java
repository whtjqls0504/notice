package com.gdu.myapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.myapp.service.NoticeService;

import lombok.RequiredArgsConstructor;


@RequestMapping("/notice")
@RequiredArgsConstructor
@Controller
public class NoticeController {

  private final NoticeService noticeService;

  @GetMapping("/list.do")
  public String list(Model model) {
    model.addAttribute("noticeList", noticeService.getNoticeList());
    return "notice/list";
  }
  
  @GetMapping("/write.do")
  public String write() {
    return "notice/write";
  }
  
  @PostMapping("/add.do")
  public String add(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("addResult", noticeService.addNotice(request));
    return "redirect:/notice/list.do";
  }
  
  @GetMapping("/detail.do")
  public String detail(HttpServletRequest request, Model model) {
    model.addAttribute("notice", noticeService.getNotice(request));
    return "notice/detail";
  }
  
  
  @GetMapping("/remove.do")
  public String remove(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("removeResult", noticeService.removeNotice(request));
    return "redirect:/notice/list.do";
  }
}
