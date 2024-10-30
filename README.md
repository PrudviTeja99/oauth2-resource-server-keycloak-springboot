# oauth2-resource-server-keycloak-springboot

* Resource Server accepts JWT or opaque-token
## JwtAuthenticationConverter
* JwtAuthenticationConverter fetches default scope authorities
* Need to implement CustomJwtAuthenticationConverter to fetch own claims/authorities
* Custom roles should start with prefix 'ROLE_'

## Config client
* optional:configserver:http://localhost:8888, if configuration is optional then when services is being started it will not throw any error and starts normally if config-server is not active.
* configserver:http://localhost:8888, if configuration is required then application will throw an error when service is being started and won't start if config-server is not active.
* property file in git should have same name as application name.