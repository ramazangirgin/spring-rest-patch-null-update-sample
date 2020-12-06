***
Sample Application for partially updating RESTFul resource with HTTP PATCH method.
***

- API documentation is defined with open-api standard http://spec.openapis.org/oas/v3.0.3

- REST Controller spring code is generated with open-api-code-generator ( https://github.com/OpenAPITools/openapi-generator )

- It is allowed to update resource field with null value 

- It is also possible to validate assigned values for optional fields 

- JsonNullable wrapper ( https://github.com/OpenAPITools/jackson-databind-nullable ) is used for serializing/deserializing the optional fields internally.