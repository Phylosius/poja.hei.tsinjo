# Tsinjo - Emergency Fund

A crowdfunding application for HEI's emergency fund.

## Configuration

The application is configured using environment variables.

### Database

This project uses a PostgreSQL database. We recommend using [Neon](https://neon.tech) for a serverless Postgres database.

1.  Create a new project in Neon.
2.  In the project dashboard, find the **Connection Details** section.
3.  Use the connection string that looks like this: `postgres://user:password@host/dbname`.

### Environment Variables

Create a `.env` file in the root of the project or set the following environment variables in your deployment environment:

```bash
# Database configuration (replace with your Neon connection string)
SPRING_DATASOURCE_URL=jdbc:postgresql://your-neon-host/your-neon-db
SPRING_DATASOURCE_USERNAME=your-neon-user
SPRING_DATASOURCE_PASSWORD=your-neon-password

# Vola API configuration
VOLA_API_KEY=your-vola-api-key

# Spring profile (prod or preprod)
SPRING_PROFILES_ACTIVE=prod
```

### Running the application

Once the environment variables are set, you can run the application with:

```bash
./gradlew bootRun
```

The application will be available at `http://localhost:8080`.
