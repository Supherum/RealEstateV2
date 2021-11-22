package com.salesianos.triana.realestate.v2.usuario.model;

import com.salesianos.triana.realestate.v2.inmobiliaria.model.Inmobiliaria;
import com.salesianos.triana.realestate.v2.interesa.model.Interesa;
import com.salesianos.triana.realestate.v2.vivienda.model.Vivienda;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder

@Table(name = "usuarios")
@EntityListeners(AuditingEntityListener.class)

public class Usuario implements Serializable, UserDetails {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )

    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String nombre, apellidos, direccion, email, telefono, avatar;

    private String password;
    @NaturalId
    private String nick;

    @Builder.Default
    private LocalDateTime lastPasswordChangeTime=LocalDateTime.now();

    @CreatedDate
    private LocalDateTime createdTime;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    // ASOCIACIONES

    @ManyToOne
    private Inmobiliaria inmobiliaria;

    @OneToMany (mappedBy = "usuario")
    @Builder.Default
    private List<Vivienda> listVivienda=new ArrayList<>();

    @OneToMany (mappedBy = "usuario")
    @Builder.Default
    private List<Interesa> listIntereses=new ArrayList<>();

    // HELPER

    //Inmobiliaria
    public void addInmobiliariaToUser(Inmobiliaria i){
        this.inmobiliaria=i;
        i.getListaUsuarios().remove(this);
    }
    public void removeInmobiliariaToUser(Inmobiliaria i){
        this.inmobiliaria=null;
        i.getListaUsuarios().remove(this);
    }

    // Métodos sobrescritos

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol.name()));
    }

    @Override
    public String getUsername() {
        return nick;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
