# Jhon-MS_Custommer
Microservice For Manage Customer

# Swagger url
localhost:8080/swagger-ui.html


## Simpple Doc
- Registration > POST /customer/register
- Login > POST /customer/login
- Logout > DELETE /customer/logout
- Session Check > GET /customer/validate-session
- Detail Customer Information > GET /customer
- Validate existing user before forgot password > POST /customer/validate-exist-user
- Do update to new password > PATCH /customer/change-password

## Forgot password flow
- do validate existing user with parameter identity customer and username on  `/customer/validate-exist-user`
- transaction id will be used to validate the customer identity

## Forgot password flow tobe enhance
the transaction id need to be sent to valid customer registered email inside url and direct it to forgot password screen



## DB Schema 
![Alt text](spring-boot-h2-db.png?raw=true "Optional Title")

## Query to find all userd logged in last 7days with H2 DB

``` select DISTINCT B.NAME, B.EMAIL
from LOGIN_HISTORY A
join CUSTOMER_DETAIL B ON A.CUSTOMER_ACCOUNT_ID = B.CUSTOMER_ACCOUNT
where LOGIN_DATE > DateAdd(DAY,-7,CURRENT_TIMESTAMP())
```

