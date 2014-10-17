#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${parentArtifactId}.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import dk.emstar.common.CommonSpringConfig;

@ComponentScan(basePackageClasses = {CommonSpringConfig.class})
@Configuration
public class SpringConfig {

}
