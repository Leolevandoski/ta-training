package com.wipro.tutorial.at.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;


@Configuration
@PropertySource("classpath:properties/${environment}/application.properties")
public class RestUtil {
    private static Logger LOGGER = Logger.getLogger(RestUtil.class);
    private static SimpleClientHttpRequestFactory REQ_FACTORY = new SimpleClientHttpRequestFactory();


    private static String  userAuth;

    public static void configureProxy(Environment env) {
        if(! Boolean.valueOf(env.getProperty("proxy.enabled"))) {
            return;
        }

        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
                env.getProperty("proxy.host"),
                Integer.parseInt(env.getProperty("proxy.port"))));
        REQ_FACTORY.setProxy(proxy);
    }


    public static String sendRequest(HttpMethod method, String url, HttpEntity httpEntity) {
        RestTemplate template = new RestTemplate(REQ_FACTORY);

        CloseableHttpClient httpClient = HttpClients.custom().build();
        template.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
        template.getMessageConverters().add(0, new StringHttpMessageConverter());
        HttpEntity<String> response = template.exchange(url, method, httpEntity, String.class);
        String responseBody = response.getBody();
        LOGGER.info("RES[" + url + "]: " + responseBody);
        return responseBody;
    }

    public static String sendGet(String url) {
        LOGGER.info("REQ[" + url + "]: GET");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " +authentication());

        HttpEntity request = new HttpEntity(null,headers);

        return sendRequest(HttpMethod.GET, url, request);
    }
    public static String sendGet404(String url) {
        LOGGER.info("REQ[" + url + "]: GET");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " +authentication());

        HttpEntity request = new HttpEntity(null,headers);

        return sendRequest(HttpMethod.GET, url, request);
    }


    public static String sendPost(String url, String payload) {
        LOGGER.info("REQ[" + url + "]: " + payload);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " +  authentication());
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(payload, headers);
        return sendRequest(HttpMethod.POST, url, request);
    }

    public static String sendPatch(String url, String payload) {
        LOGGER.info("REQ[" + url + "]: " + payload);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " +  authentication());
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(payload, headers);
        return sendRequest(HttpMethod.GET, url, request);
    }

    private static String authentication() {
        byte[] plainCredsBytes = userAuth.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        return new String(base64CredsBytes);
    }

    public static void setUserAuth(String user){
        userAuth=user;
    }
}
