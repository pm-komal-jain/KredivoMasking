# KredivoMasking
Masking script for masking credit card number . And running unit tests.


### Steps to run the program.

1. Clone the repo - 

git clone git@github.com:pm-komal-jain/KredivoMasking.git

2. Change to KredivoMasking

cd KredivoMasking

3. Build the project with or without Unit Test. Use either one of Two below.

-> To Build project and run Unit Tests.  Use - 

mvn clean install

-> To Build project and skip Unit Test execution. Use -

mvn clean install -DskipTests

## 4. Run the program
To run program. Use -

 java -classpath ./target/masking-1.0-SNAPSHOT.jar CreditCardMasking
