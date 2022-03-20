HOW TO RUN THE APPLICATION
Create a folder, open it, in the address bar type : cmd
Acommand pompt will open. type the following:
1) git clone https://github.com/ruggeromontesi/seb-task.git
2)cd seb-task
3)mvn clean install ( or more quickly :mvn clean install  -DskipTests=true )
4) mvn spring-boot:run
/********************/
/********************/
/********************/
/********************/
HOW TO RUN THE APPLICATION TESTS
5)Open postman and import collection task.postman_collection.json

This collection contains two calls:
1) requestBundle
2) modifyBundle
*********

requestBundle
Method : POST
URI:http://localhost:8080/requestBundle
The body of the rest call contains the parameters used to model the customers' answers:
age: should be within 0 and 100, side points excluded.
student: can be true or false
income : a value bigger than 0
{
    "age":32,
    "student": false,
    "income":60000

}
************

 modifyBundle
 Method : POST
 URI: http://localhost:8080/modifyBundle

The body of this call contains details needed to customize the bundle

"bundleType": Type of Bundle that the customer wants to mofify choose values within
[JUNIOR_SAVER, STUDENT, CLASSIC, CLASSIC_PLUS, GOLD]

 "customerAnswersDto": same as above
"newAccountType" : This field represents the new accounttype that customer wants to have.
It consists of  String chosen among values : 
[CURRENT_ACCOUNT, CURRENT_ACCOUNT_PLUS, JUNIOR_SAVER_ACCOUNT, STUDENT_ACCOUNT]

cardsToBeRemoved: List of card types to be removed. If the card type is not found is simply ignored.
a String chosen among values :
[DEBIT_CARD, CREDIT_CARD, GOLD_CREDIT_CARD]

"cardsToBeAdded":
If the card type is already present is simply ignored.
a String chosen among values :
[DEBIT_CARD, CREDIT_CARD, GOLD_CREDIT_CARD]


{    
    "bundleType":"GOLD",
    "customerAnswersDto": {
        "age":25,
         "student":false,
         "income":60000
    },
    "newAccountType" :"CURRENT_ACCOUNT",
    "cardsToBeRemoved": [
            "GOLD_CREDIT_CARD"
    ],
    "cardsToBeAdded": [
         "GOLD_CREDIT_CARD"
    ]
}
/********************/





