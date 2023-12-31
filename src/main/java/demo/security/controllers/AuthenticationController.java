package demo.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.security.models.Users;
import demo.security.repository.UsersRepository;
import demo.security.request.AuthenticationRequest;
import demo.security.service.TokenService;
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

        return ResponseEntity.ok(token);
    }

    // @PostMapping("register")
    // public ResponseEntity register(@RequestBody  @Valid AuthenticationRequest data) {
    //     if(this.userRepository.findByLogin(data.getLogin() != null))
    //         return ResponseEntity.badRequest().build();
        
    //         String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
    //         Users newUser = new Users(1L,data.getLogin(), encryptedPassword, null);
    // }
    

}
