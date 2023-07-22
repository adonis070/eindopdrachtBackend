# Https ****************************************************

* port 8443

# Users ****************************************************

* admin - password
* user - password

# Endpoints ****************************************************

* GET       base endpoint or home

* GET       admin

* GET       authenticated

* GET       info

* GET        /admin
https://localhost:8443/admin


* GET        /authenticated
https://localhost:8443/authenticated

# USERS*****************************************************************
* GET       /users
https://localhost:8443/users


* POST      /users
https://localhost:8443/users
JSON:
    {
        "username": "testuser",
        "password": "password",
        "authorities": [
                {
                    "username": "testuser",
                    "autorisatie": "ROLE_USER"
                }
            ]
    }


* GET       /users/{username}
https://localhost:8443/users/testuser


* PUT       /users/{username}
https://localhost:8443/users/testuser


{
"password": "password1"
}


* GET       /users/{username}/authorities
https://localhost:8443/users/testuser/authorities


* POST      /users/{username}/authorities
https://localhost:8443/users/testuser/authorities
{
    "username": "testuser",
    "autorisatie": "ROLE_ADMIN"
}

* DELETE    /users/{username}/authorities/{autorisatie}
https://localhost:8443/users/testuser/authorities/ROLE_ADMIN


* DELETE    /users/{username}
https://localhost:8443/users/testuser


# Kappers*****************************************************************

* GET       /kappers
https://localhost:8443/kappers

* POST      /kappers
https://localhost:8443/kappers
JSON:
{
    "naam": "Peter"
}

* GET       /kappers/{id}
https://localhost:8443/kappers/4

* PUT       /kappers/{id}
https://localhost:8443/kappers/4
{
    "naam": "Karel"
}

* PATCH     /kappers/{id}
* DELETE    /kappers/{id}
https://localhost:8443/kappers/4

* GET        /kappers/{kapper_id}/klanten/{klant_id}}
https://localhost:8443/kappers/3/klanten/3

* POST       /kappers/{kapper_id}/klanten/{klant_id}}
https://localhost:8443/kappers/1/klanten/3
JSON:
{
    
}

* DELETE     /kappers/{kapper_id}/klanten/{klant_id}}
https://localhost:8443/kappers/1/klanten/3

# Klanten*****************************************************************

* GET       /klanten
https://localhost:8443/klanten/

* POST      /klanten
https://localhost:8443/klanten/
JSON:
    {
        "naam": "Karel de Fries"
    }

* GET       /klanten/{id}
https://localhost:8443/klanten/4

* PUT       /klanten/{id}
https://localhost:8443/klanten/4
{
    "naam": "Karel"
}

* PATCH     /klanten/{id}
* DELETE    /klanten/{id}
https://localhost:8443/klanten/4

* POST        /klanten/{klant_id}/kappers/{kapper_id}}
https://localhost:8443/klanten/1/kappers/1
JSON:
{
    
}

* GET       /klanten/{klant_id}/kappers/{kapper_id}}
https://localhost:8443/klanten/1/kappers/1

* DELETE     /klanten/{klant_id}/kappers/{kapper_id}}
https://localhost:8443/klanten/1/kappers/1


