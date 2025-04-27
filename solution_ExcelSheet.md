# solution_ExcelSheet

## Task 1: Code Coverage Test
![ExcelSheet_jacoco.png](/images/ExcelSheet_jacoco.png)
1. Test when `columnTitle` consists of a single element
2. Test when `columnTitle` consists of two elements
3. Test when `columnTitle` consists of seven elements
4. Test when `columnTitle` consists of the same elements

## Task 2 & 3: Desinging and Testing Contracts
1. Preconditions
   1. `columnTitle` should not be null or empty
   2. `columnTitle` should contain only the uppercase English

2. Postconditions
   1. System should return the correct result
   2. Result should be more than 1
   3. Result sholud return the correct result even if the result is `Integer.MAX_VALUE (= 2147483647)`

All tests are passed

## Task 4: Property-Based Testing
1. If `columnTitle` is any combination of uppercase English, the result should be more than 1
   - However, generating any random 7 characters might occur overflow problem. However, the postcondition described in 'README.md' is integer type. Therefore, to prevent the overflow, the `columnTitle` bigger than the range of integer type is discarded
   ![ExcelSheet_pbt.png](/images/ExcelSheet_pbt.png)