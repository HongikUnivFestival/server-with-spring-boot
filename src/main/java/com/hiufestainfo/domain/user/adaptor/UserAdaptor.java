package com.hiufestainfo.domain.user.adaptor;

import com.hiufestainfo.domain.user.entity.User;
import com.hiufestainfo.domain.user.exception.UserNotFoundException;
import com.hiufestainfo.domain.user.repository.UserRepository;
import com.hiufestainfo.global.annotation.Adaptor;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class UserAdaptor {
    private final UserRepository userRepository;

    public User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException());
    }

    public User findByEmail(String email){
        return userRepository.findByAuthInfoEmail(email)
                .orElseThrow(()-> new UserNotFoundException());
    }
    public Boolean checkEmail(String email){
        return userRepository.existsByAuthInfoEmail(email);
    }
    public User save(User user){
        return userRepository.save(user);
    }
}
