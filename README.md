# Ribbon_LoadBalancing_SpringCloudExample

https://spring.io/guides/gs/client-side-load-balancing/

This example uses Eureka Server, Config Server , 3 instances of Config clients and 1 RibbonClient for Clientside  load balancing<br>

Actually instead of creating 3 config clients, with one client we can start multiple instances in different ports using property<br><br>
eureka.instance.instanceId=${spring.application.name}:${random.value}
<br><br>

Ribbon client will use the services round robin fashion to balance the load on the service.<br>
In Controller class we have configuration to use the service name from Eureka server<br><br>
@RibbonClient(name = "CONFIGCLIENT", configuration = ConfigClientConfiguration.class)
<br><br>

This will take care of balancing the load which is configured in Eureka server.

Apart from this, need to write ConfigClientConfiguration class, to fetch the url and other filter.

