# Terremoto Api
Esta api tiene como objetivo ofrecer información relacionada a los sismos mediante la **api** de
 [earthquake.usgs.gov](https://earthquake.usgs.gov/).
 
 ## Levantar el proyecto
Para levantar el proyecto se debe importar como proyecto en el IDE de preferencia.
> **Aviso:** La  APP fue desarrollada en el IDE **IntellijIDEA**.

Los Endpoints se levantan en el puerto 8080, se puede comprobar el status al obtener un HTTP Code 200 en el siguiente path :
- http://localhost:8080/api/health

### Autenticación Via JWT
Se debe autorizar el uso de la api Mediante una **api-key**, _(b123)_ esta api key generará un **token JWT** .

> Ej Login : http://localhost:8080/api/authenticate/token/b123

Una vez obtenido el Token JWT en la respuesta de authenticate, debe ser enviado en cada unas de las consultas REST dentro del **Header** : **Authorization**
> Autorizarion = Bearer ey123456789abcd

### Los Rest disponibles son los siguientes:

- #### /api/terremoto/fecha
    Retorna todos los sismos en un rango de fechas : 
     
   >  Ejemplo : http://localhost:8080/api/terremoto/fecha?inicio=2019-10-13&fin=2019-10-14 
- #### /api/terremoto/magnitud
  Retorna todos los sismos en un rango de magnitudes : 
     
   >  Ejemplo : http://localhost:8080/api/terremoto/magnitud?inicio=5.2&fin=7 
- #### /api/terremoto/fechas
  Retorna todos los sismos en dos rangos de fechas : 
     
   >  Ejemplo : http://localhost:8080/api/terremoto/fechas?inicio1=2019-10-13&fin1=2019-10-14&inicio2=2019-10-17&fin2=2019-10-18  
- #### /api/terremoto/pais
  Retorna todos los sismos de un pais determinado: 
     
   >  Ejemplo : http://localhost:8080/api/terremoto/pais?pais=Chile
- #### /api/terremoto/fecha-paises
  Retorna todos los sismos en un rango de fechas de dos paises : 
     
   >  Ejemplo : http://localhost:8080/api/terremoto/fecha-paises?pais1=Chile&pais2=Argentina&inicio=2019-10-13&fin=2019-10-14 

- Contacto : Alejandro Venegas  
ale.venegas.moreno@gmail.com