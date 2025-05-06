# ProcessMonitorMessenger - Architectural Diagram

## Component Overview

```mermaid
graph TD
    MainApp[MainApp] --> ProcessLauncher[ProcessLauncher]
    MainApp --> SybaseConnector[SybaseConnector]
    MainApp --> MessagingService[MessagingService]
    MainApp --> RvdMonitor[RvdMonitor]
    MainApp --> UserService[UserService]
    
    SybaseConnector --> VaultManager[VaultManager]
    
    subgraph Core Components
        ProcessLauncher
        SybaseConnector
        MessagingService
        RvdMonitor
        UserService
    end
    
    subgraph Security
        VaultManager
    end
    
    subgraph External Systems
        DB[(Sybase Database)]
        RVD[Rendezvous Daemon]
        ExtProc[External Processes]
    end
    
    SybaseConnector --> DB
    RvdMonitor --> RVD
    ProcessLauncher --> ExtProc
```

## Component Description

1. **MainApp**: The entry point of the application that initializes and orchestrates all services.

2. **ProcessLauncher**: Handles launching and monitoring external processes.

3. **SybaseConnector**: Manages connections to Sybase database using securely stored credentials from the vault.

4. **MessagingService**: Provides messaging capabilities for inter-service communication.

5. **RvdMonitor**: Monitors the Rendezvous Daemon (RVD) for system health checks.

6. **UserService**: Manages user authentication, registration, and user-related operations.

7. **VaultManager**: Secure vault implementation for managing sensitive credentials and secrets.

## Security Architecture

```mermaid
graph TD
    App[Application] --> VaultManager[VaultManager]
    VaultManager --> InMemory[In-Memory Storage]
    
    subgraph "Production Options"
        VaultManager --> EnvVars[Environment Variables]
        VaultManager --> SecretsMgr[AWS Secrets Manager]
        VaultManager --> HashiVault[HashiCorp Vault]
        VaultManager --> KeyVault[Azure Key Vault]
    end
    
    subgraph "Security Layers"
        L1[Authentication]
        L2[Authorization]
        L3[Encryption]
        L4[Audit Logging]
    end
```

## Technology Stack

- Java 11
- Maven for dependency management
- Sybase database for data storage
- TIBCO Rendezvous for messaging
- JUnit and Mockito for testing
