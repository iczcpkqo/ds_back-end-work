## Getting Started

### Introduction
- Send POST requests to register.
	"name": UUID4
	"email": UUID4@wada.com
	"password": 123456.
	"country": random China or India.
  "isAthlete": random true or false.
- Send GET requests to locations.
### Prerequisites

- Install [*Python 3.10*](https://www.python.org).

- Install dependencies.

  ```bash
  $ pip install locust
  ```
  
- Run
  ```bash
  $ locust -f testing.py
  ```
  
- Type **localhost:8089** into your browser

- Host : http://localhost:8080 or Server base address

