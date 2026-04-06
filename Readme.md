Apologies on the test logs sadly some files were corrupted which were the test logs though some were recovered through the github so it  might be rushed due to having to rewrite over 15 test cases and retesting them and getting files back as well.


Special instruction:
The PTest.csv file is for popularity voting and Test.csv is for STV voting.

Now for testing 10000 ballots use PTest10000 for popularity and go to main line 26 and type in the file as it parses candidates and line 42 as it parses ballots.

Now for testing 10000 ballots use Test10000 for STV and go to main line 26 and type in the file as it parses candidates and line 49 as it parses ballots.

Now the ballots in the files should say in the same format for this to work.

You can take the test data in the test data logs and put it in either of the two files (PTest.csv or Test.csv) to test

When you run main there will be input on what you can do when it comes to picking seats, stv or pl, and shuffle.
For shuffle type yes or no

Again we only used 2 csv files to test because of our code in main
