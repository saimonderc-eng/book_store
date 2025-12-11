package com.example.book_store.service;

import com.example.book_store.dto.*;
import com.example.book_store.entity.Role;
import com.example.book_store.entity.User;
import com.example.book_store.exception.NotFoundException;
import com.example.book_store.exception.UsernameAlreadyTakenException;
import com.example.book_store.mapper.UserMapper;
import com.example.book_store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    private AuthResponse generateTokens(User user) {
        return AuthResponse.builder()
                .accessToken(jwtService.generateAccessToken(user))
                .refreshToken(jwtService.generateRefreshToken(user))
                .build();
    }

    public AuthResponse register(UserCreateDto dto) throws BadRequestException {
        log.info("CREATION start");
        User user1 = userRepository.findByUsername(dto.getUsername())
                .orElse(null);
        if (user1 != null) {
            throw new BadRequestException("user with this username already exists!");
        }
        User user = userMapper.toEntity(dto);
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));

        Role role = roleService.getUserRole();
        user.setRoles(List.of(role));

        log.info("ACCOUNT created");
        userRepository.save(user);

        return generateTokens(user);
    }

    public AuthResponse login(AuthRequest request) throws BadRequestException {
        User user1 = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("password or username is incorrect!"));
        banStatusCheck(user1);

        if (!passwordEncoder.matches(request.getPassword(), user1.getPasswordHash())) {
            throw new BadRequestException("password or username is incorrect!");
        }

        return generateTokens(user1);
    }

    public void changePassword(ChangePasswordDto dto) throws BadRequestException {
        User user1 = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new NotFoundException("user not found!"));
        banStatusCheck(user1);
        if (!passwordEncoder.matches(dto.getCurrentPassword(), user1.getPasswordHash())) {
            throw new BadRequestException("passwords don't match!");
        }
        if (!dto.getNewPassword().equals(dto.getNewPasswordConfirmation())) {
            throw new BadRequestException("passwords should be equal!");
        }

        user1.setPasswordHash(passwordEncoder.encode(dto.getNewPassword()));
        userRepository.save(user1);
    }

    public AuthResponse refreshToken(RefreshTokenDto dto) throws BadRequestException {
        if (dto.getRefreshToken() == null) {
            throw new BadRequestException("refresh token is missing!");
        }
        String username = jwtService.extractUsername(dto.getRefreshToken());
        User user1 = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("user not found!"));

        if (!jwtService.isTokenValid(dto.getRefreshToken(), user1)) {
            throw new BadRequestException("Refresh token is invalid!");
        }
        return generateTokens(user1);
    }

    public void assignRole(Long userId, Long roleId) throws BadRequestException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("user not found!"));
        Role role = roleService.getRoleById(roleId);

        if (user.getRoles().contains(role)) {
            throw new BadRequestException("user already have this role!");
        }
        user.getRoles().add(role);

        userRepository.save(user);
    }

    public void unassignRole(Long userId, Long roleId) throws BadRequestException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("user not found!"));
        Role role = roleService.getRoleById(roleId);

        if (!user.getRoles().contains(role)) {
            throw new BadRequestException("user doesn't have this role!");
        }

        user.getRoles().remove(role);

        userRepository.save(user);
    }

    public User getCurrentUser() throws BadRequestException {
        User user1 = Optional.of(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .map(User.class::cast)
                .orElseThrow(() -> new NotFoundException("user not found!"));

        banStatusCheck(user1);
        return user1;
    }

    public void banUser(Long userId) throws BadRequestException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("user not found!"));
        if (user.getIsBanned()) {
            throw new BadRequestException("user already banned!");
        }
        user.setIsBanned(true);
        userRepository.save(user);
    }

    public void banStatusCheck(User user) throws BadRequestException {
        if (user.getIsBanned()) {
            throw new BadRequestException("your account is banned!");
        }
    }

    public UserResponseDto updateUser(UserUpdateDto dto) throws BadRequestException {
        User user1 = getCurrentUser();
        banStatusCheck(user1);

        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new UsernameAlreadyTakenException("Username already taken!");
        }
        safetySaveValue(dto.getUsername(), user1::setUsername);
        safetySaveValue(dto.getEmail(), user1::setEmail);
        safetySaveValue(dto.getFullName(), user1::setFullName);
        safetySaveValue(dto.getDateOfBirth(), user1::setDateOfBirth);

        userRepository.save(user1);

        return userMapper.toDto(user1);
    }

    public <T> void safetySaveValue(T value, Consumer<T> consumer) {
        if (value != null) {
            consumer.accept(value);
        }
    }

    public UserResponseDto findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toDto)
                .orElseThrow(() -> new NotFoundException("user not found"));

    }

    public void deleteCurrentUser() throws BadRequestException {
        User user1 = getCurrentUser();
        userRepository.delete(user1);
    }
}