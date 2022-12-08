package br.senai.sc.editoralivros.security.users;

import br.senai.sc.editoralivros.model.entity.Pessoa;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

@Data
public class UserJPA implements UserDetails {
    private Collection<GrantedAuthority> authorities;
    private Pessoa pessoa;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public UserJPA(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public String getPassword() {
        return pessoa.getSenha();
    }

    @Override
    public String getUsername() {
        return pessoa.getEmail();
    }

}
