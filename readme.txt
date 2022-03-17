HOW TO RUN THE APPLICATION
Create a folder, open it, in the address bar type : cmd
Acommand pompt will open. type the following:
1) git clone https://github.com/ruggeromontesi/seb-task.git
2)cd seb-task
3)mvn clean install ( or more quickly :mvn clean install  -DskipTests=true )
4) mvn spring-boot:run
5)Open postman and import collection task.postman_collection.json

This collection contains two calls:
1) requestBundle
2) modifyBundle
*********

requestBundle
The body of the rest call contains the parameters used to model the customers' answers:
{
    "age":32,
    "student": false,
    "income":60000

}
************

 modifyBundle

The body of this call contains details

{    
    "bundleType":"GOLD",
    "customerAnswersDto": {
        "age":25,
         "student":false,
         "income":60000
    },
    "accountType" :"CURRENT_ACCOUNT",
        "cardsToBeRemoved": [
            "GOLD_CREDIT_CARD"
            
            
           
    ],

    "cardsToBeAdded": [
         "GOLD_CREDIT_CARD"

    ]
}





