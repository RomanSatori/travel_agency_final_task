package com.travel_agency.controller;

import com.travel_agency.domain.Tour;
import com.travel_agency.domain.User;
import com.travel_agency.repository.TourRepository;
import com.travel_agency.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class TourController {
    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private TourService tourService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model,
                       @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Tour> page = tourService.tourList(pageable, filter);

        model.addAttribute("page", page);
        model.addAttribute("url", "/main");
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Tour tour,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file

    ) throws IOException {
        tour.setAuthor(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("message", tour);
        } else {
            saveFile(tour, file);

            model.addAttribute("message", null);

            tourRepository.save(tour);
        }

        Iterable<Tour> messages = tourRepository.findAll();

        model.addAttribute("messages", messages);

        return "main";
    }

    private void saveFile(@Valid Tour tour, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            tour.setFilename(resultFilename);
        }
    }

    @GetMapping("/user-tours/{author}")
    public String userTours(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User author,
            Model model,
            @RequestParam(required = false) Tour tour,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Tour> page = tourService.tourListForUser(pageable, currentUser, author);

        model.addAttribute("page", page);
        model.addAttribute("message", tour);
        model.addAttribute("isCurrentUser", currentUser.equals(author));
        model.addAttribute("url", "/user-tours/" + author.getId());

        return "userMessages";
    }

    @PostMapping("/user-tours/{user}")
    public String updateTour(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam("tour") Tour tour,
            @RequestParam("text") String text,
            @RequestParam("tag") String tag,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (tour.getAuthor().equals(currentUser)) {
            if (!StringUtils.isEmpty(text)) {
                tour.setText(text);
            }

            if (!StringUtils.isEmpty(tag)) {
                tour.setTag(tag);
            }

            saveFile(tour, file);

            tourRepository.save(tour);
        }

        return "redirect:/user-tours/" + user;
    }
}
