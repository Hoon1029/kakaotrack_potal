package kr.ac.jejunu.user;

import kr.ac.jejunu.login.LoginInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;

import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan("kr.ac.jejunu.controller")
@ComponentScan("kr.ac.jejunu.database.dao")
@ComponentScan("kr.ac.jejunu.database.object")
@ComponentScan("kr.ac.jejunu.login")
@ComponentScan("kr.ac.jejunu.user")
@AllArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final MainInterceptor userInterceptor;
    private final LoginInterceptor loginInterceptor;

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.mediaType("js", MediaType.APPLICATION_JSON)
                .mediaType("x", MediaType.APPLICATION_XML);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor).addPathPatterns("/**/*");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("/WEB-INF/static/");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp().prefix("/WEB-INF/views/")
                .suffix(".jsp");
        registry.enableContentNegotiation(new MappingJackson2JsonView());
        registry.enableContentNegotiation(new MappingJackson2XmlView());
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter());
    }
}
