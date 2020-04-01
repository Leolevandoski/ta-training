Narrative:
As a user,
I want to total like-to-time coefficient to be maximum for the dishes

Scenario: The solution must calculate the maximum sum of all possible Like-to-time coefficients.

Given a collection of dishes '<list>'
When evaluate coefficient of the dishes
Then the coefficient of the list should be equals '<coefficient>'
Examples:
|list          |coefficient  |
|[-1,11,56]    |189          |

Scenario: I want to be able remove some dishes, in which case, a new coefficient is calculated using the left dishes.

Given a collection of dishes '<list>'
When I delete dishes '<deleteDishes>'
When evaluate coefficient of the dishes
Then the coefficient of the list should be equals '<coefficient>'
Examples:
|list                |deleteDishes   |coefficient  |
|[-1,-9,0,5,-7]      |[-9,-7]        |14           |