package com.jwtauthdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwtauthdemo.Model.JwtRequest;
import com.jwtauthdemo.Model.JwtResponse;
import com.jwtauthdemo.jwt.JwtHelper;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class JwtAuthenticationController {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtHelper jwtHelper;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        try{

            authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
        }catch(UsernameNotFoundException e ){
            e.printStackTrace();
            throw new Exception("user not found ");
        }
  /// authenticate user
        UserDetails userDetails=  this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtHelper.generateToken(userDetails);
        String username = userDetails.getUsername();
        return ResponseEntity.ok(new JwtResponse(token,username));
    }

    private  void authenticate(String username, String password) throws Exception {

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));

        }catch(DisabledException e){
            throw new Exception("User Disable"+e.getMessage());
        }catch (BadCredentialsException e){
            throw new Exception("invalid credentials"+e.getMessage());
        }

    }
	
	
	
	

}
