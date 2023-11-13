---
layout: page
title: FAPro User Guide
---

Table of contents
--------------------------------------------------------------------------------------------------------------------
1. [Product Overview](#product-overview)
2. [Quick Start](#quick-start)
3. [Features](#features)
   * [help](#viewing-help-help)
   * [questionnaire](#viewing-risk-assessment-questionnaire-questionnaire)
   * [riskprofile](#adds-risk-profile-level-to-a-contact-riskprofile)
   * [add](#adding-a-person-add)
   * [clone](#cloning-a-person--clone)
   * [delete](#deleting-a-person--delete)
   * [list](#list-out-all-contacts--list)
   * [edit](#editing-a-person--edit)
   * [find](#searching-persons-find)
   * [undo](#undoing-a-command--undo)
   * [redo](#redoing-an-undo-command--redo)
   * [sort](#sorting-contacts--sort)
   * [calendar](#opening-calendar-window--calendar)
   * [clear](#clearing-all-entries--clear)
   * [exit](#exiting-the-program--exit)
4. [Troubleshooting](#troubleshooting)
   * [Technical Questions](#technical-questions)
   * [Common Questions](#common-questions)
   * [Known Issues](#known-issues)
5. [Command summary](#command-summary)
6. [Glossary](#glossary)

<div style="page-break-after: always;"></div>

## Product Overview

The FAPro User Guide is designed to assist financial advisors (FAs) in optimising their client management. It aims to
improve the quality of life for FAs by providing a comprehensive, user-centric resource that enables them to efficiently
 track large numbers of contacts and manage their clients in one central platform.

This guide is intended to empower FAs (who posses basic knowledge on Microsoft Excel, with the knowledge and skills
needed to make the most out of FAPro, ultimately enhancing their ability to provide financial services effectively.

FAPro allows FAs to effortlessly organise and maintain a large database of contacts. Keep detailed client profiles,
track interactions, and categorise contacts for targeted engagement.

This guide is organised into sections for easy reference. Use the table of contents to jump to specific topics, and
utilise hyperlinks for quick access to related content.

Throughout the guide, we'll provide tips and best practices to help you make the most of FAPro's features and optimise
your workflow. A glossary of key terms is included to ensure you understand the technical jargon used in FAPro.

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `FAPro.jar` from [here](https://github.com/AY2324S1-CS2103T-W09-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for FAPro

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar FAPro.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

  * `list` : Lists all contacts.

  * `add n/John Doe p/98765432 e/johnd@example.com o/Barber a/John Street, Block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

  * `delete 3` : Deletes the 3rd contact shown in the current list.

  * `clear` : Deletes all contacts.

  * `exit` : Exits the app.

* Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters you supplied<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`

* Items in square brackets are optional<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`

* Items with `…` after them can be used multiple times including zero times<br>
  e.g. `[t/TAG]…` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc

* Parameters can be in any order<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit`, `clear`, `questionnaire`, `calendar`) will be ignored<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application
</div>

<div style="page-break-after: always;"></div>

### Viewing help: `help`

Discover our basic commands right away. If you want to explore other commands, FAPro user guide link is provided for you. 

#### Format: 
* `help`

#### Precise expected outcome on success:

* You should see this message:
  ```
  Opened help window.
  ```

  <img src="images/help-UG/help_success.png" alt="image" width="700" height="auto">

* You will see a pop-up window with basic commands and a link to FAPro's user guide for other commands

  <img src="images/help-UG/helpWindow.png" alt="image" width="700" height="auto">

<div style="page-break-after: always;"></div>

### Viewing risk assessment questionnaire: `questionnaire`

Show you questions that are used for generating client risk profile levels.

#### Format: 
* `questionnaire`

#### Precise expected outcome on success:
* You should see this message:
   ```
   Opened questionnaire window.
   ```

  <img src="images/questionnaire-UG/questionnaire_success.png" alt="image" width="700" height="auto">

* You will see a pop-up window with risk assessment multiple-choice questions, including the grading and risk profile categories criteria

  <img src="images/questionnaire-UG/questionnaireResult.png" alt="image" width="700" height="auto">

<div style="page-break-after: always;"></div>

### Adds risk profile level to a contact: `riskprofile`

Generates your client risk profile based on their response to questions provided in the risk assessment questionnaire window
and adds it to their contact.

#### Format:
* `riskprofile INDEX res/RESULT`

#### Example commands:
* `riskprofile 3 res/a,b,c,d,e,e,b,c`

  <img src="images/riskProfile-UG/riskProfileFormat.png" alt="image" width="700" height="auto">

  <div markdown="span" class="alert alert-primary">:bulb: **Tip:**

  The risk profile label cannot be removed once you have created it, 
  but you can use this command to update your client risk profile
  </div>

  <div markdown="block" class="alert alert-info">

  **:information_source: Note**<br>
  When you input multiple prefixes <b>res/</b> along with its RESULT:
  * Previous <b>res/</b> entries with its RESULT will be <span style="color: red;">ignored</span>
  * The risk profile label displayed will be based on the <u>last input you entered</u>
  </div>

#### Acceptable values for each parameter:
* INDEX: Only accept positive integers less than the size of your contacts
* RESULT: Valid result format, 8 comma-separated characters without whitespace from 'a' - 'e' (e,b,a,c,b,b,a,e)

#### Precise expected outcome on success:
* You should see this message:
  ```
  Added risk profile to Person: X
  ```
  where X are the details of your client contact edited
* A risk profile label with a specified color will be added to your client contact, which has five categories:
  <span style="background-color:#4CAF50; color:white;">**Low**</span>,
  <span style="background-color:#2196F3; color:white;">**Moderately Low**</span>,
  <span style="background-color:#FFC107; color:white;">**Moderate**</span>,
  <span style="background-color:#FF6600; color:white;">**Moderately High**</span>,
  <span style="background-color:#F44336; color:white;">**High**</span>


  <img src="images/riskProfile-UG/riskProfileResult.png" alt="image" width="700" height="auto">

#### Precise expected outputs on failure:
If your RESULT is empty or INDEX is negative integers
* You should see this error message:
  ```
  Invalid command format! 
  riskprofile: Adds the risk profile of the person identified by the index number 
  used in the last person listing.
  Existing risk profile will be overwritten by the input.
  Parameters: INDEX (must be a positive integer) res/[RESULT]
  Example: riskprofile 1 res/a,e,b,d,c,a,d,e
  ```

  <img src="images/riskProfile-UG/invalidRiskProfile1.png" alt="image" width="700" height="auto">  

  <img src="images/riskProfile-UG/invalidRiskProfile3.png" alt="image" width="700" height="auto">  

If your RESULT is not separated by commas, with any whitespace or not in the range of 'a' - 'e'
* You should see this error message:
  ```
  Result must have 8 comma-separated characters from 'a' to 'e'!
  riskprofile: Adds the risk profile of the person identified by the index number 
  used in the last person listing. 
  Existing risk profile will be overwritten by the input.
  Parameters: INDEX (must be a positive integer) res/[RESULT]
  Example: riskprofile 1 res/a,e,b,d,c,a,d,e
  ```

  <img src="images/riskProfile-UG/invalidRiskProfile2.png" alt="image" width="700" height="auto">

<div style="page-break-after: always;"></div>

### Adding a person: `add`

Allows you to add a new entry to your address book, including personal details such as your clients name, address, occupation, phone number and email.

The main method you will be using to add contacts in typical situations.

#### Format:
* `add n/NAME p/PHONE_NUMBER e/EMAIL o/OCCUPATION a/ADDRESS [t/TAG]… [appt/APPOINTMENT_DATE]`

#### Example commands:
* `add n/John Doe p/98765432 e/johnd@example.com o/Barber a/John Street, Block 123, #01-01 appt/12-12-2023 08:30`
* `add n/Betsy Crowe t/Friend e/betsycrowe@example.com o/Entrepreneur a/Newgate Prison p/1234567`
* `add n/Robert Johnson p/55512345 e/robertj@email.com o/Hairdresser a/789 Oak Street, Suite 10`

#### Acceptable values for each parameter:
* NAME: Must be alphanumeric characters only. Name must be unique. (John Doe)
* ADDRESS: Can take any values except blank (8 College Ave West)
* PHONE NUMBER: Numbers only. Must be at least 3 digits long. (81234567)
* EMAIL ADDRESS: Accepts **all** types of characters but must adhere to basic email formatting as follows: <br>
  Emails should be of the format local-part@domain and adhere to the following constraints:
  1. The local-part should only contain alphanumeric characters and these special characters, +_.-. The local-part may
  not start or end with any special characters
  2. This is followed by a '@' and then a domain name. The domain name is made up of domain labels
  separated by periods
  The domain name must:
  * End with a domain label at least 2 characters long
  * Have each domain label start and end with alphanumeric characters
  * Have each domain label consist of alphanumeric characters, separated only by hyphens, if any
* OCCUPATION: Must be alphanumeric characters only
* TAG: Must be alphanumeric characters only
* APPOINTMENT_DATE: Valid string appointment date format (yyyy-mm-dd HH:mm, mm/dd/yyyy HH:mm or dd-mm-yyyy HH:mm) (date and time must be after the current date and time) 

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>


#### Precise expected outputs on success:
* You should see this message:

  ```
  New Person added: X
  ```

  where X are the details of the person you added

* For example, for Robert Johnson (the example command), it would be:<br> `New person added: Robert Johnson; Phone: 55512345; Email: robertj@email.com; Occupation: Hairdresser; Address: 789 Oak Street, Suite 10; AppointmentDate: ; Tags: `<br> Please note that both Appointment Date and Tags are empty as they are not necessary for adding a person
* This is the original empty address book

  <img src="images/add-UG/addbefore.png" alt="image" width="700" height="auto">
  
* The new entry is displayed in your address book GUI


  <img src="images/add-UG/add_success.png" alt="image" width="700" height="auto">

#### Precise expected outputs on failure:

If a required parameter is missing (e.g., NAME, EMAIL)

* You should see this message:

  ```
  Invalid command format! add: Adds a person to the address book.
  Parameters: n/NAME p/PHONE e/EMAIL a/ADDRESS [t/TAG]... 
  Example: add n/John Doe p/98765432 e/johnd@example.com a/311,
  Clementi Ave 2, #02-25 t/friends t/owesMoney
  ```

  <img src="images/add-UG/addmissingparam.png" alt="image" width="700" height="auto">

If a parameter you provided is in an invalid format (e.g., an invalid email address), an error message should indicate the invalid format.

* You should see this error message:

  ```
  Emails should be of the format local-part@domain and adhere to the 
  following constraints:
  1. The local-part should only contain alphanumeric characters and these special
  characters, excluding the parentheses, (+_.-). The local-part may not start or end 
  with any special characters.
  2. This is followed by a '@' and then a domain name. The domain name is made
  up of domain labels separated by periods.
  The domain name must:
    - end with a domain label at least 2 characters long
    - have each domain label start and end with alphanumeric characters
    - have each domain label consist of alphanumeric characters, separated
      only by hyphens, if any.
  

  ```
  
<br>

  <img src="images/add-UG/addinvalidemail.png" alt="image" width="700" height="auto">

If a parameter you enter is specified multiple times (e.g., --name John --name Doe), an error should indicate that the parameter can only be specified once.

* You should see this error message:

  ```
  Multiple values specified for the following single-valued field(s): n/
  ```

  <img src="images/add-UG/addduplicateparam.png" alt="image" width="700" height="auto">

<div style="page-break-after: always;"></div>

### Cloning a person : `clone`

Clones a contact from your address book at the specified index.

Makes it easier for you to add a contact with very similar details (i.e from the same household)

#### Format:
* `clone INDEX`
* Clones the person at the specified `INDEX`
* The index refers to the index number shown in your displayed person list

#### Example commands:
After cloning, the clone is the exact same as the original, other than a suffix either being attached at the end of their name or, if a suffix is already present, the suffix at the end of their name is incremented
* `list` followed by `clone 2` clones the 2nd person in the address book
* `find Betsy` followed by `clone 1` clones the 1st person in the results of the `find` command

#### Acceptable values for each parameter:
* INDEX: Only accept **non-negative** int values that are less than the size of the address book. Must have at least one
* PERSON: If the person being cloned has a suffix at the end, the suffix cannot be 0 or 2147483647 (MAX_INT)


#### Precise expected outputs on success:
* You should see this message:

  ```
  Cloned Person: X
  ```
  where X are the details of the person the clone is based off


* For example, if

  ```
  Name: John Doe; Phone: 98765432; Email: johnd@example.com; 
  Occupation: Barber;
  Address: Hougang Avenue 1; AppointmentDate: ; Tags:
  ```

  is the person you cloned, then the output you will see is:

  ```
  Cloned Person: John Doe; Phone: 98765432; Email: johnd@example.com;
  Occupation: Barber;
  Address: Hougang Avenue 1; AppointmentDate: ; Tags:
  ```
* This is the original address book with just one contact (John)

  <img src="images/clone-UG/clonebefore.png" alt="image" width="700" height="auto">

* The cloned entry is displayed in your address book GUI

  <img src="images/clone-UG/clone_success.png" alt="image" width="700" height="auto">

#### Precise expected outputs on failure:

If no index, 0 or a negative index is entered next to the clone command

* You should see this error message:

  ```
  Invalid command format! clone: Clones the person identified by the index
  number used in the displayed person list. Parameters: INDEX (must be a
  positive integer).
  ```

  <img src="images/clone-UG/cloneinvalidindex.png" alt="image" width="700" height="auto">

If the index you enter is greater than the current number of contacts in the address book

* You should see this error message:

  ```
  The person index provided is invalid.
  ```

  <img src="images/clone-UG/clonelargeindex.png" alt="image" width="700" height="auto">

If the suffix of the person you cloned is either 0 or 2147483647 (MAX_INT)

* You should see this error message:

  ```
  The integer suffix of the person being cloned is out of range. Please note that
  the smallest possible suffix that a person can have is 1 and the largest possible
  suffix that a person can have is 2147483647. As such, if your suffix is 0 or
  2147483647, please consider editing the names of your contacts first.
  ```

  <img src="images/clone-UG/clonesuffixerror.png" width="700" height="auto">

<div style="page-break-after: always;"></div>

### Deleting a person : `delete`

Deletes a contact from your address book at the specified indexes.

Useful in instances where you have no more need for a clients contact details.

#### Format: 
* `delete INDEX [MORE INDEXES]`
* Deletes the person at `INDEX` (and the persons at other specified INDEXES)
* The index refers to the index number shown in the displayed person list

#### Acceptable values for each parameter:
* INDEX: **Must be positive integers** (i.e 1 2 3 etc.) (less than the size of the contact list in FAPro), must have a space in between and no commas (i.e delete 1,2,3 is invalid)

#### Example commands:
* `list` followed by `delete 2` deletes the 2nd person in the address book
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command
* `list` followed by `delete 2 5` deletes the 2nd and 5th person in the address book

  <img src="images/delete-UG/deleteformat.png" alt="image" width="700" height="auto">

#### Precise expected outputs on success:

* You should see this message:

  ```
  Deleted Person(s): X
  ```

  where X are the details of the deleted persons
* Size of address book is reduced by however many indexes were entered (i.e if you entered delete 1 2 3, as you entered 3 indexes, the size of the address book decreases by 3) 
* GUI reflects that deleted contacts are now no longer there

  <img src="images/delete-UG/deleteafter.png" alt="image" width="700" height="auto">

#### Precise expected outputs on failure:

If any of the entered indexes are invalid:
* You should see this error message:

  ```
  The person index provided is invalid.
  ```

  <img src="images/delete-UG/deletewrongindex.png" alt="image" width="700" height="auto">

If no index is placed after the delete command:
* You should see this error message:

  ```
  Invalid command format! 
  delete: Deletes the person(s) identified by the index number used in the
  displayed person list. 
  Parameters: INDEXES (must be positive integers, separated by spaces)
  Example: delete 1 3 5
  ```

  <img src="images/delete-UG/deletenoindex.png" alt="image" width="700" height="auto">

<div style="page-break-after: always;"></div>

### List out all contacts : `list`

Show a list of all your contacts.

#### Format: 
* `list`

#### Precise expected outputs on success:
* You should see this message:
  ```
  Listed all persons
  ```

* You will see a list of all your contacts with their details

  <img src="images/list-UG/listResult.png" alt="image" width="700" height="auto">

<div style="page-break-after: always;"></div>

### Editing a person : `edit`

Edits an existing client's parameter in FAPro.

#### Format:
* `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [o/OCCUPATION] [appt/APPOINTMENT_DATE] [a/ADDRESS] [t/TAG]…`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …
* At least one of the optional fields must be provided
* Existing values will be updated to the input values
* When editing tags, the existing tags of the person will be removed i.e. adding of tags is not cumulative
* You can remove all the person’s tags by typing `t/` without
  specifying any tags after it

#### Acceptable values for each parameter:
* Refer to `add` command

#### Example commands:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags
*  `edit 2 n/ Jamus Lee e/jamusless@example.com` Edits the name of the 2nd person to be `Jamus Lee` and email 
   address of the 2nd person to be `jamusless@example.com`

  <img src="images/edit-UG/editformat.png" alt="image" width="700" height="auto">

#### Precise expected outputs on success:
* You should see this message:
  ```
  Edited Person:[NAME], Phone Number:[PHONE_NUMBER], Email:[EMAIL], 
  Occupation:[OCCUPATION], Address:[ADDRESS], AppointmentDate:[APPOINTMENT_DATE],
  Tags:[TAGS]…
  ```
* The new entry is displayed in the address book GUI.

  <img src="images/edit-UG/editresult.png" alt="image" width="700" height="auto">

#### Precise expected outputs on failure:

If a required parameter is missing (e.g., name, email), an error message should specify which parameter is missing.
* You should see this error message:
  ```
  Invalid command format! 
  edit: Edits the details of the person identified by the index number used in the
  displayed person list. Existing values will be overwritten by the input values.
  Parameters: INDEX (must be a positive integer) [n/NAME] [p/PHONE] [e/EMAIL]
  [o/OCCUPATION] [a/ADDRESS] [appt/APPOINTMENT_DATE] [t/TAG]…
  Example: edit 1 p/91234567 e/johndoe@example.com
  ```

  <img src="images/edit-UG/editfailure.png" alt="image" width="700" height="auto">

<div style="page-break-after: always;"></div>

### Searching persons: `find`

Finds persons based on the given keywords.

#### Format: 

* `find n/NAME [MORE NAMES]`

* `find a/ADDRESS [MORE ADDRESSES]` 

* `find appt/APPOINTMENT_DATE`

#### Acceptable values for each parameter:
* NAME: Valid string name
* ADDRESS: Valid string address
* APPOINTMENT_DATE: Valid dates (after current date), in the format of `yyyy-mm-dd`


#### Example commands:
* `find n/John Alice`, finds all contacts whose names include "John" and "Alice"
* `find a/Tokyo Geylang`, finds all contacts whose address include "Tokyo" and "Geylang" , followed by `delete 2`, 
  will delete the contact at index 2 after find
* `find appt/2024-01-01`, finds all contacts whose appointment date matches "2024-01-01", followed by `edit 1`, will 
  edit the contact at index 1 after find



#### _Find by name:_

  <img src="images/find-UG/find_n0.png" alt="image" width="700" height="auto">

#### Precise expected outputs on success:
* You should see this message: 
  ```
  X persons listed!
  ```
  where X is the number of contacts listed
* The list of contacts whose names contain the input name is shown

  <img src="images/find-UG/find_n1.png" alt="image" width="700" height="auto">

#### Precise expected outputs on failure:
If no name is input after the find command, an error message explaining the error will be shown, 
reminding you to follow the correct input format.

* You should see this error message:

  ```
  Invalid command format! find n/: Finds all persons whose names contain any of
  the specified keywords (case-insensitive) and displays them as a list with index
  numbers.
  ```

  <img src="images/find-UG/find_n2.png" alt="image" width="700" height="auto">

#### _Find by address:_


  <img src="images/find-UG/find_a0.png" alt="image" width="700" height="auto">

#### Precise expected outputs on success:

* You should see this message:
  ```
  X persons listed!
  ```
  where X is the number of contacts listed
* The list of contacts whose address contain the input address is shown

  <img src="images/find-UG/find_a1.png" alt="image" width="700" height="auto">

#### Precise expected outputs on failure:
If no address is input after the find command, an error message explaining the error will be shown,
reminding you to follow the correct input format.

* You should see this error message:

  ```
  Invalid command format! find a/: Finds all persons whose address contain any of
  the specified keywords (case-insensitive) and displays them as a list with index
  numbers.
  ```

  <img src="images/find-UG/find_a2.png" alt="image" width="700" height="auto">


#### _Find by appointment date:_


  <img src="images/find-UG/find_appt0.png" alt="image" width="700" height="auto">

#### Precise expected outputs on success:
* You should see this message:
  ```
  X persons listed!
  ```
  where X is the number of contacts listed
* The list of contacts whose appointment date matches the input date is shown

  <img src="images/find-UG/find_appt1.png" alt="image" width="700" height="auto">

#### Precise expected outputs on failure:
If no date is input after the find command, an error message explaining the error will be shown,
reminding you to follow the correct input format.

* You should see this error message:
  ```
  Invalid command format! find_appt: Finds all persons whose appointment date
  matches the specified input date and displays them as a list with index numbers.
  ```

  <img src="images/find-UG/find_appt2.png" alt="image" width="700" height="auto">

If an input date does not follow the accepted format, an error message explaining the error will be shown,
reminding you to follow the correct input format.

* You should see this error message:
  ```
  Appointment Date should follow the format of [mm/dd/yyyy] or [dd-mm-yyyy] 
  or [yyyy-mm-dd].
  ```

  <img src="images/find-UG/find_appt3.png" alt="image" width="700" height="auto">

If an input date is a past date (a date before the current date), an error message explaining the error will be shown, 
reminding you to input a valid date.

* You should see this error message:
  ```
  Appointment Date should be after the current date.
  ```

  <img src="images/find-UG/find_appt4.png" alt="image" width="700" height="auto">

<div style="page-break-after: always;"></div>

### Undoing a command : `undo`

Undoes the most recent undoable command. Undoable commands include: add, clone, delete, clear, edit. It is also 
possible to undo the most recent redo command.

#### Format: 
* `undo`

#### Example commands:
* In general, `undo` is used when you have accidentally made a mistake, reverting the change
* `add n/John Doe p/98765432 e/johnd@example.com o/Barber a/John Street, Block 123, #01-01` followed by `undo` (if
  you think adding the person was a mistake), will delete the added contact
* `clone 1`, assuming there is a contact to clone, followed by `undo` (if you think cloning the person was a mistake),
  will delete the cloned contact
* `delete 1`, assuming there is a contact to delete, followed by `undo` (if you think deleting the person was a
  mistake), will add the contact back
* `clear`, assuming there is at least one contact to clear, followed by `undo` (if you think clearing all persons was
  a mistake), will add all cleared contacts back
* `edit 1 p/91234567`, assuming there is a contact to edit, followed by `undo` (if you think editing all persons was
  a mistake), will revert the edit of the contact
* Assuming there are 3 contacts, `delete 1`, which deletes the first contact, followed by `clear`, which clears the
  remaining 2 contacts, followed by `undo` will only add the 2 cleared contacts back. A subsequent `undo` will add
  the contact deleted at the beginning back
* `edit 1 p/91234567`, assuming there is a contact to edit, edits the first contact, followed by `delete 1`, deletes
  the first contact. Then, `undo` will add back the deleted contact, and the next `undo` will revert the edit of the
  contact (which was also just added back)

#### Precise expected outputs on success:

For undoing an add command:

* Add a contact

  <img src="images/undo-UG/after_add.png" alt="image" width="700" height="auto">

* Undo
* You should see this message:

  ```
  Undo Successful! Deleted Person: X
  ```
  where X are the details of the person who was just added, and now deleted
* GUI reflects that the most recently added contact is deleted

  <img src="images/undo-UG/after_add_undo.png" alt="image" width="700" height="auto">

For undoing a clone command:

* Clone a contact

  <img src="images/undo-UG/after_clone_1.png" alt="image" width="700" height="auto">

* Undo
* You should see this message: 

  ```
  Undo Successful! Deleted Person: X
  ```
  where X are the details of the person who was just cloned, and now deleted
* GUI reflects that the most recently cloned contact is deleted

  <img src="images/undo-UG/after_clone_undo.png" alt="image" width="700" height="auto">

For undoing a delete command:

* Delete a contact

  <img src="images/undo-UG/after_delete_1.png" alt="image" width="700" height="auto">

* Undo
* You should see this message: 

  ```
  Undo Successful! Contact(s) added back: X
  ```
  where X are the details of the person who was just deleted, and now added back
* GUI reflects that the most recently deleted contact is added back

  <img src="images/undo-UG/after_delete_undo.png" alt="image" width="700" height="auto">

* Delete multiple contacts (In this case, 2 contacts)

  <img src="images/undo-UG/after_delete_multiple.png" alt="image" width="700" height="auto">

* Undo
* You should see this message: 

  ```
  Undo Successful! Contact(s) added back: X
  ```
  where X are the details of the persons who were just deleted, and now added back
* GUI reflects that the most recently deleted contacts are added back

  <img src="images/undo-UG/after_delete_multiple_undo.png" alt="image" width="700" height="auto">

For undoing a clear command:

* Clear all contacts

  <img src="images/undo-UG/after_clear.png" alt="image" width="700" height="auto">

* Undo
* You should see this message: 

  ```
  Undo Successful! All contacts have been added back!
  ```

* GUI reflects that all cleared contacts are added back

  <img src="images/undo-UG/after_clear_undo.png" alt="image" width="700" height="auto">

For undoing an edit command:

* Edit a contact

  <img src="images/undo-UG/after_edit.png" alt="image" width="700" height="auto">

* Undo
* You should see this message: 

  ```
  Undo Successful! Reverted back to: X
  ```

  where X are the details of the person before the edit
* GUI reflects that the edited contact has been reverted

  <img src="images/undo-UG/after_edit_undo.png" alt="image" width="700" height="auto">

For undoing a redo command:

* Redo
* Undo
* You should see this message:

```
Undo Successful!
```

* Note that the message shown is generic and is the same regardless of what command was undone.

  <img src="images/undo-UG/undo_redo_successful.png" alt="image" width="700" height="auto">

#### Precise expected outputs on failure:

When there is no command to undo, i.e. no previous `add`, `clone`, `delete`, `clear`, or `edit` command

* You should see this error message:

  ```
  There is no command to undo!
  ```

  <img src="images/undo-UG/undo_fail.png" alt="image" width="700" height="auto">

<div style="page-break-after: always;"></div>

### Redoing an undo command : `redo`

Redoes the most recent undo command. 


#### Format:
* `redo`


#### Example commands:

* `delete 1` will delete the first contact in the address book

  `undo` will then reverse the `delete 1` command

  `redo` will then reapply the `delete 1` command


* `add n/John Doe p/98765432 e/johnd@example.com o/Barber a/John Street, Block 123, #01-01` will add this contact in
  the address book

  <img src="images/redo-UG/redo-example/EmptyBeforeAddingJohn.png" alt="image" width="700" height="auto">

  <img src="images/redo-UG/redo-example/AfterAddingJohn.png" alt="image" width="700" height="auto">

  `undo` will then reverse the `add` command (i.e. John Doe is no longer in the address book)

  <img src="images/redo-UG/redo-example/AfterUndoingAddJohn.png" alt="image" width="700" height="auto">

  `redo` will then reapply the `add` command (i.e. John Doe is back in the address book)

  <img src="images/redo-UG/redo-example/AfterRedoingUndoJohnIsBack.png" alt="image" width="700" height="auto">


  `undo` will then reverse the `redo` command (i.e. John Doe is no longer in the address book)

  <img src="images/redo-UG/redo-example/AfterUndoingRedoJohnIsGone.png" alt="image" width="700" height="auto">

  #### Note: while it is possible to `undo` `redo` commands, this is only possible before any other `add`, `clone`, `edit`, `delete`, `clear` command is executed.

  Now, `redo` will reapply the `add` command (i.e John Doe is back in the address book)

  <img src="images/redo-UG/redo-example/AfterRedoingUndoJohnIsBack.png" alt="image" width="700" height="auto">

  `delete 1` will delete the first contact in the address book

  <img src="images/redo-UG/redo-example/AfterDeleteJohn.png" alt="image" width="700" height="auto">

  `undo` will reverse the `delete 1` command

  <img src="images/redo-UG/redo-example/AfterUndoingDeleteJohn.png" alt="image" width="700" height="auto">

  `undo` once again will not reverse the `redo` command

  <img src="images/redo-UG/redo-example/NothingToUndo.png" alt="image" width="700" height="auto">

#### Precise expected outputs on success:

For example, `add` a contact, then `undo`, then `redo`

* Add a contact

  <img src="images/redo-UG/add_person.png" alt="image" width="700" height="auto">

* Undo 

  <img src="images/redo-UG/undo_success.png" alt="image" width="700" height="auto">

* Redo
* You should see this message: 

  ```
  Redo successful!
  ```


  <img src="images/redo-UG/redo_success.png" alt="image" width="700" height="auto">
  
  * Note that the message shown is generic and is the same regardless of what command was redone.


* If wanted, undo again
* You should see this message: 

  ```
  Undo successful!
  ```

  <img src="images/redo-UG/undo_after_redo.png" alt="image" width="700" height="auto">
  
  * Note that the message shown is generic and is the same regardless of what command was redone.

#### Precise expected outputs on failure:

When there is no command to redo, i.e. no previous `undo` command

* You should see this error message:

  ```
  Redo unsuccessful! There is nothing to redo!
  ```
  <img src="images/redo-UG/redo_failure.png" alt="image" width="700" height="auto">

<div style="page-break-after: always;"></div>

### Sorting contacts : `sort`

Sort contact lists by prefix name or appointment date.

#### Format: 
* `sort PREFIX`

#### Example commands:
* `sort n/`
* `sort appt/`

#### Acceptable values for prefix:
* `n/` sort by Name prefix
* `appt/` sort by Appointment Date prefix

  <img src="images/sort-UG/sortformat.png" alt="image" width="700" height="auto">

#### Precise expected output on success:
* You should see this message:
  ```
  X persons listed!
  ```
  where X is the number of clients in the address book.
* The sorted entry is displayed in the address book GUI.

  <img src="images/sort-UG/sortresult.png" alt="image" width="700" height="auto">

#### Precise expected outputs on failure:
If either a required parameter is missing or a parameter provided is invalid (e.g., e/), an error message should indicate the invalid format.

* You should see this error message:

  ```
  Invalid command format!
  sort: Sort all persons by name or appointmnet date.
  Parameters: KEYWORD [PREFIX n/ or appt/]
  Example: sort n/
  ```

  <img src="images/sort-UG/sortfailuremissing.png" alt="image" width="700" height="auto">

<div style="page-break-after: always;"></div>

### Opening calendar window : `calendar`

Open a new calendar window.

#### Format:
* `calendar`

#### Example commands:
* `calendar`

<div markdown="span" class="alert alert-primary">:bulb: 
**Tip:** <br>
* You can open calendar window by pressing F3 key <br>
* Arrow key navigate you to different months <br>
* Enter key navigate you back to the current month
</div>

#### Precise expected outputs on success:
* You will see a pop-up of a new calendar window of the current month that indicate client's appointments in the date

  <img src="images/calendar-UG/calendarWindow.png" alt="image" width="700" height="auto">

<div style="page-break-after: always;"></div>

### Clearing all entries : `clear`

Clears all of your contacts in the address book.

#### Format:
* `clear`

#### Example commands:
* `clear`

#### Precise expected outputs on success:

* This is the initial address book as reflected in the GUI:

  <img src="images/clear-UG/clear_before.png" alt="image" width="700" height="auto">

* After executing the clear command:

* You should see this message:
  ```
  Address book has been cleared!
  ```
* Your GUI will reflect that there are 0 contacts left

  <img src="images/clear-UG/clear_success.png" alt="image" width="700" height="auto">

#### Precise expected outputs on failure:

* You should see this error message:
  ```
  Address book is empty. There is nothing to clear.
  ```

  <img src="images/clear-UG/clear_failure.png" alt="image" width="700" height="auto">

<div style="page-break-after: always;"></div>

### Exiting the program : `exit`

Your FAPro application will be closed.

#### Format: 
* `exit`

#### Precise expected outcome on success:
* You will be exited from the application

### Saving the data

FAPro data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

FAPro data is saved automatically as a JSON file `[JAR file location]/data/fapro.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, FAPro will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</div>

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Troubleshooting
Troubleshooting is separated into three categories: [Technical Questions](#technical-questions), [Common Questions](#common-questions) and [Known Issues](#known-issues).

### Technical Questions

_Technical Questions_ are questions regarding the use of the program itself.

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous FAPro home folder.

**Q**: How do I view the basic commands and refer to the others in the FAPro’s User Guide?<br>
**A**: Click the Help tab or type the `help` in the command box.

--------------------------------------------------------------------------------------------------------------------

### Common Questions

_Common Questions_ are questions that you might have about the reasons why we chose our methods of implementation for certain functions.

**Q**: Why can't I add clients with the same name? <br>
**A**:
As financial advisors, while you might deal with clients with the same name, as the name is the primary identifier for a person in the address book, the app was designed so as to prevent duplicate names. <br>

To circumvent this, you can add additional details to the name to differentiate contacts. For example, if you have two contacts names John Tan, one could be John Tan (Google) and John Tan (Facebook) (if they work at the respective companies).

**Q**: Using that logic, why not use another field as the unique identifying field (such as email or phone number)? <br>
**A**: Other fields might be shared among individuals. For example, those from the same household might have the same landline. And some married couples share the same email. As such, using the name of an individual was the ideal choice in terms of an identifier for a contact.

**Q**: Is adding the same appointment date and time for different clients allowed? <br>
**A**: Yes! Our thinking is that if an FA wants to host a group session with several clients, FAPro allows them to schedule multiple clients for the same appointment date and time, making it convenient for FA to manage group meetings.
--------------------------------------------------------------------------------------------------------------------

### Known Issues

_Known issues_ are some problems that are currently present in the program that we're aware of but are unable to fix right (for now). We intend to solve them in our next iteration.

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **In calendar window**:
   1. The default size only shows a maximum of 2 clients per day
   2. The default size does not show time of the appointment
   * The above 2 issues can be fixed by adjusting the calendar window to be larger to show more information
   * While the calendar window is open, any changes made to clients' appointment dates and times won't be dynamically updated. You will need to close and reopen the calendar window to see the latest changes
3. When searching by address, i.e. `find a/ KEYWORD [MORE_KEYWORDS]`, searching for "XXX Street" for instance, would return all clients that contain "XXX" and "Street" in their address. This may lead to a pollution of the results being returned from the `find` command. This is a known limitation that is a by-product of the feature of being able to search for multiple addresses at once
    * One simple way to circumvent this issue is to use more specific keywords as your input, instead of using broad keywords that are very common in addresses, such as (but not limited to) "Block" or "Street" or "Road"
4. It is possible to add phone numbers that have indefinite length for clients, so please be aware that there is no built-in checks for "valid" phone numbers when editing phone numbers
5. Due to the versatility of the nature of addresses, it is unrealistic to check for whether an address input is "valid" or not. So please be careful when editing addresses for your clients
6. When using the `find` command, 0 and 1 contact will still show "X persons listed!", where X can be 0 or 1. This issue is purely cosmetic and won't affect any functionality
7. After using FAPro for a while, you might notice the application slowing down. To fix the issue, simply close and open the app again

--------------------------------------------------------------------------------------------------------------------

## Command Summary
                                                                                                                                         
| Action              | Format, Examples                                                                                                                                                                                                                    |
|---------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**             | `add n/NAME p/PHONE_NUMBER e/EMAIL o/OCCUPATION a/ADDRESS [t/TAG]… [appt/APPOINTMENT_DATE]` <br> e.g. `add n/James Ho p/22224444 e/jamesho@example.com o/SWE a/123 Clementi Rd, 1234665 t/friend t/colleague appt/12-12-2023 08:30` |
| **Calendar**        | `calendar`                                                                                                                                                                                                                          |
| **Clone**           | `clone INDEX`<br> e.g. `clone 3`                                                                                                                                                                                                    |
| **Clear**           | `clear`                                                                                                                                                                                                                             |
| **Delete**          | `delete INDEXES`<br> e.g., `delete 1 2 3`                                                                                                                                                                                           |
| **Edit**            | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [o/OCCUPATION] [a/ADDRESS] [t/TAG]… [appt/APPOINTMENT_DATE]`<br> e.g.`edit 2 n/James Lee e/jameslee@example.com`                                                                    |
| **Undo**            | `undo`                                                                                                                                                                                                                              |
| **Redo**            | `redo`                                                                                                                                                                                                                              |
| **Find**            | `find PREFIX KEYWORD` <br> e.g. `find n/ James Jake`, `find a/Tokyo Geylang`, `find appt/2040-01-01`                                                                                                                                |
| **List**            | `list`                                                                                                                                                                                                                              |
| **Help**            | `help`                                                                                                                                                                                                                              |
| **Questionnaire**   | `questionnaire`                                                                                                                                                                                                                     |
| **Sort**            | `sort PREFIX` <br> e.g. `sort appt/` `sort n/`                                                                                                                                                                                      |
| **Risk Profile**    | `riskprofile 3 res/a,b,c,d,e,e,b,c`                                                                                                                                                                                                 |
| **Exit**            | `exit`                                                                                                                                                                                                                              |

## Glossary

| Word                 | Meaning                                                                                                                                                                                                                                                                                     |
|----------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **FA**               | Short form for financial advisor                                                                                                                                                                                                                                                            |
| **Parameter**        | Values input by you.<br/>e.g. NAME, OCCUPATION, ADDRESS                                                                                                                                                                                                                                     |
| **Positive Integer** | An integer that is positive (i.e. greater than 0). Please note that we are excluding 0 as a positive integer.                                                                                                                                                                               |
| **Prefix**           | Word that is added in front of parameter.<br/>e.g. n/, o/, a/                                                                                                                                                                                                                               |
| **Suffix**           | Number that is at the end of a persons name <br/>e.g. for John Doe 1, the suffix would be 1. For John Doe, no suffix is present. <br/> Please note that for contacts where the whole name is an integer (i.e 123 instead on John), there is no suffix as 123 will be treated as their name. |

<div style="page-break-after: always;"></div>

### Contributing Members
1. Bhanuka Bandara Ekanayake 
2. Daphne Shaine Wilhelmina
3. Glenn Ng Jun Jie
4. Lim Zhen Wy
5. Muhammad Syam Farhan bin Agus Rizal
