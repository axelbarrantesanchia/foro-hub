package com.proyecto.forohub.controller;

import com.proyecto.forohub.dominio.usuarios.DatosAutenticarUsuario;
import com.proyecto.forohub.dominio.usuarios.Usuario;
import com.proyecto.forohub.infra.security.DatosJWTToken;
import com.proyecto.forohub.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody DatosAutenticarUsuario datosAutenticarUsuario){
        Authentication authtoken = new UsernamePasswordAuthenticationToken(datosAutenticarUsuario.usuario(), datosAutenticarUsuario.contrasenia());
        var usuarioAutenticado = authenticationManager.authenticate(authtoken);
        var JWTToken = tokenService
                .generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTToken));
    }
}
