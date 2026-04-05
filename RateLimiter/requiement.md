Question: Design a Pluggable Rate Limiting System for External Resource Usage
You are building a backend system that serves client API requests.
Flow:
A client calls one of your APIs.
The API forwards the request to an internal service.
The internal service may or may not call an external resource depending on business logic.
The external resource is paid, and you are charged based on usage.
Your task is to design and implement a rate limiting system for the external resource call, not for the incoming client API itself.
That means:
Not every API request consumes quota.
Rate limiting should be applied only at the point where the system is about to call the external resource.
Requirements
Design a rate limiting module with the following requirements:
Functional Requirements
The system should decide whether a particular external call is allowed or denied.
The rate limiter should support multiple rate limiting algorithms.
At minimum, implement:
Fixed Window Counter
Sliding Window Counter
The design should allow plugging in any future algorithm easily, such as:
Token Bucket
Leaky Bucket
Sliding Log
The system should support configuring limits such as:
100 requests per minute
1000 requests per hour
The rate limiting key can vary based on use case, for example:
per customer
per tenant
per API key
per external provider
The module should expose a simple interface that internal services can use before making the external call.
Non-Functional Requirements
The design should be extensible.
It should follow good OOP and SOLID principles.
It should be thread-safe.
The implementation should be efficient in terms of time and space.
The code should be easy to test.
Deliverables
Design the classes and interfaces required.
Write code for the system.
Implement:
Fixed Window Counter
Sliding Window Counter
Show how a caller can switch between algorithms without changing business logic.
Explain key design decisions.
Discuss trade-offs between the two algorithms.
Example Use Case
Suppose user T1 is allowed to make at most 5 external calls per minute.
For each client request:
business logic runs first
if no external call is needed, no rate limiting check is performed
if an external call is needed, the rate limiter is consulted
if allowed, the external API is called
otherwise, the request is rejected or handled gracefully

