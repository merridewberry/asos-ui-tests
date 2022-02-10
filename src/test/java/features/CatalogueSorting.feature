@test
Feature: Sorting catalogue

  Background:
    Given I accept Cookies
    And I press SHOP WOMEN button on the main page
    And I hover over Clothing button in the menu
    And I click on Coats & Jackets product type in the dropdown menu

  @sort
  Scenario: Sorting by price from low to high
    When I click 'Sort' button
    And I click Price low to high in the sorting options dropdown menu
    Then All the articles are sorted in the following order: Price low to high

  @sort
  Scenario: Sorting by price from high to low
    When I click 'Sort' button
    And I click Price high to low in the sorting options dropdown menu
    Then All the articles are sorted in the following order: Price high to low

  @sort
  Scenario: Sorting by price from low to high and then loading more results
    When I click 'Sort' button
    And I click Price low to high in the sorting options dropdown menu
    And I click 'Load more' button
    Then All the articles are sorted in the following order: Price low to high

  @sort
  Scenario: Sorting by price from high to low and then loading more results
    When I click 'Sort' button
    And I click Price high to low in the sorting options dropdown menu
    And I click 'Load more' button
    Then All the articles are sorted in the following order: Price high to low

  @filter @brand
  Scenario Outline: Filtering by brand
    When I click Brand filter button
    And I select <brand> filter in the dropdown menu
    Then All displayed articles are from <brand> brand

    Examples:
      | brand       |
      | ASOS DESIGN |
      | Bershka     |
      | Columbia    |

  @filter @brand
  Scenario: Filtering by brand and then loading more results
    When I click Brand filter button
    And I select ASOS DESIGN filter in the dropdown menu
    And I click 'Load more' button
    Then All displayed articles are from ASOS DESIGN brand

  @filter @brand
  Scenario: Filtering by two brands
    When I click Brand filter button
    And I select Aligne filter in the dropdown menu
    And I select AllSaints filter in the dropdown menu
    Then All displayed articles are from Aligne and AllSaints brands

  @filter @sale-new-season
  Scenario: Filtering by new season
    When I click Sale/New Season filter button
    And I select New Season filter in the dropdown menu
    Then All displayed articles are from New Season category

  @filter @sale-new-season
  Scenario: Filtering by sale
    When I click Sale/New Season filter button
    And I select Sale filter in the dropdown menu
    Then All displayed articles are from Sale category

  @filter @sale-new-season
  Scenario: Filtering by new season and then loading more results
    When I click Sale/New Season filter button
    And I select New Season filter in the dropdown menu
    And I click 'Load more' button
    Then All displayed articles are from New Season category

  @filter @sale-new-season
  Scenario: Filtering by sale and then loading more results
    When I click Sale/New Season filter button
    And I select Sale filter in the dropdown menu
    And I click 'Load more' button
    Then All displayed articles are from Sale category

  @filter @price-range
  Scenario: Filtering by lower price
    When I click Price Range filter button
    And I move sliders so they cover left part of price range
    Then All the displayed prices are within selected price range

  @filter @price-range
  Scenario: Filtering by higher price
    When I click Price Range filter button
    And I move sliders so they cover right part of price range
    Then All the displayed prices are within selected price range

  @filter @price-range
  Scenario: Filtering by middle price
    When I click Price Range filter button
    And I move sliders so they cover middle part of price range
    Then All the displayed prices are within selected price range

  @filter @price-range
  Scenario: Filtering by lower price and then loading more results
    When I click Price Range filter button
    And I move sliders so they cover left part of price range
    And I click 'Load more' button
    Then All the displayed prices are within selected price range

  @filter @price-range
  Scenario: Filtering by higher price and then loading more results
    When I click Price Range filter button
    And I move sliders so they cover right part of price range
    And I click 'Load more' button
    Then All the displayed prices are within selected price range

  @filter @price-range
  Scenario: Filtering by middle price and then loading more results
    When I click Price Range filter button
    And I move sliders so they cover middle part of price range
    And I click 'Load more' button
    Then All the displayed prices are within selected price range

