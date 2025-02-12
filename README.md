# E-SHOP

- [Reflection 1](#Reflection-1)
- [Reflection 2](#Reflection-2)

## Reflection 1

I was learning along the way, especially the process of web developing that is pretty different compared to Django, which was used on our PBP projects last semester.

Still, the tutorial was easy to follow, even though the unfamiliarity caused me to miss some things as well. Fortunately, i managed to backtrack and correct those mistakes properly.

### Exercise 1: Edit and Product Features

When the exercise was asking me to create an edit and delete function by myself, i found it pretty easy to do, especially after i have fully understood the tutorials leading up to it.

All I needed to do was:
- Edit
  - Create the edit page that is accessible through the button in product list page.
  - Writes the methods in `ProductRepository.java`.
  - Create the interface in the `ProductService.java` and implements it in `ProductServiceImpl.java` that calls the repository method.
  - Calls the service from `ProductController.java` and redirects it to the list page.
- Delete
  - Create the delete button in the product list page.
  - Writes the methods in `ProductRepository.java`.
  - Create the interface in the `ProductService.java` and implements it in `ProductServiceImpl.java` that calls the repository method.
  - Calls the service from `ProductController.java`.

I tried to make the code as clean as possible, following the clean code principles, using the codes from the tutorial as inspirations.

## Reflection 2

Doing the exercise has helped me understand better about testing.

1. Number of unit tests
    - There are no fixed numbers of unit test needed, since every class acts different and some may need more unit tests than the other.
   

2. Code Coverage
    - To provide a good enough coverage for our code, we need to handle:
      - CRUD Operations
      - Null Cases
      - Edge Cases
    - 100% Code coverage doesn't necessarily mean a bug-free code since:
      - Integration bugs might not be caught
      - Logic errors can pass through tests
      - Tests might not cover all of the input combinations


3. A New Functional Test
   - Creating a new functional test with the same setup might cause a few problem to the code cleanliness:
     - Code duplication, since each of the functional test use the same setup code (such as for baseURL)
     - Updating the URL would mean to update each functional test setup.
     
    A suggestion to improve the code cleanliness would be creating a new test class that handles the base setup for every functional test.