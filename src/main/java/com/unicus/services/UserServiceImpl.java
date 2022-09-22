package com.unicus.services;

import com.unicus.dtos.requests.UserRequest;
import com.unicus.dtos.responses.Response;
import com.unicus.dtos.responses.UserResponse;
import com.unicus.models.User;
import com.unicus.repositories.UserRepository;
import com.unicus.Exceptions.EmailExistsException;
import com.unicus.Exceptions.UnicusException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.unicus.models.Roles.*;
import static com.unicus.utils.Validator.*;
import static org.springframework.http.HttpStatus.OK;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Response registerUser(UserRequest request) throws UnicusException {
        if (userRepository.existsByEmail(request.email())) throw new EmailExistsException("email used");
        if(userRepository.existsByPhoneNumber(request.phoneNumber())) throw new UnicusException("phone number used");
        validateEmail(request);
        validatePassword(request);
        validatePhoneNumber(request);
        User user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .phoneNumber(request.phoneNumber())
                .roles(new HashSet<>(Set.of(USER)))
                .build();
        userRepository.save(user);
        return new Response(OK, "User registered successfully");
    }

    @Override
    public UserResponse getUser(String email) {
        return userRepository.findUserByEmail(email).toUserResponse();
    }

    @Override
    public Response deleteUser(String email) {
        userRepository.deleteByEmail(email);
        return new Response(OK, "User deleted successfully");
    }

    @Override
    public Response updateUser(UserRequest request) {
        User user = userRepository.findUserByEmail(request.email());
        mapRequestToUser(request,user);
        if(request.password() != null) user.setPassword(passwordEncoder.encode(request.password()));

        userRepository.save(user);
        return new Response(OK, "User updated successfully");
    }
}
