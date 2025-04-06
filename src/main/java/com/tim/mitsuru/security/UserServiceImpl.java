package  com.tim.mitsuru.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tim.mitsuru.model.UserLogin;

@Service
public class UserServiceImpl implements UserDetailsService{

	private UserLogin userLogin = new UserLogin("mitsuru", "nachan");

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// We can check Encode password, check data from DB, or anything what we want to do
		// in production, can use User.builder.passwordEncoder(<EncodeType>).username.password.buid to set UserDetails
		UserDetails userDetails = User.withDefaultPasswordEncoder()
			.username(this.userLogin.getUsername())
			.password(this.userLogin.getPassword())
			.build();
		
		return userDetails;
    }
}

