# splitservice
Introduction<br/>
This application is to split and maintain balances between people.
<br/>
<br/>
Functionalities<br/>
1) Add Amount to be splitted<br/>
2) Provide Users to split with<br/>
3) Check Your Overall balance<br/>
4) Take out balance sheet<br/>
5) Split in multiple ways - EQUAL,PERCENT, CUSTOM<br/>
<br/>
<br/>
Endpoints<br/>
1) To post a transaction : 
    api/split -->includes multiple ways and settlement<br/>
    For Equal user is counted by default<br />
    In custom you can provide the exact amount which has to be assigned to any user<br />
    For percent and custom you can provide arbitary values of distribution of share <br />
    
2) To check balance :<br/>
    balance/:userId<br/>
3) To check balance sheet :<br/>
    balance/sheet/:userId<br/>
    If you owe to a user then balance will be negative corresponding to that otherwise positive<br/>
4) To add User : 
     /user
     By default three users are added by id 1,2,3 and can be used
<br/>
<br/>
DB used is h2db and will be initialized by default<br/>
DB can be accessed at http://localhost:8080/h2-console<br/>
I have commented out the dropping foreign key relationship between user and expense as it does not supports to check existing FK so run time it throws duplicate FK exception, I am figuring a way to include this also at time of setup<br/>
While again restarting the service that line can be commented out to make FK <br/>
<br/>
<br/>
Custom Exceptions are implemented and SOLID design principles are used while designing the service<br/>
Postman collection link https://www.getpostman.com/collections/68dfc1e09bd74efc2373
<br/>
<br/>


