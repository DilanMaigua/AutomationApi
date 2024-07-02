@TestStore
Feature: Gestion en la tienda de mascotas

  @CrearPedido
  Scenario Outline: Crear una orden de pedido
    Given dado que estoy en la tienda
    And se crea una nueva orden de pedido ingresando id <petId>
    And quantity <cantidad>
    And estado de compra "<estado>"
    When la orden esta creada
    Then valido el codigo de respuesta <status>
    And verifico el pedido realizado para la compra

    Examples:
      | petId | cantidad | estado    | status |
      | 1     | 2        | placed    | 200    |
      | 2     | 1        | approved  | 200    |
      | 3     | 1        | delivered | 200    |

  @ConsultarOrden
  Scenario Outline: Consultar una orden específica
    Given dado que estoy en la tienda
    And se consulta la orden con orderId <orderId>
    Then valido el codigo de respuesta <status>
    And verifico los detalles de la orden

    Examples:
      | orderId   | status |
      | 4         | 200    |
      | 5         | 200    |
      | 8         | 200    |







