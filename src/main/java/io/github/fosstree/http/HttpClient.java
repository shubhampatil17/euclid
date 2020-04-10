package io.github.fosstree.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.github.fosstree.exceptions.InvalidResponseException;
import io.github.fosstree.json.Json;
import lombok.Builder;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.util.Map;

@Singleton
public class HttpClient {

    private final OkHttpClient client;
    private final HttpLoggingInterceptor loggingInterceptor;
    private final HttpLoggingInterceptor.Level logLevel = HttpLoggingInterceptor.Level.NONE;
    private final ObjectMapper mapper;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Inject
    @Builder
    public HttpClient(OkHttpClient client) {
        this.mapper = new ObjectMapper();
        this.loggingInterceptor = new HttpLoggingInterceptor();
        this.loggingInterceptor.setLevel(this.logLevel);
        this.client = client.newBuilder().addInterceptor(loggingInterceptor).build();
    }

    public Response get(String url) throws IOException {
        Request request = new Request.Builder().url(url).get().build();
        return client.newCall(request).execute();
    }

    public Response get(String url, Map<String, String> queryParams) throws IOException {
        Request request = new Request.Builder().url(buildUrlWithParams(url, queryParams)).get().build();
        return client.newCall(request).execute();
    }

    public Response get(String url, JsonNode queryParams) throws IOException {
        Request request = new Request.Builder().url(buildUrlWithParams(url, queryParams)).get().build();
        return client.newCall(request).execute();
    }

    public Response get(String url, Headers headers) throws IOException {
        Request request = new Request.Builder().url(url).headers(headers).get().build();
        return client.newCall(request).execute();
    }

    public Response get(String url, Headers headers, Map<String, String> queryParams) throws IOException {
        Request request = new Request.Builder().url(buildUrlWithParams(url, queryParams)).headers(headers).get().build();
        return client.newCall(request).execute();
    }

    public Response get(String url, Headers headers, JsonNode queryParams) throws IOException {
        Request request = new Request.Builder().url(buildUrlWithParams(url, queryParams)).headers(headers).get().build();
        return client.newCall(request).execute();
    }

    public Response post(String url, RequestBody payload) throws IOException {
        Request request = new Request.Builder().url(url).post(payload).build();
        return client.newCall(request).execute();
    }

    public Response post(String url, JsonNode payload) throws IOException {
        return post(url, payload.toString());
    }

    public Response post(String url, String payload) throws IOException {
        RequestBody requestBody = RequestBody.create(payload, JSON);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        return client.newCall(request).execute();
    }

    public Response post(String url, RequestBody payload, Headers headers) throws IOException {
        Request request = new Request.Builder().url(url).post(payload).headers(headers).build();
        return client.newCall(request).execute();
    }

    public Response post(String url, JsonNode payload, Headers headers) throws IOException {
        return post(url, payload.toString(), headers);
    }

    public Response post(String url, String payload, Headers headers) throws IOException {
        RequestBody requestBody = RequestBody.create(payload, JSON);
        Request request = new Request.Builder().url(url).post(requestBody).headers(headers).build();
        return client.newCall(request).execute();
    }

    public Response post(String url, JsonNode payload, Map<String, String> queryParams) throws IOException {
        return post(url, payload.toString(), queryParams);
    }

    public Response post(String url, String payload, Map<String, String> queryParams) throws IOException {
        RequestBody requestBody = RequestBody.create(payload, JSON);
        Request request = new Request.Builder().url(buildUrlWithParams(url, queryParams)).post(requestBody).build();
        return client.newCall(request).execute();
    }

    public Response post(String url, JsonNode payload, Headers headers, Map<String, String> queryParams) throws IOException {
        RequestBody requestBody = RequestBody.create(payload.toString(), JSON);
        Request request = new Request.Builder().url(buildUrlWithParams(url, queryParams)).post(requestBody).headers(headers).build();
        return client.newCall(request).execute();
    }

    public Response put(String url, RequestBody payload) throws IOException {
        Request request = new Request.Builder().url(url).put(payload).build();
        return client.newCall(request).execute();
    }

    public Response put(String url, JsonNode payload) throws IOException {
        return put(url, payload.toString());
    }

    public Response put(String url, String payload) throws IOException {
        RequestBody requestBody = RequestBody.create(payload, JSON);
        Request request = new Request.Builder().url(url).put(requestBody).build();
        return client.newCall(request).execute();
    }

    public Response put(String url, JsonNode payload, Map<String, String> queryParams) throws IOException {
        return put(url, payload.toString(), queryParams);
    }

    public Response put(String url, String payload, Map<String, String> queryParams) throws IOException {
        RequestBody requestBody = RequestBody.create(payload, JSON);
        Request request = new Request.Builder().url(buildUrlWithParams(url, queryParams)).put(requestBody).build();
        return client.newCall(request).execute();
    }

    public Response put(String url, JsonNode payload, Headers headers) throws IOException {
        return put(url, payload.toString(), headers);
    }

    public Response put(String url, String payload, Headers headers) throws IOException {
        RequestBody requestBody = RequestBody.create(payload, JSON);
        Request request = new Request.Builder().url(url).put(requestBody).headers(headers).build();
        return client.newCall(request).execute();
    }

    public Response put(String url, JsonNode payload, Headers headers, Map<String, String> queryParams) throws IOException {
        return put(url, payload.toString(), headers, queryParams);
    }

    public Response put(String url, String payload, Headers headers, Map<String, String> queryParams) throws IOException {
        RequestBody requestBody = RequestBody.create(payload, JSON);
        Request request = new Request.Builder().url(buildUrlWithParams(url, queryParams)).put(requestBody).headers(headers).build();
        return client.newCall(request).execute();
    }

    public Response delete(String url) throws IOException {
        Request request = new Request.Builder().url(url).delete().build();
        return client.newCall(request).execute();
    }

    public Response delete(String url, Map<String, String> queryParams) throws IOException {
        Request request = new Request.Builder().url(buildUrlWithParams(url, queryParams)).delete().build();
        return client.newCall(request).execute();
    }

    public Response delete(String url, JsonNode queryParams) throws IOException {
        Request request = new Request.Builder().url(buildUrlWithParams(url, queryParams)).delete().build();
        return client.newCall(request).execute();
    }

    public Response delete(String url, Headers headers) throws IOException {
        Request request = new Request.Builder().url(url).headers(headers).delete().build();
        return client.newCall(request).execute();
    }

    public Response delete(String url, Headers headers, Map<String, String> queryParams) throws IOException {
        Request request = new Request.Builder().url(buildUrlWithParams(url, queryParams)).headers(headers).delete().build();
        return client.newCall(request).execute();
    }

    public Response delete(String url, Headers headers, JsonNode queryParams) throws IOException {
        Request request = new Request.Builder().url(buildUrlWithParams(url, queryParams)).headers(headers).delete().build();
        return client.newCall(request).execute();
    }

    /**
     * @param response:             okhttp3 response to extract response param
     * @param expectedResponseCode: expected response code
     * @param pathToFetch:          dotted notation based path to fetch from json
     * @throws IOException
     * @throws InvalidResponseException
     * @return: json node corresponding to final key in the path
     */
    public JsonNode parseResponse(Response response, Integer expectedResponseCode, String pathToFetch) throws IOException, InvalidResponseException {
        String responseBody = response.body() != null ? response.body().string() : mapper.createObjectNode().toString();
        if (response.code() == expectedResponseCode) {
            JsonNode responseJson = responseBody.isEmpty() ? mapper.createObjectNode() : mapper.readTree(responseBody);
            return Json.fetchPath(responseJson, pathToFetch);
        }
        throw new InvalidResponseException("Invalid Response Code : " + response.code() + " Body : " + responseBody);
    }

    /**
     * @param response:             okhttp3 response to extract response param
     * @param expectedResponseCode: expected response code
     * @throws IOException
     * @throws InvalidResponseException
     */
    public void checkResponse(Response response, Integer expectedResponseCode) throws IOException, InvalidResponseException {
        String responseBody = response.body() != null ? response.body().string() : mapper.createObjectNode().toString();
        if (response.code() != expectedResponseCode) {
            throw new InvalidResponseException("Invalid Response Code : " + response.code() + " Body : " + responseBody);
        }
    }

    public void setLogLevel(HttpLoggingInterceptor.Level level) {
        this.loggingInterceptor.setLevel(level);
    }

    private HttpUrl buildUrlWithParams(String url, Map<String, String> params) {
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        params.forEach((k, v) -> builder.addQueryParameter(k, v));
        return builder.build();
    }

    private HttpUrl buildUrlWithParams(String url, JsonNode params) {
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        params.fieldNames().forEachRemaining(key -> builder.addQueryParameter(key, params.get(key).asText()));
        return builder.build();
    }
}
