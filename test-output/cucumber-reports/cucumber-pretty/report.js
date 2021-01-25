$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("features/UI-Test.feature");
formatter.feature({
  "line": 1,
  "name": "Checkout items in the basket",
  "description": "Please use home page of https://www.saucedemo.com/",
  "id": "checkout-items-in-the-basket",
  "keyword": "Feature"
});
formatter.before({
  "duration": 702038721,
  "status": "passed"
});
formatter.scenario({
  "line": 4,
  "name": "Check item total cost and tax",
  "description": "",
  "id": "checkout-items-in-the-basket;check-item-total-cost-and-tax",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "I am on the home page",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "I login in with the following details",
  "rows": [
    {
      "cells": [
        "userName",
        "password"
      ],
      "line": 7
    },
    {
      "cells": [
        "standard_user",
        "secret_sauce"
      ],
      "line": 8
    }
  ],
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "I add the following items to the basket",
  "rows": [
    {
      "cells": [
        "Sauce Labs Backpack"
      ],
      "line": 11
    },
    {
      "cells": [
        "Sauce Labs Fleece Jacket"
      ],
      "line": 12
    },
    {
      "cells": [
        "Sauce Labs Bolt T-Shirt"
      ],
      "line": 13
    },
    {
      "cells": [
        "Sauce Labs Onesie"
      ],
      "line": 14
    }
  ],
  "keyword": "And "
});
formatter.step({
  "line": 16,
  "name": "I  should see 4 items added to the shopping cart",
  "keyword": "And "
});
formatter.step({
  "line": 17,
  "name": "I click on the shopping cart",
  "keyword": "And "
});
formatter.step({
  "line": 18,
  "name": "I verify that the QTY count for each item should be 1",
  "keyword": "And "
});
formatter.step({
  "line": 19,
  "name": "I remove the following item:",
  "rows": [
    {
      "cells": [
        "Sauce Labs Fleece Jacket"
      ],
      "line": 20
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "UIStepDefinintion.iAmOnTheHomePage()"
});
formatter.result({
  "duration": 224226595,
  "status": "passed"
});
formatter.match({
  "location": "UIStepDefinintion.i_login_in_with_the_following_details(DataTable)"
});
formatter.result({
  "duration": 673756290,
  "status": "passed"
});
formatter.match({
  "location": "UIStepDefinintion.i_add_the_following_items_to_the_basket(DataTable)"
});
formatter.result({
  "duration": 572873279,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "4",
      "offset": 14
    }
  ],
  "location": "UIStepDefinintion.i_should_see_items_added_to_the_shopping_cart(int)"
});
formatter.result({
  "duration": 76235783,
  "status": "passed"
});
formatter.match({
  "location": "UIStepDefinintion.i_click_on_the_shopping_cart()"
});
formatter.result({
  "duration": 138808042,
  "status": "passed"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.after({
  "duration": 209014063,
  "status": "passed"
});
});