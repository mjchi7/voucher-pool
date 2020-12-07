
# SpecialOffers

Collections of endpoints related to the Special Offers entity

## Indices

* [Ungrouped](#ungrouped)

  * [create](#1-create)


--------


## Ungrouped



### 1. create


Create a SpecialOffer entity.

```
name: String
    The name of the special offer.
discountPercentage: String
    The discount percentage, up to two decimal points
expiryDate: String
    The expiry date. The date format is "YYYY-MM-DD"
```


***Endpoint:***

```bash
Method: POST
Type: RAW
URL: {{url}}/offer
```



***Body:***

```js        
{
    "name": "December Special Offer 2",
    "discountPercentage": "30.20",
    "expiryDate": "2020-12-31"
}
```



***More example Requests/Responses:***


##### I. Example Request: create: More than 100 discount percentage



***Body:***

```js        
{
    "name": "December Special Offer 2",
    "discountPercentage": "130.20",
    "expiryDate": "2020-12-31"
}
```



##### I. Example Response: create: More than 100 discount percentage
```js
{
    "exceptionId": "8096f7f4-dfa3-4927-8f7c-c0ca0ee0874a",
    "status": 400,
    "message": "Invalid request body",
    "details": [
        {
            "codes": [
                "DecimalMax.specialOfferDto.discountPercentage",
                "DecimalMax.discountPercentage",
                "DecimalMax.java.math.BigDecimal",
                "DecimalMax"
            ],
            "arguments": [
                {
                    "codes": [
                        "specialOfferDto.discountPercentage",
                        "discountPercentage"
                    ],
                    "arguments": null,
                    "defaultMessage": "discountPercentage",
                    "code": "discountPercentage"
                },
                true,
                {
                    "arguments": null,
                    "defaultMessage": "100.0",
                    "codes": [
                        "100.0"
                    ]
                }
            ],
            "defaultMessage": "must be less than or equal to 100.0",
            "objectName": "specialOfferDto",
            "field": "discountPercentage",
            "rejectedValue": 130.2,
            "bindingFailure": false,
            "code": "DecimalMax"
        }
    ]
}
```


***Status Code:*** 400

<br>



##### II. Example Request: create: Less than 0 discount percentage



***Body:***

```js        
{
    "name": "December Special Offer 2",
    "discountPercentage": "-30.20",
    "expiryDate": "2020-12-31"
}
```



##### II. Example Response: create: Less than 0 discount percentage
```js
{
    "exceptionId": "c590ff84-01a4-486f-afcc-56a67017568e",
    "status": 400,
    "message": "Invalid request body",
    "details": [
        {
            "codes": [
                "DecimalMin.specialOfferDto.discountPercentage",
                "DecimalMin.discountPercentage",
                "DecimalMin.java.math.BigDecimal",
                "DecimalMin"
            ],
            "arguments": [
                {
                    "codes": [
                        "specialOfferDto.discountPercentage",
                        "discountPercentage"
                    ],
                    "arguments": null,
                    "defaultMessage": "discountPercentage",
                    "code": "discountPercentage"
                },
                true,
                {
                    "arguments": null,
                    "defaultMessage": "0.00",
                    "codes": [
                        "0.00"
                    ]
                }
            ],
            "defaultMessage": "must be greater than or equal to 0.00",
            "objectName": "specialOfferDto",
            "field": "discountPercentage",
            "rejectedValue": -30.2,
            "bindingFailure": false,
            "code": "DecimalMin"
        }
    ]
}
```


***Status Code:*** 400

<br>



##### III. Example Request: create



***Body:***

```js        
{
    "name": "December Special Offer 2",
    "discountPercentage": "30.20",
    "expiryDate": "2020-12-31"
}
```



***Status Code:*** 201

<br>



---
[Back to top](#specialoffers)
> Made with &#9829; by [thedevsaddam](https://github.com/thedevsaddam) | Generated at: 2020-12-07 12:30:18 by [docgen](https://github.com/thedevsaddam/docgen)
