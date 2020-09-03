# Mutant Detector Functions

This is a set of **AWS Lambda Functions** to detect whether a being is human or mutant.
The functions receive a two-dimensional array of nucleotide acids, based on certain rules it perform the DNA evaluation.

## Rules

A human is a mutant, if there are more than one sequence of four identical letters is found, obliquely, horizontally or vertically.

## Example

This is a Mutant being:

|||||||
|---|---|---|---|---|---|
| **A** | T | G | C | **G** | A |
| C | **A** | G | T | **G** | C |
| T | T | **A** | T | **G** | T |
| A | G | A | **A** | **G** | G |
| **C** | **C** | **C** | **C** | T | A |
| T | C | A | C | T | G |
|||||||


- You can see 4 identical "A" letters obliquely.
- You can see 4 identical "C" letters horizontally.
- You can see 4 identical "G" letters vertically

## Function Validations

The function do the following validations:
- The only valid letters are **"A", "C","G","T"**.
- The number of columns and rows must be the same

## Resources Used

In the construction of the APP, different tools were used such as:
- **Java** Corretto 11 distribution of the Open Java Development Kit as programming language.
- Redis **ElastiCache** like the data persistence tool.
- **AWS Lambda**, with VPC, roles and Security groups configurations.
- **Terraform**, tool for building, changing, and versioning infrastructure safely and efficiently in AWS.
- **Maven** for the management and construction of the Java project.

#  Run locally

The Functions were built with the **"AWS Serverless Application Model"** CLI for windows. [I# AWS Serverless Application Model](https://aws.amazon.com/es/serverless/sam/)
To run the whole app is necessary to run **docker** to simulate the amazon cloud, and perform the following instructions in a terminal (in the project):

```bash
    sam build --template PROJECTFOLDER/MutantFunctions/template.yaml --build-dir PROJECTFOLDER\MutantFunctions\.aws-sam\build --use-container
   ```

# Use The Api

Use the Api is cooler than using it locally.
You can use it in this link:
[Mutant Functions API](https://8o0hr355e1.execute-api.us-east-1.amazonaws.com/Stage/)

# /mutant/ EndPoint
To use this endpoint you have to do a **POST** Request to ***https://8o0hr355e1.execute-api.us-east-1.amazonaws.com/Stage/mutant*** with the following payload:

 ```json
    {"dna":[
    "AGCCTA",
    "TGCCCT",
    "CGACGG",
    "GCCTAC",
    "TGCCCT",
    "GCCTAC"]}
   ```

It returns a **HTTP STATUS CODE**:
- 200 if the DNA is mutant
- 403 if the DNA is human
- 400 if there is an user error (validation error)
- 500 if there is a server error.

## Payload Examples
- Human:
 ```json
    {"dna":["AGCCTA","TGCCCT","CGACGG","GCCTAC","TGCCCT","GCCTAC"]}
   ```
   - Mutant (horizontally) :
 ```json
    {"dna":  ["AGCC","TGCC","CGAC","CCCC"]}
   ```
   - Mutant Obliquely:
 ```json
    {"dna":["AGGCGA","ATGGTA","TTAGGT","ATCGGA","ACCGTA","TGATCT"]}
   ```
   - Mutant Vertically:
 ```json
    {"dna":["AGGCGA","ATGGTA","ATACGT","ATCGGA","ACCGTA","TGATCT"]}
   ```
   
# /stats/ EndPoint
To use this endpoint you have to do a **GET** Request to https://8o0hr355e1.execute-api.us-east-1.amazonaws.com/Stage/stats.
It returns a  **JSON** like this:
 ```json
    {
	    "count_mutant_dna": 7,
	    "count_human_dna": 8,
	    "ratio": 0.875
    }
   ```


