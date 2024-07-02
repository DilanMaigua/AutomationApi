package com.everis.base.stepDefinitions;

import com.everis.base.models.Mascota;
import com.everis.base.steps.MascotaStep;
import io.cucumber.java.en.*;
import static org.junit.Assert.assertEquals;


public class MascotaStepDef {

    private Mascota mascota = new Mascota();
    private MascotaStep mas = new MascotaStep(mascota);

    @Given("dado que estoy en la tienda")
    public void dadoQueEstoyEnLaTienda() {
        mas.setBaseURI();
    }

    @And("se crea una nueva orden de pedido ingresando id {int}")
    public void seCreaUnaNuevaOrdenDePedidoIngresandoId(int petId) {
        mascota.setId(petId);
    }

    @And("quantity {int}")
    public void quantity(int cantidad) {
        mascota.setQuantity(cantidad);
    }

    @And("estado de compra {string}")
    public void estadoDeCompra(String estado) {
        mascota.setStatus(estado);
        mas.crearOrden();
    }

    @When("la orden esta creada")
    public void laOrdenEstaCreada() {
    }

    @Then("valido el codigo de respuesta {int}")
    public void validoElCodigoDeRespuesta(int status) {
        assertEquals("El código de respuesta es incorrecto", status, mas.getResponseStatusCode());
    }

    @And("verifico el pedido realizado para la compra")
    public void verificoElPedidoRealizadoParaLaCompra() {
        mas.verificarOrden();
    }

    @And("se consulta la orden con orderId {int}")
    public void seConsultaLaOrdenConOrderId(int orderId) {
        mascota.setId(orderId);
        mas.getDetallesOrden();
    }

    @Then("verifico los detalles de la orden")
    public void verificoLosDetallesDeLaOrden() {
        mas.verificacionDetallesOrden();
    }

}
