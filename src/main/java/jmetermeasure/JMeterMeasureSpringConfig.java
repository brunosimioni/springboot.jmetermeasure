package jmetermeasure;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
	@PropertySource("classpath:default.properties")
})
public class JMeterMeasureSpringConfig implements InitializingBean {

	public void afterPropertiesSet() throws Exception {
	}
}