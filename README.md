### This project is for WEX sofware engineer test

---
#  Requirements
- java 11
- maven
- curl

To run basically build and run using mvn, on project directory
```
mvn spring-boot:run 
```

Then add a transaction ursing the following command
```bash
curl -H "content-type: application/json" -X POST --data '{"transaction_date":"2021-09-30","description":"Example of description", "amount":10}'  127.0.0.1:8080/transaction -vv 

```

Then query the transaction by its ID and country name for conversion
```bash
curl 127.0.0.1:8080/transaction/1/Brazil
```