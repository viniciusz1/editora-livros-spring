package br.senai.sc.editoralivros.security;

import br.senai.sc.editoralivros.security.service.GoogleService;
import br.senai.sc.editoralivros.security.service.JpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AutenticacaoConfig {

    @Autowired
    private JpaService jpaService;

    @Autowired
    private GoogleService googleService;

    // Configura as autorizações de acesso

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(jpaService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        httpSecurity.authenticationProvider(provider);
        httpSecurity.authorizeRequests()
                // Libera o acesso sem autenticação para /login
                .antMatchers("/editora-livros-api/login", "/editora-livros-api/usuarios").permitAll()
                // Determina que todas as demais requisições terão de ser autenticadas
                .anyRequest().authenticated()
//                .anyRequest().permitAll()
                .and().csrf().disable()
                .formLogin().permitAll()
                    .loginPage("/editora-livros-api/login")
                    .defaultSuccessUrl("/editora-livros-api/home")
                .and()
                .oauth2Login()
                    .userInfoEndpoint()
                        .userService(googleService)

                .and()
                    .loginPage("/editora-livros-api/login")
                    .defaultSuccessUrl("/editora-livros-api/home")
                .and()
                .logout().permitAll();
//                .and()

//                .sessionManagement().sessionCreationPolicy(
//                        SessionCreationPolicy.STATELESS)
//                .and().addFilterBefore(
//                        new AutenticacaoFiltro(jpaService),
//                        UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
