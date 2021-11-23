package com.salesianos.triana.realestate.v2.usuario.service;

import com.salesianos.triana.realestate.v2.shared.service.BaseService;
import com.salesianos.triana.realestate.v2.usuario.dto.auth.UserRegisterDto;
import com.salesianos.triana.realestate.v2.usuario.dto.auth.UserRegisterDtoConverter;
import com.salesianos.triana.realestate.v2.usuario.model.Rol;
import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import com.salesianos.triana.realestate.v2.usuario.repository.UsuarioRepository;
import com.salesianos.triana.realestate.v2.vivienda.model.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("userDetailsService")
@RequiredArgsConstructor
public class AuthService extends BaseService<Usuario, UUID, UsuarioRepository> implements UserDetailsService {

    private final PasswordEncoder codificador;
    private final UsuarioRepository usuarioRepository;
    private final UserRegisterDtoConverter userLoginDtoConverter;

    // BUSCA A UN USUARIO POR SU NICK
    @Override
    public UserDetails loadUserByUsername(String nick) throws UsernameNotFoundException {
        return usuarioRepository.findFirstByNick(nick)
                .orElseThrow(()-> new UsernameNotFoundException(nick + " no encontrado"));
    }



    // Crea un nuevo Usuario
    public Usuario saveUsuario(UserRegisterDto dto, Rol rol){
        if(dto.getPassword().contentEquals(dto.getPassword2()) ||
                dto.getApellidos()!=null ||
                dto.getEmail() != null ||
                dto.getNick() != null ||
                dto.getNombre()!=null){
           return save(userLoginDtoConverter.UserLoginDtoToUser(dto,rol));
        }
        return null;
    }




}
