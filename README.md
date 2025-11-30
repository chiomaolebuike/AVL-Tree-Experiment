CSC2001F 2025 Data Structures Assignment 2

Overview

This assignment evaluates the performance of an AVL Tree to determine if it truly balances nodes efficiently, irrespective of dataset size. You will develop an application to store and retrieve data using an AVL binary search tree and conduct experiments to measure its performance.

Dataset

The dataset used is the same as in Assignment 1. It consists of key-value pairs where:

Key: A term (unique, non-duplicate)

Value: A sentence and a confidence score

Two files are provided:

GenericsKB.txt - Contains the dataset to be stored in the AVL tree.

GenericsKB-queries.txt - Contains query terms, one per line.

Part 1: Program Implementation

Requirements

Develop an application called GenericsKbAVLApp.

Read GenericsKB.txt and store its contents in an AVL Tree.

Read GenericsKB-queries.txt and perform searches for each term.

Print results:

If found: Print the term and its associated data.

If not found: Print "Term not found: <term>".

Redirect output to a file using Unix output redirection.

Constraints

The AVL Tree must be used (no alternative data structures allowed).

The AVL Tree can be implemented from scratch or adapted from existing code.

Testing

Ensure correct dataset loading.

Construct a manual query file with 10 queries, containing both existing and non-existing terms, and verify correct results.

Part 2: Performance Experiment

Instrumentation

Add instrumentation to count comparison operations (<, >, ==) on keys.

Track the number of comparisons during insertion and search.

Report comparison counts at the end of the program.

Experiment Setup

Vary dataset size (n) and measure comparisons for best, average, and worst cases.

Use 10 values of n on a logarithmic scale (e.g., 5, 50, 500, 5,000, 50,000).

Generate a randomized subset of n entries from the dataset.

Use a fixed query file for all tests.

Store comparison counts for both insertion and search operations.

Analysis & Graphs

Compute minimum (best case), maximum (worst case), and average comparison counts.

Plot graphs comparing experimental results with theoretical complexity of AVL insert and search.

Python or Java is recommended for automating experiments and plotting results.

Submission

Submit your GenericsKbAVLApp source code.

Include results and graphs comparing theoretical vs. experimental performance.

Provide documentation detailing your implementation and analysis.

Notes

Ensure your AVL Tree correctly handles balancing.

Verify all results before submission.

Use proper coding conventions and comments for readability.

Author: Chioma Olebuike 
Course: CSC2001F 2025
University: University of Cape Town
Date: 28/03/2025