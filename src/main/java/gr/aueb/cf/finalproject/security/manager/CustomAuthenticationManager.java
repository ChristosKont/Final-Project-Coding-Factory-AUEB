//package gr.aueb.cf.finalproject.security.manager;
//
//import com.ltp.gradesubmission.entity.User;
//import com.ltp.gradesubmission.service.UserService;
//import gr.aueb.cf.finalproject.model.MovieUser;
//import gr.aueb.cf.finalproject.service.UserServiceImpl;
//import lombok.AllArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//@AllArgsConstructor
//public class CustomAuthenticationManager implements AuthenticationManager {
//
//    private UserServiceImpl userService;
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        MovieUser user = userService.getMovieUser(authentication.getDetails());
//        if (!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
//            throw new BadCredentialsException("You provided incorrect password  ");
//        }
//
//        return new UsernamePasswordAuthenticationToken(authentication.getName(), user.getPassword());
//    }
//}
