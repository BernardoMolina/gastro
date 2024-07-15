package br.csi.clinica_gastro.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AutenticacaoFilter autenticacaoFilter;
    public SecurityConfig(AutenticacaoFilter filtro){
        this.autenticacaoFilter = filtro;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(crsf-> crsf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth->
                        auth.requestMatchers(HttpMethod.POST,"/login").permitAll()
                                .requestMatchers(HttpMethod.POST,"/usuario").permitAll()
                                .requestMatchers(HttpMethod.POST,"/medico").permitAll()
                                .requestMatchers(HttpMethod.POST,"/paciente").permitAll()
                                .requestMatchers(HttpMethod.POST,"/consulta").permitAll()
                                .requestMatchers(HttpMethod.GET,"/medico/{idmed}").permitAll()
                                .requestMatchers(HttpMethod.DELETE,"/consulta/{id}").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/exame/{id}").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.POST,"/medicamento").hasAnyAuthority("ROLE_MEDICO","ROLE_ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/medicamento/{id}").hasAnyAuthority("ROLE_MEDICO","ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET,"/usuario").permitAll()
                                .requestMatchers(HttpMethod.GET,"/medico").permitAll()
                                .requestMatchers(HttpMethod.POST,"/exame").permitAll()
                                .requestMatchers(HttpMethod.GET,"/paciente").permitAll()
                                .requestMatchers(HttpMethod.GET,"/consulta").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET,"/paciente/detalhes_consulta/{id}").hasAnyAuthority("ROLE_PACIENTE","ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET,"/paciente/todas_consultas/{id}").hasAnyAuthority("ROLE_PACIENTE","ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET,"/paciente/receitas_consulta/{id}").hasAnyAuthority("ROLE_PACIENTE","ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET,"/paciente/detalhes_exame/{id}").hasAnyAuthority("ROLE_PACIENTE","ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET,"/paciente/todas_exames/{id}").hasAnyAuthority("ROLE_PACIENTE","ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET,"/medico/detalhes_consulta_medico/{id}").hasAnyAuthority("ROLE_MEDICO","ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET,"/medico/todas_consultas_medico/{id}").hasAnyAuthority("ROLE_MEDICO","ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET,"/medico/receitas_consulta_medico/{id}").hasAnyAuthority("ROLE_MEDICO","ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET,"/medico/detalhes_exame_medico/{id}").hasAnyAuthority("ROLE_MEDICO","ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET,"/medico/todas_exames_medico/{id}").hasAnyAuthority("ROLE_MEDICO","ROLE_ADMIN")
                                .requestMatchers(HttpMethod.POST,"/pagamento").hasAnyAuthority("ROLE_MEDICO","ROLE_ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/pagamento").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/medico/{idmed}").permitAll()
                                .requestMatchers(HttpMethod.PUT,"/medico").permitAll()
                                .requestMatchers(HttpMethod.PUT,"/usuario").permitAll()
                                .requestMatchers(HttpMethod.PUT,"/consulta").permitAll()
                                .requestMatchers(HttpMethod.PUT,"/receita").hasAnyAuthority("ROLE_MEDICO","ROLE_ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/medicamento").hasAnyAuthority("ROLE_MEDICO","ROLE_ADMIN")
                                .anyRequest().authenticated())
                .addFilterBefore(this.autenticacaoFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }







    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
            throws Exception{
        return configuration.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}