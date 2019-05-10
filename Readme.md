# Test 3

Connection retries

Main Code in com.vgalli.client.Client
   The client will retry for a configurable amount of time. 
   After each failure the client will re-attempt to connect after an exponentila amount of time.
   
   I also implemented a Backoff mechanism. If the server asks the client to backoff, the client will backoff, 
   reset the number of retries until the  client gets an error or the client generates an error.
   
   For the sake of testing I set the number of maximum backoff servrer response to 10.
   
   I any client errors occurs the client will stop.
   
   
   
The test class ClientTest covers the following
    testConnectWithJitter() - Tests the back off mechanism
    testConnectWithClientError() - Test the client error case
    testConnect() - Test valid connection
    testConnectWithRetry() - Test max retries
    
    Note The timestamp is being displayed to demonstrate the exponential retries. 