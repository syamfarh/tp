---
layout: page
title: Syam Farhan's Project Portfolio Page
---

### Project: FAPro

FApro seeks to improve the quality of life of financial advisors (FAs). It allows FAs to keep track of large numbers of contacts. It allows FAs to have a one-stop platform to manage their contacts and conduct financial analytics while providing a big-picture view of their clientele as a whole.

Given below are my contributions to the project.

* **New Parameter**: Added Appointment Date parameter
  * What it does: Allows the store of appointment date of a client
  * Justification: Users may wish to store the Appointment Date of their clients, to remind them of their next appointment with their client 
  * Highlights: As the value inputted is in String datatype, creating a date parser can be hard as there are many ways to format the date without creating bugs. Overall, a proper planning should have been done before going straight to coding. 

* **New Feature**: Added the ability to sort contacts
  * What it does: Allows the user to sort the contact list by Name or Appointment Date parameter.
  * Justification: Users may wish to have an organized contact lists to find the contacts easily or to find the earliest appointment date to remind them of their next appointment.
  * Highlights: Slightly difficult as the list is saved in an immutable list. Circumvented by returning a new sorted list each time the function is called. Overall,  manageable to implement.

* **New Feature**: Added calendar window 
  * What it does: Allows the user to view appointment date in a calendar month view.
    * Justification: Users may want to quickly identify available dates, plan ahead, and avoid double-booking of clients appointments
  * Highlights: Manageable as I used CalendarFX library which is available online. However, it was challenging to find ways to disable allowing users to add new appointment date directly into the calendar instead of using CLI. In the end, I was able to disable the feature and only allow user to navigate the window using left and right arrow key to go between month page. 
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=syamfarh&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22)

* **Project management**:
  * Set up GitHub team organisation and repository.
  * Set up GitHub workflows and CI.
  * Set up deploy as a Jekyll site.
  * Set up Codecov.
  * Added Codecov and CI status indicators to repository.

* **Enhancements to existing features**:
  * Fix edit function to work with Occupation parameter
  
* **Documentation**:
    * User Guide:
        * Added documentation on `edit`, `sort` and `calendar`
        * Added glossary section
        * Added known issues for calendar window
        * Added common questions for calendar window issues
        * Added page break for every features 
    * Developer Guide:
        * Added use case for `sort`
        * Added implementation for `calendar`
        * Added implementation for `sort`
        * Added implementation for `edit`
        * Added instructions for manual testing for `sort`
        * Added 2 points for future implementation
        * Added 1 point for planned enhancement
        * Added acknowledgement for CalendarFX
        * Added user story for `sort` feature

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#11](https://github.com/AY2324S1-CS2103T-W09-1/tp/pull/11), [\#15](https://github.com/AY2324S1-CS2103T-W09-1/tp/pull/15), [\#65](https://github.com/AY2324S1-CS2103T-W09-1/tp/pull/65), [\#78](https://github.com/AY2324S1-CS2103T-W09-1/tp/pull/78), [\#86](https://github.com/AY2324S1-CS2103T-W09-1/tp/pull/86), [\#87](https://github.com/AY2324S1-CS2103T-W09-1/tp/pull/87), [\#88](https://github.com/AY2324S1-CS2103T-W09-1/tp/pull/88), [\#90](https://github.com/AY2324S1-CS2103T-W09-1/tp/pull/90), [\#99](https://github.com/AY2324S1-CS2103T-W09-1/tp/pull/99), [\#100](https://github.com/AY2324S1-CS2103T-W09-1/tp/pull/100), [\#105](https://github.com/AY2324S1-CS2103T-W09-1/tp/pull/105), [\#116](https://github.com/AY2324S1-CS2103T-W09-1/tp/pull/116), [\#127](https://github.com/AY2324S1-CS2103T-W09-1/tp/pull/127), [\#153](https://github.com/AY2324S1-CS2103T-W09-1/tp/pull/153), [\#204](https://github.com/AY2324S1-CS2103T-W09-1/tp/pull/204), [\#205](https://github.com/AY2324S1-CS2103T-W09-1/tp/pull/205), [\#206](https://github.com/AY2324S1-CS2103T-W09-1/tp/pull/206), [\#221](https://github.com/AY2324S1-CS2103T-W09-1/tp/pull/221), [`#222](https://github.com/AY2324S1-CS2103T-W09-1/tp/pull/222) 

