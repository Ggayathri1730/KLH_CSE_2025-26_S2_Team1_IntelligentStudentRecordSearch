# Intelligent Student Record Search System

**Course:**   Data Structures and Algorithms – 3  
**Team:**  1
**Supervisor:**   Dr. S. Vinay Kumar, Associate Professor, Department of Computer Science and Engineering  
**Current Phase:**   Pattern/String Matching Implementation – Review 2

---

## Student Details

| S. No. | Student Name         | Roll Number  |
| -----: | ----------------     | ------------ |
|      1 | Guttikonda Gayathri  | 2520030465   |
|      2 | Sai Sowseelya Vedula | 2520030260   |
|      3 | Somana Divya Sai     | 2520039622   |


---

## Abstract

The Intelligent Student Record Search System is a data-structures-and-algorithms-based project designed to provide efficient search and retrieval of information from a structured student record corpus.

The system processes a collection of student profile documents and applies advanced string-matching algorithms to support efficient pattern and keyword searching across student information such as names, skills, projects, certifications, interests, and other relevant attributes.

For pattern matching, the system implements the Knuth-Morris-Pratt (KMP) and Rabin-Karp algorithms. KMP uses the Longest Proper Prefix which is also Suffix (LPS) array to avoid unnecessary comparisons, while Rabin-Karp uses a rolling hash to identify candidate matches efficiently.

The project is designed to demonstrate the practical application of advanced string algorithms in a real-world student information search scenario.

---

## Objectives

1. Implement efficient pattern and string-matching algorithms.
2. Search student information stored in the project's student record corpus.
3. Compare KMP and Rabin-Karp for the same search queries.
4. Verify consistency of matching results produced by different algorithms.
5. Measure and compare algorithm execution performance.
6. Extend the system with advanced search capabilities as the project progresses.
7. Demonstrate practical applications of Data Structures and Algorithms in a student-record search scenario.

---

## Algorithms and Data Structures

The project includes:

- Knuth-Morris-Pratt (KMP) pattern matching
- Rabin-Karp pattern matching
- Pattern/string matching
- Rolling hash
- Student record corpus loading
- Search result matching
- Algorithm performance comparison

Future phases may incorporate additional DSA-3 concepts based on the project requirements and course progression.

---

## Current Phase Status

### Review 2 – Pattern/String Matching

**Status:** In Progress

The current implementation focuses on:

- KMP string matching
- Rabin-Karp string matching
- Search over the project's student record corpus
- Comparison of KMP and Rabin-Karp
- Matching-result consistency verification
- Execution-time benchmarking
- Operation-count comparison

Both algorithms will be integrated into the project's search engine.

---

## Project Flow

```text
Student Record Corpus
        |
        v
   Corpus Loader
        |
        v
 Student Records
        |
        v
    User Query
        |
        +-------------------+
        |                   |
        v                   v
       KMP             Rabin-Karp
        |                   |
        +---------+---------+
                  |
                  v
       Matching Student Records
                  |
                  v
        Algorithm Comparison
                  |
                  v
       Performance Analysis
