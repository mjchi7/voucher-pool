
# Recipient

Collections of endpoints related to the Recipient entity

## Indices

* [Ungrouped](#ungrouped)

  * [create](#1-create)


--------


## Ungrouped



### 1. create


Create a Recipient Entity.

```
name: String  
  The name of the Entity  
email: String  
  The email of the Entity. Must be unique
```


***Endpoint:***

```bash
Method: POST
Type: RAW
URL: {{url}}/recipient
```



***Body:***

```js        
{
    "name": "CMJ3",
    "email": "chinmingjun83@gmail.com"
}
```



***More example Requests/Responses:***


##### I. Example Request: create



***Body:***

```js        
{
    "name": "CMJ3",
    "email": "chinmingjun83@gmail.com"
}
```



***Status Code:*** 201

<br>



##### II. Example Request: create: email non unique



***Body:***

```js        
{
    "name": "CMJ3",
    "email": "chinmingjun83@gmail.com"
}
```



##### II. Example Response: create: email non unique
```js
{
    "exceptionId": "bafde584-f0db-4467-98e1-d69fe1fe3f63",
    "status": 400,
    "message": "The email is already exists in the system. {email='chinmingjun83@gmail.com'}",
    "details": null
}
```


***Status Code:*** 400

<br>



---
[Back to top](#recipient)
> Made with &#9829; by [thedevsaddam](https://github.com/thedevsaddam) | Generated at: 2020-12-07 12:29:58 by [docgen](https://github.com/thedevsaddam/docgen)
