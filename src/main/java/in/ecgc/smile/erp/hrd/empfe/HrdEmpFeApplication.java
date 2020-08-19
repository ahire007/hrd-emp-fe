package in.ecgc.smile.erp.hrd.empfe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/*HRD-FE-Service
 * 
 * @Author Architecture Team C-DAC Mumbai
 */
@SpringBootApplication(scanBasePackages = "in")
@EnableDiscoveryClient	// To register with Eureka Server
//@EnableFeignClients     // Enable FeignClients for calling other service
@EnableCircuitBreaker   // Enable Circuit breaker via Hystrix
@EnableHystrix
@EnableHystrixDashboard
public class HrdEmpFeApplication {
	public static void main(String[] args) {
		SpringApplication.run(HrdEmpFeApplication.class, args);
	}	
}
