## Scanner and Parser
Notes for testing: 
* All of the files used for testing are stored in String constants at the top of the AdhocScanner file 
* **TEST_FILE_1** is testing for 
    * whitespace deletion 
    * detection of digits (one decimal point allowed) 
    * assignment 
    * operator detection [ (, ), +, -, * ]

Correct output should look like: \
```[assign, +, -, *, -, ), number, +, number, number, +, number, number, number, number, number, (, number, ), +, (, number, ), +, number, *, number]```

* **TEST_FILE_2** is testing for
    * alphanumeric characters (identifiers)
    * read and write tokens 
* **TEST_FILE_3** is testing whether the program will halt upon receiving incorrect input (i.e., the program is supposed to throw an error). 
