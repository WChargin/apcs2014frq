apcs2014frq
===========

A framework for answering and evaluating the 2014 AP Computer Science Free Response Questions.

This is set up as an Eclipse project with three source directories:

 * `exam`&mdash;write responses to FRQs here
 * `support`&mdash;classes provided by the exam, like `Student` and `Salad`
 * `test`&mdash;JUnit tests used to assess performance on FRQs

To use this repository:
 
 * Download the contents to your computer. Extract the files, if necessary.
 * In Eclipse, press *File* &rarr; *Import...* &rarr; *General* &rarr; *Existing Projects into Workspace*. Locate the project and import it.
 * Write your code in the four provided classes in the `exam` source directory (`Scrambler`, `Director`, `SeatingChart`, and `Trio`).
 * Open the file `AllFRQTests.java` in the `test` source directory and run it. If prompted, select "Run as JUnit Test."
 * Observe the results in the left pane (labeled "JUnit").
