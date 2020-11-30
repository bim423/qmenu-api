# qmenu-api

### `/product` Service

---

##### Add product (create)

> PUT /product

*Request parameters:*

* product-name: String
* product-description: String
* product-price: double

*Successful response* (200 OK)*:*

```
{
    "message" : "Product added successfully.",
    "product_id": 0
}
```

##### Update product

> PUT /product

*Request parameters:*

* product-id: integer
* product-name: String
* product-description: String
* product-price: double

*Successful response* (200 OK)*:*

```
{
    "message" : "Product updated successfully.",
    "product_id": 0
}
```

##### Delete product (delete)

> DELETE /product

*Request parameters:*

* product_id: integer

*Successful response (200 OK):*

```
{
    "message" : "Product deleted successfully."
}
```

### `/menu` Service

---

##### Edit menu (create, update)

> `PUT /menu/save`

*Request body (JSON):*

```
{ 
    "menu" : [
        { 
            "category": "Sub Menu name",
            "description": "Sub menu description",
            "products" : [array of product ids] 
        },
        .... and more
    ] 
}
```

##### Get menu

> GET /menu

*Response (JSON):*

```
{ 
    "menu" : [
        { 
            "category": "Sub Menu name",
            "description": "Sub menu description",
            "products" : [
                {
                    "product_id": "Product ID"
                    "product_name": "A product"
                    "description": "A product description"
                },
                ... and more products
            ] 
        },
        .... and more categories
    ] 
}
```

### /desk Service

---

#### Get all desks

> GET /desk

*Response (200 OK):*

```
{
    "desks" : [
        {
            "desk_id" : 0,
            "desk_label" : "A1",
        },
        ... and more
    ]
}
```

#### Get a specific desk with code

> GET /desk

*Parameters:*

* desk_id : integer
* *new_code : (Optional) boolean*

*Response (200 OK):*

```
{
    "desk_id" : 0,
    "desk_label" : "A1",
    "desk_code" : "1eab201bce32ab5636bea"
}
```

#### Create desk

> PUT /desk

*Parameters:*

* desk_label: String

*Response (200 OK):*

```
{
    "message" : "Desk created successfully."
    "desk_id" : 0
}
```

#### Update a desk

> PUT /desk

*Parameters:*

* desk_id: integer
* desk_label: String

*Response (200 OK):*

```
{
    "message" : "Desk updated successfully."
}
```

#### Delete a desk

> DELETE /desk

Parameters:

* desk_id : integer

*Response (200 OK):*

```
{
    "message" : "Desk deleted successfully."
}
```

### /order Service

---

#### List orders

> GET /order/list

*Response (200):*

```
{
    "orders" : [
        {
            "order_id" : 0,
            "order_state" : 0,
            "price" : 3.75,
            "ordered_products" : [
                {
                    "product_id" : 0,
                    "product_name" : "A product",
                    "quantity" : 2
                },
                ... and more products
            ]
        },
        ... and more orders
    ]
}
```

#### Place orders

> PUT /order/new

*Request Body:*

```
{
    "desk_code" : "1eab201bce32ab5636bea",
    "ordered_products" : [
        {
            "product_id" : 0,
            "quantity" : 2
        },
        ... and more products
    ]
}
```

#### Update order state

> UPDATE /order

*Parameters:*

* order_id : int
* state : int (0,1,2)

*Response (200 OK):*

```
{
    "message" : "Order state updated to Processing."
}
```

#### Delete order

> DELETE /order

*Parameters:*

* order_id : int

*Response (200 OK):*

```
{
    "message" : "Order deleted successfully."
}
```

### /staff Service

---

#### Get all personnel

> GET /staff

```
{
    "personnel" : [
        {
            "first_name" : "John",
            "last_name" : "Doe",
            "username" : "john.doe",
            "email" : "johndoe@allybros.com",
            "role" : 1  
        },
        ... and more users
    ]
}
```

#### Create personnel

> PUT /staff

*Parameters:*

* first-name :
* last-name :
* username :
* password :
* email :
* role :

// ... might be more

*Response:*

```
{
    "message" : "New personnel created successfully."
}
```

#### Delete personnel

> DELETE /staff

*Parameters:*

* username

*Response:*

```
{
    "message" : "The personnel, $username is deleted."
}
```

#### Update personnel

> UPDATE /staff

*Parameters:*

* old-username
* *old-password (Not required when administrator makes request.)*
* *new-first-name*
* *new-last-name*
* *new-username*
* *new-password*
* *new-email*
* *new-role*

*Response:*

```
{
    "message" : "The personnel, $username is updated successfully.",
    "user" : {
        "first_name" : "John",
        "last_name" : "Doe",
        "username" : "john.doe",
        "email" : "johndoe@allybros.com",
        "role" : 1
    }
}
```

### /stat Service

---

Used for creating dashboard view, sends basic insights about platform. Requires administrator privileges.

### /login service

---

> POST /login

* uid
* password

*Response:*

```
{
     "message" : "Login success",
     "session_id" : 
}
```

### /logout service

---

> GET /logout

```
{
     "message" : "Session cleared successfully"
}
```
