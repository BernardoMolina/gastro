package br.csi.clinica_gastro.service;

import br.csi.clinica_gastro.model.usuario.Usuario;
import br.csi.clinica_gastro.model.usuario.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    private final UsuarioRepository repository;
    public AutenticacaoService(UsuarioRepository repository){
        this.repository = repository;
    }
    //Método destiando a criação de um UserDetail que será inserido no contexto o Spring
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = this.repository.findByEmail(email);
        if(usuario ==null){
            throw new UsernameNotFoundException("Usuário ou senha incorretos");
        }else {
            UserDetails user = User.withUsername(usuario.getEmail()).password(usuario.getSenha())
                    .authorities(usuario.getPermissao()).build();
            return user;
        }
    }
}