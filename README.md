# Ski Resort Management Application

This application is designed to manage ski lifts and ski runs in a ski resort. The classes are located in the `it.polito.ski` package, with the main class being `SkiArea`. The `TestExample` class in the example package provides usage examples for the main methods and requested checks. You are required to implement the requested checks only. Any exceptions in the methods described below are of `InvalidLiftException` type.


## R1 - Lift Types

- To interact with the system, instantiate the `SkiArea` class by providing the constructor with the name of the resort. You can retrieve the name using the `getName()` getter.
- Define a new lift type using the `liftType()` method, which accepts a code, category (e.g., platter-lift, chairlift, gondola lift), and the number of seats for each single unit. If a duplicate code is provided, the method throws an exception.
- Use the `getCategory()` and `getCapacity()` methods, which accept a type and return the category and the number of seats, respectively. An exception is thrown if the provided type is undefined.
- Get a list of all defined lift types with the `types()` method, which returns a collection of defined type names.

## R2 - Lifts

- Create a new lift using the `createLift()` method, which accepts the name and lift type. An exception is thrown if the provided type is undefined.
- Retrieve the type of a given lift with the `getType()` method.
- Get the collection of lifts sorted by name using the `getLifts()` method.

## R3 - Ski Slopes

- Describe a new ski run using the `createSlope()` method, providing the name of the run, difficulty ("green," "blue," "red," "black"), and the name of the lift from which the slope starts. An exception is thrown if the lift name is unknown.
- Retrieve the difficulty and the name of the starting lift for a slope using the `getDifficulty()` and `getStartLift()` methods, respectively.
- Use the `getSlopes()` method to get a collection of all ski runs.
- Additionally, the `getSlopesFrom()` method accepts the name of a lift and provides a list of all ski slopes starting from that lift.

## R4 - Parking

- Describe a car parking using the `createParking()` method, providing the name of the parking and the number of slots.
- Get the number of slots available in a parking with the `getParkingSlots()` method.
- Indicate that a lift departs from a parking using the `liftServedByParking()` method, which accepts the name of the lift and the name of the parking. You can invoke this method multiple times to add all lifts departing from the same parking.
- Retrieve the collection of all lifts served by a parking using the `servedLifts()` method.
- Check whether the parking size is proportional to the capacity of the lifts departing from it with the `isParkingProportionate()` method. It returns true if the parking size divided by the sum of the seat numbers of the lifts departing from the parking is less than 30.

## R5 - Input from File

- Read the description of lift types and lifts from a text file using the `readLifts()` method.
- The file should be organized by lines, with each line starting with a letter indicating the type of information: "T" for lift type and "L" for lift.
- A lift type is described by code, category, and seat number, while a lift is described by the name and the type code.
- Different data on a line should be separated by ";" (semicolons), and any spaces surrounding the separator are ignored.

Example file format:

T ; S4P; seggiovia; 4
T;S;skilift;1
L;Fraiteve;S4P
L;Baby;S


