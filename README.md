# ProcessMonitorMessenger

A Java-based application designed to launch and monitor external processes, and provide messaging capabilities. The system connects to a Sybase database and integrates with TIBCO Rendezvous for monitoring and messaging.

## Features

- Process launching and monitoring
- Database connectivity with secure credential management via vault
- Messaging capabilities
- Rendezvous Daemon monitoring
- User management and authentication

## Architecture

The project architecture is documented in the [architectural diagram](docs/diagrams/architecture.md).

## Flow

The application flows are documented in the [flow diagram](docs/diagrams/flow.md).

## Building the Project

```bash
mvn clean install
```

## Running Tests

```bash
mvn test
```

## Security

Database credentials and other sensitive information are stored in a secure vault. In the development environment, this is implemented as an in-memory store, but in production, it should be connected to a proper secrets management solution like:

- Environment variables
- HashiCorp Vault
- AWS Secrets Manager
- Azure Key Vault

## Development

### Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- Sybase JDBC driver (for database connectivity)

### Project Structure

- `src/main/java/` - Main source code
- `src/test/java/` - Unit tests
- `docs/diagrams/` - Architectural and flow diagrams

## Testing

The project includes comprehensive unit tests for all components:

- VaultManager tests
- SybaseConnector tests
- UserService tests
- ProcessLauncher tests
- MessagingService tests
- RvdMonitor tests
- MainApp tests
