package ru.prokhorov.povod.config.rest;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import ru.prokhorov.povod.exception.IntegrationException;

import java.io.IOException;

/**
 * RestTemplateErrorResponseHandler.
 *
 * @author Evgeniy_Prokhorov
 */
@Component
public class RestTemplateErrorResponseHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        throw new IntegrationException("Ошибка при обращении во внешнюю систему "
                + response.getStatusCode().value(), response.getStatusCode().value());
    }
}
