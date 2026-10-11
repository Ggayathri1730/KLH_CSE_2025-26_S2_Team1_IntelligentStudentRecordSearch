# Intelligent Student Record Search System

**Course:** Data Structures and Algorithms – III  
**Team:** 1  
**Project Supervisor:** Dr. S. Vinay Kumar, Associate Professor, Department of Computer Science and Engineering  
**Project Phase:** Pattern Matching Implementation – Review 2

## 1. Abstract

The Intelligent Student Record Search System is a software application developed to demonstrate the practical application of data structures and algorithms in the efficient retrieval of information from student profile documents.

The system processes a corpus of student records and performs pattern-based searches to identify records containing specified keywords or phrases. Depending on the information available in the corpus, searchable attributes may include student names, technical skills, projects, certifications, and interests.

The current implementation incorporates three string-matching algorithms: Naive Pattern Matching, Knuth–Morris–Pratt (KMP), and Rabin–Karp. The KMP algorithm employs the Longest Proper Prefix that is also a Suffix (LPS) array to reduce redundant character comparisons, whereas Rabin–Karp uses a rolling hash to identify potential matches.

The project evaluates these algorithms through matching-result consistency checks and execution-time benchmarking under a common workload. The current phase focuses on the implementation and comparative analysis of string-matching techniques within a student-record retrieval environment.

## 2. Project Objectives

1. Implement string-matching algorithms for searching student profile documents.
2. Develop a corpus-based search mechanism for retrieving relevant student records.
3. Compare the performance of Naive Pattern Matching, KMP, and Rabin–Karp using identical search queries.
4. Verify the consistency of matching results across the implemented algorithms.
5. Measure and analyze execution times under a common benchmarking procedure.
6. Evaluate the practical applicability of string-matching algorithms to document retrieval.
7. Extend the system in subsequent phases in accordance with the project requirements and course objectives.

## 3. Scope of the Current Phase

The current phase focuses on string-pattern matching, corpus-based retrieval, result validation, and performance evaluation.

The implementation includes the following components:

- Naive Pattern Matching
- Knuth–Morris–Pratt (KMP)
- Rabin–Karp
- Student-record corpus loading
- Query processing and text normalization
- Matching-record collection and consistency verification
- Execution-time benchmarking and comparative analysis

Additional algorithms and functionalities will be documented as implemented only after their integration and verification.

## 4. Algorithm Overview

### 4.1 Naive Pattern Matching

Naive Pattern Matching examines successive positions in the text and compares the pattern characters at each position. It provides a straightforward baseline against which more advanced pattern-matching algorithms can be evaluated.

### 4.2 Knuth–Morris–Pratt (KMP)

The KMP algorithm preprocesses the search pattern to construct an LPS array. This information enables the algorithm to avoid unnecessary comparisons after a mismatch, improving its worst-case time complexity to \(O(n+m)\), where \(n\) is the text length and \(m\) is the pattern length.

### 4.3 Rabin–Karp

The Rabin–Karp algorithm uses hashing to compare the search pattern with text windows. A rolling hash facilitates efficient hash updates as the search window moves through the text. Candidate matches are verified as necessary to account for hash collisions.

## 5. System Workflow

The system follows a structured retrieval and evaluation process:

1. Load student profile documents from the corpus.
2. Process and normalize the search query and relevant text.
3. Execute the query using the implemented pattern-matching algorithms.
4. Collect the matching student-record paths.
5. Compare the results returned by the algorithms.
6. Verify result consistency before performance evaluation.
7. Measure execution times and analyze the benchmark results.

## 6. Build and Execution

### Prerequisites

- A Java Development Kit compatible with the project source code.
- The Java source files in `src/algorithms`.
- The student-record corpus in `corpus/student-records`.

### Compilation

Execute the following command from the project root:

```powershell
javac -d bin src/algorithms/*.java
```

### Application Execution

```powershell
java -cp bin algorithms.Main
```

The application uses the student-record corpus for searching. Available operations and menu options are determined by the current implementation of `algorithms.Main`.

## 7. Performance Evaluation

The benchmark evaluates the implemented algorithms using a common set of search queries. Matching results are compared to establish consistency before execution-time measurements are considered.

The benchmark methodology, test environment, corpus size, query set, and measurement procedure should be documented alongside the reported results to support reproducibility.

Execution-time measurements are environment-dependent and should be interpreted as experimental observations rather than universal performance guarantees.

## 8. Conclusion

The Intelligent Student Record Search System demonstrates the application of string-matching algorithms to the retrieval of information from student profile documents. The current phase emphasizes the implementation of Naive Pattern Matching, Knuth–Morris–Pratt, and Rabin–Karp, together with result-consistency verification and execution-time benchmarking.

The project establishes a foundation for evaluating algorithmic correctness and performance in a practical search scenario. Further development and validation will be undertaken in accordance with the project requirements and the progression of the DSA-III course.
