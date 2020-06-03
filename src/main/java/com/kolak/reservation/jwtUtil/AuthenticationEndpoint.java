package com.kolak.reservation.jwtUtil;


import com.kolak.reservation.jwtUtil.model.AuthentcationResponse;
import com.kolak.reservation.jwtUtil.model.AuthenticationRequest;
import com.kolak.reservation.securityConfig.UserDetailServiceImpl;
import com.kolak.reservation.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authenticate")
public class AuthenticationEndpoint {

    private AuthenticationManager authenticationManager;
    private UserDetailServiceImpl userDetailService;
    private JwtUtil jwtUtil;

    @Autowired
    public AuthenticationEndpoint(AuthenticationManager authenticationManager, UserDetailServiceImpl userDetailService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping()
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );

        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password!", e);
        }

        final User userDetails = (User) userDetailService.loadUserByUsername(authenticationRequest.getUsername());

        String generatedToken = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthentcationResponse(generatedToken));
    }
}
