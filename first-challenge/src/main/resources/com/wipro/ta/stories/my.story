



 Narrative:
 As a user,
 I want to the total Like-to-time coefficient to be maximum for the dishes
   Scenario:

Given A value of dishes '<list>'
When I calculate the solution
Then I shall have the coefficiente '<coefficient>'
Examples:

|list        |coefficient  |
|[-1,3,4]    |17           |

Scenario: Check the solution when a dish must be removed and when anyone is removed



