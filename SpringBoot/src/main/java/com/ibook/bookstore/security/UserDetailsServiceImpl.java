package com.ibook.bookstore.security;

import com.google.common.base.Optional;
import com.ibook.bookstore.Dao.UserDao;
import com.ibook.bookstore.Entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        System.out.println(name);
        Optional<User> userOptional = userDao.findByName(name);
        User user = userOptional.orNull();
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        /**
         * Check if session expires
         */
        UserDetails userDetails;
        if (name.equals("admin")) {
            userDetails =  new org.springframework.security.core.userdetails.User(
                    user.getName(), user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN, ROLE_USER")
            );
        } else {
            userDetails = new org.springframework.security.core.userdetails.User(
                    user.getName(), user.getPassword(),
                    true, true, true, !user.isState(),
                    AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER")
            );
        }
        logger.info("username:" + user.getName());
        logger.info("password:" + user.getPassword());
        return userDetails;
    }
}
