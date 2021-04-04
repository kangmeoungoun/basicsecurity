package io.security.basicsecurity.security.service;

import io.security.basicsecurity.domain.Account;
import io.security.basicsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Account account = userRepository.findByUsername(username)
                                        .orElseThrow(() -> new UsernameNotFoundException("UsernameNotFoundException"));

        return new AccountContext(account , getGrantedAuthorities(account));
    }


    private List<GrantedAuthority> getGrantedAuthorities(Account account){
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(account.getRole()));
        return roles;
    }
}
