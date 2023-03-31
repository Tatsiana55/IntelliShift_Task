@wip
  Feature:
    Scenario: looking for a gel pen
      Given user navigates to Amazon page
      When user puts into search bar "gel pen"
      And verifies all suggestions contain "gel pen"
      And user chooses last suggestions option
      And user verifies all search result have "Pen" in its  title
      And user clicks on the item that has the lowest price
      And changes quantity to two, adds item to the cart
      Then user empty cart
      And verifies the message
