package mangadexparser.customizer;

import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class ProxyCustomizer implements RestTemplateCustomizer {
    private static final boolean USE_PROXY = false;
    private static final String PROXY_URL = "localhost";
    private static final int PROXY_PORT = 9150;

    @Override
    public void customize(RestTemplate restTemplate) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(PROXY_URL, PROXY_PORT));
        if (USE_PROXY) {
            requestFactory.setProxy(proxy);
        }
        restTemplate.setRequestFactory(requestFactory);
    }
}