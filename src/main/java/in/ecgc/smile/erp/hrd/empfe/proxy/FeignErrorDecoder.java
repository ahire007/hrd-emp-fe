package in.ecgc.smile.erp.hrd.empfe.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

	private static final Logger log = LoggerFactory.getLogger(FeignErrorDecoder.class);

	@Override
	public Exception decode(String methodKey, Response response) {
		String reason = response.body().toString();
		
		switch (response.status()) {
		case 400:
			log.error("Status code " + response.status() + ", methodKey = " + methodKey);
		case 404: {
			log.error("Error occured. Status code " + response.status()
					+ ", methodKey = " + methodKey);
			
			return new ResponseStatusException(HttpStatus.valueOf(response.status()),
					reason);
		}
		default:
			return new Exception(reason);
		}
	}

}
