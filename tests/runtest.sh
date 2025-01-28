#!/usr/bin/env bash

# check if the current directory is called "tests"
if [ "$(basename "$PWD")" != "tests" ]; then
    cd tests
fi

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]; then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]; then
    rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/C3PO.java; then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

cd ..

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ./bin C3PO < tests/input.txt > tests/ACTUAL.TXT

# convert to UNIX format
cp tests/EXPECTED.TXT tests/EXPECTED-UNIX.TXT
dos2unix tests/ACTUAL.TXT tests/EXPECTED-UNIX.TXT

# compare the output to the expected output
diff tests/ACTUAL.TXT tests/EXPECTED-UNIX.TXT
if [ $? -eq 0 ]; then
    echo "Test result: PASSED"
    result=0
else
    echo "Test result: FAILED"
    result=1
fi

# remove the EXPECTED-UNIX.TXT file
if [ -e "./tests/EXPECTED-UNIX.TXT" ]; then
    rm tests/EXPECTED-UNIX.TXT
fi

exit $result
