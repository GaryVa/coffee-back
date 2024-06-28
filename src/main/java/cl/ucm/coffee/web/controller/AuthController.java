package cl.ucm.coffee.web.controller;

import cl.ucm.coffee.persitence.entity.UserEntity;
import cl.ucm.coffee.persitence.entity.UserRoleEntity;
import cl.ucm.coffee.service.IUserService;
import cl.ucm.coffee.service.dto.LoginDto;
import cl.ucm.coffee.service.dto.RegistroDto;
import cl.ucm.coffee.web.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private  JwtUtil jwtUtil;

    @Autowired
    private IUserService userService;

//    @Autowired
//    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
//        this.authenticationManager = authenticationManager;
//        this.jwtUtil = jwtUtil;
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(login);

       // System.out.println(authentication.isAuthenticated());
       // System.out.println(authentication.getPrincipal());

        String jwt = this.jwtUtil.create(loginDto.getUsername());
        Map map = new HashMap<>();
        map.put("token",jwt);
        return ResponseEntity.ok(map);
        //return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
    }

    @PostMapping("/create")
    public ResponseEntity<?> registro(@RequestBody RegistroDto registroDto){
        System.out.println("paso registro");
       UserEntity userEntity= userService.crearUsuario(registroDto);

        return ResponseEntity.ok(userEntity);
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscar() {
        return ResponseEntity.ok(userService.listarUsuarios());
    }
}
