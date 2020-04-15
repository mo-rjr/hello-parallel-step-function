# hello-parallel-step-function

Working towards an example parallel step function

## Example domain
Three lambdas together make a system for counting the frequency of letters
in text read from URLs.

### InstructionHandler
Receives an `Instruction` object, which is a list of URLs.
See `trollope-instructions.json` and `patristics.json` examples. 
It returns an `InstructionBreakdown` which has an id and the urls as an array.

### TextAnalysisHandler
Receives a `TextAnalysisInstruction` object. 
Visits the specified URL and extracts the frequencies of each letter. 
Returns a ` Map<String, Long>` which contains those frequencies.

### ResultSummaryHandler
Receives a `SummaryInput` which has the id and an array of the `TextAnalysisHandler` results. 
Concatenates and outputs the combined the frequencies.
 
## Step Function / State Machine
That Step Function that links the three lambdas is defined in `parallel-step-function.json`.
`InstructionHandler` generates a random id for the execution and outputs it with the urls.
The Step Function sends the id and one url to multiple invocations of the `TextAnalysisHandler`. 
It then concatenates the results of the invocations 
and sends them with the execution id to the `ResultSummaryHandler`.

## Deployment
At present deployment is a manual process.
* build the code into a far jar by running `mvn clean package`
* deploy the three lambdas: each uses the same jar file, but the handlers are:  
  * `uk.co.littlestickyleaves.hello.handler.InstructionHandler::handleRequest`
  * `uk.co.littlestickyleaves.hello.handler.TextAnalysisHandler::handleRequest`
  * `uk.co.littlestickyleaves.hello.handler.ResultSummaryHandler::handleRequest`
* deploy the Step Function using `parallel-step-function.json` 
but with each arn replaced by the arn of your corresponding lambda deployment
  * it will require a role that has permission to call lambdas
* trigger the step function with suitable json, e.g. `trollope-instructions.json`

## Additional failure example
If the zip is deployed as a lambda with the handler:  
`uk.co.littlestickyleaves.hello.fails.RandomFailingHandler::handleRequest`  
then it will fail as directed.  The step function json is  
`failure-step-function.json`.  
It retries or catches failures according to their type.

It takes an instruction like  
`{ "failureType": "RANDOM" }`  
where available failure types are "RANDOM", "TIMEOUT", "CUSTOM", "RUNTIME", and "NONE".
