# Voting System
## Overview

A scalable voting system that supports multiple election methods, including Plurality Voting, Single Transferable Voting (STV) using the Droop Quota, and Municipal Voting (MV).

The system processes ballot data from CSV files, computes election results efficiently, and generates detailed audit reports to ensure transparency.

## Features/Voting Algorithms

Plurality Voting (PV):
* One vote per ballot
* Highest vote count wins
* Tie-breaking via fair random selection

Single Transferable Voting (STV)
* Ranked-choice voting using Droop Quota
* Multi-round redistribution of ballots
* Eliminates the lowest candidates and reallocates votes

Municipal Voting (MV)
* Voters can select multiple candidates (up to the number of seats)
* Winners determined by the highest total votes


## Core Functionality
* Supports multiple seats per election
* Processes CSV ballot files as input
* Automatically extracts:
  * Election type
  * Number of seats
  * Candidates
* Handles up to 100,000 ballots efficiently
* Displays:
  * Election summary
  * Winners and non-winners
  * Vote statistics


## Reporting & Transparency
* Generates audit reports for STV elections:
  * Tracks ballot distribution across rounds
  * Records candidate elimination and election order
* Logs invalid ballots (e.g., insufficient rankings in STV)
* Produces detailed election statistics:
  * Total votes
  * Vote percentages per candidate


## Usability
* User-friendly interface for running elections
* Supports GUI-based file selection
* Includes help functionality for users
* Designed to require minimal training


## Tech Stack
* **Languages:** Java / C++
* **Input Format:** CSV files
* **Tools:** Git, GitHub
* **Methodologies:**
  * Waterfall (requirements engineering & SRS)
  * Agile Scrum (iterative development & feature expansion)


## How It Works
### Input Format

Each election is defined using a CSV file:
- **First row**: Election type (PV, STV, MV)
- **Second row**: Number of seats
- **Third row**: [Candidate1, Party],[Candidate2, Party],...
- The rest of the file is ballots for the election


## Election Flow
1. Read election configuration from file
2. Parse ballots
3. Apply the selected voting algorithm
4. Compute results
5. Generate:
    - Console summary
    - Report files (for auditing)
