---
layout: page
title: Bhanuka Bandara Ekanayake's Project Portfolio Page
---

### Project: FAPro

FApro seeks to improve the quality of life of financial advisors (FAs). It allows FAs to keep track of large numbers of contacts. It allows FAs to have a one-stop platform to manage their contacts and conduct financial analytics while providing a big-picture view of their clientele as a whole.

Given below are my contributions to the project.

* **New Parameter**: Added occupation parameter
  * What it does: Allows the store the occupation of as person when adding the contact 
  * Justification: As Financial Advisors, users may wish to store the Occupation of their clients, so as to get a better gauge on their clients overall financial profile and what investment plans might be more suitable for them.
  * Highlights: As this was my first significant contribution to the groups code, it was initially rather daunting, considering that adding a parameter entails changing many classes (every class that has to do with Person), and additionally meant I had to update all the test cases. However, overall it was a smooth process of coding and deployment.

* **New Feature**: Added the ability to clone contacts
  * What it does: Allows the user to clone contacts if they want to add a similar contact 
  * Justification: Users may have to add multiple similar contacts at one time (i.e people from the same household. As such, the clone feature provides and alternative should the user not wish to type out all the details to add a contact.
  * Highlights: This enhancement was manageable in it's implementation. One difficulty faced was how to clone a command while ensuring that duplicate contacts still could not be held in the addressbook. This difficulty was overcome by adding an integer to the end of the name, so as to make the contacts dissimilar and hence, not duplicates of one another. 

* **Code contributed**: [RepoSense link] (https://nus-cs2103-ay2324s1.github.io/tp-dashboard/#/widget/?search=bhnuka&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos&chartGroupIndex=0&chartIndex=0" frameBorder="0" width="800px" height="142px")

* **Project management**:
  * To be added

* **Enhancements to existing features**:
  * Updated Add Command to take in occupation as a parameter (Pull request [\#49](https://github.com/AY2324S1-CS2103T-W09-1/tp/pull/49))
  * Updated the GUI Colour Scheme and aesthetics (Pull request [\#65](https://github.com/AY2324S1-CS2103T-W09-1/tp/pull/65))
  * Updated Delete Command to take in multiple indexes to delete (Pull request [\#105](https://github.com/AY2324S1-CS2103T-W09-1/tp/pull/105)))

* **Documentation**:
  * User Guide:
    * Added documentation on 'add' and 'clone'
  * Developer Guide:
    * Did implementation of the 'clone' feature

* **Community**:
  * PRs reviewed (with non-trivial review comments): (Pull request [\#43](https://github.com/AY2324S1-CS2103T-W09-1/tp/pull/43), Pull request [\#50](https://github.com/AY2324S1-CS2103T-W09-1/tp/pull/50), Pull request [\#66](https://github.com/AY2324S1-CS2103T-W09-1/tp/pull/66), Pull request [\#71](https://github.com/AY2324S1-CS2103T-W09-1/tp/pull/71), Pull request [\#110](https://github.com/AY2324S1-CS2103T-W09-1/tp/pull/110), Pull request [\#128](https://github.com/AY2324S1-CS2103T-W09-1/tp/pull/128))
