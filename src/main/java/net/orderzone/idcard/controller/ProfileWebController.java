package net.orderzone.idcard.controller;

import lombok.RequiredArgsConstructor;
import net.orderzone.idcard.model.Profile;
import net.orderzone.idcard.model.ProfileType;
import net.orderzone.idcard.service.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ProfileWebController {

    private final ProfileService profileService;

    @GetMapping("/profiles")
    public String profileList(Model model) {

        model.addAttribute(
                "profiles",
                profileService.getAllProfiles()
        );

        return "profile-list";
    }

    @GetMapping("/profiles/create")
    public String createProfile(Model model) {

        model.addAttribute(
                "profile",
                new Profile()
        );

        model.addAttribute(
                "types",
                ProfileType.values()
        );

        return "profile-form";
    }

    @PostMapping("/profiles/save")
    public String saveProfile(
            @RequestParam String fullName,
            @RequestParam ProfileType type,
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String email
    ) {

        Profile profile = new Profile();

        profile.setFullName(fullName);
        profile.setType(type);
        profile.setDepartment(department);
        profile.setEmail(email);

        profileService.saveProfile(profile);

        return "redirect:/profiles";
    }

    @GetMapping("/profiles/view/{id}")
    public String viewProfile(
            @PathVariable Long id,
            Model model
    ) {

        Profile profile =
                profileService
                        .getProfileById(id)
                        .orElseThrow();

        model.addAttribute(
                "profile",
                profile
        );

        return "profile-view";
    }

    @GetMapping("/profiles/edit/{id}")
    public String editProfile(
            @PathVariable Long id,
            Model model
    ) {

        Profile profile =
                profileService
                        .getProfileById(id)
                        .orElseThrow();

        model.addAttribute(
                "profile",
                profile
        );

        model.addAttribute(
                "types",
                ProfileType.values()
        );

        return "profile-form";
    }

    @GetMapping("/profiles/delete/{id}")
    public String deleteProfile(
            @PathVariable Long id
    ) {

        profileService.deleteProfile(id);

        return "redirect:/profiles";
    }
}