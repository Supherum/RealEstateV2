package com.salesianos.triana.realestate.v2.usuario.service;

import com.salesianos.triana.realestate.v2.inmobiliaria.model.Inmobiliaria;
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

import java.util.Optional;
import java.util.UUID;

@Service("userDetailsService")
@RequiredArgsConstructor
public class AuthService extends BaseService<Usuario, UUID, UsuarioRepository> implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final UserRegisterDtoConverter userLoginDtoConverter;

    // BUSCA A UN USUARIO POR SU NICK
    @Override
    public UserDetails loadUserByUsername(String nick) throws UsernameNotFoundException {
        return usuarioRepository.findFirstByNick(nick)
                .orElseThrow(()-> new UsernameNotFoundException(nick + " no encontrado"));
    }

    public Optional<Usuario> findByNick(String nick){
        return usuarioRepository.findFirstByNick(nick);
    }


    // Crea un nuevo Usuario
    public Usuario saveUsuario(UserRegisterDto dto, Rol rol){
        if(dto.getPassword().equals(dto.getPassword2()) ||
                dto.getApellidos()!=null ||
                dto.getEmail() != null ||
                dto.getNick() != null ||
                dto.getNombre()!=null){
           return save(userLoginDtoConverter.UserLoginDtoToUser(dto,rol));
        }
        return null;
    }

    public Usuario saveGestorConInmobiliaria(UserRegisterDto dto, Rol rol, Inmobiliaria i){
        if(dto.getPassword().equals(dto.getPassword2()) ||
                dto.getApellidos()!=null ||
                dto.getEmail() != null ||
                dto.getNick() != null ||
                dto.getNombre()!=null){
          Usuario u=  userLoginDtoConverter.UserLoginDtoToUser(dto,rol);
          u.addInmobiliariaToUser(i);
            return save(u);
        }
        return null;
    }




}
