package net.orderzone.idcard.service;

import lombok.RequiredArgsConstructor;
import net.orderzone.idcard.model.Profile;
import net.orderzone.idcard.model.ProfileType;
import net.orderzone.idcard.repository.ProfileRepository;
import org.springframework.stereotype.Service;
import java.time.Year;
import java.util.UUID;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public Optional<Profile> getProfileById(Long id) {
        return profileRepository.findById(id);
    }

    public Profile saveProfile(Profile profile) {

        if (profile.getUuid() == null ||
            profile.getUuid().isBlank()) {

            profile.setUuid(
                    UUID.randomUUID().toString()
            );
        }

        if (profile.getRegistrationNumber() == null ||
            profile.getRegistrationNumber().isBlank()) {

            String prefix;

            switch (profile.getType()) {

                case STUDENT:
                    prefix = "STD";
                    break;

                case EMPLOYEE:
                    prefix = "EMP";
                    break;

                default:
                    prefix = "USR";
            }

            profile.setRegistrationNumber(
                    prefix +
                    "-" +
                    Year.now().getValue() +
                    "-" +
                    (System.currentTimeMillis() % 10000)
            );
        }

        return profileRepository.save(profile);
    }

    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }

    public List<Profile> findByType(ProfileType type) {
        return profileRepository.findByType(type);
    }

    public boolean existsByRegistrationNumber(String registrationNumber) {
        return profileRepository.existsByRegistrationNumber(registrationNumber);
    }
}