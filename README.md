# Voucher Pool Service
## Introduction
This Spring Boot based web app is a voucher pool microservice.

## Entities
The microservice has the following entities:
1. Recipient
2. SpecialOffer
3. VoucherCode

## Getting Started
### Using Docker Compose
1. Open up a terminal in the folder `voucherpool`
2. Run `docker-compose up -d`
3. The microservice is now available via the port :8080. 

## Usage Reference
For API usage reference, checkout the markdowns generated from the Postman collections in `docs/generated`.

## Shortcomings/Caveats
The following is a list of items that are lacking in this project:
1. Lacks of extensive integration test
2. Lacks of service layer parameter verifications.
3. Lacks of unit testing on the expiry date.