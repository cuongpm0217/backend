package com.hongha.ver1.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hongha.ver1.entities.CustomUserDetail;
import com.hongha.ver1.entities.History;
import com.hongha.ver1.entities.User;
import com.hongha.ver1.entities.enums.EAction;
import com.hongha.ver1.repositories.HistoryRepository;
import com.hongha.ver1.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private HistoryRepository historyRepo;
    private History history;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        user = userRepo.findByUsername(username);
        if(user==null)
        throw new UsernameNotFoundException(username);
        else
            return new CustomUserDetail(user);
    }
    public void updateProfile(User user) {
		userRepo.save(user);
		history = new History(User.class.getName(), EAction.UPDATE, user.getId().toString());
		historyRepo.save(history);
	}
}
