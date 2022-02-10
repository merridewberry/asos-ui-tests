# Tests for Reddit API

These are tests for some sorting and filtering options for the Asos catalogue page. 

These tests by no means are supposed to cover the sorting functional throughout - most of the features obviously need specific pre-prepared data to sufficiently test them, and using existing data from production servers would result in wonky unreliable tests.

### Test execution

#### Components

- Open JDK 11 (for example, Coretto: https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html)
- Maven (https://maven.apache.org/install.html)

#### Execution

To execute all the tests, run the following command in the project's root directory:

```
    mvn clean test
```

To run tests marked with certain tags, specify it by using the following prompt:

```
    mvn clean test -Dcucumber.filter.tags=@test
```

where:

`-Dcucumber.filter.tags=@filter` runs only the tests with `@filter` tag;

`-Dcucumber.filter.tags='@filter or @brand'` runs all tests that have either `@filter` or `@brand` tag;

`-Dcucumber.filter.tags='@filter and @brand'` runs only the tests that have `@filter` and `@brand` tags at the same time.

#### Tags

All tests have at least one tag that allow you to run specific groups of tests.

- `@test` - all tests
- `@sort` - sorting feature
- `@filter` - filter feature
- `@brand` - filter by brand
- `@sale-new-season` - filter by Sale\New Season
- `@price-range` - filter by price range

#### Reports

You can access test reports by executing the following prompt in the project's root directory:

```
    mvn allure:serve
```

The report will be opened automatically in your default browser.