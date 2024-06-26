# tech-test-carreseller
Answer of a car reseller technical test with DDD and Hexagonal Architecture

### Test description

This is the answer to a technical test in a job application.

See the description at the end of this README.md

### Choosen Architecture

Domain Driven Architecture was the software development technique choosen due to it's capability to isolate business rules from software infrastructure.

Hexagonal Architecture war the software architecture choosen due to it's alignment do DDD.

### DDD specification

The DDD project will be inside an Hexagonal Architecture and developed as a Maven project so it can be used in any type of java client:
1. Java apps
2. Kafka consumers
3. Springboot
4. Quarkus
5. etc...

### Hexagonal Architecture specification

1. Hibernate will be used as sql adapter
2. DAO pattern will be used to build the Outbound Adapter
3. Adapter pattern will be used to build the Inbound Adapter

### DDD steps

1. Choose Ubiquotous Language
2. Identify the Bounded Context
3. Identify Entities
4. Identify Use Cases

### Hexagonal Architecture Steps

1. Map Entities to Classes
2. Map Use Cases to Classes
3. Identify Inbound Adapters
4. Identify Outbound Adapters

### Next steps
