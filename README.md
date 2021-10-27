# ss-utopia-email-registration
Prerequisite: MailDev, H2, Postman

run maildev command in your terminal

http://0.0.0.0:1080 (mailDev) use to check email after sign up


http://localhost:8090/h2/  (h2 database) check data change after user click the confirm link. JDBC LINK: jdbc:h2:mem:springAuth


According to different staff the user sign up. Use postman Send a POST request to: http://localhost:8090/api/v1/signup/account or http://localhost:8090/api/v1/signup/card or 
http://localhost:8090/api/v1/signup/loan with JSON body.



<img width="802" alt="Screen Shot 2021-09-24 at 12 52 50 AM" src="https://user-images.githubusercontent.com/87037749/134638700-697b9865-d1d0-405a-8d78-1c556338b4b2.png">


after seding post request confirm email should be receive in MailDev



<img width="1639" alt="Screen Shot 2021-10-26 at 8 40 49 PM" src="https://user-images.githubusercontent.com/87037749/138980853-6f9c3d7a-4c3c-434d-9576-50841c8194e3.png">


then click the link. 
<img width="978" alt="Screen Shot 2021-09-24 at 12 59 16 AM" src="https://user-images.githubusercontent.com/87037749/134639576-f496023d-1cd2-4f0e-9402-ebad106652d2.png">


check the data change in h2.
<img width="918" alt="Screen Shot 2021-09-24 at 1 00 05 AM" src="https://user-images.githubusercontent.com/87037749/134639681-ed80ef9f-ba75-4fbd-a5e7-752d60123db1.png">
