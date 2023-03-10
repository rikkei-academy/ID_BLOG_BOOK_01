package ra.security;
import ra.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;





@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
//         Get AuthenticationManager bean
        return super.authenticationManagerBean();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        // Password encoder, ????? Spring Security s??? d???ng m?? h??a m???t kh???u ng?????i d??ng
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService) // Cung cap customUserDetailService cho spring security
                .passwordEncoder(passwordEncoder()); // cung c???p password encoder
    }
    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService() {
        final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        return (userRequest) -> {
            OAuth2User oAuth2User = delegate.loadUser(userRequest);
            return oAuth2User;
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors() // Ng??n ch???n request t??? m???t domain kh??c
                .and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/users/**").permitAll()// Cho ph??p t???t c??? m???i ng?????i truy c???p v??o ?????a ch??? n??y
                .antMatchers("/api/v1/category/**").permitAll()// Cho ph??p t???t c??? m???i ng?????i truy c???p v??o ?????a ch??? n??y
                .antMatchers("/api/v1/passReset/**").permitAll()// Cho ph??p t???t c??? m???i ng?????i truy c???p v??o ?????a ch??? n??y
                .antMatchers("/api/v1/book/**").permitAll()// Cho ph??p t???t c??? m???i ng?????i truy c???p v??o ?????a ch??? n??y
                .antMatchers("/api/v1/author/**").permitAll()// Cho ph??p t???t c??? m???i ng?????i truy c???p v??o ?????a ch??? n??y
                .antMatchers("/api/v1/likeBook/**").permitAll()// Cho ph??p t???t c??? m???i ng?????i truy c???p v??o ?????a ch??? n??y
                .antMatchers("/api/v1/contact/**").permitAll()
                .antMatchers("/api/v1/tag/**").permitAll()
                .antMatchers("/api/v1/cart/**").permitAll()// Cho ph??p t???t c??? m???i ng?????i truy c???p v??o ?????a ch??? n??y
                .antMatchers("/api/v1/comment/**").permitAll()// Cho ph??p t???t c??? m???i ng?????i truy c???p v??o ?????a ch??? n??y
                .anyRequest().authenticated().and()
                .oauth2Login()
                .loginPage("/signIn")
                .defaultSuccessUrl("/api/v1/users/success/profile")
                .userInfoEndpoint()
                .userService(oAuth2UserService());

        // Th??m m???t l???p Filter ki???m tra jwt
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}