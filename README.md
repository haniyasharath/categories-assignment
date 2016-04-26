# About this project #

categories-assignment is a file processing project which contains logic to
- Reading the file
- Filtering the contents based on set of rules
- Processing each line by satisfying the rules
- Producing the generated output to the console

Program expects maximum two number of arguments
 - When two arguments passed then first argument considered as file name and second argument as path to the file.
 - When only one argument has been passed then that argument will be taken as file name and path will be looked at project's resource folder.
 - when no argument passed then default file name will be taken which present at project's resource folder.

#### Requirement to build and run the project ####
Jdk 1.8.x, 
Maven 3.x

#### How to run the project ####
- Clone the project 
- Go to the project's root folder and run maven command to compile, mvn compile
- Then Run below command to execute the program
  mvn exec:java -Dexec.mainClass="com.newstar.file.handler.ProcessFile" -Dexec.args="file-name.txt  path-to-file" 
  
  Note : exec.args is optional field

#### Helper libraries used ####
- apache's commons-lang3
	<p>Provides easy way to manipulate java's core classes. Ex: String manipulation methods.</p>
- googles's guava library
	<p>Used this library to make use of readily available utility classes for handling collections, objects etc.</p>
- junit for testing the application


