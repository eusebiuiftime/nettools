# NetTools

NetTools is a small personal project built with Java and Spring Boot to experiment with network management, monitoring, and automation.

The project focuses on collecting network statistics from a **ZTE MC889 5G router running WINDTRE firmware**, storing the data in PostgreSQL, and visualizing it through Metabase.

It also serves as a way to explore backend development, database management, and self-hosted applications.

## Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA / Hibernate
- PostgreSQL
- Liquibase
- Docker & Docker Compose
- Metabase

## Planned Features

- **Traffic Monitoring** — Collect download and upload statistics from the ZTE MC889.
- **Scheduled Data Collection** — Periodically retrieve and store network usage data.
- **Wake-on-LAN** — Remotely power on devices within the local network.
- **Data Visualization** — Analyze historical traffic statistics using Metabase.

## Getting Started

The project is currently under development.

### Prerequisites

- Java 21
- Docker & Docker Compose
- Maven (or the included Maven Wrapper)

### Configuration

Create a `.env` file based on `.env.example` and configure the database credentials.

Start PostgreSQL:

```bash
docker compose up -d
```

Make sure the required environment variables are available to Spring Boot before starting the application.

## Project Status

This is a personal learning project and is still in its early stages. Features and implementation details may change over time.
