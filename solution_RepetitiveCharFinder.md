# solution_RepetitiveCharFinder

## Task 1: Code Coverage Test
![RepetitiveCharFinder_jacoco.png](/images/RepetitiveCharFinder_jacoco.png)
1. Test when there's only one non-unique string
2. Test when there's multiple non-unique string
3. Test when there's no non-unique string
4. Test when `input` contains both lowercase and uppercase
5. Test when `input` contains various types of characters
6. Test when `input` is empty

## Task 2 & 3: Desinging and Testing Contracts
1. Preconditions
   1. `input` should not be null

2. Postconditions
   1. The system should not return null
   2. The system should return unique elements of array
   3. The order of the result should follow the order of their first appearance

All tests are passed

## Task 4: Property-Based Testing
1. The system should not return null
   ![RepetitiveCharFinder_pbt_1.png](/images/RepetitiveCharFinder_pbt_1.png)
2. The elements of the result array should be unique
   ![RepetitiveCharFinder_pbt_3.png](/images/RepetitiveCharFinder_pbt_3.png)
3. The order of the result should follow the order of their first appearance
   ![RepetitiveCharFinder_pbt_2.png](/images/RepetitiveCharFinder_pbt_2.png)
