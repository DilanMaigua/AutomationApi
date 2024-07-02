package com.everis.base.steps;

import com.everis.base.models.Mascota;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class MascotaStep {

    private static final String BASE_URI = "https://petstore.swagger.io/v2";
    private Mascota mascota;
    private Response response;
    private String responseBody;

    public MascotaStep(Mascota mascota) {
        this.mascota = mascota;
    }

    public void setBaseURI() {
        RestAssured.baseURI = BASE_URI;
    }

    public void crearOrden() {
        response = given()
                .contentType("application/json")
                .body("{ \"petId\": " + mascota.getId() + ", \"quantity\": " + mascota.getQuantity() + ", \"status\": \"" + mascota.getStatus() + "\" }")
                .when()
                .post("/store/order");

        responseBody = response.getBody().asString();
    }

    public void verificarOrden() {
        response.then()
                .body("petId", equalTo(mascota.getId()))
                .body("quantity", equalTo(mascota.getQuantity()))
                .body("status", equalTo(mascota.getStatus()));

        System.out.println("Response Body: " + responseBody);
    }

    public int getResponseStatusCode() {
        return response.getStatusCode();
    }

    public void getDetallesOrden() {
        response = given()
                .pathParam("orderId", mascota.getId())
                .when()
                .get("/store/order/{orderId}");

        responseBody = response.getBody().asString();
    }

    public void verificacionDetallesOrden() {
        response.then()
                .statusCode(getResponseStatusCode())
                .body("id", equalTo(mascota.getId()));

        System.out.println("Response Body: " + responseBody);
    }
}