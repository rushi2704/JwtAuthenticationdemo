# JwtAuthenticationdemo
Create Basic Rest API that will return the list of Details like Employee,Student or any other.

Secure the Rest API by adding security dependecy

Use the properties file to create custom username and password for authentication

Create the SpringSecurityConfig class to define the bean like PasswordEncoder, UserDetailsService and AuthenticationManager

Create JwtAuthenticationEntryPoint Class Which implements AuthenticationEntryPoint interface and override method commence

Create JwtHelper Class which is used to perform action like validateToken and generateToken etc


Create JWTAuthenticationFilter class which is used for the filter purpose

Create SecurityFilterConfig class to define request processing logic

Create JwtRequest and JwtResponse class
Create JwtAuthenticationController to return the JwtResponse if everything works fine
