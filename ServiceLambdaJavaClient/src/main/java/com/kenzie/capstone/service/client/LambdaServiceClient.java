package com.kenzie.capstone.service.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.capstone.service.model.ExampleData;
import com.kenzie.capstone.service.model.NotificationData;
import com.kenzie.capstone.service.model.UserRequest;

import javax.management.Notification;


public class LambdaServiceClient {

    private static final String GET_EXAMPLE_ENDPOINT = "example/{id}";
    private static final String SET_EXAMPLE_ENDPOINT = "example";

    private static final String NOTIFICATION_ENDPOINT = "user/test";

    private ObjectMapper mapper;

    public LambdaServiceClient() {
        this.mapper = new ObjectMapper();
    }

    public ExampleData getExampleData(String id) {
        EndpointUtility endpointUtility = new EndpointUtility();
        String response = endpointUtility.getEndpoint(GET_EXAMPLE_ENDPOINT.replace("{id}", id));
        ExampleData exampleData;
        try {
            exampleData = mapper.readValue(response, ExampleData.class);
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        return exampleData;
    }

    public ExampleData setExampleData(String data) {
        EndpointUtility endpointUtility = new EndpointUtility();
        String response = endpointUtility.postEndpoint(SET_EXAMPLE_ENDPOINT, data);
        ExampleData exampleData;
        try {
            exampleData = mapper.readValue(response, ExampleData.class);
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        return exampleData;
    }

    public NotificationData setNotificationData(NotificationData data) {
        String jsonData = null;
        try {
             jsonData = mapper.writeValueAsString(data);
        }catch(Exception e){
            e.getMessage();
        }
        System.out.println("IN SETNOTIFICATION DATA " + jsonData);

        EndpointUtility endpointUtility = new EndpointUtility();
        String response = endpointUtility.postEndpoint(NOTIFICATION_ENDPOINT, jsonData);
        System.out.println("INside the Response " + response);
        NotificationData stringData;
        try{
            System.out.println("IN HERNEH IANSUBDNHASINBDIA asdasdb ashdhaSDHI BD ASYHBUD AB   " + response);
            stringData = mapper.readValue(response, NotificationData.class);
            System.out.println(stringData);
        } catch (Exception e){
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        return stringData;
    }
}
