package com.company.service;

import com.company.dto.AttachDto;
import com.company.dto.AuthDto;
import com.company.dto.ProfileDto;
import com.company.dto.RegistrationDto;
import com.company.entity.AttachEntity;
import com.company.entity.ProfileEntity;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.company.exp.AppForbiddenException;
import com.company.exp.EmailAlreadyExistsException;
import com.company.exp.ItemNotFoundException;
import com.company.exp.PasswordOrEmailWrongException;
import com.company.repository.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private AttachService attachService;

    public ProfileDto login(AuthDto dto) {
        String pswd = DigestUtils.md5Hex(dto.getPassword());
        Optional<ProfileEntity> optional = profileRepository.
                findByEmailAndPassword(dto.getEmail(), pswd);

        if (optional.isEmpty()) {
            log.warn("Password or email wrong!: {}", dto);
            throw new PasswordOrEmailWrongException("Password or email wrong!");
        }

        ProfileEntity entity = optional.get();
        if (!entity.getStatus().equals(ProfileStatus.ACTIVE)) {
            log.warn("Not access: {}", dto);
            throw new AppForbiddenException("not access");
        }

        ProfileDto profileDto = new ProfileDto();
        profileDto.setName(entity.getName());
        profileDto.setSurname(entity.getSurname());
        profileDto.setEmail(entity.getEmail());
        profileDto.setRole(entity.getRole());
        //TODO
        profileDto.setJwt("");

        AttachEntity image = entity.getAttach();
        if (image != null) {
            AttachDto imageDto=new AttachDto();
            imageDto.setUrl(attachService.toOpenURL(imageDto.getId()));
            profileDto.setAttachDto(imageDto);
        }
        return profileDto;
    }

    public void registration(RegistrationDto dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());
        if (optional.isPresent()) {
            log.warn("Email already axists : {}", dto);
            throw new EmailAlreadyExistsException("Email already exists!");
        }

        ProfileEntity entity = toProfileEntity(dto);
        profileRepository.save(entity);

        //TODO
//        Thread thread = new Thread() {
//            @Override
//            public void run() {
//                sendVerificationEmail(entity);
//            }
//        };
//        thread.start();
    }

    public void verification(String jwt) {

    }

    public ProfileEntity toProfileEntity(RegistrationDto dto) {
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(DigestUtils.md5Hex(dto.getPassword()));
        entity.setRole(ProfileRole.USER);
        entity.setStatus(ProfileStatus.NOT_CONFIRMED);
        return entity;
    }
}
