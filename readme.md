# hello-parallel-step-function

Working towards an example parallel step function

## Example domain
Three lambdas together make a system for counting the frequency of letters
in text read from URLs.

### InstructionHandler
Receives an `Instruction` object, which is a list of URLs.
See `trollope-instructions.json` for an example. 
It turns each URL into a `TextAnalysisTask` and returns them as an array.

### TextAnalysisHandler
Receives a `TextAnalysisTask` object. 
Visits the specified URL and extracts the frequencies of each letter. 
Returns a `TextAnalysisResult` object which contains those frequencies.

### ResultSummaryHandler
Receives an array of `TextAnalysisResult` objects. 
Concatenates and outputs the combined the frequencies.
 
## Step Function / State Machine
Defined in `parallel-step-function.json`. 
Links the three lambdas so that the `TextAnalysisHandler` is called in parallel,
one invocation for each `TextAnalysisTask` produced by the `InstructionHandler`. 
Then combines the resulting `TextAnalysisResult` objects into an array 
and sends them to the `ResultSummaryHandler`.

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
