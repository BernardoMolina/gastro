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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.List;

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
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth
                                // ---------- PÚBLICO ----------
                                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                                // ---------- SECRETÁRIA: gerencia médicos, pacientes e secretárias ----------
                                .requestMatchers(HttpMethod.POST,   "/medico").hasAuthority("SECRETARIA")
                                .requestMatchers(HttpMethod.DELETE, "/medico/**").hasAuthority("SECRETARIA")
                                .requestMatchers(HttpMethod.DELETE, "/usuario/**").hasAuthority("SECRETARIA")
                                .requestMatchers(HttpMethod.DELETE, "/paciente/**").hasAuthority("SECRETARIA")
                                .requestMatchers(HttpMethod.POST,   "/paciente").hasAuthority("SECRETARIA")
                                .requestMatchers(HttpMethod.POST,   "/usuario").hasAuthority("SECRETARIA")
                                .requestMatchers(HttpMethod.PUT,    "/usuario").hasAuthority("SECRETARIA")
                                .requestMatchers(HttpMethod.GET,    "/usuario").hasAuthority("SECRETARIA")

                                // PUT /medico: secretária (editar médico) OU o próprio médico (perfil)
                                .requestMatchers(HttpMethod.PUT,    "/medico").hasAnyAuthority("SECRETARIA", "MEDICO")

                                // Listas usadas por secretária, médico (selects) e paciente (descobrir o próprio id/perfil)
                                .requestMatchers(HttpMethod.GET,    "/paciente/detalhes_pacientes").hasAnyAuthority("SECRETARIA", "MEDICO", "PACIENTE")
                                .requestMatchers(HttpMethod.GET,    "/medico").hasAnyAuthority("SECRETARIA", "MEDICO", "PACIENTE")
                                .requestMatchers(HttpMethod.GET,    "/medico/**").hasAnyAuthority("SECRETARIA", "MEDICO", "PACIENTE")
                                .requestMatchers(HttpMethod.GET,    "/paciente/**").hasAnyAuthority("SECRETARIA", "MEDICO", "PACIENTE")

                                // ---------- CONSULTAS: médico gerencia, paciente só lê ----------
                                .requestMatchers(HttpMethod.GET,    "/consulta").hasAnyAuthority("MEDICO", "PACIENTE")
                                .requestMatchers(HttpMethod.POST,   "/consulta").hasAuthority("MEDICO")
                                .requestMatchers(HttpMethod.PUT,    "/consulta").hasAuthority("MEDICO")
                                .requestMatchers(HttpMethod.DELETE, "/consulta/**").hasAuthority("MEDICO")

                                // ---------- EXAMES: médico gerencia, paciente só lê ----------
                                .requestMatchers(HttpMethod.GET,    "/exame/**").hasAnyAuthority("MEDICO", "PACIENTE")
                                .requestMatchers(HttpMethod.POST,   "/exame").hasAuthority("MEDICO")
                                .requestMatchers(HttpMethod.DELETE, "/exame/**").hasAuthority("MEDICO")

                                // Os 3 tipos: GET (detalhes) para médico e paciente; escrita só médico
                                .requestMatchers(HttpMethod.GET,    "/colangioressonancia/**", "/manometria/**", "/endoscopia/**").hasAnyAuthority("MEDICO", "PACIENTE")
                                .requestMatchers(HttpMethod.POST,   "/colangioressonancia", "/manometria", "/endoscopia").hasAuthority("MEDICO")
                                .requestMatchers(HttpMethod.PUT,    "/colangioressonancia", "/manometria", "/endoscopia").hasAuthority("MEDICO")
                                .requestMatchers(HttpMethod.DELETE, "/colangioressonancia/**", "/manometria/**", "/endoscopia/**").hasAuthority("MEDICO")

                                .anyRequest().authenticated())
                .addFilterBefore(this.autenticacaoFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }



    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
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