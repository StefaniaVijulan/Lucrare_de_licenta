package com.medicalclinicapp.medicalclinicapp.security.config;

import com.medicalclinicapp.medicalclinicapp.security.config.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public WebSecurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/register",
                        "/login",
                        "/changePass",
                        "/blockDate",
                        "/deleteAppointmentCardio",
                        "/checkAvailabilityHourCardio",
                        "/allCardiolog",
                        "addAppointment").permitAll()
                .antMatchers("/moderator/registerSecretary",
                        "/moderator/registerCardiolog",
                        "/moderator/registerHematolog",
                        "/moderator/registerImgist",

                        "/moderator/allUsers",
                        "/moderator/allSecretaries",
                        "/moderator/allCardiolog",
                        "/moderator/allHematolog",
                        "/moderator/allImagist",

                        "/moderator/resetPass",

                        "/moderator/userCnp{cnp}",
                        "/moderator/deleteCurant",
                        "/moderator/deleteSecretary",
                        "/moderator/editUser",
                        "/moderator/allHospitalizationCardiolog",
                        "/moderator/deleteUser"

                        ).hasAuthority("MODERATOR")
                .antMatchers(
                        "/secretary/allAppointments",
                        "/secretary/addPatient",
                        "/secretary/todayAppointments",
                        "/secretary/allCardiolog",
                        "/secretary/specificHospitalization",
                        "/secretary/allHospitalization",
                        "/secretary/checkPatient",
                        "/secretary/addFisa",
                        "/secretary/specificD",
                        "/secretary/allDoctors",
                        "/secretary/allPatients",
                        "/secretary/infoPatient",
                        "/secretary/afisare",
                        "/secretary/specificP",
                        "/secretary/deletePatient").hasAuthority("SECRETAR")
                .antMatchers("/cardiolog/allSpecificAppointment",
                        "/cardiolog/editFisaP",
                        "/deleteFisa",
                        "/cardiolog/specificFisa",
                        "/cardiolog/addAppointmentHematology",
                        "/cardiolog/addAppointmentRadiology"
                        ).hasAuthority("CARDIOLOG")
                .antMatchers("/hematolog/allAppointmentHematology",
                        "/hematolog/allTodayAppointmentHematology").hasAuthority("HEMATOLOG")
                .antMatchers("/imagist/allAppointmentRadiology",
                        "/imagist/allTodayAppointmentRadiology").hasAuthority("IMAGIST")

                .and().cors().and().csrf().disable()
                .exceptionHandling()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
    /*
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }*/
}