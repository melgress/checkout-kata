## The Excercise:

### Implement the code for a supermarket checkout that calculates the total price of a number of items.

### Items each have their own price, which can change frequently.

### There are also weekly special offers for when multiple items are bought.

### An example of this would be “Apples are 50 each or 3 for 130”.

### The pricing table example:

| Item   |Price for 1 item | Offer                |
|--------|-----------------|----------------------|
| Apple  | 30              | 2 for 45             |
| Banana | 50              | 3 for 130            |
| Peach  | 60              |  -                   |
| Kiwi   | 20              |  -                   |

The checkout accepts the items in any order, so that if we scan an apple, a banana and another apple, we’ll recognise two apples and apply the discount of 2 for 45.

## How To start the app
the entry point of the checkout-service is the CheckoutServiceApplication.java
the project can be built with maven, the mvn wrapper provides the necessary configuration
schema.sql provides a h2 database with the necessary tables at the application start
import.sql provides testdata at the application start
the db schema is available at the h2 console: http://localhost:8080/h2-console

## Test the app
 the application provides three endpoints:
 ### GET http://localhost:8080/api/v1/items
 provides the list of all items with their price and offer as shown in the table above
 ### POST http://localhost:8080/api/v1/cart/{cartId}?itemId=?&quantity=?
 adds an item to the cart
 ### POST http://localhost:8080/api/v1/checkout/{cartId} 
 checks out, meaning all items in cart, their prices and quantity as well as the total price is shown

## Open Points
#### provide a UI that calls the REST endpoints
#### use string UUIDs instead of generated Ids
