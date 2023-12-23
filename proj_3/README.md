# Gauss Elimination with Foata Norm

## Description

This project implements parallel Gauss Elimination using Foata norm. In this project, we apply the Foata norm to parallelize the Gauss elimination algorithm, which is used to solve systems of linear equations.

## Required dependencies:

    1. Python
    - graphviz
    - sys
    2. Java
    - basic Java sdk

## Directory Structure

- nb.py - the helping program to generate the foata norm and the graph of dependencies
- GaussionElimination.java - the main program to run the Gauss Elimination using Foata Norm from nb.py
- Calculators:
    - ACalculator caluclates factor for given row
    - BCalculator multiplies factor with given cell
    - CCalculator subtracts two cells 
    All the calculators are run in separate threads according to what the foata norm generates

- ./inputs - the directory that contains the input files
- ./outputs - the directory that contains the output files like graphs and the result matrixes

## Usage

Run the program:

    make - automatically run the program with the default inputs

    make with_file "file_name" - run the program with the one input file



