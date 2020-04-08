Narrative:
As a user,
I want to total like-to-time coefficient to be maximum for the dishes



Scenario 1: The solution must calculate the maximum sum of all possible Like-to-time coefficients.



Given a collection of dishes '<list>'
When evaluate coefficient of the dishes
Then the coefficient of the list should be equals '<coefficient>'


Examples 1:
|list          |coefficient  |
|[-1,3,4]      |17           |



Scenario 2: I want to be able remove some dishes, in which case, a new coefficient is calculated using the left dishes.



Given a collection of dishes '<list>'
When I remove dishes '<removeDishes>'
When evaluate coefficient of the dishes
Then the coefficient of the list should be equals '<coefficient>'



Examples 2:
|list                |removeDishes   |coefficient  |
|[-1,-9,0,5,-7]      |[-9,-7]        |14           |