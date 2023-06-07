# 3560OOP-Assignment1
Programming Assignment 1 - iVote Simulator

# Question ADT
- Question Interface
    --This project implements a Question Interface, which includes definitions for
    methods to maintain and control Answers lists.
- Question
    --The concrete Question class implements the Question Interface and implements 
    a simple 'single-answer' approach to Questions
- Multiple Choice Question
    --The concrete MC Question class extends the Question class and adds the ability
    to have multiple answers in a Question

- All these classes depend on the Answer class and HashSets

# Student
- A Student has a Unique Student ID
- This hashcode of the Student ID is the hashcode of the Student object
- There is a static field to keep track of all the used IDs to maintain uniqueness
- There is also a definition for choosing an answer (index or object) based on a Question

# Voting Simulator
- The main bulk of the project
- Keeps track of statistics and delegates choosing answers for each question to the Students

# SimulationDriver
- Has a main method
- Initializes the necessary fields and passes them into the Voting Simulator
- Calls methods from the VotingSimulator to start the voting process and the statistics printing
