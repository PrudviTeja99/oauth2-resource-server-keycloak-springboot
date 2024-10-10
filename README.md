# oauth2-resource-server-keycloak-springboot

* Resource Server accepts JWT or opaque-token
## JwtAuthenticationConverter
* JwtAuthenticationConverter fetches default scope authorities
* Need to implement CustomJwtAuthenticationConverter to fetch own claims/authorities
* Custom roles should start with prefix 'ROLE_'