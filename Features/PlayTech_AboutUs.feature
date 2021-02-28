Feature: About Us
Scenario Outline: Verify Number of Employees, Number of countries Playtech has offices, Global licensees, Regulated Jurisdictions on About Us page
Given User enters age details - Day as '<day>',  Month as '<month>',  Year as '<year>' on Home Page
When User visits About Us page
Then Following attributes should be displayed - Number of Employees as <Number of Employees>, Number of countries Playtech has offices as <Number of countries>, Global licensees as <Global licensees>, Regulated Jurisdictions as <Regulated Jurisdictions>
Examples:
|Number of Employees|Number of countries|Global licensees|Regulated Jurisdictions|day|month|year|
|5900|19|140|20|01|05|1989|
