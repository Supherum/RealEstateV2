package com.salesianos.triana.realestate.v2.usuario.service;

import com.salesianos.triana.realestate.v2.shared.security.PasswordCodifier;
import com.salesianos.triana.realestate.v2.shared.service.BaseService;
import com.salesianos.triana.realestate.v2.usuario.dto.auth.UserRegisterDto;
import com.salesianos.triana.realestate.v2.usuario.dto.auth.UserRegisterDtoConverter;
import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import com.salesianos.triana.realestate.v2.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("userDetailsService")
@RequiredArgsConstructor
public class AuthService extends BaseService<Usuario, UUID, UsuarioRepository> implements UserDetailsService {

    private final PasswordCodifier codificador;
    private final UsuarioRepository usuarioRepository;
    private final UserRegisterDtoConverter userLoginDtoConverter;

    // BUSCA A UN USUARIO POR SU NICK
    @Override
    public UserDetails loadUserByUsername(String nick) throws UsernameNotFoundException {
        return usuarioRepository.findFirstByNick(nick)
                .orElseThrow(()-> new UsernameNotFoundException(nick + " no encontrado"));
    }


    // Crea un nuevo Usuario "Propietario"
    public Usuario saveUser(UserRegisterDto dto){
        if(dto.getPassword().contentEquals(dto.getPassword2()) ||
                dto.getApellidos()!=null ||
                dto.getEmail() != null ||
                dto.getNick() != null ||
                dto.getNombre()!=null){
           return userLoginDtoConverter.UserLoginDtoToUser(dto);
        }
        return null;
    }


}
