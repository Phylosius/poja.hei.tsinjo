package school.hei.tsinjo.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import school.hei.tsinjo.client.dto.VolaPayment;
import school.hei.tsinjo.model.Payment;

@Component
public class VolaClient {

  private final RestTemplate restTemplate;
  private final String apiKey;
  private final String apiBaseUrl;

  public VolaClient(
      RestTemplate restTemplate,
      @Value("${vola.api.key}") String apiKey,
      @Value("${vola.api.base-url}") String apiBaseUrl) {
    this.restTemplate = restTemplate;
    this.apiKey = apiKey;
    this.apiBaseUrl = apiBaseUrl;
  }

  public VolaPayment createPayment(
      String payerEmail, String pspPaymentId, Payment.PaymentMethod pspType) {
    String url =
        UriComponentsBuilder.fromHttpUrl(apiBaseUrl)
            .path("/payment")
            .queryParam("apiKey", apiKey)
            .queryParam("payerEmail", payerEmail)
            .queryParam("pspPaymentId", pspPaymentId)
            .queryParam("pspType", pspType.toString())
            .toUriString();
    return restTemplate.postForObject(url, null, VolaPayment.class);
  }

  public VolaPayment getPayment(
      String payerEmail, String pspPaymentId, Payment.PaymentMethod pspType) {
    String url =
        UriComponentsBuilder.fromHttpUrl(apiBaseUrl)
            .path("/payment")
            .queryParam("apiKey", apiKey)
            .queryParam("payerEmail", payerEmail)
            .queryParam("pspPaymentId", pspPaymentId)
            .queryParam("pspType", pspType.toString())
            .toUriString();
    return restTemplate.getForObject(url, VolaPayment.class);
  }
}
