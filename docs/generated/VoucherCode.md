
# VoucherCode

Collections of endpoints related to the VoucherCode entity

## Indices

* [Ungrouped](#ungrouped)

  * [find](#1-find)
  * [use](#2-use)


--------


## Ungrouped



### 1. find


Find voucher codes using Recipient's email

```
email: String
    The recipient email
```


***Endpoint:***

```bash
Method: GET
Type: 
URL: {{url}}/voucher
```



***Query params:***

| Key | Value | Description |
| --- | ------|-------------|
| email | chinmingjun83@gmail.com |  |



***More example Requests/Responses:***


##### I. Example Request: find



***Query:***

| Key | Value | Description |
| --- | ------|-------------|
| email | chinmingjun83@gmail.com |  |



##### I. Example Response: find
```js
[
    {
        "id": 3,
        "recipient": {
            "id": 1,
            "name": "CMJ3",
            "email": "chinmingjun83@gmail.com"
        },
        "specialOffer": {
            "id": 2,
            "name": "December Special Offer 2",
            "discountPercentage": 30.2
        },
        "code": "f3febf49-5522-4f66-9011-25baf5c46438",
        "expiryDate": "2020-12-31",
        "usageDate": null
    }
]
```


***Status Code:*** 200

<br>



### 2. use


Use a voucher code. 

```
email: String
    The email address associated to the voucher code.
code: String
    The voucher code
```


***Endpoint:***

```bash
Method: PUT
Type: RAW
URL: {{url}}/voucher/use
```



***Body:***

```js        
{
    "email": "chinmingjun83@gmail.com",
    "code": "f3febf49-5522-4f66-9011-25baf5c46438"
}
```



***More example Requests/Responses:***


##### I. Example Request: use



***Body:***

```js        
{
    "email": "chinmingjun83@gmail.com",
    "code": "f3febf49-5522-4f66-9011-25baf5c46438"
}
```



##### I. Example Response: use
```js
{
    "id": 3,
    "recipient": {
        "id": 1,
        "name": "CMJ3",
        "email": "chinmingjun83@gmail.com"
    },
    "specialOffer": {
        "id": 2,
        "name": "December Special Offer 2",
        "discountPercentage": 30.2
    },
    "code": "f3febf49-5522-4f66-9011-25baf5c46438",
    "expiryDate": "2020-12-31",
    "usageDate": "2020-12-07"
}
```


***Status Code:*** 200

<br>



##### II. Example Request: use: Using an invalid voucher code



***Body:***

```js        
{
    "email": "chinmingjun83@gmail.com",
    "code": "invalid-code"
}
```



##### II. Example Response: use: Using an invalid voucher code
```js
{
    "exceptionId": "40a270e6-4461-49b9-b437-caaa2e1a9d93",
    "status": 404,
    "message": "Requested resource not found",
    "details": null
}
```


***Status Code:*** 404

<br>



##### III. Example Request: use: Using a used voucher code



***Body:***

```js        
{
    "email": "chinmingjun83@gmail.com",
    "code": "f3febf49-5522-4f66-9011-25baf5c46438"
}
```



##### III. Example Response: use: Using a used voucher code
```js
{
    "exceptionId": "33064324-1baf-485c-9c97-adc9fbf435ff",
    "status": 404,
    "message": "Invalid voucher code. {voucherCode='f3febf49-5522-4f66-9011-25baf5c46438'}",
    "details": null
}
```


***Status Code:*** 404

<br>



---
[Back to top](#vouchercode)
> Made with &#9829; by [thedevsaddam](https://github.com/thedevsaddam) | Generated at: 2020-12-07 12:30:28 by [docgen](https://github.com/thedevsaddam/docgen)
