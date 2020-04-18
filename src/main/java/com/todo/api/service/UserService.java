package com.todo.api.service;

import com.todo.api.domain.UserEntity;
import com.todo.api.dto.request.SignInRequestDto;
import com.todo.api.dto.request.SignUpRequestDto;
import com.todo.api.handler.authentication.JwtTokenProvider;
import com.todo.api.model.CommonResult;
import com.todo.api.model.SingleResult;
import com.todo.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ResponseService responseService;

    public SingleResult<String> signin(SignInRequestDto request) {
        Optional<UserEntity> user = userRepository.findByUid(request.getId());
        AtomicReference<SingleResult<String>> result = null;

        user.ifPresent(entity -> {
            if (!passwordEncoder.matches(request.getPassword(), entity.getPassword())) {
                try {
                    throw new Exception("패스워드가 틀립니다.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                result.set(responseService.getSingleResult(
                        jwtTokenProvider.createToken(
                                String.valueOf(entity.getId()),
                                entity.getRoles()
                        )
                ));
            }
        });

        return result.get();
    }

    @Transactional
    public CommonResult signUp(SignUpRequestDto request) {
        userRepository.save(UserEntity.builder()
                .uid(request.getId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .roles(Collections.singletonList("ROLE_USER"))
                .build()
        );

        return responseService.getSuccessResult();
    }

}
