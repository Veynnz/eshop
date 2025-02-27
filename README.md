# E-SHOP

WEEK 1
- [Reflection 1](#Reflection-1)
- [Reflection 2](#Reflection-2)

WEEK 2
- [Reflection 3](#Reflection-3)

WEEK 3
- [Reflection 4](#Reflection-4)

# WEEK 1
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

# WEEK 2
## Reflection 3

1. In total, there are 10 quality code issues that i fixed, which are:
   - `build.gradle.kts`
     - Fixed a group dependency quality issue by reordering the dependency part based on their own groups to make it look clean.
   - `ProductController.java`
     - Changed field injection to constructor injection instead by declaring the `service` variable as a final and making the constructor for it.
     - Avoid repeating the path "redirect:/product/list" by defining it as a variable.
   - `ProductServiceImpl.java`
     - Changed field injection to constructor injection instead by declaring the `productRepository` variable as a final and making the constructor for it.
   - `EshopApplicationTests.java`
     - Added a comment on the method `contextLoads` to explain why it is left empty.
     - Added an assertion test in the method `mainMethodRunsWithoutError` to test that the application runs without error.
   - `CreateProductFunctionalTest.java`
     - Removed exception throwing from the methods, as it cannot be thrown from the methods` body.
   - `HomePageFunctionalTest.java`
     - Removed exception throwing from the methods, as it cannot be thrown from the methods` body.
   - `ProductRepositoryTest.java`
     - Removed the `setUp` method since it is not needed, because every test has their own set up.
     - Removed importing the `BeforeEach` modifier since it is not used anymore. (It was previously used by the setUp method)

2. **CI/CD IMPLEMENTATION**

Currently my program has implemented:

- Automated testing, which consits of running the unit tests, done by `ci.yml`.
- Automated security analysis, particularly on supply chain security, done by `scorecard.yml`.
- Automated quality issue analysis, done through the integration with SonarCloud.
- Automated deployment, done through the integration with Koyeb.

All of these are done automatically on push/pull request (except deployment that only triggers by a push in main branch).

Since it met every requirements, the current implementation has met the definition of Continuous Integration and Continuous Deployment.

# WEEK 3
## Reflection 4

1. **SOLID Principles I Applied**

Manually, i only implemented 3 principles which are:

- SRP (*Single Responsibility Principle*)
  - Removed the extension from ProductController to CarController
  since they both handle different items.
  - Separates CarController onto a different file.
- OCP (*Open-Closed Principle*)
  - Adds interfaces for repository, to allow extension.
  - Implements constructor injection for most of the clases.
  - Creates abstract classes for model, controller, repository, and service.
- ISP (*Interface Segregation Principle*)
  - Separates the interface for post(create, update, delete) and get(findById, findAll) methods. 
  Including the implementations that depends on them.

2. **Advantages of SOLID**

- Each class or function has a single responsibility, making it easier to modify without affecting other parts of the system.
  - For example: Debugging the controller (that i had many trouble with) was much easier, since the controllers was split between Car, Product, Post, and Get.
- With principles like `Open/Closed`, components can be reused in different parts of the project easily.
  - For example: If i were to create another service with similar implementation, i just need to inherit from the abstract class without remaking the same thing.
- New features can be added without modifying existing code.
  - For example: If i want to give Product another feature, like adding a description, i only need to implement on the Product side, wihtout affecting the Car side at all.

3. **Disadvantages of not Applying SOLID**

- The codes are tightly coupled together, making it hard to modify.
  - For example: If CarController were to ProductController like the original, then changing the ProductController would affect CarController as well, since it inherits the methods and attributes.
- Modifying codes become time consuming.
  - For example: If a class were to get a new feature, there is a possibility that the whole class needs to change, instead of just adding a new module.
- If an interface forces a class to implement unnecessary methods, it creates confusion and unnecessary dependencies.
  - For example: If there were an implementation of get methods (like routing etc), forcing it to have post methods (like create, update, delete) would create confusion and unnecessary.