package demo.security.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.security.models.Users;
import demo.security.repository.UsersRepository;
import demo.security.request.AuthenticationRequest;
import demo.security.response.AuthenticationToken;
import demo.security.service.AuthorizationService;
import demo.security.service.TokenService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private TokenService tokenService;
    
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationRequest data){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Users) auth.getPrincipal());

        return ResponseEntity.ok(new AuthenticationToken(token));
    }

    @PostMapping("/user")
    public ResponseEntity<Users> user(HttpServletResponse response) throws UsernameNotFoundException{
        System.out.println("testanuy");
        // Users teste = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(AuthorizationService.userAuthenticated());
    }

    // @PostMapping("register")
    // public ResponseEntity register(@RequestBody  @Valid AuthenticationRequest data) {
    //     if(this.userRepository.findByLogin(data.getLogin() != null))
    //         return ResponseEntity.badRequest().build();
        
    //         String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
    //         Users newUser = new Users(1L,data.getLogin(), encryptedPassword, null);
    // }
    

}
