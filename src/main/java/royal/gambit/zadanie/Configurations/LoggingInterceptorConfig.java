package royal.gambit.zadanie.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import royal.gambit.zadanie.Interceptors.EndpointLoggingInterceptor;

@Configuration
public class LoggingInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    EndpointLoggingInterceptor endpointLoggingInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(endpointLoggingInterceptor);
    }
}
