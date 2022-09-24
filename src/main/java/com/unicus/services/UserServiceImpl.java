package com.unicus.services;

import com.unicus.dtos.requests.LoginRequest;
import com.unicus.dtos.requests.UserRequest;
import com.unicus.dtos.responses.Response;
import com.unicus.dtos.responses.UserResponse;
import com.unicus.models.User;
import com.unicus.repositories.UserRepository;
import com.unicus.Exceptions.EmailExistsException;
import com.unicus.Exceptions.UnicusException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.unicus.models.Roles.*;
import static com.unicus.utils.Validator.*;
import static org.springframework.http.HttpStatus.OK;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Response registerUser(UserRequest request) throws UnicusException {
        if (userRepository.existsByEmail(request.email())) throw new EmailExistsException("email used");

        validateEmail(request);
        validatePassword(request);
        if (request.phoneNumber() != null) {
            validatePhoneNumber(request);
            if (userRepository.existsByPhoneNumber(request.phoneNumber()))
                throw new UnicusException("phone number used");
        }
        User user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .phoneNumber(request.phoneNumber())
                .roles(new HashSet<>(Set.of(USER)))
                .day(request.day())
                .month(request.month())
                .year(request.year())
                .aboutMe(request.aboutMe())
                .imageUrl(request.imageUrl())
                .build();
        log.info("user -> {}", user);
        userRepository.save(user);
        return new Response(OK, "user registered successfully");
    }

    @Override
    public UserResponse getUser(String email) {
        return userRepository.findUserByEmail(email).toUserResponse();
    }

    @Override
    public Response deleteUser(String id) {
        userRepository.deleteById(id);
        return new Response(OK, "User deleted successfully");
    }

    @Override
    public Response updateUser(UserRequest request) {
        User user = userRepository.findUserByEmail(request.email());
        mapRequestToUser(request, user);
        if (request.password() != null) user.setPassword(passwordEncoder.encode(request.password()));

        userRepository.save(user);
        return new Response(OK, "User updated successfully");
    }

    @Override
    public UserResponse login(LoginRequest loginRequest) {
        return userRepository.findUserByEmail(loginRequest.email()).toUserResponse();
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(User::toUserResponse).toList();
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }
}
