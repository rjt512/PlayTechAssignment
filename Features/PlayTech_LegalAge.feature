Feature: Legal age feature

Scenario Outline: Verify legal age feature functions as expected for over 18
Given User is on PlayTech Legal age verification page
When User enters age details - Day as '<day>',  Month as '<month>',  Year as '<year>' 
Then User is taken to Home Page
Examples:
|day|month|year|
|01|05|1989|

Scenario Outline: Verify legal age feature functions as expected for below 18
Given User is on PlayTech Legal age verification page
When User enters age details - Day as '<day>',  Month as '<month>',  Year as '<year>'
Then Following alert message is displayed "Alert: Sorry you must be over 18 to enter."
Examples:
|day|month|year|
|01|05|2020|